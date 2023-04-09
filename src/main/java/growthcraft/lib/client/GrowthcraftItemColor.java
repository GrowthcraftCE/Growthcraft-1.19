package growthcraft.lib.client;

import growthcraft.cellar.item.CellarPotionItem;
import growthcraft.lib.item.GrowthcraftItem;
import net.minecraft.client.color.item.ItemColor;
import net.minecraft.world.item.ItemStack;

public class GrowthcraftItemColor implements ItemColor {

    public int getColor(ItemStack itemStack, int layer) {

        if (itemStack.getItem() instanceof GrowthcraftItem growthcraftItem) {
            return growthcraftItem.getColor(layer);
        }
        if (itemStack.getItem() instanceof CellarPotionItem cellarPotionItem) {
            return cellarPotionItem.getColor(layer);
        }
        return 0;
    }
}
