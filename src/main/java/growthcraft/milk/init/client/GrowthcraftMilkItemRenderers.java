package growthcraft.milk.init.client;

import growthcraft.lib.client.ItemRendererUtils;
import growthcraft.milk.init.GrowthcraftMilkBlocks;
import growthcraft.milk.init.GrowthcraftMilkFluids;
import growthcraft.milk.init.GrowthcraftMilkItems;
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

        ItemRendererUtils.registerItem(event, Reference.ItemColor.APPENZELLER_CHEESE.toItemColor(), GrowthcraftMilkItems.APPENZELLER_CHEESE_CURDS_DRAINED.get());
        ItemRendererUtils.registerItem(event, Reference.ItemColor.ASIAGO_CHEESE.toItemColor(), GrowthcraftMilkItems.ASIAGO_CHEESE_CURDS_DRAINED.get());
        ItemRendererUtils.registerItem(event, Reference.ItemColor.CASU_MARZU_CHEESE.toItemColor(), GrowthcraftMilkItems.CASU_MARZU_CHEESE_CURDS_DRAINED.get());
        ItemRendererUtils.registerItem(event, Reference.ItemColor.CHEDDAR_CHEESE.toItemColor(), GrowthcraftMilkItems.CHEDDAR_CHEESE_CURDS_DRAINED.get());
        ItemRendererUtils.registerItem(event, Reference.ItemColor.EMMENTALER_CHEESE.toItemColor(), GrowthcraftMilkItems.EMMENTALER_CHEESE_CURDS_DRAINED.get());
        ItemRendererUtils.registerItem(event, Reference.ItemColor.GORGONZOLA_CHEESE.toItemColor(), GrowthcraftMilkItems.GORGONZOLA_CHEESE_CURDS_DRAINED.get());
        ItemRendererUtils.registerItem(event, Reference.ItemColor.GOUDA_CHEESE.toItemColor(), GrowthcraftMilkItems.GOUDA_CHEESE_CURDS_DRAINED.get());
        ItemRendererUtils.registerItem(event, Reference.ItemColor.MONTEREY_CHEESE.toItemColor(), GrowthcraftMilkItems.MONTEREY_CHEESE_CURDS_DRAINED.get());
        ItemRendererUtils.registerItem(event, Reference.ItemColor.PARMESAN_CHEESE.toItemColor(), GrowthcraftMilkItems.PARMESAN_CHEESE_CURDS_DRAINED.get());
        ItemRendererUtils.registerItem(event, Reference.ItemColor.PROVOLONE_CHEESE.toItemColor(), GrowthcraftMilkItems.PROVOLONE_CHEESE_CURDS_DRAINED.get());
        ItemRendererUtils.registerItem(event, Reference.ItemColor.RICOTTA_CHEESE.toItemColor(), GrowthcraftMilkItems.RICOTTA_CHEESE_CURDS_DRAINED.get());

        ItemRendererUtils.registerItem(event, Reference.ItemColor.APPENZELLER_CHEESE.toItemColor(), GrowthcraftMilkBlocks.APPENZELLER_CHEESE_CURDS.get().asItem());
        ItemRendererUtils.registerItem(event, Reference.ItemColor.ASIAGO_CHEESE.toItemColor(), GrowthcraftMilkBlocks.ASIAGO_CHEESE_CURDS.get().asItem());
        ItemRendererUtils.registerItem(event, Reference.ItemColor.CHEDDAR_CHEESE.toItemColor(), GrowthcraftMilkBlocks.CHEDDAR_CHEESE_CURDS.get().asItem());
        ItemRendererUtils.registerItem(event, Reference.ItemColor.EMMENTALER_CHEESE.toItemColor(), GrowthcraftMilkBlocks.EMMENTALER_CHEESE_CURDS.get().asItem());
        ItemRendererUtils.registerItem(event, Reference.ItemColor.GORGONZOLA_CHEESE.toItemColor(), GrowthcraftMilkBlocks.GORGONZOLA_CHEESE_CURDS.get().asItem());
        ItemRendererUtils.registerItem(event, Reference.ItemColor.GOUDA_CHEESE.toItemColor(), GrowthcraftMilkBlocks.GOUDA_CHEESE_CURDS.get().asItem());
        ItemRendererUtils.registerItem(event, Reference.ItemColor.CASU_MARZU_CHEESE.toItemColor(), GrowthcraftMilkBlocks.CASU_MARZU_CHEESE_CURDS.get().asItem());
        ItemRendererUtils.registerItem(event, Reference.ItemColor.MONTEREY_CHEESE.toItemColor(), GrowthcraftMilkBlocks.MONTEREY_CHEESE_CURDS.get().asItem());
        ItemRendererUtils.registerItem(event, Reference.ItemColor.PARMESAN_CHEESE.toItemColor(), GrowthcraftMilkBlocks.PARMESAN_CHEESE_CURDS.get().asItem());
        ItemRendererUtils.registerItem(event, Reference.ItemColor.PROVOLONE_CHEESE.toItemColor(), GrowthcraftMilkBlocks.PROVOLONE_CHEESE_CURDS.get().asItem());
        ItemRendererUtils.registerItem(event, Reference.ItemColor.RICOTTA_CHEESE.toItemColor(), GrowthcraftMilkBlocks.RICOTTA_CHEESE_CURDS.get().asItem());
    }

    private GrowthcraftMilkItemRenderers() {
        /* Prevent automatic public constructor */
    }
}
