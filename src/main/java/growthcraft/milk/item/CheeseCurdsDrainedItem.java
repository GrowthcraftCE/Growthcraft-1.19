package growthcraft.milk.item;

import growthcraft.lib.item.GrowthcraftItem;

import java.awt.*;

public class CheeseCurdsDrainedItem extends GrowthcraftItem {

    private final int color;

    public CheeseCurdsDrainedItem(Color color) {
        super();
        this.color = color.getRGB();
    }

    public int getColor() {
        return this.color;
    }

    public int getColor(int i) {
        return i == 0 ? this.color : 0xFFFFFF;
    }
}
