package growthcraft.apiary;

import growthcraft.apiary.init.*;
import growthcraft.apiary.init.client.GrowthcraftApiaryBlockRenders;
import growthcraft.apiary.init.config.GrowthcraftApiaryConfig;
import growthcraft.apiary.shared.Reference;
import growthcraft.core.init.GrowthcraftCreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.CreativeModeTabEvent;
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
        modEventBus.addListener(this::buildCreativeTabContents);

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

    public void buildCreativeTabContents(CreativeModeTabEvent.BuildContents event) {
        if (event.getTab() == GrowthcraftCreativeModeTabs.GROWTHCRAFT_CREATIVE_TAB) {
            GrowthcraftApiaryItems.ITEMS.getEntries().forEach(itemRegistryObject -> {
                if (!GrowthcraftApiaryItems.excludeItemRegistry(itemRegistryObject.getId())) {
                    event.accept(new ItemStack(itemRegistryObject.get()));
                }
            });
        }
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        LOGGER.info("Growthcraft Apiary starting up server-side ...");
    }

}
