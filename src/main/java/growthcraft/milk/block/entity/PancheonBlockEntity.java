package growthcraft.milk.block.entity;

import net.minecraft.world.level.block.entity.BlockEntity;

public class PancheonBlockEntity extends BlockEntity extends BlockEntity implements BlockEntityTicker<PancheonBlockEntity>, MenuProvider {



    public PancheonBlockEntity(BlockPos blockPos, BlockState state) {
        super(GrowthcraftMilkBlockEntities.PANCHEON_BLOCK_ENTITY.get(), blockPos, state);
    }

}
