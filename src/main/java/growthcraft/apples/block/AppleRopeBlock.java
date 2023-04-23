package growthcraft.apples.block;

import growthcraft.apples.init.GrowthcraftApplesBlockEntities;
import growthcraft.core.block.RopeBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class AppleRopeBlock extends RopeBlock {
    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return GrowthcraftApplesBlockEntities.APPLE_WOOD_ROPE_BLOCK_ENTITY.get().create(blockPos, blockState);
    }
}
