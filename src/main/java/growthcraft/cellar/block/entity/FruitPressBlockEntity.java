package growthcraft.cellar.block.entity;

import growthcraft.cellar.block.FruitPressBlock;
import growthcraft.cellar.init.GrowthcraftCellarBlockEntities;
import growthcraft.cellar.lib.networking.GrowthcraftCellarMessages;
import growthcraft.cellar.lib.networking.packet.FruitPressFluidTankPacket;
import growthcraft.cellar.recipe.FruitPressRecipe;
import growthcraft.cellar.screen.container.FruitPressMenu;
import growthcraft.lib.block.entity.GrowthcraftFluidTank;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
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

import static growthcraft.cellar.block.FruitPressPistonBlock.PRESSED;

public class FruitPressBlockEntity extends BlockEntity implements BlockEntityTicker<FruitPressBlockEntity>, MenuProvider {
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
        public void onContentsChanged() {
            setChanged();
            if (!level.isClientSide) {
                GrowthcraftCellarMessages.sendToClients(new FruitPressFluidTankPacket(0, this.fluid, worldPosition));
            }
        }
    };

    private LazyOptional<IFluidHandler> lazyInputFluidHandler0 = LazyOptional.empty();

    public FruitPressBlockEntity(BlockPos blockPos, BlockState blockState) {
        this(GrowthcraftCellarBlockEntities.FRUIT_PRESS_BLOCK_ENTITY.get(),
                blockPos, blockState);
    }

    public FruitPressBlockEntity(BlockEntityType<?> blockEntityType, BlockPos blockPos, BlockState blockState) {
        super(blockEntityType, blockPos, blockState);

        this.FLUID_TANK_INPUT_0.allowAnyFluid(true);

        this.data = new ContainerData() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> FruitPressBlockEntity.this.tickClock;
                    case 1 -> FruitPressBlockEntity.this.tickMax;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> FruitPressBlockEntity.this.tickClock = value;
                    case 1 -> FruitPressBlockEntity.this.tickMax = value;
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
                : Component.translatable("container.growthcraft_cellar.fruit_press");
    }

    public void tick() {
        if (this.getLevel() != null) {
            this.tick(this.getLevel(), this.getBlockPos(), this.getBlockState(), this);
        }
    }

    @Override
    public void tick(Level level, BlockPos blockPos, BlockState blockState, FruitPressBlockEntity blockEntity) {
        if(!level.isClientSide
                && level.getBlockState(blockPos.above()).getValue(PRESSED)
        ) {
            List<FruitPressRecipe> recipes = this.getMatchingRecipes();
            FruitPressRecipe recipe = recipes.isEmpty() ? null : recipes.get(0);

            if(recipe != null && this.tickClock <= this.tickMax
                && this.getFluidTank(0).canFluidStackFit(recipe.getResultingFluid())
            ) {
                // Then continue to increment the ticker.
                this.tickClock++;
            } else if (recipe != null && this.tickMax > 0) {
                // Then process the recipe results.
                this.itemStackHandler.getStackInSlot(0).shrink(
                        recipe.getIngredientItemStack().getCount()
                );

                FluidStack resultingFluidStack = recipe.getResultingFluid().copy();

                this.getFluidTank(0).fill(resultingFluidStack, IFluidHandler.FluidAction.EXECUTE);

                this.resetTickClock();

                level.sendBlockUpdated(this.getBlockPos(), this.getBlockState(), this.getBlockState(), Block.UPDATE_ALL);
            } else if(recipe != null && this.tickMax == -1) {
                this.tickMax = recipe.getProcessingTime();
            } else {
                this.resetTickClock();
            }

            level.sendBlockUpdated(this.getBlockPos(), this.getBlockState(), this.getBlockState(), Block.UPDATE_ALL);
        }
    }

    private List<FruitPressRecipe> getMatchingRecipes() {
        List<FruitPressRecipe> matchingRecipes = new ArrayList<>();

        List<FruitPressRecipe> recipes = level.getRecipeManager()
                .getAllRecipesFor(FruitPressRecipe.Type.INSTANCE);

        for(FruitPressRecipe recipe : recipes) {
            if(recipe.matches(this.itemStackHandler.getStackInSlot(0))) {
                matchingRecipes.add(recipe);
            }
        }
        return matchingRecipes;
    }

    private void resetTickClock() {
        this.tickClock = 0;
        this.tickMax = -1;
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int containerId, @NotNull Inventory inventory, @NotNull Player player) {
        return new FruitPressMenu(containerId, inventory, this, this.data);
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

    public static <E extends BlockEntity> void particleTick(Level level, BlockPos blockPos, BlockState blockState, FruitPressBlockEntity blockEntity) {
        RandomSource randomsource = level.random;
        if (randomsource.nextFloat() < 0.11F && blockEntity.getTickClock("current") > 0) {
            for (int i = 0; i < randomsource.nextInt(2) + 2; ++i) {
                FruitPressBlock.makeParticles(level, blockPos, blockState);
            }
        }
    }
}
