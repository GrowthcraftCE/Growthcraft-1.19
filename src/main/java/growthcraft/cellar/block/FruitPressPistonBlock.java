package growthcraft.cellar.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static net.minecraft.world.phys.shapes.BooleanOp.OR;

public class FruitPressPistonBlock  extends BaseEntityBlock {

    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final BooleanProperty PRESSED = BooleanProperty.create("pressed");

    private VoxelShape[] VOXEL_SHAPES_UP = new VoxelShape[] {
            Block.box(3, 0, 3, 13, 16, 13)
    };

    private VoxelShape[] VOXEL_SHAPES_DOWN = new VoxelShape[] {
            Block.box(6.5, 0, 6.5, 9.5, 9, 9.5),
            Block.box(3, 9, 3, 13, 16, 13)
    };

    public FruitPressPistonBlock() {
        super(getInitProperties());
    }

    public FruitPressPistonBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(
                this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(PRESSED, false)
        );
    }

    private static Properties getInitProperties() {
        Properties properties = Properties.copy(Blocks.PISTON);
        properties.noOcclusion();
        return properties;
    }

    @Override
    public RenderShape getRenderShape(BlockState p_49232_) {
        return RenderShape.MODEL;
    }

    @Override
    public VoxelShape getShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext context) {
        return blockState.getValue(PRESSED)
                ? Arrays.stream(VOXEL_SHAPES_DOWN).reduce((v1, v2) -> Shapes.join(v1, v2, OR)).get()
                : Arrays.stream(VOXEL_SHAPES_UP).reduce((v1, v2) -> Shapes.join(v1, v2, OR)).get();
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> blockStateBuilder) {
        blockStateBuilder.add(FACING, PRESSED);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite())
                .setValue(PRESSED, this.isPowered(context.getLevel(), context.getClickedPos()));
    }

    @Override
    public PushReaction getPistonPushReaction(BlockState p_60584_) {
        return PushReaction.DESTROY;
    }

    @Override
    public BlockState rotate(BlockState state, LevelAccessor level, BlockPos pos, Rotation rotation) {
        return state.setValue(FACING, rotation.rotate(state.getValue(FACING))).setValue(PRESSED, state.getValue(PRESSED));
    }

    @Override
    public BlockState mirror(BlockState state, Mirror mirror) {
        return state.rotate(mirror.getRotation(state.getValue(FACING))).setValue(PRESSED, state.getValue(PRESSED));
    }

    @Override
    public void onRemove(BlockState blockState, Level level, BlockPos blockPos, BlockState state, boolean isMoving) {
        super.onRemove(blockState, level, blockPos, state, isMoving);

        if(level.getBlockState(blockPos.below()).getBlock() instanceof FruitPressBlock) {
            level.destroyBlock(blockPos.below(), false);
        }
    }

    @Override
    public List<ItemStack> getDrops(BlockState p_60537_, LootContext.Builder p_60538_) {
        return Collections.emptyList();
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos p_153215_, BlockState p_153216_) {
        // TODO: Instantiate FruitPressPistonBlockEntity
        return null;
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level p_153212_, BlockState p_153213_, BlockEntityType<T> p_153214_) {
        // TODO: Create hook to FruitPressPistonBlockEntity ticker.
        return super.getTicker(p_153212_, p_153213_, p_153214_);
    }

    @Override
    public boolean canConnectRedstone(BlockState state, BlockGetter level, BlockPos pos, @Nullable Direction direction) {
        return false;
    }

    @Override
    public InteractionResult use(BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult) {

        if(!level.isClientSide && player.getItemInHand(interactionHand).getItem() == Items.LEVER) {
            level.setBlock(blockPos.above(), Blocks.LEVER.getStateDefinition().any().setValue(FACING, blockState.getValue(FACING)), Block.UPDATE_ALL_IMMEDIATE);
        }
        // TODO: Handle insert item into the Fruit Press

        // TODO: Handle GUI for FruitPress

        // TODO: Handle Fluid extraction for the FruitPress

        return super.use(blockState, level, blockPos, player, interactionHand, blockHitResult);
    }

    public boolean isPowered(Level level, BlockPos pos) {
        return level.getBestNeighborSignal(pos) == 15;
    }


}
