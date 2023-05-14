package growthcraft.milk;

import growthcraft.core.init.GrowthcraftCreativeModeTabs;
import growthcraft.milk.init.*;
import growthcraft.milk.init.client.GrowthcraftMilkBlockEntityRenderers;
import growthcraft.milk.init.client.GrowthcraftMilkBlockRenderers;
import growthcraft.milk.init.config.GrowthcraftMilkConfig;
import growthcraft.milk.lib.networking.GrowthcraftMilkMessages;
import growthcraft.milk.screen.ChurnScreen;
import growthcraft.milk.screen.PancheonScreen;
import growthcraft.milk.shared.Reference;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.event.EntityRenderersEvent;
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
public class GrowthcraftMilk {
    public static final Logger LOGGER = LogManager.getLogger(Reference.MODID);

    public GrowthcraftMilk() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::setup);
        modEventBus.addListener(this::clientSetupEvent);
        modEventBus.addListener(this::buildCreativeTabContents);
        modEventBus.addListener(this::onRegisterRenderers);

        // Config
        GrowthcraftMilkConfig.loadConfig();

        // Blocks, Items, Fluids, Block Entities, Containers
        GrowthcraftMilkBlocks.BLOCKS.register(modEventBus);
        GrowthcraftMilkItems.ITEMS.register(modEventBus);
        GrowthcraftMilkBlockEntities.BLOCK_ENTITIES.register(modEventBus);
        GrowthcraftMilkFluids.FLUID_TYPES.register(modEventBus);
        GrowthcraftMilkFluids.FLUIDS.register(modEventBus);
        GrowthcraftMilkMenus.MENUS.register(modEventBus);

        GrowthcraftMilkRecipes.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void clientSetupEvent(final FMLClientSetupEvent event) {
        GrowthcraftMilkBlockRenderers.setRenderLayers();
        MenuScreens.register(GrowthcraftMilkMenus.PANCHEON_MENU.get(), PancheonScreen::new);
        MenuScreens.register(GrowthcraftMilkMenus.CHURN_MENU.get(), ChurnScreen::new);
    }

    private void setup(final FMLCommonSetupEvent event) {
        MinecraftForge.EVENT_BUS.register(new LivingDropLootModifier());
        GrowthcraftMilkMessages.register();
    }

    public void buildCreativeTabContents(CreativeModeTabEvent.BuildContents event) {
        if (event.getTab() == GrowthcraftCreativeModeTabs.GROWTHCRAFT_CREATIVE_TAB) {
            GrowthcraftMilkItems.ITEMS.getEntries().forEach(itemRegistryObject -> {
                if (!GrowthcraftMilkItems.excludeItemRegistry(itemRegistryObject.getId())) {
                    event.accept(new ItemStack(itemRegistryObject.get()));
                }
            });
        }
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        LOGGER.info("Growthcraft Milk starting up ...");
    }

    public void onRegisterRenderers(EntityRenderersEvent.RegisterRenderers event) {
        GrowthcraftMilkBlockEntityRenderers.register(event);
    }
}