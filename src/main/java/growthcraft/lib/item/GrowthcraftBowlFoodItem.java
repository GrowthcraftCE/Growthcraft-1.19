package growthcraft.lib.item;

import growthcraft.core.init.GrowthcraftCreativeModeTabs;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BowlFoodItem;

public class GrowthcraftBowlFoodItem extends BowlFoodItem {

    public GrowthcraftBowlFoodItem() {
        this(1, 0.2F, 64);
    }

    public GrowthcraftBowlFoodItem(int maxStackSize) {
        this(1, 0.2F, maxStackSize);
    }

    public GrowthcraftBowlFoodItem(int hunger, float saturation, int maxStackSize) {
        super(getInitProperties(hunger, saturation, maxStackSize));
    }

    private static Properties getInitProperties(int hunger, float saturation, int maxStackSize) {
        Properties properties = new Properties().tab(GrowthcraftCreativeModeTabs.GROWTHCRAFT_CREATIVE_TAB);
        properties.tab(GrowthcraftCreativeModeTabs.GROWTHCRAFT_CREATIVE_TAB);
        properties.stacksTo(maxStackSize);
        properties.food(new FoodProperties.Builder().nutrition(hunger).saturationMod(saturation).build());
        return properties;
    }
}
