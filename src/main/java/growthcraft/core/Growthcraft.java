package growthcraft.core;

import growthcraft.core.init.GrowthcraftBlockEntities;
import growthcraft.core.init.GrowthcraftBlocks;
import growthcraft.core.init.GrowthcraftItems;
import growthcraft.core.init.config.GrowthcraftConfig;
import growthcraft.core.shared.Reference;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.RegistryObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Field;

@Mod(Reference.MODID)
@Mod.EventBusSubscriber(modid = Reference.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Growthcraft {
    public static final Logger LOGGER = LogManager.getLogger(Reference.MODID);

    public static CreativeModeTab CREATIVE_TAB;

    public Growthcraft() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::setup);
        modEventBus.addListener(this::clientSetupEvent);

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
        //event.enqueueWork( () -> {
        //   GrowthcraftOreGeneration.registerConfiguredFeatures();
        //});
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        //LOGGER.info("HELLO from server starting");
    }

    public void registerCreativeModeTab(CreativeModeTabEvent.Register event) {
        Growthcraft.LOGGER.warn("CREATIVE_TAB_DECO is registering ...");

        CREATIVE_TAB = event.registerCreativeModeTab(new ResourceLocation(Reference.MODID, "tab"), builder ->
                // Set name of tab to display
                builder.title(Component.translatable("item_group." + Reference.MODID + ".tab"))
                        // Set icon of creative tab
                        .icon(() -> new ItemStack(GrowthcraftItems.CROWBAR_ORANGE.get()))
                        // Add default items to tab
                        .displayItems((enabledFlags, populator, hasPermissions) -> {
                            // Add blocks
                            for (Field field : GrowthcraftBlocks.class.getFields()) {
                                if (field.getType() != RegistryObject.class) continue;

                                try {
                                    RegistryObject<Block> block = (RegistryObject) field.get(null);
                                    if (!GrowthcraftBlocks.excludeBlockItemRegistry(block.getId())) {
                                        populator.accept(new ItemStack(block.get()));
                                    }
                                } catch (IllegalAccessException e) {

                                }
                            }

                            // Add items
                            for (Field field : GrowthcraftItems.class.getFields()) {
                                if (field.getType() != RegistryObject.class) continue;

                                try {
                                    RegistryObject<Item> item = (RegistryObject) field.get(null);
                                    if (!GrowthcraftItems.excludeItemRegistry(item.getId())) {
                                        populator.accept(new ItemStack(item.get()));
                                    }
                                } catch (IllegalAccessException e) {

                                }
                            }
                        })
        );
    }

}
