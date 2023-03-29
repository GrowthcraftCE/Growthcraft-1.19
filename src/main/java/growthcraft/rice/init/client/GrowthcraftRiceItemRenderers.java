package growthcraft.rice.init.client;

import growthcraft.apiary.shared.Reference;
import growthcraft.lib.client.GrowthcraftItemColor;
import growthcraft.lib.client.ItemRendererUtils;
import growthcraft.rice.init.GrowthcraftRiceFluids;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;

public class GrowthcraftRiceItemRenderers {

    public static void registerItemRenders(RegisterColorHandlersEvent.Item event) {
        GrowthcraftItemColor itemColor = new GrowthcraftItemColor();

        ItemRendererUtils.registerItem(event, Reference.FluidColor.HONEY.toItemColor(), GrowthcraftRiceFluids.RICE_WATER.bucket.get());
        ItemRendererUtils.registerItem(event, Reference.FluidColor.HONEY.toItemColor(), GrowthcraftRiceFluids.RICE_WINE.bucket.get());
        ItemRendererUtils.registerItem(event, Reference.FluidColor.HONEY.toItemColor(), GrowthcraftRiceFluids.SAKE.bucket.get());

    }



    private GrowthcraftRiceItemRenderers() {
        /* Prevent automatic public constructor */
    }

}
