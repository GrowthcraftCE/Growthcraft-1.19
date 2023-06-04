package growthcraft.lib.networking.packet;

import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public abstract class FluidTankSyncPacket {
    public FluidStack fluidStack;
    public int tankID;

    public final BlockPos blockPos;

    public FluidTankSyncPacket(int tankID, FluidStack fluidStack, BlockPos blockPos) {
        this.fluidStack = fluidStack;
        this.tankID = tankID;
        this.blockPos = blockPos;
    }

    public FluidTankSyncPacket(FriendlyByteBuf byteBuf) {
        this.fluidStack = byteBuf.readFluidStack();
        this.blockPos = byteBuf.readBlockPos();
    }

    public void toBytes(FriendlyByteBuf byteBuf) {
        byteBuf.writeFluidStack(this.fluidStack);
        byteBuf.writeBlockPos(this.blockPos);
    }

    public abstract boolean handle(Supplier<NetworkEvent.Context> supplier);

}
