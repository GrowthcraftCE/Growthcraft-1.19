package growthcraft.rice.init.client;

import growthcraft.lib.client.GrowthcraftItemColor;
import growthcraft.rice.init.GrowthcraftRiceItems;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.registries.RegistryObject;

public class GrowthcraftRiceItemRenderers {

    public static void registerItemRenders(RegisterColorHandlersEvent.Item event) {
        GrowthcraftItemColor itemColor = new GrowthcraftItemColor();

        for (RegistryObject<? extends Item> bucket : GrowthcraftRiceItems.BUCKETS) {
            event.getItemColors().register(itemColor, bucket::get);
        }
    }

    private GrowthcraftRiceItemRenderers() {
        /* Prevent automatic public constructor */
    }

}
