package growthcraft.milk.init.client;

import growthcraft.lib.client.BlockRendererUtils;
import growthcraft.milk.init.GrowthcraftMilkBlocks;
import growthcraft.milk.init.GrowthcraftMilkFluids;
import growthcraft.milk.shared.Reference;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class GrowthcraftMilkBlockRenderers {

    public static void setRenderLayers() {
        setFluidRenderLayers();
    }

    private static void setFluidRenderLayers() {
        ItemBlockRenderTypes.setRenderLayer(GrowthcraftMilkFluids.BUTTER_MILK.source.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(GrowthcraftMilkFluids.BUTTER_MILK.flowing.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(GrowthcraftMilkFluids.CHEESE_BASE.source.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(GrowthcraftMilkFluids.CHEESE_BASE.flowing.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(GrowthcraftMilkFluids.CONDENSED_MILK.source.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(GrowthcraftMilkFluids.CONDENSED_MILK.flowing.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(GrowthcraftMilkFluids.CREAM.source.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(GrowthcraftMilkFluids.CREAM.flowing.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(GrowthcraftMilkFluids.CULTURED_MILK.source.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(GrowthcraftMilkFluids.CULTURED_MILK.flowing.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(GrowthcraftMilkFluids.KUMIS.source.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(GrowthcraftMilkFluids.KUMIS.flowing.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(GrowthcraftMilkFluids.MILK.source.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(GrowthcraftMilkFluids.MILK.flowing.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(GrowthcraftMilkFluids.RENNET.source.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(GrowthcraftMilkFluids.RENNET.flowing.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(GrowthcraftMilkFluids.SKIM_MILK.source.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(GrowthcraftMilkFluids.SKIM_MILK.flowing.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(GrowthcraftMilkFluids.WHEY.source.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(GrowthcraftMilkFluids.WHEY.flowing.get(), RenderType.translucent());
    }

    @SubscribeEvent
    public static void registerBlockRenders(RegisterColorHandlersEvent.Block event) {
        BlockRendererUtils.registerBlock(event, Reference.ItemColor.APPENZELLER_CHEESE, GrowthcraftMilkBlocks.APPENZELLER_CHEESE.get() );
        BlockRendererUtils.registerBlock(event, Reference.ItemColor.APPENZELLER_CHEESE, GrowthcraftMilkBlocks.APPENZELLER_CHEESE_CURDS.get() );
        BlockRendererUtils.registerBlock(event, Reference.ItemColor.ASIAGO_CHEESE, GrowthcraftMilkBlocks.ASIAGO_CHEESE.get() );
        BlockRendererUtils.registerBlock(event, Reference.ItemColor.ASIAGO_CHEESE, GrowthcraftMilkBlocks.ASIAGO_CHEESE_CURDS.get() );
        BlockRendererUtils.registerBlock(event, Reference.ItemColor.CASU_MARZU_CHEESE, GrowthcraftMilkBlocks.CASU_MARZU_CHEESE.get() );
        BlockRendererUtils.registerBlock(event, Reference.ItemColor.CASU_MARZU_CHEESE, GrowthcraftMilkBlocks.CASU_MARZU_CHEESE_CURDS.get() );
        BlockRendererUtils.registerBlock(event, Reference.ItemColor.CHEDDAR_CHEESE, GrowthcraftMilkBlocks.CHEDDAR_CHEESE.get() );
        BlockRendererUtils.registerBlock(event, Reference.ItemColor.CHEDDAR_CHEESE, GrowthcraftMilkBlocks.CHEDDAR_CHEESE_CURDS.get() );
        BlockRendererUtils.registerBlock(event, Reference.ItemColor.EMMENTALER_CHEESE, GrowthcraftMilkBlocks.EMMENTALER_CHEESE.get() );
        BlockRendererUtils.registerBlock(event, Reference.ItemColor.EMMENTALER_CHEESE, GrowthcraftMilkBlocks.EMMENTALER_CHEESE_CURDS.get() );
        BlockRendererUtils.registerBlock(event, Reference.ItemColor.GORGONZOLA_CHEESE, GrowthcraftMilkBlocks.GORGONZOLA_CHEESE.get() );
        BlockRendererUtils.registerBlock(event, Reference.ItemColor.GORGONZOLA_CHEESE, GrowthcraftMilkBlocks.GORGONZOLA_CHEESE_CURDS.get() );
        BlockRendererUtils.registerBlock(event, Reference.ItemColor.GOUDA_CHEESE, GrowthcraftMilkBlocks.GOUDA_CHEESE.get() );
        BlockRendererUtils.registerBlock(event, Reference.ItemColor.GOUDA_CHEESE, GrowthcraftMilkBlocks.GOUDA_CHEESE_CURDS.get() );
        BlockRendererUtils.registerBlock(event, Reference.ItemColor.MONTEREY_CHEESE, GrowthcraftMilkBlocks.MONTEREY_CHEESE.get() );
        BlockRendererUtils.registerBlock(event, Reference.ItemColor.MONTEREY_CHEESE, GrowthcraftMilkBlocks.MONTEREY_CHEESE_CURDS.get() );
        BlockRendererUtils.registerBlock(event, Reference.ItemColor.PARMESAN_CHEESE, GrowthcraftMilkBlocks.PARMESAN_CHEESE.get() );
        BlockRendererUtils.registerBlock(event, Reference.ItemColor.PARMESAN_CHEESE, GrowthcraftMilkBlocks.PARMESAN_CHEESE_CURDS.get() );
        BlockRendererUtils.registerBlock(event, Reference.ItemColor.PROVOLONE_CHEESE, GrowthcraftMilkBlocks.PROVOLONE_CHEESE.get() );
        BlockRendererUtils.registerBlock(event, Reference.ItemColor.PROVOLONE_CHEESE, GrowthcraftMilkBlocks.PROVOLONE_CHEESE_CURDS.get() );
        BlockRendererUtils.registerBlock(event, Reference.ItemColor.RICOTTA_CHEESE, GrowthcraftMilkBlocks.RICOTTA_CHEESE_CURDS.get() );
    }


    private GrowthcraftMilkBlockRenderers() {
        /* Prevent default public constructor */
    }
}
