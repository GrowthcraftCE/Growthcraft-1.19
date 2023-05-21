package growthcraft.milk.lib.networking.packet;

import growthcraft.lib.networking.packet.FluidTankSyncPacket;
import growthcraft.milk.block.entity.ChurnBlockEntity;
import growthcraft.milk.screen.container.ChurnMenu;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class ChurnFluidTankSyncPacket extends FluidTankSyncPacket {

    public ChurnFluidTankSyncPacket(int tankID, FluidStack fluidStack, BlockPos blockPos) {
        super(tankID, fluidStack, blockPos);
    }

    public ChurnFluidTankSyncPacket(FriendlyByteBuf byteBuf) {
        super(byteBuf);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
                    if (Minecraft.getInstance().level.getBlockEntity(this.blockPos) instanceof ChurnBlockEntity blockEntity) {
                        blockEntity.setFluidStackInTank(this.tankID, this.fluidStack);

                        if (Minecraft.getInstance().player.containerMenu instanceof ChurnMenu menu &&
                                menu.getBlockEntity().getBlockPos().equals(this.blockPos)) {
                            menu.setFluid(this.tankID, this.fluidStack);
                        }
                    }

                }
        );
        return true;
    }
}
