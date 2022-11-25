package growthcraft.lib.block;

import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;

public class GrowthcraftLeavesBlock extends LeavesBlock {
    public GrowthcraftLeavesBlock() {
        this(getInitProperties());
    }

    public GrowthcraftLeavesBlock(Properties properties) {
        super(properties);
    }

    private static Properties getInitProperties() {
        Properties properties = Properties.of(Material.LEAVES);
        properties.randomTicks();
        properties.strength(0.2F, 0.2F);
        properties.sound(SoundType.CROP);
        properties.noOcclusion();
        return properties;
    }
}
