package growthcraft.milk.init.client;

import growthcraft.milk.block.entity.renderer.MixingVatBlockEntityRenderer;
import growthcraft.milk.block.entity.renderer.PancheonBlockEntityRenderer;
import growthcraft.milk.init.GrowthcraftMilkBlockEntities;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class GrowthcraftMilkBlockEntityRenderers {
    @SubscribeEvent
    public static void register(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(GrowthcraftMilkBlockEntities.PANCHEON_BLOCK_ENTITY.get(), context -> new PancheonBlockEntityRenderer());
        event.registerBlockEntityRenderer(GrowthcraftMilkBlockEntities.MIXING_VAT_BLOCK_ENTITY.get(), context -> new MixingVatBlockEntityRenderer());
    }

    private GrowthcraftMilkBlockEntityRenderers() {
        /* Prevent default public constructor */
    }
}
