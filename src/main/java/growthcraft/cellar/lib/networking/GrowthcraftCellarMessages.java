package growthcraft.cellar.lib.networking;

import growthcraft.cellar.lib.networking.packet.CultureJarFluidSyncPacket;
import growthcraft.cellar.lib.networking.packet.FermentationBarrelFluidTankPacket;
import growthcraft.cellar.lib.networking.packet.FruitPressFluidTankPacket;
import growthcraft.cellar.shared.Reference;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

public class GrowthcraftCellarMessages {
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

        net.messageBuilder(CultureJarFluidSyncPacket.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(CultureJarFluidSyncPacket::new)
                .encoder(CultureJarFluidSyncPacket::toBytes)
                .consumerMainThread(CultureJarFluidSyncPacket::handle)
                .add();

        net.messageBuilder(FermentationBarrelFluidTankPacket.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(FermentationBarrelFluidTankPacket::new)
                .encoder(FermentationBarrelFluidTankPacket::toBytes)
                .consumerMainThread(FermentationBarrelFluidTankPacket::handle)
                .add();

        net.messageBuilder(FruitPressFluidTankPacket.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(FruitPressFluidTankPacket::new)
                .encoder(FruitPressFluidTankPacket::toBytes)
                .consumerMainThread(FruitPressFluidTankPacket::handle)
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
