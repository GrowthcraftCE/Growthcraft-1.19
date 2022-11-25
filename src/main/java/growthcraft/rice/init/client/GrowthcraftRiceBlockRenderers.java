package growthcraft.rice.init.client;

import growthcraft.rice.init.GrowthcraftRiceBlocks;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;

public class GrowthcraftRiceBlockRenderers {
    public static void setRenderLayers() {
        ItemBlockRenderTypes.setRenderLayer(GrowthcraftRiceBlocks.RICE_CROP.get(), RenderType.cutout());
    }

    private GrowthcraftRiceBlockRenderers() {
        /* Prevent default public constructor */
    }
}
