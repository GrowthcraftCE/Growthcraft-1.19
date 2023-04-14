package growthcraft.milk.block.entity;

import growthcraft.lib.block.entity.GrowthcraftFluidTank;
import growthcraft.milk.GrowthcraftMilk;
import growthcraft.milk.init.GrowthcraftMilkBlockEntities;
import growthcraft.milk.lib.networking.GrowthcraftMilkMessages;
import growthcraft.milk.lib.networking.packet.PancheonFluidSyncPacket;
import growthcraft.milk.recipe.PancheonRecipe;
import growthcraft.milk.screen.PancheonMenu;
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
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PancheonBlockEntity extends BlockEntity implements BlockEntityTicker<PancheonBlockEntity>, MenuProvider {

    private int tickClock = 0;
    private int tickMax = -1;

    protected final ContainerData data;

    private Component customName;

    private final GrowthcraftFluidTank FLUID_TANK_INPUT_0 = new GrowthcraftFluidTank(2000) {
        @Override
        protected void onContentsChanged() {
            setChanged();
            if(!level.isClientSide) {
                GrowthcraftMilkMessages.sendToClients(new PancheonFluidSyncPacket(0, this.fluid, worldPosition));
            }
        }
    };

    private final GrowthcraftFluidTank FLUID_TANK_OUTPUT_0 = new GrowthcraftFluidTank(1000, true) {
        @Override
        protected void onContentsChanged() {
            setChanged();
            if(!level.isClientSide) {
                GrowthcraftMilkMessages.sendToClients(new PancheonFluidSyncPacket(1, this.fluid, worldPosition));
            }
        }
    };

    private final GrowthcraftFluidTank FLUID_TANK_OUTPUT_1 = new GrowthcraftFluidTank(1000, true) {
        @Override
        protected void onContentsChanged() {
            setChanged();
            if(!level.isClientSide) {
                GrowthcraftMilkMessages.sendToClients(new PancheonFluidSyncPacket(2, this.fluid, worldPosition));
            }
        }
    };

    private LazyOptional<IFluidHandler> lazyInputFluidHandler0 = LazyOptional.empty();
    private LazyOptional<IFluidHandler> lazyOutputFluidHandler0 = LazyOptional.empty();
    private LazyOptional<IFluidHandler> lazyOutputFluidHandler1 = LazyOptional.empty();

    public PancheonBlockEntity(BlockPos blockPos, BlockState state) {
        super(GrowthcraftMilkBlockEntities.PANCHEON_BLOCK_ENTITY.get(), blockPos, state);

        this.FLUID_TANK_INPUT_0.allowAnyFluid(true);

        this.data = new ContainerData() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> PancheonBlockEntity.this.tickClock;
                    case 1 -> PancheonBlockEntity.this.tickMax;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> PancheonBlockEntity.this.tickClock = value;
                    case 1 -> PancheonBlockEntity.this.tickMax = value;
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
        return Component.translatable("container.growthcraft_milk.pancheon");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int containerId, @NotNull Inventory inventory, @NotNull Player player) {
        return new PancheonMenu(containerId, inventory, this, this.data);
    }

    public void tick() {
        if (this.getLevel() != null) {
            this.tick(this.getLevel(), this.getBlockPos(), this.getBlockState(), this);
        }
    }

    public boolean isInputTankFull() {
        return this.getFluidStackInTank(0).getAmount() == 2000;
    }

    private boolean isOutputTankFull() {
        return (this.getFluidStackInTank(1).getAmount() == 1000 || this.getFluidStackInTank(2).getAmount() == 1000);
    }

    @Override
    public void tick(Level level, BlockPos pos, BlockState blockState, PancheonBlockEntity blockEntity) {

        if(!level.isClientSide && this.isInputTankFull()) {
            List<PancheonRecipe> recipes = this.getMatchingRecipes(this.getFluidStackInTank(0));
            PancheonRecipe recipe = recipes.isEmpty() ? null : recipes.get(0);

            if (recipe != null && this.tickClock <= this.tickMax ) {
                // Then increment the clock.
                if(this.tickClock %100 == 0) GrowthcraftMilk.LOGGER.warn(String.format("[%d/%d] Processing %s ...", this.tickClock, this.tickMax, this.getFluidStackInTank(0).getFluid().toString()));

                this.tickClock++;
            } else if (recipe != null && this.tickMax > 0 && this.tickClock > this.tickMax) {
                // Then process the recipe.
                this.setFluidStackInTank(1, recipe.getFluidStack("output0").copy());
                this.setFluidStackInTank(2, recipe.getFluidStack("output1").copy());
                this.setFluidStackInTank(0, FluidStack.EMPTY);

                this.tickMax = -1;
                this.tickClock = 0;

            } else if(recipe != null && this.tickMax == -1) {
                // Then we have a new recipe that needs to start processing.
                GrowthcraftMilk.LOGGER.warn("We found a new recipe!");
                this.tickMax = recipe.getRecipeProcessingTime();
            } else {
                // Else make sure the clock is zero.
                this.tickMax = -1;
                this.tickClock = 0;
            }

            this.level.sendBlockUpdated(this.getBlockPos(), this.getBlockState(), this.getBlockState(), 3);

        } else if(!level.isClientSide && this.isOutputTankFull()) {
            this.level.sendBlockUpdated(this.getBlockPos(), this.getBlockState(), this.getBlockState(), 3);
        }
    }

    private List<PancheonRecipe> getMatchingRecipes(FluidStack fluidStack) {
        List<PancheonRecipe> matchingRecipes = new ArrayList<>();

        List<PancheonRecipe> recipes = level.getRecipeManager()
                .getAllRecipesFor(PancheonRecipe.Type.INSTANCE);

        for(PancheonRecipe recipe : recipes) {
            if(recipe.getFluidStack("input0").getFluid() == fluidStack.getFluid()) {
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

        this.FLUID_TANK_INPUT_0.readFromNBT(nbt.getCompound("fluid_tank_input_0"));
        this.FLUID_TANK_OUTPUT_0.readFromNBT(nbt.getCompound("fluid_tank_output_0"));
        this.FLUID_TANK_OUTPUT_1.readFromNBT(nbt.getCompound("fluid_tank_output_1"));
        this.tickClock = nbt.getInt("CurrentProcessTicks");
        this.tickMax = nbt.getInt("MaxProcessTicks");

        if (nbt.contains("CustomName", 8)) {
            this.customName = Component.Serializer.fromJson(nbt.getString("CustomName"));
        }
    }

    @Override
    @ParametersAreNonnullByDefault
    protected void saveAdditional(CompoundTag nbt) {

        nbt.put("fluid_tank_input_0", FLUID_TANK_INPUT_0.writeToNBT(new CompoundTag()));
        nbt.put("fluid_tank_output_0", FLUID_TANK_OUTPUT_0.writeToNBT(new CompoundTag()));
        nbt.put("fluid_tank_output_1", FLUID_TANK_OUTPUT_1.writeToNBT(new CompoundTag()));
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
        lazyInputFluidHandler0 = LazyOptional.of( () -> FLUID_TANK_INPUT_0);
        lazyOutputFluidHandler0 = LazyOptional.of( () -> FLUID_TANK_OUTPUT_0);
        lazyOutputFluidHandler1 = LazyOptional.of( () -> FLUID_TANK_OUTPUT_1);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        lazyInputFluidHandler0.invalidate();
        lazyOutputFluidHandler0.invalidate();
        lazyOutputFluidHandler1.invalidate();
    }

    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == ForgeCapabilities.FLUID_HANDLER) {
            int inputFluidAmount0 = this.FLUID_TANK_INPUT_0.getFluidAmount();
            int outputFluidAmount0 = this.FLUID_TANK_OUTPUT_0.getFluidAmount();
            int outputFluidAmount1 = this.FLUID_TANK_OUTPUT_1.getFluidAmount();

            // If input is empty and output0 is not empty, return output0
            if (inputFluidAmount0 == 0 && outputFluidAmount0 > 0) {
                return lazyOutputFluidHandler0.cast();
            }
            // If input and output0 are empty but output1 is not empty, return Output1
            if (inputFluidAmount0 == 0 && outputFluidAmount0 == 0 && outputFluidAmount1 > 0) {
                return lazyOutputFluidHandler1.cast();
            }
            // If inputTank has fluid or all tanks or empty, return the input tank.
            if (inputFluidAmount0 > 0 || inputFluidAmount0 + outputFluidAmount0 + outputFluidAmount1 == 0) {
                return this.lazyInputFluidHandler0.cast();
            }
        }

        return super.getCapability(cap, side);
    }

    public void setFluidStackInTank(int tankID, FluidStack fluidStack) {
        switch (tankID) {
            case 1 -> this.FLUID_TANK_OUTPUT_0.setFluid(fluidStack);
            case 2 -> this.FLUID_TANK_OUTPUT_1.setFluid(fluidStack);
            default -> this.FLUID_TANK_INPUT_0.setFluid(fluidStack);
        }
    }

    public FluidStack getFluidStackInTank(int tankID) {
        return switch (tankID) {
            case 1 -> this.FLUID_TANK_OUTPUT_0.getFluid();
            case 2 -> this.FLUID_TANK_OUTPUT_1.getFluid();
            default -> this.FLUID_TANK_INPUT_0.getFluid();
        };
    }

    public GrowthcraftFluidTank getFluidTank(int tankID) {
        return switch (tankID) {
            case 1 -> this.FLUID_TANK_OUTPUT_0;
            case 2 -> this.FLUID_TANK_OUTPUT_1;
            default -> this.FLUID_TANK_INPUT_0;
        };
    }

    public boolean isFluidEmpty() {
        return getFluidStackInTank(0).isEmpty() && getFluidStackInTank(1).isEmpty() && getFluidStackInTank(2).isEmpty();
    }
}
