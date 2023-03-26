package growthcraft.lib.item;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;

public class GrowthcraftFoodItem extends Item {

    public GrowthcraftFoodItem() {
        this(1, 0.2F, 64);
    }

    public GrowthcraftFoodItem(int maxStackSize) {
        this(1, 0.2F, maxStackSize);
    }

    public GrowthcraftFoodItem(int hunger, float saturation, int maxStackSize) {
        super(getInitProperties(hunger, saturation, maxStackSize));
    }

    private static Properties getInitProperties(int hunger, float saturation, int maxStackSize) {
        Properties properties = new Properties();
        properties.stacksTo(maxStackSize);
        properties.food(new FoodProperties.Builder().nutrition(hunger).saturationMod(saturation).build());
        return properties;
    }




}
