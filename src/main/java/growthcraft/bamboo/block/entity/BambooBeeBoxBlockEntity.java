package growthcraft.bamboo.block.entity;

import growthcraft.apiary.block.entity.BeeBoxBlockEntity;
import growthcraft.bamboo.init.GrowthcraftBambooBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class BambooBeeBoxBlockEntity extends BeeBoxBlockEntity {

    public BambooBeeBoxBlockEntity(BlockPos pos, BlockState state) {
        super(GrowthcraftBambooBlockEntities.BAMBOO_BEE_BOX_BLOCK_ENTITY.get(), pos, state, 1200);
    }

}
