package growthcraft.milk.init.client;

import growthcraft.milk.block.entity.renderer.PancheonBlockEntityRenderer;
import growthcraft.milk.init.GrowthcraftMilkBlockEntities;
import net.minecraftforge.client.event.EntityRenderersEvent;

public class GrowthcraftMilkBlockEntityRenderers {
    public static void register(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(GrowthcraftMilkBlockEntities.PANCHEON_BLOCK_ENTITY.get(), context -> new PancheonBlockEntityRenderer());

    }

    private GrowthcraftMilkBlockEntityRenderers() {
        /* Prevent default public constructor */
    }
}
