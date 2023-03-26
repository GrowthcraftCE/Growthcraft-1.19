package growthcraft.lib.block;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.PressurePlateBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;

public class GrowthcraftPressurePlateBlock extends PressurePlateBlock {
    public GrowthcraftPressurePlateBlock() {
        this(Sensitivity.EVERYTHING, getInitProperties(Material.WOOD));
    }

    public GrowthcraftPressurePlateBlock(Material material) {
        this(Sensitivity.EVERYTHING, getInitProperties(material));
    }

    public GrowthcraftPressurePlateBlock(Sensitivity sensitivity, Properties properties) {
        super(sensitivity, properties, SoundEvents.WOODEN_PRESSURE_PLATE_CLICK_OFF, SoundEvents.WOODEN_PRESSURE_PLATE_CLICK_ON);
    }

    private static Properties getInitProperties(Material material) {
        Properties properties = Properties.of(material);
        properties.strength(1.5F);
        properties.sound(SoundType.WOOD);
        return properties;
    }
}
