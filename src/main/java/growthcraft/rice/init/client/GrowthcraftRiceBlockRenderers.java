package growthcraft.rice.init.client;

import growthcraft.rice.init.GrowthcraftRiceFluids;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;

public class GrowthcraftRiceBlockRenderers {

    public static void setRenderLayers() {
        setFluidRenderLayers();
    }

    private static void setFluidRenderLayers() {
        ItemBlockRenderTypes.setRenderLayer(GrowthcraftRiceFluids.RICE_WATER.source.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(GrowthcraftRiceFluids.RICE_WATER.flowing.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(GrowthcraftRiceFluids.RICE_WINE.source.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(GrowthcraftRiceFluids.RICE_WINE.flowing.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(GrowthcraftRiceFluids.SAKE.source.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(GrowthcraftRiceFluids.SAKE.flowing.get(), RenderType.translucent());
    }

    private GrowthcraftRiceBlockRenderers() {
        /* Prevent default public constructor */
    }
}
