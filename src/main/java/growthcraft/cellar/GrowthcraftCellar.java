package growthcraft.cellar;

import growthcraft.cellar.init.GrowthcraftCellarBlocks;
import growthcraft.cellar.init.GrowthcraftCellarFluids;
import growthcraft.cellar.init.GrowthcraftCellarItems;
import growthcraft.cellar.init.client.GrowthcraftCellarBlockRenderers;
import growthcraft.cellar.init.client.GrowthcraftCellarItemRenderers;
import growthcraft.cellar.init.config.GrowthcraftCellarConfig;
import growthcraft.cellar.shared.Reference;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.IForgeRegistry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@Mod(Reference.MODID)
@Mod.EventBusSubscriber(modid = Reference.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class GrowthcraftCellar {
    public static final Logger LOGGER = LogManager.getLogger(Reference.MODID);

    public GrowthcraftCellar() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::setup);
        modEventBus.addListener(this::clientSetupEvent);

        // Config
        GrowthcraftCellarConfig.loadConfig();

        // Blocks, Items, Fluids, Block Entities, Containers
        GrowthcraftCellarBlocks.BLOCKS.register(modEventBus);
        GrowthcraftCellarItems.ITEMS.register(modEventBus);
        GrowthcraftCellarFluids.FLUIDS.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void clientSetupEvent(final FMLClientSetupEvent event) {
        GrowthcraftCellarBlockRenderers.setRenderLayers();
    }

    private void setup(final FMLCommonSetupEvent event) {
        // Do Nothing for now ...
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        LOGGER.info("Growthcraft Cellar starting up ...");
    }

    @SubscribeEvent
    public static void onItemsRegistry(final RegistryEvent.Register<Item> event) {
        final IForgeRegistry<Item> itemRegistry = event.getRegistry();
        final Item.Properties properties = new Item.Properties().tab(growthcraft.core.shared.Reference.CREATIVE_TAB);
        GrowthcraftCellarBlocks.registerBlockItems(itemRegistry, properties);
    }

    @SubscribeEvent
    public static void onColorHandle(RegisterColorHandlersEvent.Item event) {
        GrowthcraftCellarItemRenderers.registerItemRenders(event);
    }
}
