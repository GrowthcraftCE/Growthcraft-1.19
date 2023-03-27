package growthcraft.core;

import growthcraft.core.init.GrowthcraftBlockEntities;
import growthcraft.core.init.GrowthcraftBlocks;
import growthcraft.core.init.GrowthcraftItems;
import growthcraft.core.init.config.GrowthcraftConfig;
import growthcraft.core.shared.Reference;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
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
public class Growthcraft {
    public static final Logger LOGGER = LogManager.getLogger(Reference.MODID);

    public Growthcraft() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::setup);
        modEventBus.addListener(this::clientSetupEvent);
        modEventBus.addListener(this::registerCreativeModeTab);

        GrowthcraftConfig.loadConfig();

        GrowthcraftBlocks.BLOCKS.register(modEventBus);
        GrowthcraftItems.ITEMS.register(modEventBus);
        GrowthcraftBlockEntities.BLOCK_ENTITIES.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void clientSetupEvent(final FMLClientSetupEvent event) {
        // Do nothing for now ...
    }

    private void setup(final FMLCommonSetupEvent event) {
        // Do Nothing
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        // Do nothing
    }

    public void registerCreativeModeTab(CreativeModeTabEvent.Register event) {
        event.registerCreativeModeTab(new ResourceLocation(Reference.MODID, "tab"), builder ->
                // Set name of tab to display
                builder.title(Component.translatable("item_group." + Reference.MODID + ".tab"))
                        // Set icon of creative tab
                        .icon(() -> new ItemStack(GrowthcraftItems.CROWBAR_ORANGE.get()))
                        // Add default items to tab
                        .displayItems((enabledFlags, populator) -> {
                            // Add blocks
                            //GrowthcraftBlocks.BLOCKS.getEntries().forEach(blockRegistryObject -> {
                            //    if (!GrowthcraftBlocks.excludeBlockItemRegistry(blockRegistryObject.getId())) {
                            //        populator.accept(new ItemStack(blockRegistryObject.get()));
                            //    }
                            //});
                            // Add registered items
                            GrowthcraftItems.ITEMS.getEntries().forEach(itemRegistryObject -> {
                                if (!GrowthcraftItems.excludeItemRegistry(itemRegistryObject.getId())) {
                                    populator.accept(new ItemStack(itemRegistryObject.get()));
                                }
                            });

                        })
        );
    }

}
