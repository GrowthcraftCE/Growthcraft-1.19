package growthcraft.apples.init.client;

import growthcraft.apples.init.GrowthcraftApplesFluids;
import growthcraft.apples.shared.Reference;
import growthcraft.lib.client.ItemRendererUtils;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD, modid = Reference.MODID)
public class GrowthcraftApplesItemRenders {

    @SubscribeEvent
    public static void registerItemRenders(RegisterColorHandlersEvent.Item event) {
        ItemRendererUtils.registerItem(event, Reference.FluidColor.APPLE_CIDER_FLUID_COLOR.toItemColor(), GrowthcraftApplesFluids.APPLE_CIDER_FLUID.bucket.get());
        ItemRendererUtils.registerItem(event, Reference.FluidColor.APPLE_JUICE_FLUID_COLOR.toItemColor(), GrowthcraftApplesFluids.APPLE_JUICE_FLUID.bucket.get());
    }

}
