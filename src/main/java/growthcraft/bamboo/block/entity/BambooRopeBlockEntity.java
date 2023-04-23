package growthcraft.bamboo.block.entity;

import growthcraft.bamboo.init.GrowthcraftBambooBlockEntities;
import growthcraft.core.block.entity.RopeBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class BambooRopeBlockEntity extends RopeBlockEntity {

    public BambooRopeBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(GrowthcraftBambooBlockEntities.BAMBOO_FENCE_ROPE_BLOCK_ENTITY.get(), blockPos, blockState);
    }
}
