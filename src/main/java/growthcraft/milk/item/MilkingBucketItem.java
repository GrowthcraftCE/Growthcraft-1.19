package growthcraft.milk.item;

import growthcraft.core.shared.Reference;
import growthcraft.milk.GrowthcraftMilk;
import growthcraft.milk.init.GrowthcraftMilkItems;
import growthcraft.milk.init.GrowthcraftMilkTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class MilkingBucketItem extends Item {

    public MilkingBucketItem() {
        this(getInitProperties());
    }

    public MilkingBucketItem(Properties properties) {
        super(properties);
    }

    private static Properties getInitProperties() {
        Properties properties = new Properties();
        properties.tab(Reference.CREATIVE_TAB);
        properties.stacksTo(16);
        return properties;
    }

    @Override
    public InteractionResult interactLivingEntity(ItemStack itemStack, Player player, LivingEntity livingEntity, InteractionHand hand) {
        if (livingEntity.level.isClientSide) return InteractionResult.PASS;

        if (livingEntity.getType().is(GrowthcraftMilkTags.EntityTypes.MILKABLE)) {
            player.getInventory().add(new ItemStack(GrowthcraftMilkItems.BUCKET_MILK.get()));
            player.getItemInHand(hand).shrink(1);
        }

        return super.interactLivingEntity(itemStack, player, livingEntity, hand);
    }

    @Override
    public ItemStack getContainerItem(ItemStack itemStack) {
        GrowthcraftMilk.LOGGER.error(itemStack.getItem().toString());
        return super.getContainerItem(itemStack);
    }

    @Override
    public boolean hasContainerItem(ItemStack stack) {
        return true;
    }
}
