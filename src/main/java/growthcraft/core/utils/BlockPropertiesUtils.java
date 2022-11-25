package growthcraft.core.utils;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

public class  BlockPropertiesUtils {

    public static BlockBehaviour.Properties getInitProperties(String blockType, Block block) {
        BlockBehaviour.Properties properties = BlockBehaviour.Properties.copy(block);

        switch (blockType) {
            case "rope_block" -> {
                properties.sound(SoundType.WOOL);
                properties.strength(0.3F, 0.3F);
                properties.noOcclusion();
                properties.isValidSpawn(BlockPropertiesUtils::never);
                properties.isRedstoneConductor(BlockPropertiesUtils::never);
                properties.isSuffocating(BlockPropertiesUtils::never);
                properties.isViewBlocking(BlockPropertiesUtils::never);
            }
            default -> {
                // Do nothing.
            }
        }

        return properties;

    }

    public static Boolean never(BlockState state, BlockGetter world, BlockPos pos, EntityType<?> entity) {
        return BlockPropertiesUtils.never(state, world, pos);
    }

    public static Boolean always(BlockState state, BlockGetter world, BlockPos pos, EntityType<?> entity) {
        return BlockPropertiesUtils.always(state, world, pos);
    }

    public static boolean always(BlockState state, BlockGetter world, BlockPos pos) {
        return true;
    }

    public static boolean never(BlockState state, BlockGetter world, BlockPos pos) {
        return false;
    }
}
