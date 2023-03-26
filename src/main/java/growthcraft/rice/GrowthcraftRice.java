package growthcraft.rice;

import growthcraft.rice.init.GrowthcraftRiceBlocks;
import growthcraft.rice.init.GrowthcraftRiceFluids;
import growthcraft.rice.init.GrowthcraftRiceItems;
import growthcraft.rice.init.client.GrowthcraftRiceBlockRenderers;
import growthcraft.rice.init.client.GrowthcraftRiceItemRenderers;
import growthcraft.rice.init.config.GrowthcraftRiceConfig;
import growthcraft.rice.shared.Reference;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(Reference.MODID)
@Mod.EventBusSubscriber(modid = Reference.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class GrowthcraftRice {
    public static final Logger LOGGER = LogManager.getLogger(Reference.MODID);

    public GrowthcraftRice() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::setup);
        modEventBus.addListener(this::clientSetupEvent);

        // Config
        GrowthcraftRiceConfig.loadConfig();

        // Blocks, Items, Fluids, Block Entities, Containers
        GrowthcraftRiceBlocks.BLOCKS.register(modEventBus);
        GrowthcraftRiceItems.ITEMS.register(modEventBus);
        GrowthcraftRiceFluids.FLUID_TYPES.register(modEventBus);
        GrowthcraftRiceFluids.FLUIDS.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void clientSetupEvent(final FMLClientSetupEvent event) {
        GrowthcraftRiceBlockRenderers.setRenderLayers();
    }

    private void setup(final FMLCommonSetupEvent event) {
        // Do Nothing for now ...
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        LOGGER.info("Growthcraft Rice starting up ...");
    }

    @SubscribeEvent
    public static void onColorHandle(RegisterColorHandlersEvent.Item event) {
        GrowthcraftRiceItemRenderers.registerItemRenders(event);
    }
}
