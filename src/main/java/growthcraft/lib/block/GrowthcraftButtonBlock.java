package growthcraft.lib.block;

import net.minecraft.world.level.block.ButtonBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.material.Material;

public class GrowthcraftButtonBlock extends ButtonBlock {

    public GrowthcraftButtonBlock() {
        super(getInitProperties(Material.DECORATION), BlockSetType.OAK, 30, true);
    }

    private static Properties getInitProperties(Material material) {
        Properties properties = Properties.of(material);
        properties.strength(0.5F);
        properties.noCollission();
        properties.sound(SoundType.WOOD);
        return properties;
    }
}
