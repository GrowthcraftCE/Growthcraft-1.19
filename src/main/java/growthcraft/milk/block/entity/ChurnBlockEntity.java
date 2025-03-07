package growthcraft.milk.block.entity;

import growthcraft.lib.block.entity.GrowthcraftFluidTank;
import growthcraft.milk.init.GrowthcraftMilkBlockEntities;
import growthcraft.milk.lib.networking.GrowthcraftMilkMessages;
import growthcraft.milk.lib.networking.packet.ChurnFluidTankSyncPacket;
import growthcraft.milk.recipe.ChurnRecipe;
import growthcraft.milk.screen.container.ChurnMenu;
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
import net.minecraft.world.MenuProvider;
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
import org.apache.commons.lang3.BooleanUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.ParametersAreNonnullByDefault;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static growthcraft.milk.block.ChurnBlock.PLUNGED;

public class ChurnBlockEntity extends BlockEntity implements BlockEntityTicker<ChurnBlockEntity>, MenuProvider {
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
        public void onContentsChanged() {
            setChanged();
            if (!level.isClientSide) {
                GrowthcraftMilkMessages.sendToClients(new ChurnFluidTankSyncPacket(0, this.fluid, worldPosition));
            }
        }
    };

    private LazyOptional<IFluidHandler> lazyInputFluidHandler0 = LazyOptional.empty();

    public ChurnBlockEntity(BlockPos blockPos, BlockState blockState) {
        this(GrowthcraftMilkBlockEntities.CHURN_BLOCK_ENTITY.get(), blockPos, blockState);
    }

    public ChurnBlockEntity(BlockEntityType<?> blockEntityType, BlockPos blockPos, BlockState blockState) {
        super(blockEntityType, blockPos, blockState);

        this.FLUID_TANK_INPUT_0.allowAnyFluid(true);

        this.data = new ContainerData() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> ChurnBlockEntity.this.tickClock;
                    case 1 -> ChurnBlockEntity.this.tickMax;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> ChurnBlockEntity.this.tickClock = value;
                    case 1 -> ChurnBlockEntity.this.tickMax = value;
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
                : Component.translatable("container.growthcraft_milk.churn");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int containerId, @NotNull Inventory inventory, @NotNull Player player) {
        return new ChurnMenu(containerId, inventory, this, this.data);
    }

    public void tick() {
        if (this.getLevel() != null) {
            this.tick(this.getLevel(), this.getBlockPos(), this.getBlockState(), this);
        }
    }

    @Override
    public void tick(Level level, BlockPos blockPos, BlockState blockState, ChurnBlockEntity blockEntity) {
        // Nothing really  to do here. The churn is a manual block entity.
    }

    public void togglePlungerState() {
        if(this.level != null && !this.level.isClientSide) {
            BlockState blockState = level.getBlockState(this.getBlockPos());
            boolean plungerState = blockState.getValue(PLUNGED);

            if(plungerState) {
                level.playSound(null, this.getBlockPos(), SoundEvents.WOODEN_BUTTON_CLICK_OFF, SoundSource.BLOCKS, 1.0f, 1.0f);
            } else {
                level.playSound(null, this.getBlockPos(), SoundEvents.WOODEN_BUTTON_CLICK_ON, SoundSource.BLOCKS, 1.0f, 1.0f);
                tryPlunger();
            }

            plungerState = BooleanUtils.negate(plungerState);

            level.setBlock(this.getBlockPos(), blockState.setValue(PLUNGED, plungerState), Block.UPDATE_ALL);
        }
    }

    public void tryPlunger() {
        this.tryPlunger(1);
    }

    public void tryPlunger(int i) {

        if(this.level != null && !this.level.isClientSide
                && !this.getFluidTank(0).isEmpty()
                && this.getInventoryHandler().getStackInSlot(0).isEmpty()
        ) {

            List<ChurnRecipe> recipes = getMatchingRecipes(this.getFluidStackInTank(0));
            ChurnRecipe recipe = recipes.isEmpty() ? null : recipes.get(0);

            if(recipe != null) {
                // Reminder, tick in this case is the number of plunges.
                if(this.tickClock <= this.tickMax) {
                    this.tickClock++;
                } else if (this.tickMax > 0 && this.tickClock > this.tickMax) {
                    this.setFluidStackInTank(0, recipe.getOutputFluidStack().copy());
                    if(new SecureRandom().nextInt(100) <= recipe.getByProductChance()) {
                        this.getInventoryHandler().setStackInSlot(0, recipe.getResultItemStack());
                    }
                    this.tickMax = -1;
                    this.tickClock = 0;
                } else if (this.tickMax == -1) {
                    this.tickMax = recipe.getPlungesNeeded();
                } else {
                    this.tickMax = -1;
                    this.tickClock = 0;
                }

                this.level.sendBlockUpdated(this.getBlockPos(), this.getBlockState(), this.getBlockState(), Block.UPDATE_ALL);
            }
        }
    }

    private List<ChurnRecipe> getMatchingRecipes(FluidStack fluidStack) {
        List<ChurnRecipe> matchingRecipes = new ArrayList<>();

        List<ChurnRecipe> recipes = level.getRecipeManager().getAllRecipesFor(
                ChurnRecipe.Type.INSTANCE
        );

        for (ChurnRecipe recipe : recipes) {
            if (recipe.getInputFluidStack().getFluid() == fluidStack.getFluid()) {
                matchingRecipes.add(recipe);
            }
        }
        return matchingRecipes;
    }

    public ItemStackHandler getInventoryHandler() {
        return this.itemStackHandler;
    }

    public boolean hasByProductItem() {
        return !this.itemStackHandler.getStackInSlot(0).isEmpty();
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
