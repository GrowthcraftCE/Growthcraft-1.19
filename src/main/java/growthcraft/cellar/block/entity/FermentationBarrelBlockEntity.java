package growthcraft.cellar.block.entity;

import growthcraft.cellar.init.GrowthcraftCellarBlockEntities;
import growthcraft.cellar.lib.networking.GrowthcraftCellarMessages;
import growthcraft.cellar.lib.networking.packet.FermentationBarrelFluidTankPacket;
import growthcraft.cellar.recipe.FermentationBarrelRecipe;
import growthcraft.cellar.screen.FermentationBarrelMenu;
import growthcraft.lib.block.entity.GrowthcraftFluidTank;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FermentationBarrelBlockEntity extends BlockEntity implements BlockEntityTicker<FermentationBarrelBlockEntity>, MenuProvider {

    private int tickClock = 0;
    private int tickMax = -1;

    protected final ContainerData data;

    private Component customName;

    private final ItemStackHandler itemStackHandler = new ItemStackHandler(1) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
        }
    };

    private LazyOptional<IItemHandler> itemHandlerLazyOptional = LazyOptional.empty();

    private final GrowthcraftFluidTank FLUID_TANK_INPUT_0 = new GrowthcraftFluidTank(4000) {
        @Override
        protected void onContentsChanged() {
            setChanged();
            if (!level.isClientSide) {
                GrowthcraftCellarMessages.sendToClients(new FermentationBarrelFluidTankPacket(0, this.fluid, worldPosition));
            }
        }
    };

    private LazyOptional<IFluidHandler> lazyInputFluidHandler0 = LazyOptional.empty();

    public FermentationBarrelBlockEntity(BlockPos blockPos, BlockState blockState) {
        this(GrowthcraftCellarBlockEntities.FERMENTATION_BARREL_BLOCK_ENTITY.get(), blockPos, blockState);
    }

    public FermentationBarrelBlockEntity(BlockEntityType<?> blockEntityType, BlockPos blockPos, BlockState blockState) {
        super(blockEntityType, blockPos, blockState);

        this.FLUID_TANK_INPUT_0.allowAnyFluid(true);

        this.data = new ContainerData() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> FermentationBarrelBlockEntity.this.tickClock;
                    case 1 -> FermentationBarrelBlockEntity.this.tickMax;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> FermentationBarrelBlockEntity.this.tickClock = value;
                    case 1 -> FermentationBarrelBlockEntity.this.tickMax = value;
                }
            }

            @Override
            public int getCount() {
                return 2;
            }
        };
    }

    @Override
    public Component getDisplayName() {
        return this.customName != null
                ? this.customName
                : Component.translatable("container.growthcraft_cellar.fermentation_barrel");
    }

    public void tick() {
        if (this.getLevel() != null) {
            this.tick(this.getLevel(), this.getBlockPos(), this.getBlockState(), this);
        }
    }

    @Override
    public void tick(Level level, BlockPos blockPos, BlockState blockState, FermentationBarrelBlockEntity blockEntity) {

        if(!level.isClientSide && !this.getFluidTank(0).isEmpty()) {
            List<FermentationBarrelRecipe> recipes = this.getMatchingRecipes();
            FermentationBarrelRecipe recipe = recipes.isEmpty() ? null : recipes.get(0);

            if(recipe != null && this.tickClock <= this.tickMax) {
                this.tickClock++;
            } else if (recipe != null && this.tickMax > 0 && this.tickClock > this.tickMax) {
                // Determine multiplier for fluid output.
                int multiplier = recipe.getOutputMultiplier(
                        this.getFluidStackInTank(0)
                );

                this.itemStackHandler.getStackInSlot(0).shrink(multiplier);
                FluidStack resultingFluidStack = recipe.getResultingFluid().copy();
                int resultingAmount = resultingFluidStack.getAmount() * multiplier;
                resultingFluidStack.setAmount(resultingAmount);

                // Clear the current fluid and replace with the resulting FluidStack with amount multiplier.
                this.setFluidStackInTank(0, resultingFluidStack);
                this.resetTickClock();

                this.level.sendBlockUpdated(this.getBlockPos(), this.getBlockState(), this.getBlockState(), Block.UPDATE_ALL);
            } else if(recipe != null && this.tickMax == -1) {
                this.tickMax = recipe.getProcessingTime() * recipe.getOutputMultiplier(this.getFluidStackInTank(0));
            } else {
                this.resetTickClock();
            }

            this.level.sendBlockUpdated(this.getBlockPos(), this.getBlockState(), this.getBlockState(), Block.UPDATE_ALL);
        }
    }

    private List<FermentationBarrelRecipe> getMatchingRecipes() {
        List<FermentationBarrelRecipe> matchingRecipes = new ArrayList<>();

        List<FermentationBarrelRecipe> recipes = level.getRecipeManager()
                .getAllRecipesFor(FermentationBarrelRecipe.Type.INSTANCE);

        for(FermentationBarrelRecipe recipe : recipes) {
            if(recipe.matches(this.itemStackHandler.getStackInSlot(0), this.getFluidStackInTank(0))) {
                matchingRecipes.add(recipe);
            }
        }
        return matchingRecipes;
    }

    private List<FermentationBarrelRecipe> getMatchingRecipes(FluidStack fluidStack) {
        List<FermentationBarrelRecipe> matchingRecipes = new ArrayList<>();

        List<FermentationBarrelRecipe> recipes = level.getRecipeManager()
                .getAllRecipesFor(FermentationBarrelRecipe.Type.INSTANCE);

        for(FermentationBarrelRecipe recipe : recipes) {
            if(recipe.matches(fluidStack)) {
                matchingRecipes.add(recipe);
            }
        }
        return matchingRecipes;
    }

    @Nullable
    public ItemStack getResultingPotionItemStack() {
        List<FermentationBarrelRecipe> matchingRecipes = this.getMatchingRecipes(this.getFluidStackInTank(0));
        FermentationBarrelRecipe recipe = matchingRecipes.get(0);
        return recipe != null ? recipe.getBottleItemStack().copy() : null;
    }

    private void resetTickClock() {
        this.tickClock = 0;
        this.tickMax = -1;
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int containerId, @NotNull Inventory inventory, @NotNull Player player) {
        return new FermentationBarrelMenu(containerId, inventory, this, this.data);
    }

    public boolean isInputTankFull() {
        return this.getFluidTank(0).getCapacity() == this.getFluidTank(0).getFluidAmount();
    }

    public void setFluidStackInTank(int tankID, FluidStack fluidStack) {
        this.FLUID_TANK_INPUT_0.setFluid(fluidStack);
    }

    public FluidStack getFluidStackInTank(int tankID) {
        return this.FLUID_TANK_INPUT_0.getFluid();
    }

    @Nonnull
    public GrowthcraftFluidTank getFluidTank(int tankID) {
        return this.FLUID_TANK_INPUT_0;
    }

    public boolean isFluidEmpty() {
        return getFluidStackInTank(0).isEmpty();
    }

    public int getTickClock(String type) {
        switch (type) {
            case "current":
                return this.tickClock;
            case "max":
                return this.tickMax;
            default:
                return 0;
        }
    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public @NotNull CompoundTag getUpdateTag() {
        return this.serializeNBT();
    }

    @Override
    public void handleUpdateTag(CompoundTag tag) {
        this.load(tag);
    }

    @Override
    public void load(@NotNull CompoundTag nbt) {
        super.load(nbt);
        this.itemStackHandler.deserializeNBT(nbt.getCompound("inventory"));
        this.FLUID_TANK_INPUT_0.readFromNBT(nbt.getCompound("fluid_tank_input_0"));
        this.tickClock = nbt.getInt("CurrentProcessTicks");
        this.tickMax = nbt.getInt("MaxProcessTicks");

        if (nbt.contains("CustomName", 8)) {
            this.customName = Component.Serializer.fromJson(nbt.getString("CustomName"));
        }
    }

    @Override
    @ParametersAreNonnullByDefault
    protected void saveAdditional(CompoundTag nbt) {
        nbt.put("inventory", itemStackHandler.serializeNBT());
        nbt.put("fluid_tank_input_0", FLUID_TANK_INPUT_0.writeToNBT(new CompoundTag()));
        nbt.putInt("CurrentProcessTicks", this.tickClock);
        nbt.putInt("MaxProcessTicks", this.tickMax);

        if (this.customName != null) {
            nbt.putString("CustomName", Component.Serializer.toJson(this.customName));
        }

        super.saveAdditional(nbt);
    }

    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt) {
        this.load(Objects.requireNonNull(pkt.getTag()));
    }

    @Override
    public void onLoad() {
        super.onLoad();
        itemHandlerLazyOptional = LazyOptional.of(() -> itemStackHandler);
        lazyInputFluidHandler0 = LazyOptional.of(() -> FLUID_TANK_INPUT_0);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        itemHandlerLazyOptional.invalidate();
        lazyInputFluidHandler0.invalidate();
    }

    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == ForgeCapabilities.FLUID_HANDLER) {
            return this.lazyInputFluidHandler0.cast();
        } else if (cap == ForgeCapabilities.ITEM_HANDLER) {
            return itemHandlerLazyOptional.cast();
        }
        return super.getCapability(cap, side);
    }

    public void dropItems() {
        SimpleContainer inventory = new SimpleContainer(itemStackHandler.getSlots());
        for (int i = 0; i < itemStackHandler.getSlots(); i++) {
            inventory.setItem(i, itemStackHandler.getStackInSlot(i));
        }
        Containers.dropContents(this.getLevel(), this.worldPosition, inventory);
    }

    public void drainFluidTank(int tankID, int amount) {
        this.getFluidTank(0).drain(amount, IFluidHandler.FluidAction.EXECUTE);
        this.level.sendBlockUpdated(this.getBlockPos(), this.getBlockState(), this.getBlockState(), Block.UPDATE_ALL);
    }

}
