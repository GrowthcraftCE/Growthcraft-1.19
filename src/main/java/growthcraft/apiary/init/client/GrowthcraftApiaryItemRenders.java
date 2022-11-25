package growthcraft.apiary.init.client;

import growthcraft.apiary.init.GrowthcraftApiaryItems;
import growthcraft.lib.client.GrowthcraftItemColor;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.registries.RegistryObject;

public class GrowthcraftApiaryItemRenders {
    public static void registerItemRenders(ColorHandlerEvent.Item event) {
        GrowthcraftItemColor itemColor = new GrowthcraftItemColor();

        for (RegistryObject<? extends Item> bucket : GrowthcraftApiaryItems.BUCKETS) {
            event.getItemColors().register(itemColor, bucket::get);
        }
    }


}
