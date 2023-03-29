package growthcraft.cellar.init.client;

import growthcraft.cellar.init.GrowthcraftCellarFluids;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;

public class GrowthcraftCellarBlockRenderers {

    public static void setRenderLayers() {
        setFluidRenderLayers();
    }

    private static void setFluidRenderLayers() {
        // TODO: Set GrowthcraftCellar Fluid Renderers
        ItemBlockRenderTypes.setRenderLayer(GrowthcraftCellarFluids.AMBER_ALE.source.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(GrowthcraftCellarFluids.AMBER_ALE.flowing.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(GrowthcraftCellarFluids.AMBER_LAGER.source.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(GrowthcraftCellarFluids.AMBER_LAGER.flowing.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(GrowthcraftCellarFluids.AMBER_WORT.source.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(GrowthcraftCellarFluids.AMBER_WORT.flowing.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(GrowthcraftCellarFluids.BROWN_ALE.source.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(GrowthcraftCellarFluids.BROWN_ALE.flowing.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(GrowthcraftCellarFluids.BROWN_LAGER.source.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(GrowthcraftCellarFluids.BROWN_LAGER.flowing.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(GrowthcraftCellarFluids.BROWN_WORT.source.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(GrowthcraftCellarFluids.BROWN_WORT.flowing.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(GrowthcraftCellarFluids.COPPER_ALE.source.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(GrowthcraftCellarFluids.COPPER_ALE.flowing.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(GrowthcraftCellarFluids.COPPER_LAGER.source.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(GrowthcraftCellarFluids.COPPER_LAGER.flowing.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(GrowthcraftCellarFluids.COPPER_WORT.source.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(GrowthcraftCellarFluids.COPPER_WORT.flowing.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(GrowthcraftCellarFluids.DARK_LAGER.source.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(GrowthcraftCellarFluids.DARK_LAGER.flowing.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(GrowthcraftCellarFluids.DARK_WORT.source.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(GrowthcraftCellarFluids.DARK_WORT.flowing.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(GrowthcraftCellarFluids.DEEP_AMBER_WORT.source.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(GrowthcraftCellarFluids.DEEP_AMBER_WORT.flowing.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(GrowthcraftCellarFluids.GOLDEN_WORT.source.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(GrowthcraftCellarFluids.GOLDEN_WORT.flowing.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(GrowthcraftCellarFluids.HOPPED_GOLDEN_WORT.source.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(GrowthcraftCellarFluids.HOPPED_GOLDEN_WORT.flowing.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(GrowthcraftCellarFluids.IPA_ALE.source.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(GrowthcraftCellarFluids.IPA_ALE.flowing.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(GrowthcraftCellarFluids.OLD_PORT_ALE.source.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(GrowthcraftCellarFluids.OLD_PORT_ALE.flowing.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(GrowthcraftCellarFluids.PALE_ALE.source.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(GrowthcraftCellarFluids.PALE_ALE.flowing.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(GrowthcraftCellarFluids.PALE_GOLDEN_WORT.source.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(GrowthcraftCellarFluids.PALE_GOLDEN_WORT.flowing.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(GrowthcraftCellarFluids.PALE_LAGER.source.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(GrowthcraftCellarFluids.PALE_LAGER.flowing.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(GrowthcraftCellarFluids.PILSNER_LAGER.source.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(GrowthcraftCellarFluids.PILSNER_LAGER.flowing.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(GrowthcraftCellarFluids.PURPLE_GRAPE_JUICE.source.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(GrowthcraftCellarFluids.PURPLE_GRAPE_JUICE.flowing.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(GrowthcraftCellarFluids.PURPLE_GRAPE_WINE.source.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(GrowthcraftCellarFluids.PURPLE_GRAPE_WINE.flowing.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(GrowthcraftCellarFluids.RED_GRAPE_JUICE.source.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(GrowthcraftCellarFluids.RED_GRAPE_JUICE.flowing.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(GrowthcraftCellarFluids.RED_GRAPE_WINE.source.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(GrowthcraftCellarFluids.RED_GRAPE_WINE.flowing.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(GrowthcraftCellarFluids.STOUT_ALE.source.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(GrowthcraftCellarFluids.STOUT_ALE.flowing.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(GrowthcraftCellarFluids.VIENNA_LAGER.source.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(GrowthcraftCellarFluids.VIENNA_LAGER.flowing.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(GrowthcraftCellarFluids.WHITE_GRAPE_JUICE.source.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(GrowthcraftCellarFluids.WHITE_GRAPE_JUICE.flowing.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(GrowthcraftCellarFluids.WHITE_GRAPE_WINE.source.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(GrowthcraftCellarFluids.WHITE_GRAPE_WINE.flowing.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(GrowthcraftCellarFluids.WORT.source.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(GrowthcraftCellarFluids.WORT.flowing.get(), RenderType.translucent());
    }

    private GrowthcraftCellarBlockRenderers() {
        /* Prevent default public constructor */
    }
}
