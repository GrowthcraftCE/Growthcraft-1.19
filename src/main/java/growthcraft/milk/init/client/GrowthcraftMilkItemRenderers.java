package growthcraft.milk.init.client;

import growthcraft.lib.client.ItemRendererUtils;
import growthcraft.milk.init.GrowthcraftMilkFluids;
import growthcraft.milk.shared.Reference;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class GrowthcraftMilkItemRenderers {

    @SubscribeEvent
    public static void registerItemRenders(RegisterColorHandlersEvent.Item event) {
        ItemRendererUtils.registerItem(event, Reference.FluidColor.BUTTER_MILK.toItemColor(), GrowthcraftMilkFluids.BUTTER_MILK.bucket.get());
        ItemRendererUtils.registerItem(event, Reference.FluidColor.CHEESE_BASE.toItemColor(), GrowthcraftMilkFluids.CHEESE_BASE.bucket.get());
        ItemRendererUtils.registerItem(event, Reference.FluidColor.CONDENSED_MILK.toItemColor(), GrowthcraftMilkFluids.CONDENSED_MILK.bucket.get());
        ItemRendererUtils.registerItem(event, Reference.FluidColor.CREAM.toItemColor(), GrowthcraftMilkFluids.CREAM.bucket.get());
        ItemRendererUtils.registerItem(event, Reference.FluidColor.CULTURED_MILK.toItemColor(), GrowthcraftMilkFluids.CULTURED_MILK.bucket.get());
        ItemRendererUtils.registerItem(event, Reference.FluidColor.KUMIS.toItemColor(), GrowthcraftMilkFluids.KUMIS.bucket.get());
        ItemRendererUtils.registerItem(event, Reference.FluidColor.MILK.toItemColor(), GrowthcraftMilkFluids.MILK.bucket.get());
        ItemRendererUtils.registerItem(event, Reference.FluidColor.RENNET.toItemColor(), GrowthcraftMilkFluids.RENNET.bucket.get());
        ItemRendererUtils.registerItem(event, Reference.FluidColor.SKIM_MILK.toItemColor(), GrowthcraftMilkFluids.SKIM_MILK.bucket.get());
        ItemRendererUtils.registerItem(event, Reference.FluidColor.WHEY.toItemColor(), GrowthcraftMilkFluids.WHEY.bucket.get());
    }

    private GrowthcraftMilkItemRenderers() {
        /* Prevent automatic public constructor */
    }
}
