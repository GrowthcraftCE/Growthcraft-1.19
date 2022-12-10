package growthcraft.milk;

import growthcraft.milk.init.GrowthcraftMilkBlockEntities;
import growthcraft.milk.init.GrowthcraftMilkBlocks;
import growthcraft.milk.init.GrowthcraftMilkFluids;
import growthcraft.milk.init.GrowthcraftMilkItems;
import growthcraft.milk.init.client.GrowthcraftMilkBlockRenderers;
import growthcraft.milk.init.client.GrowthcraftMilkItemRenderers;
import growthcraft.milk.init.config.GrowthcraftMilkConfig;
import growthcraft.milk.shared.Reference;
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
public class GrowthcraftMilk {
    public static final Logger LOGGER = LogManager.getLogger(Reference.MODID);

    public GrowthcraftMilk() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::setup);
        modEventBus.addListener(this::clientSetupEvent);

        // Config
        GrowthcraftMilkConfig.loadConfig();

        // Blocks, Items, Fluids, Block Entities, Containers
        GrowthcraftMilkBlocks.BLOCKS.register(modEventBus);
        GrowthcraftMilkItems.ITEMS.register(modEventBus);
        GrowthcraftMilkBlockEntities.BLOCK_ENTITIES.register(modEventBus);
        GrowthcraftMilkFluids.FLUID_TYPES.register(modEventBus);
        GrowthcraftMilkFluids.FLUIDS.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void clientSetupEvent(final FMLClientSetupEvent event) {
        GrowthcraftMilkBlockRenderers.setRenderLayers();
    }

    private void setup(final FMLCommonSetupEvent event) {
        // Do Nothing for now ...
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        LOGGER.info("Growthcraft Milk starting up ...");
    }

    @SubscribeEvent
    public static void onColorHandle(RegisterColorHandlersEvent.Item event) {
        GrowthcraftMilkItemRenderers.registerItemRenders(event);
    }
}
