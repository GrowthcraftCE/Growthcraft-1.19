package growthcraft.lib.block;

import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;

public class GrowthcraftDoorBlock extends DoorBlock {
    public GrowthcraftDoorBlock() {
        this(getInitProperties(Material.WOOD));
    }

    public GrowthcraftDoorBlock(Material material) {
        this(getInitProperties(material));
    }

    public GrowthcraftDoorBlock(Properties properties) {
        super(properties, BlockSetType.OAK);
    }

    private static Properties getInitProperties(Material material) {
        Properties properties = Properties.of(material, MaterialColor.WOOD);
        properties.sound(SoundType.WOOD);
        properties.strength(3.0F);
        properties.noOcclusion();
        return properties;
    }
}
