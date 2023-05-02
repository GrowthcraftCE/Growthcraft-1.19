package growthcraft.cellar.init.client;

import growthcraft.cellar.block.entity.renderer.CultureJarBlockEntityRenderer;
import growthcraft.cellar.init.GrowthcraftCellarBlockEntities;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class GrowthcraftCellarBlockEntityRenderers {
    @SubscribeEvent
    public static void register(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(GrowthcraftCellarBlockEntities.CULTURE_JAR_BLOCK_ENTITY.get(), context -> new CultureJarBlockEntityRenderer());
    }

    private GrowthcraftCellarBlockEntityRenderers() {
        /* Prevent default public constructor */
    }
}
