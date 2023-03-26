package growthcraft.apiary.init.client;

import growthcraft.apiary.init.GrowthcraftApiaryFluids;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;

public class GrowthcraftApiaryBlockRenders {

    public static void setRenderLayers() {
        setFluidRenderLayers();
    }

    private static void setFluidRenderLayers() {
        ItemBlockRenderTypes.setRenderLayer(GrowthcraftApiaryFluids.HONEY_MEAD.source.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(GrowthcraftApiaryFluids.HONEY_MEAD.flowing.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(GrowthcraftApiaryFluids.HONEY.source.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(GrowthcraftApiaryFluids.HONEY.flowing.get(), RenderType.translucent());
    }

    private GrowthcraftApiaryBlockRenders() {
        /* Prevent default public constructor */
    }
}
