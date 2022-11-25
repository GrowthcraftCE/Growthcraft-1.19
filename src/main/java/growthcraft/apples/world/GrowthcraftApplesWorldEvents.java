package growthcraft.apples.world;

import growthcraft.apples.shared.Reference;
import growthcraft.apples.world.gen.TreeGeneration;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Reference.MODID)
public class GrowthcraftApplesWorldEvents {
    @SubscribeEvent
    public static void biomeLoadingEvent(final BiomeLoadingEvent event) {
        TreeGeneration.generateTrees(event);
    }
}
