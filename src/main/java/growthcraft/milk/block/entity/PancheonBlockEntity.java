package growthcraft.milk.block.entity;

import growthcraft.milk.init.GrowthcraftMilkBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class PancheonBlockEntity extends BlockEntity {



    public PancheonBlockEntity(BlockPos blockPos, BlockState state) {
        super(GrowthcraftMilkBlockEntities.PANCHEON_BLOCK_ENTITY.get(), blockPos, state);
    }

}
