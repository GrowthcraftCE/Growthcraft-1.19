package growthcraft.apples.init.client;

import growthcraft.lib.client.GrowthcraftItemColor;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;

public class GrowthcraftApplesItemRenders {
    public static void registerItemRenders(RegisterColorHandlersEvent.Item event) {
        GrowthcraftItemColor itemColor = new GrowthcraftItemColor();

        //for (RegistryObject<? extends Item> bucket : GrowthcraftApplesItems.BUCKETS) {
        //    event.getItemColors().register(itemColor, bucket::get);
        //}
    }


}
