package growthcraft.cellar.item;

import growthcraft.lib.item.GrowthcraftItem;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.awt.*;
import java.util.List;

public class CellarPotionItem extends GrowthcraftItem {

    private static final int DRINK_DURATION = 32;

    public CellarPotionItem() {
        super(16);
    }

    // TODO[8]: Review custom potions for 1.19
    @Override
    public @NotNull ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity livingEntity) {
        Player player = livingEntity instanceof Player ? (Player) livingEntity : null;
        if(player instanceof ServerPlayer) {
            CriteriaTriggers.CONSUME_ITEM.trigger((ServerPlayer) player, stack);
        }

        if(!level.isClientSide) {
            CompoundTag compoundTag = stack.getTag();
            if(compoundTag != null && compoundTag.contains("CustomPotionEffects", 9)) {
                ListTag customPotionEffects = compoundTag.getList("CustomPotionEffects", 10);

                for(int i = 0; i < customPotionEffects.size(); i++) {
                    CompoundTag tag = customPotionEffects.getCompound(i);
                    MobEffectInstance effect = MobEffectInstance.load(tag);
                    player.addEffect(effect);
                }
            }
        }

        if(player != null) {
            player.awardStat(Stats.ITEM_USED.get(this));
            if(!player.getAbilities().instabuild) {
                stack.shrink(1);
            }
        }

        if(player == null || !player.getAbilities().instabuild) {
            if(stack.isEmpty()) {
                return new ItemStack(Items.GLASS_BOTTLE);
            }
            if(player != null) {
                player.getInventory().add(new ItemStack(Items.GLASS_BOTTLE));
            }
        }

        livingEntity.gameEvent(GameEvent.DRINK);
        return stack;
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return 32;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.DRINK;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        return ItemUtils.startUsingInstantly(level, player, hand);
    }

    @Override
    public @NotNull String getDescriptionId(ItemStack stack) {
        return PotionUtils.getPotion(stack).getName(this.getDescriptionId() + ".effect.");
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag tooltipFlag) {
        PotionUtils.addPotionTooltip(stack, tooltip, 1.0F);
    }

    @Override
    public boolean isFoil(ItemStack stack) {
        return super.isFoil(stack) || !PotionUtils.getMobEffects(stack).isEmpty();
    }

    @Override
    public int getColor(int layer) {
        return layer == 0 ? new Color(0xCFA26F).getRGB() : 0xFFFFFF;
    }
}
