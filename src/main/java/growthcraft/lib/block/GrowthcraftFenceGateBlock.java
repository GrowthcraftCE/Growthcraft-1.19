package growthcraft.lib.block;

import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;

public class GrowthcraftFenceGateBlock extends FenceGateBlock {
    public GrowthcraftFenceGateBlock() {
        this(getInitProperties(Material.WOOD));
    }

    public GrowthcraftFenceGateBlock(Material material) {
        this(getInitProperties(material));
    }

    public GrowthcraftFenceGateBlock(Properties properties) {
        super(properties, WoodType.OAK);
    }

    private static Properties getInitProperties(Material material) {
        Properties properties = Properties.of(material, MaterialColor.WOOD);
        properties.sound(SoundType.WOOD);
        properties.strength(2.0F, 3.0F);
        return properties;
    }
}
