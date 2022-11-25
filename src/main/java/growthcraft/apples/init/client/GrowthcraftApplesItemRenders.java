package growthcraft.apples.init.client;

import growthcraft.apples.init.GrowthcraftApplesItems;
import growthcraft.lib.client.GrowthcraftItemColor;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.registries.RegistryObject;

public class GrowthcraftApplesItemRenders {
    public static void registerItemRenders(ColorHandlerEvent.Item event) {
        GrowthcraftItemColor itemColor = new GrowthcraftItemColor();

        for (RegistryObject<? extends Item> bucket : GrowthcraftApplesItems.BUCKETS) {
            event.getItemColors().register(itemColor, bucket::get);
        }
    }


}
