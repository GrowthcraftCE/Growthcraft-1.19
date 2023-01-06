package growthcraft.milk.block.entity;

import growthcraft.lib.block.entity.GrowthcraftFluidTank;
import growthcraft.lib.utils.TickUtils;
import growthcraft.milk.init.GrowthcraftMilkBlockEntities;
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
import java.util.Objects;

public class PancheonBlockEntity extends BlockEntity implements BlockEntityTicker<PancheonBlockEntity>, MenuProvider {

    private int tickClock = 0;
    private final int tickMax = TickUtils.toTicks(15, "seconds");

    private Component customName;

    private final GrowthcraftFluidTank FLUID_TANK_INPUT_0 = new GrowthcraftFluidTank(2000);
    private final GrowthcraftFluidTank FLUID_TANK_OUTPUT_0 = new GrowthcraftFluidTank(1000, true);
    private final GrowthcraftFluidTank FLUID_TANK_OUTPUT_1 = new GrowthcraftFluidTank(1000, true);

    private LazyOptional<IFluidHandler> lazyInputFluidHandler0 = LazyOptional.empty();
    private LazyOptional<IFluidHandler> lazyOutputFluidHandler0 = LazyOptional.empty();
    private LazyOptional<IFluidHandler> lazyOutputFluidHandler1 = LazyOptional.empty();

    public PancheonBlockEntity(BlockPos blockPos, BlockState state) {
        super(GrowthcraftMilkBlockEntities.PANCHEON_BLOCK_ENTITY.get(), blockPos, state);
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("container.growthcraft_milk.pancheon");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int containerId, @NotNull Inventory inventory, @NotNull Player player) {
        return new PancheonMenu(containerId, inventory, this);
    }

    public void tick() {
        if (this.getLevel() != null) {
            this.tick(this.getLevel(), this.getBlockPos(), this.getBlockState(), this);
        }
    }

    @Override
    public void tick(Level level, BlockPos pos, BlockState blockState, PancheonBlockEntity beeBoxBlockEntity) {
        // TODO: If contains a valid pancheon.json recipe, process it.
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
        this.tickClock = nbt.getInt("CurrentProcessTicks");
        this.FLUID_TANK_INPUT_0.readFromNBT(nbt);
        this.FLUID_TANK_OUTPUT_0.readFromNBT(nbt);
        this.FLUID_TANK_OUTPUT_1.readFromNBT(nbt);

        if (nbt.contains("CustomName", 8)) {
            this.customName = Component.Serializer.fromJson(nbt.getString("CustomName"));
        }
    }

    @Override
    @ParametersAreNonnullByDefault
    protected void saveAdditional(CompoundTag nbt) {
        super.saveAdditional(nbt);
        nbt.putInt("CurrentProcessTicks", this.tickClock);

        nbt = FLUID_TANK_INPUT_0.writeToNBT(nbt);
        nbt = FLUID_TANK_OUTPUT_0.writeToNBT(nbt);
        nbt = FLUID_TANK_OUTPUT_1.writeToNBT(nbt);

        if (this.customName != null) {
            nbt.putString("CustomName", Component.Serializer.toJson(this.customName));
        }

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
}
