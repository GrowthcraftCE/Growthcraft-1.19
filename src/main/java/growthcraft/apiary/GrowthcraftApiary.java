package growthcraft.apiary;

import growthcraft.apiary.init.*;
import growthcraft.apiary.init.client.GrowthcraftApiaryBlockRenders;
import growthcraft.apiary.init.client.GrowthcraftApiaryItemRenders;
import growthcraft.apiary.init.config.GrowthcraftApiaryConfig;
import growthcraft.apiary.shared.Reference;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
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

    public void registerCreativeModeTab(CreativeModeTabEvent.Register event) {
        event.registerCreativeModeTab(new ResourceLocation(Reference.MODID, "tab"), builder ->
                // Set name of tab to display
                builder.title(Component.translatable("item_group." + Reference.MODID + ".tab"))
                        // Set icon of creative tab
                        .icon(() -> new ItemStack(GrowthcraftApiaryBlocks.BEE_BOX_OAK.get()))
                        // Add default items to tab
                        .displayItems((enabledFlags, output) -> {
                            // Add blocks
                            GrowthcraftApiaryBlocks.BLOCKS.getEntries().forEach(
                                    blockRegistryObject -> {
                                        if (!GrowthcraftApiaryBlocks.excludeBlockItemRegistry(blockRegistryObject.getId())) {
                                            output.accept(new ItemStack(blockRegistryObject.get()));
                                        }
                                    }
                            );
                            // Add items
                            GrowthcraftApiaryItems.ITEMS.getEntries().forEach(itemRegistryObject -> {
                                if (!GrowthcraftApiaryItems.excludeItemRegistry(itemRegistryObject.getId())) {
                                    output.accept(new ItemStack(itemRegistryObject.get()));
                                }
                            });
                        })
        );
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
