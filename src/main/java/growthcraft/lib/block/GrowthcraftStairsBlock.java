package growthcraft.lib.block;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;

import java.util.function.Supplier;

public class GrowthcraftStairsBlock extends StairBlock {

    public GrowthcraftStairsBlock() {
        this(
                Blocks.OAK_STAIRS.defaultBlockState(),
                getInitProperties(Material.WOOD)
        );
    }

    @SuppressWarnings("deprecation")
    public GrowthcraftStairsBlock(BlockState state, Properties properties) {
        super(state, properties);
    }

    public GrowthcraftStairsBlock(Supplier<BlockState> state, Properties properties) {
        super(state, properties);
    }

    private static Properties getInitProperties(Material material) {
        Properties properties = Properties.of(material);
        properties.strength(1.0F);
        properties.sound(SoundType.STONE);
        return properties;
    }
}
