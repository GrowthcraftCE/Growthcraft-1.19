package growthcraft.lib.item;

import growthcraft.core.shared.Reference;
import net.minecraft.client.color.item.ItemColor;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.material.Fluid;

import java.awt.*;
import java.util.function.Supplier;

public class GrowthcraftBucketItem extends BucketItem implements ItemColor {
    private final int color;

    public GrowthcraftBucketItem(Supplier<? extends Fluid> supplier, Color color) {
        super(supplier, new Item.Properties().tab(Reference.CREATIVE_TAB).stacksTo(1));
        this.color = color.getRGB();
    }

    @Override
    public boolean hasContainerItem(ItemStack stack) {
        return true;
    }

    @Override
    public ItemStack getContainerItem(ItemStack itemStack) {
        return new ItemStack(Items.BUCKET);
    }

    @Override
    public int getColor(ItemStack stack, int layer) {
        return layer == 0 ? this.color : 0;
    }

    public int getColor(int layer) {
        return layer == 0 ? this.color : 0xFFFFFF;
    }
}
