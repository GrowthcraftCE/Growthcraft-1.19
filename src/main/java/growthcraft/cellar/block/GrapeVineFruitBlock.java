package growthcraft.cellar.block;

import growthcraft.lib.block.GrowthcraftCropsRopeBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class GrapeVineFruitBlock extends GrowthcraftCropsRopeBlock {

    protected static final VoxelShape[] CUSTOM_SHAPE_BY_AGE = new VoxelShape[]{
            Block.box(4.0D, 13.0D, 4.0D, 12.0D, 16.0D, 12.0D),
            Block.box(4.0D, 13.0D, 4.0D, 12.0D, 16.0D, 12.0D),
            Block.box(4.0D, 13.0D, 4.0D, 12.0D, 16.0D, 12.0D),
            Block.box(4.0D, 13.0D, 4.0D, 12.0D, 16.0D, 12.0D),
            Block.box(4.0D, 4.0D, 4.0D, 12.0D, 16.0D, 12.0D),
            Block.box(4.0D, 4.0D, 4.0D, 12.0D, 16.0D, 12.0D),
            Block.box(4.0D, 4.0D, 4.0D, 12.0D, 16.0D, 12.0D),
            Block.box(4.0D, 4.0D, 4.0D, 12.0D, 16.0D, 12.0D)
    };


    public GrapeVineFruitBlock() {
        super();
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter blockGetter, BlockPos pos, CollisionContext context) {
        return CUSTOM_SHAPE_BY_AGE[state.getValue(AGE)];
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext context) {
        return getActualBlockState(context.getLevel(), context.getClickedPos());
    }

    @Override
    public BlockState getActualBlockState(BlockGetter level, BlockPos blockPos) {
        return getActualBlockStateWithAge(level, blockPos, level.getBlockState(blockPos).getValue(this.getAgeProperty()));
    }

    @Override
    public BlockState getActualBlockStateWithAge(BlockGetter level, BlockPos pos, int age) {
        BlockState state = super.getActualBlockStateWithAge(level, pos, age);
        state.setValue(NORTH, false);
        state.setValue(EAST, false);
        state.setValue(SOUTH, false);
        state.setValue(WEST, false);
        state.setValue(UP, false);
        state.setValue(DOWN, false);
        return state;
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (level.isClientSide) {
            return InteractionResult.PASS;
        }

        if (state.getValue(AGE) == this.getMaxAge()) {
            LootContext.Builder context = new LootContext.Builder((ServerLevel) level)
                    .withParameter(LootContextParams.ORIGIN, new Vec3(pos.getX(), pos.getY(), pos.getZ()))
                    .withParameter(LootContextParams.BLOCK_STATE, state)
                    .withParameter(LootContextParams.THIS_ENTITY, player)
                    .withOptionalParameter(LootContextParams.TOOL, player.getItemInHand(hand));

            List<ItemStack> drops = state.getDrops(context);

            for (ItemStack stack : drops) {
                Containers.dropItemStack(level, pos.getX(), pos.getY(), pos.getZ(), stack);
            }

            level.setBlock(pos, this.getStateForAge(level, pos, 1), Block.UPDATE_ALL);
        }

        return super.use(state, level, pos, player, hand, hitResult);
    }


}
