package growthcraft.lib.block;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.TrapDoorBlock;
import net.minecraft.world.level.material.Material;

public class GrowthcraftTrapDoorBlock extends TrapDoorBlock {
    public GrowthcraftTrapDoorBlock() {
        this(getInitProperties(Material.WOOD));
    }

    public GrowthcraftTrapDoorBlock(Material material) {
        this(getInitProperties(material));
    }

    public GrowthcraftTrapDoorBlock(Properties properties) {
        super(properties, SoundEvents.WOODEN_TRAPDOOR_CLOSE, SoundEvents.WOODEN_TRAPDOOR_OPEN);
    }

    private static Properties getInitProperties(Material material) {
        Properties properties = Properties.of(material);
        properties.strength(2.0F, 3.0F);
        properties.sound(SoundType.WOOD);
        properties.noOcclusion();
        return properties;
    }
}
