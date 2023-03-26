package growthcraft.lib.block;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.ButtonBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;

public class GrowthcraftButtonBlock extends ButtonBlock {

    public GrowthcraftButtonBlock() {
        super(getInitProperties(Material.DECORATION), 30, true, SoundEvents.WOODEN_BUTTON_CLICK_OFF, SoundEvents.WOODEN_BUTTON_CLICK_ON);
    }

    private static Properties getInitProperties(Material material) {
        Properties properties = Properties.of(material);
        properties.strength(0.5F);
        properties.noCollission();
        properties.sound(SoundType.WOOD);
        return properties;
    }
}
