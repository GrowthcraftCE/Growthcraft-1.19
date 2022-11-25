package growthcraft.lib.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;

public class GrowthcraftPlankBlock extends Block {
    public GrowthcraftPlankBlock() {
        this(getInitProperties(Material.WOOD));
    }

    public GrowthcraftPlankBlock(Material material) {
        this(getInitProperties(material));
    }

    public GrowthcraftPlankBlock(Properties properties) {
        super(properties);
    }

    private static Properties getInitProperties(Material material) {
        Properties properties = Properties.of(material);
        properties.strength(2.0F, 3.0F);
        properties.sound(SoundType.WOOD);
        return properties;
    }
}
