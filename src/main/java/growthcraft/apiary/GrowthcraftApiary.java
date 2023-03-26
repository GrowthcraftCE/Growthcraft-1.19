package growthcraft.apiary;

import growthcraft.apiary.init.*;
import growthcraft.apiary.init.client.GrowthcraftApiaryBlockRenders;
import growthcraft.apiary.init.client.GrowthcraftApiaryItemRenders;
import growthcraft.apiary.init.config.GrowthcraftApiaryConfig;
import growthcraft.apiary.shared.Reference;
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
public class GrowthcraftApiary {

    public static final Logger LOGGER = LogManager.getLogger(Reference.MODID);

    public GrowthcraftApiary() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::setup);
        modEventBus.addListener(this::clientSetupEvent);

        GrowthcraftApiaryConfig.loadConfig();

        GrowthcraftApiaryBlocks.BLOCKS.register(modEventBus);
        GrowthcraftApiaryItems.ITEMS.register(modEventBus);
        GrowthcraftApiaryFluids.FLUID_TYPES.register(modEventBus);
        GrowthcraftApiaryFluids.FLUIDS.register(modEventBus);
        GrowthcraftApiaryBlockEntities.BLOCK_ENTITIES.register(modEventBus);
        GrowthcraftApiaryMenus.MENUS.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void clientSetupEvent(final FMLClientSetupEvent event) {
        GrowthcraftApiaryBlockRenders.setRenderLayers();
        GrowthcraftApiaryMenus.registerMenus();
    }

    private void setup(final FMLCommonSetupEvent event) {
        //event.enqueueWork( () -> {
        //   GrowthcraftOreGeneration.registerConfiguredFeatures();
        //});
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        LOGGER.info("Growthcraft Apiary starting up server-side ...");
    }

    @SubscribeEvent
    public static void onColorHandle(RegisterColorHandlersEvent.Item event) {
        GrowthcraftApiaryItemRenders.registerItemRenders(event);
    }
}
