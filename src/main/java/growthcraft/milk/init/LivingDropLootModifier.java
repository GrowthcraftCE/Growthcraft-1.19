package growthcraft.milk.init;

import growthcraft.milk.init.config.GrowthcraftMilkConfig;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.security.SecureRandom;
import java.util.Collection;

public class LivingDropLootModifier {
    @SubscribeEvent
    public void onLivingDropsEvent(LivingDropsEvent event) {
        LivingEntity entity = event.getEntity();
        Level level = entity.getLevel();

        if(!level.getGameRules().getBoolean(GameRules.RULE_DOMOBLOOT)) {
            return;
        }

        if(entity instanceof Cow) {
            onCowLivingDrops(event, entity);
        }

    }

    private void onCowLivingDrops(LivingDropsEvent event, LivingEntity cow) {
        Collection<ItemEntity> drops = event.getDrops();
        int chance = GrowthcraftMilkConfig.getStomachLootChance();

        if(GrowthcraftMilkConfig.isStomachLootingEnabled() && new SecureRandom().nextInt(100) <= chance) {
            drops.add(
                    new ItemEntity(
                        event.getEntity().getLevel(),
                        cow.getX(), cow.getY(), cow.getZ(),
                        new ItemStack(GrowthcraftMilkItems.STOMACH.get())
                    )
            );
        }
    }
}
