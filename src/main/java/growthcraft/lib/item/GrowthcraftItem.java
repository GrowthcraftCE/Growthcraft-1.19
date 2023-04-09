package growthcraft.lib.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.awt.*;

public class GrowthcraftItem extends Item {

    private int color;

    public GrowthcraftItem() {
        this(64);
        this.color = 0x0;
    }

    public GrowthcraftItem(int maxStackSize) {
        super(getInitProperties(maxStackSize));
        this.color = 0x0;
    }

    public GrowthcraftItem(int maxStackSize, Color color) {
        this(maxStackSize);
        this.color = color.getRGB();
    }

    private static Properties getInitProperties(int maxStackSize) {
        Properties properties = new Properties();
        properties.stacksTo(maxStackSize);
        return properties;
    }

    public int getColor(ItemStack stack, int layer) {
        return this.getColor(layer);
    }

    public int getColor(int layer) {
        return layer == 0 ? this.color : 0xFFFFFF;
    }
}
