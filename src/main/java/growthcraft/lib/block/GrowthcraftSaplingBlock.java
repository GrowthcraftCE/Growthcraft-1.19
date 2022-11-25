package growthcraft.lib.block;

import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.material.Material;

public class GrowthcraftSaplingBlock extends SaplingBlock {
    private final AbstractTreeGrower tree;

    public GrowthcraftSaplingBlock(AbstractTreeGrower tree) {
        this(tree, getInitProperties());
    }

    public GrowthcraftSaplingBlock(AbstractTreeGrower tree, Properties properties) {
        super(tree, properties);
        this.tree = tree;
    }

    private static Properties getInitProperties() {
        Properties properties = Properties.of(Material.PLANT);
        properties.noCollission();
        properties.randomTicks();
        properties.sound(SoundType.GRASS);
        properties.noOcclusion();
        properties.instabreak();
        return properties;
    }
}
