package growthcraft.apiary.init.client;

import growthcraft.apiary.init.GrowthcraftApiaryFluids;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;

public class GrowthcraftApiaryBlockRenders {

    public static void setRenderLayers() {
        ItemBlockRenderTypes.setRenderLayer(GrowthcraftApiaryFluids.HONEY.source.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(GrowthcraftApiaryFluids.HONEY.flowing.get(), RenderType.translucent());
    }

    private GrowthcraftApiaryBlockRenders() {
        /* Prevent default public constructor */
    }
}
