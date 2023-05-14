package growthcraft.cellar.lib.networking.packet;

import growthcraft.lib.networking.packet.FluidTankSyncPacket;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class FermentationBarrelFluidTankPacket extends FluidTankSyncPacket {

    public FermentationBarrelFluidTankPacket(int tankID, FluidStack fluidStack, BlockPos blockPos) {
        super(tankID, fluidStack, blockPos);
    }

    public FermentationBarrelFluidTankPacket(FriendlyByteBuf byteBuf) {
        super(byteBuf);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
                    //TODO: Implement network sync hook to fermentation barrel block entity and menu.
                    /*
                    if(Minecraft.getInstance().level.getBlockEntity(this.blockPos) instanceof CultureJarBlockEntity blockEntity) {
                        blockEntity.setFluidStackInTank(this.tankID, this.fluidStack);

                        if(Minecraft.getInstance().player.containerMenu instanceof CultureJarMenu menu &&
                                menu.getBlockEntity().getBlockPos().equals(this.blockPos)) {
                            menu.setFluid(this.tankID, this.fluidStack);
                        }
                    }
                    */
                }
        );
        return true;
    }
}
