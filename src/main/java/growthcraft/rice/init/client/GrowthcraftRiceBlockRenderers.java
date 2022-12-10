package growthcraft.rice.init.client;

import growthcraft.rice.init.GrowthcraftRiceBlocks;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;

public class GrowthcraftRiceBlockRenderers {

    @Deprecated(forRemoval = true, since = "1.19")
    public static void setRenderLayers() {
        // TODO: No longer programmatically set. Use "render_type": "cutout|translucent" in block model json.
        ItemBlockRenderTypes.setRenderLayer(GrowthcraftRiceBlocks.RICE_CROP.get(), RenderType.cutout());
    }

    private GrowthcraftRiceBlockRenderers() {
        /* Prevent default public constructor */
    }
}
