package growthcraft.milk.init.client;

public class GrowthcraftMilkBlockRenderers {

    @Deprecated(forRemoval = true, since = "1.19")
    public static void setRenderLayers() {
        // No longer programmatically set. Use "render_type": "cutout|translucent" in block model json.
    }

    private GrowthcraftMilkBlockRenderers() {
        /* Prevent default public constructor */
    }
}
