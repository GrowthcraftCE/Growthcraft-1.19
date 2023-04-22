package growthcraft.apples.block.entity;

import growthcraft.apples.init.GrowthcraftApplesBlockEntities;
import growthcraft.core.block.entity.RopeBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class AppleRopeBlockEntity extends RopeBlockEntity {

    public AppleRopeBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(GrowthcraftApplesBlockEntities.APPLE_WOOD_ROPE_BLOCK_ENTITY.get(), blockPos, blockState);
    }
}
