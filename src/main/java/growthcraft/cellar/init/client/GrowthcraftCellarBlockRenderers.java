package growthcraft.cellar.init.client;

import growthcraft.cellar.init.GrowthcraftCellarBlocks;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;

public class GrowthcraftCellarBlockRenderers {
    public static void setRenderLayers() {
        ItemBlockRenderTypes.setRenderLayer(GrowthcraftCellarBlocks.HOPS_VINE.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(GrowthcraftCellarBlocks.RED_GRAPE_VINE.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(GrowthcraftCellarBlocks.RED_GRAPE_VINE_LEAVES.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(GrowthcraftCellarBlocks.RED_GRAPE_VINE_FRUIT.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(GrowthcraftCellarBlocks.PURPLE_GRAPE_VINE.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(GrowthcraftCellarBlocks.PURPLE_GRAPE_VINE_LEAVES.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(GrowthcraftCellarBlocks.PURPLE_GRAPE_VINE_FRUIT.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(GrowthcraftCellarBlocks.WHITE_GRAPE_VINE.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(GrowthcraftCellarBlocks.WHITE_GRAPE_VINE_LEAVES.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(GrowthcraftCellarBlocks.WHITE_GRAPE_VINE_FRUIT.get(), RenderType.cutout());
    }

    private GrowthcraftCellarBlockRenderers() {
        /* Prevent default public constructor */
    }
}
