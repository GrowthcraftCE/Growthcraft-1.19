package growthcraft.lib.item;

import growthcraft.core.shared.Reference;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class GrowthcraftFoodItem extends Item {

    private boolean hasBowl = false;

    public GrowthcraftFoodItem() {
        this(1, 0.2F, 64);
    }

    public GrowthcraftFoodItem(int maxStackSize) {
        this(1, 0.2F, maxStackSize);
    }

    public GrowthcraftFoodItem(int hunger, float saturation, int maxStackSize) {
        super(getInitProperties(hunger, saturation, maxStackSize));
    }

    public GrowthcraftFoodItem(int hunger, float saturation, int maxStackSize, boolean hasBowl) {
        this(hunger, saturation, maxStackSize);
        this.hasBowl = hasBowl;
    }

    @Override
    public boolean hasContainerItem(ItemStack stack) {
        return this.hasBowl;
    }

    @Override
    public ItemStack getContainerItem(ItemStack itemStack) {
        return this.hasBowl ? new ItemStack(Items.BOWL) : null;
    }

    private static Properties getInitProperties(int hunger, float saturation, int maxStackSize) {
        Properties properties = new Properties();
        properties.tab(Reference.CREATIVE_TAB);
        properties.stacksTo(maxStackSize);
        properties.food(new FoodProperties.Builder().nutrition(hunger).saturationMod(saturation).build());
        return properties;
    }




}
