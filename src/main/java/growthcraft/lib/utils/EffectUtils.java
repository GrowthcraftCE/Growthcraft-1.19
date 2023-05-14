package growthcraft.lib.utils;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class EffectUtils {

    public static List<MobEffectInstance> getEffectsList(ItemStack stack) {
        List<MobEffectInstance> effects = new ArrayList<>();
        ListTag customPotionEffects = getEffects(stack);

        for (int i = 0; i < customPotionEffects.size(); ++i) {
            CompoundTag compoundPotionEffect = customPotionEffects.getCompound(i);
            MobEffectInstance mobEffectInstance = MobEffectInstance.load(compoundPotionEffect);
            effects.add(mobEffectInstance);
        }

        return effects;
    }

    public static ListTag getEffects(ItemStack stack) {
        CompoundTag compound = stack.getTag();
        return compound.getList("CustomPotionEffects", 9);
    }

    public static void addEffect(ItemStack stack, MobEffect effect, int duration, int level) {
        boolean flag = true;

        CompoundTag compoundNBT = stack.getOrCreateTag();
        ListTag mobEffects = getEffects(stack);

        MobEffectInstance effectInstance = new MobEffectInstance(
                effect, duration, level
        );

        mobEffects.add(effectInstance.save(new CompoundTag()));
        compoundNBT.put("CustomPotionEffects", mobEffects);

    }
}
