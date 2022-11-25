package growthcraft.apples.init.client;

import growthcraft.apples.init.GrowthcraftApplesBlocks;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;

public class GrowthcraftApplesBlockRenderers {
    public static void setRenderLayers() {
        ItemBlockRenderTypes.setRenderLayer(GrowthcraftApplesBlocks.APPLE_TREE_LEAVES.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(GrowthcraftApplesBlocks.APPLE_TREE_SAPLING.get(), RenderType.cutout());
    }

    private GrowthcraftApplesBlockRenderers() {
        /* Prevent default public constructor */
    }
}
