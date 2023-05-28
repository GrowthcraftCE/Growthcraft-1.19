package growthcraft.cellar.lib.networking.packet;

import growthcraft.cellar.block.entity.BrewKettleBlockEntity;
import growthcraft.cellar.screen.container.BrewKettleMenu;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class BrewKettleFluidTankPacket {
    private FluidStack fluidStack;
    private int tankID;

    private final BlockPos blockPos;

    public BrewKettleFluidTankPacket(int tankID, FluidStack fluidStack, BlockPos blockPos) {
        this.fluidStack = fluidStack;
        this.tankID = tankID;
        this.blockPos = blockPos;
    }

    public BrewKettleFluidTankPacket(FriendlyByteBuf byteBuf) {
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
                    if(Minecraft.getInstance().level.getBlockEntity(this.blockPos) instanceof BrewKettleBlockEntity blockEntity) {
                        blockEntity.setFluidStackInTank(this.tankID, this.fluidStack);

                        LocalPlayer player = Minecraft.getInstance().player;

                        if(player.containerMenu instanceof BrewKettleMenu) {
                            BrewKettleMenu menu = (BrewKettleMenu) player.containerMenu;
                            if(menu.getBlockEntity().getBlockPos().equals(this.blockPos)) {
                                menu.setFluid(this.tankID, this.fluidStack);
                           }
                        }
                    }
                }
        );
        return true;
    }
}
