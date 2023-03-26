package growthcraft.apples.init.client;

public class GrowthcraftApplesBlockRenderers {
    @Deprecated(forRemoval = true, since = "1.19")
    public static void setRenderLayers() {
        // No longer programmatically set. Use "render_type": "cutout|translucent" in block model json.
    }

    private GrowthcraftApplesBlockRenderers() {
        /* Prevent default public constructor */
    }
}
