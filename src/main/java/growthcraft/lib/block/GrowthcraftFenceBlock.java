package growthcraft.lib.block;

import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;

public class GrowthcraftFenceBlock extends FenceBlock {
    public GrowthcraftFenceBlock() {
        this(getInitProperties(Material.WOOD));
    }

    public GrowthcraftFenceBlock(Material material) {
        this(getInitProperties(material));
    }

    public GrowthcraftFenceBlock(Properties properties) {
        super(properties);
    }

    private static Properties getInitProperties(Material material) {
        Properties properties = Properties.of(material, MaterialColor.WOOD);
        properties.sound(SoundType.WOOD);
        properties.strength(2.0F, 3.0F);
        return properties;
    }
}
