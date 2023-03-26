package growthcraft.rice.init.client;

import growthcraft.lib.client.GrowthcraftItemColor;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;

public class GrowthcraftRiceItemRenderers {

    public static void registerItemRenders(RegisterColorHandlersEvent.Item event) {
        GrowthcraftItemColor itemColor = new GrowthcraftItemColor();

        //for (RegistryObject<? extends Item> bucket : GrowthcraftRiceItems.BUCKETS) {
        //    event.getItemColors().register(itemColor, bucket::get);
        //}
    }

    private GrowthcraftRiceItemRenderers() {
        /* Prevent automatic public constructor */
    }

}
