package growthcraft.bamboo.block;

import growthcraft.bamboo.init.GrowthcraftBambooBlockEntities;
import growthcraft.core.block.RopeBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class BambooRopeBlock extends RopeBlock {
    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return GrowthcraftBambooBlockEntities.BAMBOO_FENCE_ROPE_BLOCK_ENTITY.get().create(blockPos, blockState);
    }
}
