package growthcraft.rice.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.fluids.FluidStack;

public class CultivatedFarmlandBlock extends Block implements SimpleWaterloggedBlock {
    public static final IntegerProperty MOISTURE = BlockStateProperties.MOISTURE;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    protected static final VoxelShape SHAPE = Block.box(
            0.0D, 0.0D, 0.0D,
            16.0D, 15.0D, 16.0D
    );

    public static final int MAX_MOISTURE = 7;

    public CultivatedFarmlandBlock() {
        super(getInitProperties());
        this.registerDefaultState(this.stateDefinition.any().setValue(MOISTURE, Integer.valueOf(0)).setValue(WATERLOGGED, false));
    }

    private static Properties getInitProperties() {
        Properties properties = BlockBehaviour.Properties.copy(Blocks.FARMLAND);
        properties.noOcclusion();
        properties.randomTicks();
        return properties;
    }

    @Override
    public BlockState updateShape(BlockState p_53276_, Direction p_53277_, BlockState p_53278_, LevelAccessor p_53279_, BlockPos p_53280_, BlockPos p_53281_) {
        if (p_53277_ == Direction.UP && !p_53276_.canSurvive(p_53279_, p_53280_)) {
            p_53279_.scheduleTick(p_53280_, this, 1);
        }

        return super.updateShape(p_53276_, p_53277_, p_53278_, p_53279_, p_53280_, p_53281_);
    }

    @Override
    public boolean canSurvive(BlockState blockState, LevelReader level, BlockPos blockPos) {
        return true;
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext p_53249_) {
        return !this.defaultBlockState().canSurvive(p_53249_.getLevel(), p_53249_.getClickedPos()) ? Blocks.DIRT.defaultBlockState() : super.getStateForPlacement(p_53249_);
    }

    @Override
    public boolean useShapeForLightOcclusion(BlockState p_53295_) {
        return true;
    }

    @Override
    public VoxelShape getShape(BlockState p_53290_, BlockGetter p_53291_, BlockPos p_53292_, CollisionContext p_53293_) {
        return SHAPE;
    }

    @Override
    public void tick(BlockState p_53262_, ServerLevel p_53263_, BlockPos p_53264_, RandomSource p_53265_) {
        if (!p_53262_.canSurvive(p_53263_, p_53264_)) {
            turnToDirt(p_53262_, p_53263_, p_53264_);
        }
    }

    @Override
    public void randomTick(BlockState blockState, ServerLevel level, BlockPos blockPos, RandomSource random) {
        int i = blockState.getValue(MOISTURE);
        if (!isNearWater(level, blockPos) && !level.isRainingAt(blockPos.above())) {
            if (i > 0) {
                level.setBlock(blockPos, blockState.setValue(MOISTURE, Integer.valueOf(i - 1)), 2);
            } else if (!isUnderCrops(level, blockPos)) {
                turnToDirt(blockState, level, blockPos);
            }
            return;
        }

        if (isNearWater(level, blockPos) && !blockState.getValue(WATERLOGGED)) {
            this.placeLiquid(level, blockPos, blockState, new FluidStack(Fluids.WATER, 1000).getFluid().defaultFluidState());
            return;
        }

        if(isNearWater(level, blockPos) && i < MAX_MOISTURE) {
            level.setBlock(
                    blockPos,
                    blockState.setValue(MOISTURE, Integer.valueOf(i + 1)), 2
            );
        }

    }

    public boolean isWaterlogged(BlockState blockState, ServerLevel serverLevel, BlockPos blockPos) {
        return serverLevel.getFluidState(blockPos).getType() == Fluids.WATER;
    }

    @Override
    public void fallOn(Level p_153227_, BlockState p_153228_, BlockPos p_153229_, Entity p_153230_, float p_153231_) {
        if (!p_153227_.isClientSide && net.minecraftforge.common.ForgeHooks.onFarmlandTrample(p_153227_, p_153229_, Blocks.DIRT.defaultBlockState(), p_153231_, p_153230_)) { // Forge: Move logic to Entity#canTrample
            turnToDirt(p_153228_, p_153227_, p_153229_);
        }

        super.fallOn(p_153227_, p_153228_, p_153229_, p_153230_, p_153231_);
    }

    public static void turnToDirt(BlockState p_53297_, Level p_53298_, BlockPos p_53299_) {
        p_53298_.setBlockAndUpdate(p_53299_, pushEntitiesUp(p_53297_, Blocks.DIRT.defaultBlockState(), p_53298_, p_53299_));
    }

    private static boolean isUnderCrops(BlockGetter p_53251_, BlockPos p_53252_) {
        BlockState plant = p_53251_.getBlockState(p_53252_.above());
        BlockState state = p_53251_.getBlockState(p_53252_);
        return plant.getBlock() instanceof net.minecraftforge.common.IPlantable && state.canSustainPlant(p_53251_, p_53252_, Direction.UP, (net.minecraftforge.common.IPlantable)plant.getBlock());
    }

    private static boolean isNearWater(LevelReader levelReader, BlockPos blockPos) {
        for(BlockPos blockpos : BlockPos.betweenClosed(blockPos.offset(-4, 0, -4), blockPos.offset(4, 1, 4))) {
            if (levelReader.getFluidState(blockpos).is(FluidTags.WATER)) {
                return true;
            }
        }

        return net.minecraftforge.common.FarmlandWaterManager.hasBlockWaterTicket(levelReader, blockPos);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> p_53283_) {
        p_53283_.add(MOISTURE, WATERLOGGED);
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    @Override
    public boolean isPathfindable(BlockState p_53267_, BlockGetter p_53268_, BlockPos p_53269_, PathComputationType p_53270_) {
        return false;
    }
}
