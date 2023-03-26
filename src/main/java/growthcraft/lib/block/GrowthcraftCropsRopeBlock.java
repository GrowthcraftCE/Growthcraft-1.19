package growthcraft.lib.block;

import growthcraft.cellar.GrowthcraftCellar;
import growthcraft.core.block.entity.RopeBlockEntity;
import growthcraft.core.init.GrowthcraftTags;
import growthcraft.lib.utils.BlockStateUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.monster.Ravager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.ArrayList;

public class GrowthcraftCropsRopeBlock extends BushBlock implements BonemealableBlock {

    public static IntegerProperty AGE = BlockStateProperties.AGE_7;
    public static final BooleanProperty DOWN = BooleanProperty.create("down");
    public static final BooleanProperty EAST = BooleanProperty.create("east");
    public static final BooleanProperty NORTH = BooleanProperty.create("north");
    public static final BooleanProperty SOUTH = BooleanProperty.create("south");
    public static final BooleanProperty UP = BooleanProperty.create("up");
    public static final BooleanProperty WEST = BooleanProperty.create("west");

    public static final VoxelShape KNOT_BOUNDING_BOX = Block.box(7.0D, 7.0D, 7.0D, 9.0D, 9.0D, 9.0D);
    public static final VoxelShape NORTH_BOUNDING_BOX = Block.box(7.0D, 7.0D, 0.0D, 9.0D, 9.0D, 7.0D);
    public static final VoxelShape EAST_BOUNDING_BOX = Block.box(9.0D, 7.0D, 7.0D, 16.0D, 9.0D, 9.0D);
    public static final VoxelShape SOUTH_BOUNDING_BOX = Block.box(7.0D, 7.0D, 9.0D, 9.0D, 9.0D, 16.0D);
    public static final VoxelShape WEST_BOUNDING_BOX = Block.box(0.0D, 7.0D, 7.0D, 7.0D, 9.0D, 9.0D);
    public static final VoxelShape UP_BOUNDING_BOX = Block.box(7.0D, 9.0D, 7.0D, 9.0D, 16.0D, 9.0D);
    public static final VoxelShape DOWN_BOUNDING_BOX = Block.box(7.0D, 0.0D, 7.0D, 9.0D, 7.0D, 9.0D);

    private Item seedsItem;

    public GrowthcraftCropsRopeBlock() {
        this(getInitProperties());
    }

    public GrowthcraftCropsRopeBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(NORTH, false)
                .setValue(EAST, false)
                .setValue(SOUTH, false)
                .setValue(WEST, false)
                .setValue(UP, false)
                .setValue(DOWN, true)
                .setValue(AGE, 0)
        );
    }

    public static Properties getInitProperties() {
        BlockBehaviour.Properties properties = BlockBehaviour.Properties.of(Material.PLANT);
        properties.noCollission();
        properties.randomTicks();
        properties.instabreak();
        properties.sound(SoundType.CROP);
        return properties;
    }

    public BlockState getStateForAge(Level level, BlockPos pos, int i) {
        BlockState currentState = level.getBlockState(pos);
        return getActualBlockStateWithAge(level, pos, Integer.valueOf(i));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> blockStateBuilder) {
        blockStateBuilder.add(NORTH, EAST, SOUTH, WEST, UP, DOWN, AGE);
    }

    @Override
    public boolean isRandomlyTicking(BlockState state) {
        return !this.isMaxAge(state);
    }

    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        this.tryGrow(state, level, pos, random.nextInt(4));
    }

    public void tryGrow(BlockState state, ServerLevel level, BlockPos pos, int randomness) {
        if (!level.isAreaLoaded(pos, 1)) return;
        if (level.getRawBrightness(pos, 0) >= 9) {
            int i = this.getAge(state);
            if (i < this.getMaxAge()) {
                if (net.minecraftforge.common.ForgeHooks.onCropsGrowPre(level, pos, state, randomness == 0)) {
                    level.setBlock(pos, this.getStateForAge(level, pos, i + 1), Block.UPDATE_ALL);
                    net.minecraftforge.common.ForgeHooks.onCropsGrowPost(level, pos, state);
                }
            }
        }
    }

    @Override
    @ParametersAreNonnullByDefault
    public @NotNull BlockState updateShape(BlockState state, Direction direction, BlockState blockState, LevelAccessor levelAccessor, BlockPos blockPos1, BlockPos blockPos2) {
        return super.updateShape(getActualBlockState(levelAccessor, blockPos1), direction, blockState, levelAccessor, blockPos1, blockPos2);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return getActualBlockState(context.getLevel(), context.getClickedPos());
    }

    public BlockState getActualBlockState(BlockGetter level, BlockPos blockPos) {
        GrowthcraftCellar.LOGGER.warn(String.format("%s [CropsRopeBlock,getActualBlockState]", blockPos.toString()));

        return getActualBlockStateWithAge(level, blockPos, level.getBlockState(blockPos).getValue(this.getAgeProperty()));
    }

    public BlockState getActualBlockStateWithAge(BlockGetter blockGetter, BlockPos pos, int age) {
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

        return blockState.setValue(AGE, age)
                .setValue(NORTH, northBlockState.is(GrowthcraftTags.Blocks.ROPE))
                .setValue(EAST, eastBlockState.is(GrowthcraftTags.Blocks.ROPE))
                .setValue(SOUTH, southBlockState.is(GrowthcraftTags.Blocks.ROPE))
                .setValue(WEST, westBlockState.is(GrowthcraftTags.Blocks.ROPE))
                .setValue(UP, upBlockState.is(GrowthcraftTags.Blocks.ROPE))
                .setValue(DOWN, downBlockState.is(GrowthcraftTags.Blocks.ROPE));
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

        if (northBlockState.is(GrowthcraftTags.Blocks.ROPE)) voxelShapeArrayList.add(NORTH_BOUNDING_BOX);
        if (eastBlockState.is(GrowthcraftTags.Blocks.ROPE)) voxelShapeArrayList.add(EAST_BOUNDING_BOX);
        if (southBlockState.is(GrowthcraftTags.Blocks.ROPE)) voxelShapeArrayList.add(SOUTH_BOUNDING_BOX);
        if (westBlockState.is(GrowthcraftTags.Blocks.ROPE)) voxelShapeArrayList.add(WEST_BOUNDING_BOX);
        if (upBlockState.is(GrowthcraftTags.Blocks.ROPE)) voxelShapeArrayList.add(UP_BOUNDING_BOX);
        if (downBlockState.is(GrowthcraftTags.Blocks.ROPE)) voxelShapeArrayList.add(DOWN_BOUNDING_BOX);

        VoxelShape[] voxelShapes = new VoxelShape[voxelShapeArrayList.size()];
        voxelShapes = voxelShapeArrayList.toArray(voxelShapes);

        return Shapes.or(KNOT_BOUNDING_BOX, voxelShapes);
    }

    public boolean isMaxAge(BlockState blockState) {
        return blockState.getValue(this.getAgeProperty()) >= this.getMaxAge();
    }

    public IntegerProperty getAgeProperty() {
        return AGE;
    }

    public int getMaxAge() {
        return 7;
    }

    protected int getAge(BlockState blockState) {
        return blockState.getValue(this.getAgeProperty());
    }

    protected int getBonemealAgeIncrease(Level level) {
        return Mth.nextInt(level.random, 0, 1);
    }

    public void growCrops(Level level, BlockPos blockPos, BlockState state) {
        int i = this.getAge(state) + this.getBonemealAgeIncrease(level);
        int j = this.getMaxAge();
        if (i > j) {
            i = j;
        }

        level.setBlock(blockPos, this.getStateForAge(level, blockPos, i), Block.UPDATE_ALL);
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader level, BlockPos blockPos, BlockState state, boolean isClientSide) {
        return !this.isMaxAge(state);
    }

    @Override
    public boolean isBonemealSuccess(Level level, RandomSource random, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {
        this.growCrops(level, pos, state);
    }

    protected static float getGrowthSpeed(Block block, BlockGetter blockGetter, BlockPos pos) {
        float f = 1.0F;
        BlockPos blockpos = pos.below();

        for(int i = -1; i <= 1; ++i) {
            for(int j = -1; j <= 1; ++j) {
                float f1 = 0.0F;
                BlockState blockstate = blockGetter.getBlockState(blockpos.offset(i, 0, j));
                if (blockstate.canSustainPlant(blockGetter, blockpos.offset(i, 0, j), net.minecraft.core.Direction.UP, (net.minecraftforge.common.IPlantable) block)) {
                    f1 = 1.0F;
                    if (blockstate.isFertile(blockGetter, pos.offset(i, 0, j))) {
                        f1 = 3.0F;
                    }
                }

                if (i != 0 || j != 0) {
                    f1 /= 4.0F;
                }

                f += f1;
            }
        }

        BlockPos blockpos1 = pos.north();
        BlockPos blockpos2 = pos.south();
        BlockPos blockpos3 = pos.west();
        BlockPos blockpos4 = pos.east();
        boolean flag = blockGetter.getBlockState(blockpos3).is(block) || blockGetter.getBlockState(blockpos4).is(block);
        boolean flag1 = blockGetter.getBlockState(blockpos1).is(block) || blockGetter.getBlockState(blockpos2).is(block);
        if (flag && flag1) {
            f /= 2.0F;
        } else {
            boolean flag2 = blockGetter.getBlockState(blockpos3.north()).is(block) || blockGetter.getBlockState(blockpos4.north()).is(block) || blockGetter.getBlockState(blockpos4.south()).is(block) || blockGetter.getBlockState(blockpos3.south()).is(block);
            if (flag2) {
                f /= 2.0F;
            }
        }

        return f * 2.0F;
    }


    @Override
    public void entityInside(BlockState blockState, Level level, BlockPos pos, Entity entity) {
        if (entity instanceof Ravager && net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(level, entity)) {
            level.destroyBlock(pos, true, entity);
        }

        super.entityInside(blockState, level, pos, entity);
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        return InteractionResult.PASS;
    }

    @Override
    public boolean canSurvive(@NotNull BlockState state, LevelReader level, BlockPos pos) {
        return true;
    }

    public boolean canBeConnectedTo(BlockState state, BlockGetter world, BlockPos pos, Direction facing) {
        return BlockStateUtils.isRopeBlock(state);
    }

}
