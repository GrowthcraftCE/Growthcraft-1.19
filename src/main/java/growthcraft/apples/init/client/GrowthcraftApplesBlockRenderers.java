package growthcraft.apples.init.client;

import growthcraft.apples.init.GrowthcraftApplesFluids;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;

public class GrowthcraftApplesBlockRenderers {
    public static void setRenderLayers() {
        // No longer programmatically set for blocks. Use "render_type": "cutout|translucent" in block model json.
        setFluidRenderLayers();
    }

    private static void setFluidRenderLayers() {
        ItemBlockRenderTypes.setRenderLayer(GrowthcraftApplesFluids.APPLE_CIDER_FLUID.source.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(GrowthcraftApplesFluids.APPLE_CIDER_FLUID.flowing.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(GrowthcraftApplesFluids.APPLE_JUICE_FLUID.source.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(GrowthcraftApplesFluids.APPLE_JUICE_FLUID.flowing.get(), RenderType.translucent());
    }

    private GrowthcraftApplesBlockRenderers() {
        /* Prevent default public constructor */
    }
}
