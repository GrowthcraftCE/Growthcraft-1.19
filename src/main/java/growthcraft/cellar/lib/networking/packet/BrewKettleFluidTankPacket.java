package growthcraft.cellar.lib.networking.packet;

import growthcraft.cellar.block.entity.BrewKettleBlockEntity;
import growthcraft.cellar.screen.container.BrewKettleMenu;
import growthcraft.lib.networking.packet.FluidTankSyncPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class BrewKettleFluidTankPacket extends FluidTankSyncPacket {

    public BrewKettleFluidTankPacket(int tankID, FluidStack fluidStack, BlockPos blockPos) {
        super(tankID, fluidStack, blockPos);
    }

    public BrewKettleFluidTankPacket(FriendlyByteBuf byteBuf) {
        super(byteBuf);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
                    if (Minecraft.getInstance().level.getBlockEntity(this.blockPos) instanceof BrewKettleBlockEntity blockEntity) {
                        blockEntity.setFluidStackInTank(this.tankID, this.fluidStack);

                        if (Minecraft.getInstance().player.containerMenu instanceof BrewKettleMenu menu &&
                                menu.getBlockEntity().getBlockPos().equals(this.blockPos)) {
                            menu.setFluid(this.tankID, this.fluidStack);
                        }
                    }
                }
        );
        return true;
    }
}
