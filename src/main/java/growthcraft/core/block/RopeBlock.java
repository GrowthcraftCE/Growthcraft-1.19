package growthcraft.core.block;

import growthcraft.core.Growthcraft;
import growthcraft.core.block.entity.RopeBlockEntity;
import growthcraft.core.init.GrowthcraftBlockEntities;
import growthcraft.core.init.GrowthcraftTags;
import growthcraft.core.utils.BlockPropertiesUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;
import java.util.ArrayList;

public class RopeBlock extends BaseEntityBlock implements SimpleWaterloggedBlock {

    public static final BooleanProperty NORTH = BooleanProperty.create("north");
    public static final BooleanProperty EAST = BooleanProperty.create("east");
    public static final BooleanProperty SOUTH = BooleanProperty.create("south");
    public static final BooleanProperty WEST = BooleanProperty.create("west");
    public static final BooleanProperty UP = BooleanProperty.create("up");
    public static final BooleanProperty DOWN = BooleanProperty.create("down");
    public static final BooleanProperty KNOT = BooleanProperty.create("knot");

    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    public static final VoxelShape KNOT_BOUNDING_BOX = Block.box(7.0D, 7.0D, 7.0D, 9.0D, 9.0D, 9.0D);
    public static final VoxelShape NORTH_BOUNDING_BOX = Block.box(7.0D, 7.0D, 0.0D, 9.0D, 9.0D, 7.0D);
    public static final VoxelShape EAST_BOUNDING_BOX = Block.box(9.0D, 7.0D, 7.0D, 16.0D, 9.0D, 9.0D);
    public static final VoxelShape SOUTH_BOUNDING_BOX = Block.box(7.0D, 7.0D, 9.0D, 9.0D, 9.0D, 16.0D);
    public static final VoxelShape WEST_BOUNDING_BOX = Block.box(0.0D, 7.0D, 7.0D, 7.0D, 9.0D, 9.0D);
    public static final VoxelShape UP_BOUNDING_BOX = Block.box(7.0D, 9.0D, 7.0D, 9.0D, 16.0D, 9.0D);
    public static final VoxelShape DOWN_BOUNDING_BOX = Block.box(7.0D, 0.0D, 7.0D, 9.0D, 7.0D, 9.0D);
    public static final VoxelShape KNOT_FENCE_BOUNDING_BOX = Block.box(5.0D, 6.0D, 5.0D, 11.0D, 14.0D, 11.0D);
    public static final VoxelShape FENCE_POST_BOUNDING_BOX = Block.box(6.0D, 0.0D, 6.0D, 10.0D, 16.0D, 10.0D);

    public RopeBlock() {
        this(BlockPropertiesUtils.getInitProperties("rope_block", Blocks.TRIPWIRE));
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(NORTH, false)
                .setValue(EAST, false)
                .setValue(SOUTH, false)
                .setValue(WEST, false)
                .setValue(UP, false)
                .setValue(DOWN, false)
                .setValue(KNOT, false)
                .setValue(WATERLOGGED, false)
        );

    }

    public RopeBlock(Properties properties) {
        super(properties);
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return getActualBlockState(context.getLevel(), context.getClickedPos());
    }

    @Override
    public BlockState updateShape(BlockState p_60541_, Direction p_60542_, BlockState p_60543_, LevelAccessor levelAccessor, BlockPos p_60545_, BlockPos p_60546_) {
        return super.updateShape(getActualBlockState(levelAccessor, p_60545_ ), p_60542_, p_60543_, levelAccessor, p_60545_, p_60546_);
    }

    public BlockState getActualBlockState(BlockGetter blockGetter, BlockPos pos) {
        BlockState blockState = this.defaultBlockState();
        FluidState fluidState = blockGetter.getFluidState(pos);

        BlockPos northBlockPos = pos.north();
        BlockPos eastBlockPos = pos.east();
        BlockPos southBlockPos = pos.south();
        BlockPos westBlockPos = pos.west();
        BlockPos upBlockPos = pos.above();
        BlockPos downBlockPos = pos.below();

        BlockState northBlockState = blockGetter.getBlockState(northBlockPos);
        BlockState eastBlockState = blockGetter.getBlockState(eastBlockPos);
        BlockState southBlockState = blockGetter.getBlockState(southBlockPos);
        BlockState westBlockState = blockGetter.getBlockState(westBlockPos);
        BlockState upBlockState = blockGetter.getBlockState(upBlockPos);
        BlockState downBlockState = blockGetter.getBlockState(downBlockPos);

        RopeBlockEntity entity = (RopeBlockEntity) blockGetter.getBlockEntity(pos);

        return blockState.setValue(WATERLOGGED, fluidState.getType() == Fluids.WATER)
                .setValue(NORTH, northBlockState.is(GrowthcraftTags.Blocks.ROPE))
                .setValue(EAST, eastBlockState.is(GrowthcraftTags.Blocks.ROPE))
                .setValue(SOUTH, southBlockState.is(GrowthcraftTags.Blocks.ROPE))
                .setValue(WEST, westBlockState.is(GrowthcraftTags.Blocks.ROPE))
                .setValue(UP, upBlockState.is(GrowthcraftTags.Blocks.ROPE))
                .setValue(DOWN, downBlockState.is(GrowthcraftTags.Blocks.ROPE))
                .setValue(KNOT, entity != null ? entity.hasFenceItemStack() : false);
    }


    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> blockStateBuilder) {
        blockStateBuilder.add(NORTH, EAST, SOUTH, WEST, UP, DOWN, KNOT, WATERLOGGED);
    }

    @Override
    public PushReaction getPistonPushReaction(BlockState blockState) {
        return PushReaction.DESTROY;
    }

    @Override
    public FluidState getFluidState(BlockState blockState) {
        return Boolean.TRUE.equals(blockState.getValue(WATERLOGGED))
                ? Fluids.WATER.getSource(false)
                : super.getFluidState(blockState);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return GrowthcraftBlockEntities.ROPE_BLOCK_ENTITY.get().create(blockPos, blockState);
    }

    @Override
    public void onPlace(BlockState state, Level level, BlockPos pos, BlockState p_60569_, boolean p_60570_) {
        super.onPlace(state, level, pos, p_60569_, p_60570_);
    }

    @Override
    public void onRemove(BlockState blockState, Level level, BlockPos blockPos, BlockState newBlockState, boolean isMoving) {
        if (blockState.getBlock() != newBlockState.getBlock()) {
            try {
                RopeBlockEntity blockEntity = (RopeBlockEntity) level.getBlockEntity(blockPos);
                blockEntity.dropItems();
            } catch (Exception ex) {
                Growthcraft.LOGGER.error(String.format("Invalid blockEntity type at %s, expected RopeBlockEntity", blockPos));
            }
        }
        super.onRemove(blockState, level, blockPos, newBlockState, isMoving);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter blockGetter, BlockPos pos, CollisionContext context) {
        ArrayList<VoxelShape> voxelShapeArrayList = new ArrayList<VoxelShape>();

        BlockPos northBlockPos = pos.north();
        BlockPos eastBlockPos = pos.east();
        BlockPos southBlockPos = pos.south();
        BlockPos westBlockPos = pos.west();
        BlockPos upBlockPos = pos.above();
        BlockPos downBlockPos = pos.below();

        BlockState northBlockState = blockGetter.getBlockState(northBlockPos);
        BlockState eastBlockState = blockGetter.getBlockState(eastBlockPos);
        BlockState southBlockState = blockGetter.getBlockState(southBlockPos);
        BlockState westBlockState = blockGetter.getBlockState(westBlockPos);
        BlockState upBlockState = blockGetter.getBlockState(upBlockPos);
        BlockState downBlockState = blockGetter.getBlockState(downBlockPos);

        if(northBlockState.is(GrowthcraftTags.Blocks.ROPE)) voxelShapeArrayList.add(NORTH_BOUNDING_BOX);
        if(eastBlockState.is(GrowthcraftTags.Blocks.ROPE)) voxelShapeArrayList.add(EAST_BOUNDING_BOX);
        if(southBlockState.is(GrowthcraftTags.Blocks.ROPE)) voxelShapeArrayList.add(SOUTH_BOUNDING_BOX);
        if(westBlockState.is(GrowthcraftTags.Blocks.ROPE)) voxelShapeArrayList.add(WEST_BOUNDING_BOX);
        if(upBlockState.is(GrowthcraftTags.Blocks.ROPE)) voxelShapeArrayList.add(UP_BOUNDING_BOX);
        if(downBlockState.is(GrowthcraftTags.Blocks.ROPE)) voxelShapeArrayList.add(DOWN_BOUNDING_BOX);
        if(state.getValue(KNOT).booleanValue()) {
            voxelShapeArrayList.add(KNOT_FENCE_BOUNDING_BOX);
            voxelShapeArrayList.add(FENCE_POST_BOUNDING_BOX);
        }

        VoxelShape[] voxelShapes = new VoxelShape[voxelShapeArrayList.size()];
        voxelShapes = voxelShapeArrayList.toArray(voxelShapes);

        return Shapes.or(KNOT_BOUNDING_BOX, voxelShapes);
    }
}
