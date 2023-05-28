package growthcraft.milk.lib.networking.packet;

import growthcraft.milk.block.entity.PancheonBlockEntity;
import growthcraft.milk.screen.container.PancheonMenu;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class PancheonFluidSyncPacket {
    private FluidStack fluidStack;
    private int tankID;

    private final BlockPos blockPos;

    public PancheonFluidSyncPacket(int tankID, FluidStack fluidStack, BlockPos blockPos) {
        this.fluidStack = fluidStack;
        this.tankID = tankID;
        this.blockPos = blockPos;
    }

    public PancheonFluidSyncPacket(FriendlyByteBuf byteBuf) {
        this.fluidStack = byteBuf.readFluidStack();
        this.blockPos = byteBuf.readBlockPos();
    }

    public void toBytes(FriendlyByteBuf byteBuf) {
        byteBuf.writeFluidStack(this.fluidStack);
        byteBuf.writeBlockPos(this.blockPos);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork( () -> {
                    if(Minecraft.getInstance().level.getBlockEntity(this.blockPos) instanceof PancheonBlockEntity blockEntity) {
                        blockEntity.setFluidStackInTank(this.tankID, this.fluidStack);
                        LocalPlayer player = Minecraft.getInstance().player;

                        if(player.containerMenu instanceof PancheonMenu menu &&
                                menu.getBlockEntity().getBlockPos().equals(this.blockPos)) {
                            menu.setFluid(this.tankID, this.fluidStack);
                        }
                    }
                }
        );
        return true;
    }
}
