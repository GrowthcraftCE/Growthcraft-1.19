package growthcraft.lib.block;

import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.WoodButtonBlock;
import net.minecraft.world.level.material.Material;

public class GrowthcraftButtonBlock extends WoodButtonBlock {

    public GrowthcraftButtonBlock() {
        super(getInitProperties(Material.WOOD));
    }

    public GrowthcraftButtonBlock(String unlocalizedName) {
        super(getInitProperties(Material.WOOD));
    }

    protected GrowthcraftButtonBlock(String unlocalizedName, Properties properties) {
        super(properties);
    }

    private static Properties getInitProperties(Material material) {
        Properties properties = Properties.of(material);
        properties.strength(0.5F);
        properties.noCollission();
        properties.sound(SoundType.WOOD);
        return properties;
    }
}
