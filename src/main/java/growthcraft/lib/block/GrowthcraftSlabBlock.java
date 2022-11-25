package growthcraft.lib.block;

import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.material.Material;

public class GrowthcraftSlabBlock extends SlabBlock {
    public GrowthcraftSlabBlock() {
        this(getInitProperties(Material.WOOD));
    }

    public GrowthcraftSlabBlock(Material material) {
        this(getInitProperties(material));
    }

    public GrowthcraftSlabBlock(Properties properties) {
        super(properties);
    }

    private static Properties getInitProperties(Material material) {
        Properties properties = Properties.of(material);
        properties.strength(2.0F, 3.0F);
        properties.noOcclusion();
        return properties;
    }
}
