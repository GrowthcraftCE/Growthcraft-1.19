package growthcraft.bamboo.block;

import growthcraft.apiary.block.BeeBoxBlock;
import growthcraft.bamboo.init.GrowthcraftBambooBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class BambooBeeBoxBlock extends BeeBoxBlock {

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return GrowthcraftBambooBlockEntities.BAMBOO_BEE_BOX_BLOCK_ENTITY.get().create(pos, state);
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> blockEntityType) {
        return createTickerHelper(
                blockEntityType,
                GrowthcraftBambooBlockEntities.BAMBOO_BEE_BOX_BLOCK_ENTITY.get(),
                (worldLevel, pos, blockState, blockEntity) -> (blockEntity).tick()
        );
    }

}
