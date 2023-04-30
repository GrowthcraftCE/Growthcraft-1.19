package growthcraft.cellar.block.entity;

import growthcraft.cellar.init.GrowthcraftCellarBlockEntities;
import growthcraft.cellar.lib.networking.GrowthcraftCellarMessages;
import growthcraft.cellar.lib.networking.packet.CultureJarFluidSyncPacket;
import growthcraft.cellar.recipe.CultureJarRecipe;
import growthcraft.cellar.screen.CultureJarMenu;
import growthcraft.lib.block.entity.GrowthcraftFluidTank;
import growthcraft.lib.utils.BlockStateUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.MenuProvider;
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

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static growthcraft.cellar.block.CultureJarBlock.LIT;

public class CultureJarBlockEntity extends BlockEntity implements BlockEntityTicker<CultureJarBlockEntity>, MenuProvider {
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

    private final GrowthcraftFluidTank FLUID_TANK_INPUT_0 = new GrowthcraftFluidTank(1000) {
        @Override
        protected void onContentsChanged() {
            setChanged();
            if (!level.isClientSide) {
                GrowthcraftCellarMessages.sendToClients(new CultureJarFluidSyncPacket(0, this.fluid, worldPosition));
            }
        }
    };

    private LazyOptional<IFluidHandler> lazyInputFluidHandler0 = LazyOptional.empty();

    public CultureJarBlockEntity(BlockPos blockPos, BlockState blockState) {
        this(GrowthcraftCellarBlockEntities.CULTURE_JAR_BLOCK_ENTITY.get(), blockPos, blockState);
    }

    public CultureJarBlockEntity(BlockEntityType<?> blockEntityType, BlockPos blockPos, BlockState blockState) {
        super(blockEntityType, blockPos, blockState);

        this.FLUID_TANK_INPUT_0.allowAnyFluid(true);

        this.data = new ContainerData() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> CultureJarBlockEntity.this.tickClock;
                    case 1 -> CultureJarBlockEntity.this.tickMax;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> CultureJarBlockEntity.this.tickClock = value;
                    case 1 -> CultureJarBlockEntity.this.tickMax = value;
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
                : Component.translatable("container.growthcraft_cellar.culture_jar");
    }

    @Override
    public void tick(Level level, BlockPos blockPos, BlockState blockState, CultureJarBlockEntity blockEntity) {
        //TODO: Check for Culture Jar recipe and processing.

        if(!level.isClientSide && this.isHeated()) {
            // Do nothing, we are just ensuring that the LIT property is accurate.
        }

        // The Culture Jar requires a fluid and an item in order to do anything.
        if(!level.isClientSide && this.getFluidTank(0).getFluidAmount() > 0 && !this.itemStackHandler.getStackInSlot(0).isEmpty()) {

            // Check for recipe.
            List<CultureJarRecipe> recipes = this.getMatchingRecipes(this.getFluidStackInTank(0), this.itemStackHandler.getStackInSlot(0));
            CultureJarRecipe recipe = recipes.isEmpty() ? null : recipes.get(0);

            if(recipe.isHeatSourceRequired() && !this.isHeated()) {
                return;
            }

            if(recipe != null && this.tickClock <= this.tickMax) {
                this.tickClock++;
            } else if(recipe != null && this.tickMax > 0 && this.tickClock > this.tickMax) {
                // Process the resulting recipe.
                int amountToDrain = recipe.getInputFluidStack().getAmount();

                this.getFluidTank(0).drain(amountToDrain, IFluidHandler.FluidAction.EXECUTE);
                this.itemStackHandler.getStackInSlot(0).grow(1);

                this.tickMax = -1;
                this.tickClock = 0;

            } else if (recipe != null && this.tickMax == -1) {
                this.tickMax = recipe.getRecipeProcessingTime();
            } else {
                this.tickMax = -1;
                this.tickClock = 0;
            }

            this.level.sendBlockUpdated(this.getBlockPos(), this.getBlockState(), this.getBlockState(), Block.UPDATE_ALL);
        }

    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int containerId, @NotNull Inventory inventory, @NotNull Player player) {
        return new CultureJarMenu(containerId, inventory, this, this.data);
    }

    public void tick() {
        if (this.getLevel() != null) {
            this.tick(this.getLevel(), this.getBlockPos(), this.getBlockState(), this);
        }
    }

    public boolean isHeated() {
        boolean heated = BlockStateUtils.isHeated(this.level, this.getBlockPos());
        // Only change the blockstate if it is different.
        if(this.getBlockState().getValue(LIT).booleanValue() != heated) {
            this.level.setBlock(this.getBlockPos(), this.getBlockState().setValue(LIT, heated), Block.UPDATE_ALL);
        }
        return heated;
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

    private List<CultureJarRecipe> getMatchingRecipes(FluidStack fluidStack, ItemStack itemStack) {
        List<CultureJarRecipe> matchingRecipes = new ArrayList<>();

        List<CultureJarRecipe> recipes = level.getRecipeManager()
                .getAllRecipesFor(CultureJarRecipe.Type.INSTANCE);

        for (CultureJarRecipe recipe : recipes) {
            if (recipe.getInputFluidStack().getFluid() == fluidStack.getFluid()
                    && recipe.getInputItemStack().getItem() == itemStack.getItem()) {
                matchingRecipes.add(recipe);
            }
        }
        return matchingRecipes;
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


}
