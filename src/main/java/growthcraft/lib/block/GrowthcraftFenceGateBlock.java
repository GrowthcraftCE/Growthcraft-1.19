package growthcraft.lib.block;

import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;

public class GrowthcraftFenceGateBlock extends FenceGateBlock {
    /**
     * Basic wooden Fence Gate
     */
    public GrowthcraftFenceGateBlock() {
        this(getInitProperties(Material.WOOD));
    }

    /**
     * Fence Gate with custom material.
     *
     * @param material Material for default block properties.
     */
    public GrowthcraftFenceGateBlock(Material material) {
        this(getInitProperties(material));
    }

    /**
     * Fence Gate with custom block properties
     *
     * @param properties Block properties.
     */
    public GrowthcraftFenceGateBlock(Properties properties) {
        super(properties);
    }

    /**
     * @param unlocalizedName
     * @deprecated Use {@link GrowthcraftFenceGateBlock#GrowthcraftFenceGateBlock()} instead.
     */
    @Deprecated
    public GrowthcraftFenceGateBlock(String unlocalizedName) {
        super(getInitProperties(Material.WOOD));
    }

    private static Properties getInitProperties(Material material) {
        Properties properties = Properties.of(material, MaterialColor.WOOD);
        properties.sound(SoundType.WOOD);
        properties.strength(2.0F, 3.0F);
        return properties;
    }
}
