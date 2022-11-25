package growthcraft.apples.block.entity;

import growthcraft.apiary.block.entity.BeeBoxBlockEntity;
import growthcraft.apples.init.GrowthcraftApplesBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class AppleBeeBoxBlockEntity extends BeeBoxBlockEntity {

    public AppleBeeBoxBlockEntity(BlockPos pos, BlockState state) {
        super(GrowthcraftApplesBlockEntities.BEE_BOX_BLOCK_ENTITY.get(), pos, state, 1200);
    }

}
