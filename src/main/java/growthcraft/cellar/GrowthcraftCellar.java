package growthcraft.cellar;

import growthcraft.cellar.init.*;
import growthcraft.cellar.init.client.GrowthcraftCellarBlockRenderers;
import growthcraft.cellar.init.config.GrowthcraftCellarConfig;
import growthcraft.cellar.lib.networking.GrowthcraftCellarMessages;
import growthcraft.cellar.screen.CultureJarScreen;
import growthcraft.cellar.shared.Reference;
import growthcraft.core.init.GrowthcraftCreativeModeTabs;
import net.minecraft.client.gui.screens.MenuScreens;
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
public class GrowthcraftCellar {
    public static final Logger LOGGER = LogManager.getLogger(Reference.MODID);

    public GrowthcraftCellar() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::setup);
        modEventBus.addListener(this::clientSetupEvent);
        modEventBus.addListener(this::buildCreativeTabContents);

        // Config
        GrowthcraftCellarConfig.loadConfig();

        // Blocks, Items, Fluids, Block Entities, Containers
        GrowthcraftCellarBlocks.BLOCKS.register(modEventBus);
        GrowthcraftCellarItems.ITEMS.register(modEventBus);
        GrowthcraftCellarBlockEntities.BLOCK_ENTITIES.register(modEventBus);
        GrowthcraftCellarFluids.FLUID_TYPES.register(modEventBus);
        GrowthcraftCellarFluids.FLUIDS.register(modEventBus);
        GrowthcraftCellarMenus.MENUS.register(modEventBus);

        GrowthcraftCellarRecipes.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);
    }


    private void clientSetupEvent(final FMLClientSetupEvent event) {
        GrowthcraftCellarBlockRenderers.setRenderLayers();
        MenuScreens.register(GrowthcraftCellarMenus.CULTURE_JAR_MENU.get(), CultureJarScreen::new);
    }

    private void setup(final FMLCommonSetupEvent event) {
        GrowthcraftCellarMessages.register();
    }

    public void buildCreativeTabContents(CreativeModeTabEvent.BuildContents event) {
        if (event.getTab() == GrowthcraftCreativeModeTabs.GROWTHCRAFT_CREATIVE_TAB) {
            GrowthcraftCellarItems.ITEMS.getEntries().forEach(itemRegistryObject -> {
                if (!GrowthcraftCellarItems.excludeItemRegistry(itemRegistryObject.getId())) {
                    event.accept(new ItemStack(itemRegistryObject.get()));
                }
            });
        }
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        LOGGER.info("Growthcraft Cellar starting up ...");
    }

}
