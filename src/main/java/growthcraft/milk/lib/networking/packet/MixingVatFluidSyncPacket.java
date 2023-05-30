package growthcraft.milk.lib.networking.packet;

import growthcraft.milk.block.entity.MixingVatBlockEntity;
import growthcraft.milk.screen.container.MixingVatMenu;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class MixingVatFluidSyncPacket {
    private FluidStack fluidStack;
    private int tankID;

    private final BlockPos blockPos;

    public MixingVatFluidSyncPacket(int tankID, FluidStack fluidStack, BlockPos blockPos) {
        this.fluidStack = fluidStack;
        this.tankID = tankID;
        this.blockPos = blockPos;
    }

    public MixingVatFluidSyncPacket(FriendlyByteBuf byteBuf) {
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
                    if(Minecraft.getInstance().level.getBlockEntity(this.blockPos) instanceof MixingVatBlockEntity blockEntity) {
                        blockEntity.setFluidStackInTank(this.tankID, this.fluidStack.copy());
                        LocalPlayer player = Minecraft.getInstance().player;

                        if(player.containerMenu instanceof MixingVatMenu menu &&
                                menu.getBlockEntity().getBlockPos().equals(this.blockPos)) {
                            menu.setFluid(this.tankID, this.fluidStack.copy());
                        }
                    }
                }
        );
        return true;
    }
}
