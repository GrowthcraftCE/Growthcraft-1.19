package growthcraft.cellar.init.client;

import growthcraft.cellar.init.GrowthcraftCellarItems;
import growthcraft.lib.client.GrowthcraftItemColor;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.registries.RegistryObject;

public class GrowthcraftCellarItemRenderers {

    public static void registerItemRenders(ColorHandlerEvent.Item event) {
        GrowthcraftItemColor itemColor = new GrowthcraftItemColor();

        for(RegistryObject<? extends Item> bucket : GrowthcraftCellarItems.BUCKETS) {
            event.getItemColors().register(itemColor, bucket::get);
        }

        for(RegistryObject<? extends Item> grain : GrowthcraftCellarItems.GRAINS ) {
            event.getItemColors().register(itemColor, grain::get);
        }

        for(RegistryObject<? extends Item> potion : GrowthcraftCellarItems.POTIONS ) {
            event.getItemColors().register(itemColor, potion::get);
        }
    }

    private GrowthcraftCellarItemRenderers() {
        /* Prevent automatic public constructor */
    }

}
