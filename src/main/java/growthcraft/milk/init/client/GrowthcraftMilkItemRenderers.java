package growthcraft.milk.init.client;

import growthcraft.lib.client.GrowthcraftItemColor;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;

public class GrowthcraftMilkItemRenderers {
    public static void registerItemRenders(RegisterColorHandlersEvent.Item event) {
        GrowthcraftItemColor itemColor = new GrowthcraftItemColor();

        //for (RegistryObject<? extends Item> bucket : GrowthcraftMilkItems.BUCKETS) {
        //    event.getItemColors().register(itemColor, bucket::get);
       // }
    }

    private GrowthcraftMilkItemRenderers() {
        /* Prevent automatic public constructor */
    }
}
