package growthcraft.apiary.init.client;

import growthcraft.lib.client.GrowthcraftItemColor;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;

public class GrowthcraftApiaryItemRenders {
    public static void registerItemRenders(RegisterColorHandlersEvent.Item event) {
        GrowthcraftItemColor itemColor = new GrowthcraftItemColor();

        //for (RegistryObject<? extends Item> bucket : GrowthcraftApiaryItems.BUCKETS) {
        //    event.getItemColors().register(itemColor, bucket::get);
        //}
    }

}
