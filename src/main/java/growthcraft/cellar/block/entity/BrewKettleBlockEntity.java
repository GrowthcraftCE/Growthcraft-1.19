package growthcraft.cellar.block.entity;

import growthcraft.cellar.block.BrewKettleBlock;
import growthcraft.cellar.init.GrowthcraftCellarBlockEntities;
import growthcraft.cellar.init.GrowthcraftCellarItems;
import growthcraft.cellar.lib.networking.GrowthcraftCellarMessages;
import growthcraft.cellar.lib.networking.packet.BrewKettleFluidTankPacket;
import growthcraft.cellar.recipe.BrewKettleRecipe;
import growthcraft.cellar.screen.container.BrewKettleMenu;
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
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
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

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static growthcraft.cellar.block.CultureJarBlock.LIT;

public class BrewKettleBlockEntity extends BlockEntity implements BlockEntityTicker<BrewKettleBlockEntity>, MenuProvider {

    private int tickClock = 0;
    private int tickMax = -1;

    protected final ContainerData data;

    private Component customName;

    private final ItemStackHandler itemStackHandler = new ItemStackHandler(3) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
        }

        @Override
        public boolean isItemValid(int slot, @NotNull ItemStack stack) {
            return switch (slot) {
                case 0 -> stack.getItem() == GrowthcraftCellarItems.BREW_KETTLE_LID.get();
                case 2 -> false;
                default -> true;
            };
        }
    };

    private LazyOptional<IItemHandler> inventoryItemHandler = LazyOptional.empty();

    private final GrowthcraftFluidTank FLUID_TANK_0 = new GrowthcraftFluidTank(4000) {
        @Override
        protected void onContentsChanged() {
            setChanged();
            if (!level.isClientSide) {
                GrowthcraftCellarMessages.sendToClients(new BrewKettleFluidTankPacket(0, this.getFluid(), worldPosition));
            }
        }
    };

    private LazyOptional<IFluidHandler> fluidHandler0 = LazyOptional.empty();

    private final GrowthcraftFluidTank FLUID_TANK_1 = new GrowthcraftFluidTank(4000) {
        @Override
        protected void onContentsChanged() {
            setChanged();
            if (!level.isClientSide) {
                GrowthcraftCellarMessages.sendToClients(new BrewKettleFluidTankPacket(1, this.fluid, worldPosition));
            }
        }
    };

    private LazyOptional<IFluidHandler> fluidHandler1 = LazyOptional.empty();

    public BrewKettleBlockEntity(BlockPos blockPos, BlockState blockState) {
        this(GrowthcraftCellarBlockEntities.BREW_KETTLE_BLOCK_ENTITY.get(),
                blockPos, blockState);
    }

    public BrewKettleBlockEntity(BlockEntityType<?> blockEntityType, BlockPos blockPos, BlockState blockState) {
        super(blockEntityType, blockPos, blockState);

        this.FLUID_TANK_0.allowAnyFluid(true);

        this.data = new ContainerData() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> BrewKettleBlockEntity.this.tickClock;
                    case 1 -> BrewKettleBlockEntity.this.tickMax;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> BrewKettleBlockEntity.this.tickClock = value;
                    case 1 -> BrewKettleBlockEntity.this.tickMax = value;
                }
            }

            @Override
            public int getCount() {
                return 2;
            }
        };

    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int containerId, @NotNull Inventory inventory, @NotNull Player player) {
        return new BrewKettleMenu(containerId, inventory, this, this.data);
    }

    @Override
    public Component getDisplayName() {
        return this.customName != null
                ? this.customName
                : Component.translatable("container.growthcraft_cellar.brew_kettle");
    }

    public void tick() {
        if (this.getLevel() != null) {
            this.tick(this.getLevel(), this.getBlockPos(), this.getBlockState(), this);
        }
    }

    @Override
    public void tick(Level level, BlockPos blockPos, BlockState blockState, BrewKettleBlockEntity blockEntity) {
        // TODO: Implement BrewKettleBlockEntity ticking.
        if(!level.isClientSide) {
            
            if(!this.itemStackHandler.getStackInSlot(1).isEmpty() 
                && !this.FLUID_TANK_0.isEmpty()) {
                List<BrewKettleRecipe> recipes = this.getMatchingRecipes();
                BrewKettleRecipe recipe = recipes.get(0);

                if(recipe != null) {
                    if(this.tickClock <= this.tickMax) {
                        this.tickClock++;
                    } else if(this.tickMax > 0) {
                        // Process recipe results.
                        this.itemStackHandler.getStackInSlot(1).shrink(
                                recipe.getInputItemStack().getCount()
                        );

                        this.getFluidTank(0).drain(
                                recipe.getInputFluidStack().getAmount(),
                                IFluidHandler.FluidAction.EXECUTE
                        );

                        this.getFluidTank().fill(
                                recipe.getOutputFluidStack().copy(),
                                IFluidHandler.FluidAction.EXECUTE
                        );

                        this.resetTickClock();
                    } else if (this.tickMax == -1) {
                        this.tickMax = recipe.getProcessingTime();
                    } else {
                        this.resetTickClock();
                    }

                    level.sendBlockUpdated(this.getBlockPos(), this.getBlockState(), this.getBlockState(), Block.UPDATE_ALL);
                }
            }
        } else {
            // Do nothing on the client side.
        }

    }


    private List<BrewKettleRecipe> getMatchingRecipes() {
        List<BrewKettleRecipe> matchingRecipes = new ArrayList<>();

        List<BrewKettleRecipe> recipes = level.getRecipeManager()
                .getAllRecipesFor(BrewKettleRecipe.Type.INSTANCE);

        for (BrewKettleRecipe recipe : recipes) {
            if (recipe.matches(
                    this.itemStackHandler.getStackInSlot(0),
                    this.FLUID_TANK_0.getFluid(),
                    this.hasLid(),
                    BlockStateUtils.isHeated(this.getLevel(), this.getBlockPos())
            )) {
                if(!this.FLUID_TANK_1.getFluid().isEmpty()) {
                    if(this.FLUID_TANK_1.getFluid().getRawFluid() == recipe.getOutputFluidStack().getFluid()) {
                        matchingRecipes.add(recipe);
                    }
                } else {
                    matchingRecipes.add(recipe);
                }
            }
        }
        return matchingRecipes;
    }

    public boolean hasLid() {
        return this.itemStackHandler.getStackInSlot(0).getItem() == GrowthcraftCellarItems.BREW_KETTLE_LID.get();
    }

    public boolean isHeated() {
        boolean heated = BlockStateUtils.isHeated(this.level, this.getBlockPos());
        // Only change the blockstate if it is different.
        if (this.getBlockState().getValue(LIT).booleanValue() != heated) {
            this.level.setBlock(this.getBlockPos(), this.getBlockState().setValue(LIT, heated), Block.UPDATE_ALL);
        }
        return heated;
    }

    private void resetTickClock() {
        this.tickClock = 0;
        this.tickMax = -1;
    }

    public void setFluidStackInTank(int tankID, FluidStack fluidStack) {
        switch (tankID) {
            case 0 -> this.FLUID_TANK_0.setFluid(fluidStack);
            case 1 -> this.FLUID_TANK_1.setFluid(fluidStack);
            default -> {
                // Do nothing
            }
        }
    }

    public FluidStack getFluidStackInTank(int tankID) {
        return switch (tankID) {
            case 0 -> this.FLUID_TANK_0.getFluid();
            case 1 -> this.FLUID_TANK_1.getFluid();
            default -> null;
        };
    }

    public GrowthcraftFluidTank getFluidTank(int tankID) {
        return switch (tankID) {
            case 0 -> this.FLUID_TANK_0;
            case 1 -> this.FLUID_TANK_1;
            default -> null;
        };
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
        this.FLUID_TANK_0.readFromNBT(nbt.getCompound("fluid_tank_input_0"));
        this.FLUID_TANK_1.readFromNBT(nbt.getCompound("fluid_tank_output_0"));
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
        nbt.put("fluid_tank_input_0", FLUID_TANK_0.writeToNBT(new CompoundTag()));
        nbt.put("fluid_tank_output_0", FLUID_TANK_1.writeToNBT(new CompoundTag()));
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
        inventoryItemHandler = LazyOptional.of(() -> itemStackHandler);
        fluidHandler0 = LazyOptional.of(() -> FLUID_TANK_0);
        fluidHandler1 = LazyOptional.of(() -> FLUID_TANK_1);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        inventoryItemHandler.invalidate();
        fluidHandler0.invalidate();
        fluidHandler1.invalidate();
    }

    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {

        if (side == Direction.UP && cap == ForgeCapabilities.FLUID_HANDLER) {
            return this.fluidHandler0.cast();
        } else if (cap == ForgeCapabilities.FLUID_HANDLER) {
            return this.fluidHandler1.cast();
        } else if (cap == ForgeCapabilities.ITEM_HANDLER) {
            return inventoryItemHandler.cast();
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

    public static <E extends BlockEntity> void particleTick(Level level, BlockPos blockPos, BlockState blockState, BrewKettleBlockEntity blockEntity) {
        RandomSource randomsource = level.random;
        if (randomsource.nextFloat() < 0.11F) {
            for (int i = 0; i < randomsource.nextInt(2) + 2; ++i) {
                BrewKettleBlock.makeParticles(level, blockPos, blockState);
            }
        }
    }

    public boolean isProcessing() {
        return this.tickClock > 0;
    }

    public int getPercentProgress() {
        float progress = (float) this.tickClock / this.tickMax;
        float percentage = progress * 100;
        return Math.round(percentage);
    }

    public void playSound(String sound) {
        if(Objects.equals(sound, "open") && this.level != null) {
            this.level.playSound(null, this.getBlockPos(), SoundEvents.IRON_DOOR_OPEN, SoundSource.BLOCKS);
        }
    }

}
