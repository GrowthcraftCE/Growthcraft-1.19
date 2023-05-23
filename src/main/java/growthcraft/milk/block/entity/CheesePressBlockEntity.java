package growthcraft.milk.block.entity;

import growthcraft.milk.init.GrowthcraftMilkBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class CheesePressBlockEntity extends BlockEntity implements BlockEntityTicker<CheesePressBlockEntity> {

    public CheesePressBlockEntity(BlockPos blockPos, BlockState blockState) {
        this(GrowthcraftMilkBlockEntities.CHEESE_PRESS_BLOCK_ENTITY.get(),
                blockPos, blockState);
    }
    public CheesePressBlockEntity(BlockEntityType<?> entityType, BlockPos blockPos, BlockState blockState) {
        super(entityType, blockPos, blockState);

    }

    @Override
    public void tick(Level p_155253_, BlockPos p_155254_, BlockState p_155255_, CheesePressBlockEntity p_155256_) {

    }
    //TODO[63]: Implement CheesePressBlockEntity





}
