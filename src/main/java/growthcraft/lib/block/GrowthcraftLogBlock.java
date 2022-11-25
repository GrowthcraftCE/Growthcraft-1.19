package growthcraft.lib.block;

import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;

public class GrowthcraftLogBlock extends RotatedPillarBlock {
    public GrowthcraftLogBlock() {
        this(getInitProperties(Material.WOOD));
    }

    public GrowthcraftLogBlock(Properties properties) {
        super(properties);
    }

    private static Properties getInitProperties(Material material) {
        Properties properties = Properties.of(material);
        properties.strength(2.0F);
        properties.sound(SoundType.WOOD);
        return properties;
    }
}
