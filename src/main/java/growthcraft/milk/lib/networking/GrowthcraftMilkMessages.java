package growthcraft.milk.lib.networking;

import growthcraft.milk.lib.networking.packet.ChurnFluidTankSyncPacket;
import growthcraft.milk.lib.networking.packet.MixingVatFluidSyncPacket;
import growthcraft.milk.lib.networking.packet.PancheonFluidSyncPacket;
import growthcraft.milk.shared.Reference;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

public class GrowthcraftMilkMessages {
    private static SimpleChannel INSTANCE;

    private static int packetID = 0;

    private static int id() {
        return packetID++;
    }

    public static void register() {
        SimpleChannel net = NetworkRegistry.ChannelBuilder
                .named(new ResourceLocation(Reference.MODID, "messages"))
                .networkProtocolVersion( () -> "1.0" )
                .clientAcceptedVersions( s -> true )
                .serverAcceptedVersions( s -> true )
                .simpleChannel();

        INSTANCE = net;

        net.messageBuilder(MixingVatFluidSyncPacket.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(MixingVatFluidSyncPacket::new)
                .encoder(MixingVatFluidSyncPacket::toBytes)
                .consumerMainThread(MixingVatFluidSyncPacket::handle)
                .add();

        net.messageBuilder(PancheonFluidSyncPacket.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(PancheonFluidSyncPacket::new)
                .encoder(PancheonFluidSyncPacket::toBytes)
                .consumerMainThread(PancheonFluidSyncPacket::handle)
                .add();

        net.messageBuilder(ChurnFluidTankSyncPacket.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(ChurnFluidTankSyncPacket::new)
                .encoder(ChurnFluidTankSyncPacket::toBytes)
                .consumerMainThread(ChurnFluidTankSyncPacket::handle)
                .add();
    }

    public static <MSG> void sendToClients(MSG message) {
        INSTANCE.send(PacketDistributor.ALL.noArg(), message);
    }

    public static <MSG> void sendToServer(MSG message) {
        INSTANCE.sendToServer(message);
    }

    public static <MSG> void sendToPlayer(MSG message, ServerPlayer player) {
        INSTANCE.send(PacketDistributor.PLAYER.with( () -> player), message);
    }

}
