package growthcraft.lib.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;

public class GrowthcraftBlock extends Block {

    public GrowthcraftBlock() {
        this(getInitProperties(Material.WOOD, SoundType.WOOD));
    }

    public GrowthcraftBlock(Material material) {
        this(getInitProperties(material, SoundType.WOOD));
    }

    public GrowthcraftBlock(Block block) {
        this(getInitProperties(block, SoundType.WOOD));
    }

    public GrowthcraftBlock(Material material, SoundType soundType) {
        this(getInitProperties(material, soundType));
    }

    public GrowthcraftBlock(Block block, SoundType soundType) {
        this(getInitProperties(block, soundType));
    }

    public GrowthcraftBlock(Properties properties) {
        super(properties);
    }

    private static Properties getInitProperties(Material material, SoundType soundType) {
        Properties properties = Properties.of(material);
        properties.sound(SoundType.WOOD);
        return properties;
    }

    private static Properties getInitProperties(Block block, SoundType soundType) {
        Properties properties = Properties.copy(block);
        properties.sound(SoundType.WOOD);
        return properties;
    }
}
