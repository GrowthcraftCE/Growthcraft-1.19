package growthcraft.milk.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.awt.*;

public class CheeseWheelBlock extends BaseEntityBlock {
    //TODO[12]: Implement CheeseWheelBlock
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final BooleanProperty AGED = BooleanProperty.create("aged");

    public static final IntegerProperty SLICE_COUNT_TOP = IntegerProperty.create("slicestop", 0, 4);
    public static final IntegerProperty SLICE_COUNT_BOTTOM = IntegerProperty.create("slicesbottom", 0, 4);

    public static final VoxelShape BOUNDING_BOX = Block.box(
            1.00F, 0.0F, 1.0F,
            15.0F, 16.0F, 15.0F
    );

    public static final VoxelShape BOUNDING_BOX_HALF = Block.box(
            1.00F, 0.0F, 1.0F,
            15.0F, 8.0F, 15.0F
    );

    private final int color;

    public CheeseWheelBlock(Color color) {
        super(getInitProperties());
        this.color = color.getRGB();
    }

    public static Properties getInitProperties() {
        Properties properties = Properties.copy(Blocks.CAKE);
        properties.noOcclusion();
        return properties;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos p_153215_, BlockState p_153216_) {
        return null;
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level p_153212_, BlockState p_153213_, BlockEntityType<T> p_153214_) {
        return super.getTicker(p_153212_, p_153213_, p_153214_);
    }

    @Override
    public RenderShape getRenderShape(BlockState p_60550_) {
        return RenderShape.MODEL;
    }

    @Override
    public VoxelShape getShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext context) {
        return blockState.getValue(SLICE_COUNT_TOP) > 0
                ? BOUNDING_BOX
                : BOUNDING_BOX_HALF;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> stateBuilder) {
        super.createBlockStateDefinition(stateBuilder.add(FACING, AGED, SLICE_COUNT_BOTTOM, SLICE_COUNT_TOP));
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite()).setValue(SLICE_COUNT_BOTTOM, 4).setValue(SLICE_COUNT_TOP, 0).setValue(AGED, false);
    }

    @Override
    public PushReaction getPistonPushReaction(BlockState p_60584_) {
        return PushReaction.DESTROY;
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rotation) {
        return state.setValue(FACING, rotation.rotate(state.getValue(FACING)))
                .setValue(SLICE_COUNT_BOTTOM, state.getValue(SLICE_COUNT_BOTTOM))
                .setValue(SLICE_COUNT_TOP, state.getValue(SLICE_COUNT_TOP))
                .setValue(AGED, state.getValue(AGED));
    }

    @Override
    public BlockState mirror(BlockState state, Mirror mirror) {
        return state.rotate(mirror.getRotation(state.getValue(FACING)))
                .setValue(SLICE_COUNT_BOTTOM, state.getValue(SLICE_COUNT_BOTTOM))
                .setValue(SLICE_COUNT_TOP, state.getValue(SLICE_COUNT_TOP))
                .setValue(AGED, state.getValue(AGED));
    }

    @Override
    public InteractionResult use(BlockState p_60503_, Level p_60504_, BlockPos p_60505_, Player p_60506_, InteractionHand p_60507_, BlockHitResult p_60508_) {
        //TODO[12]: Implement stacking cheese wheels

        return super.use(p_60503_, p_60504_, p_60505_, p_60506_, p_60507_, p_60508_);
    }

    @Deprecated
    public int getColor() {
        return this.color;
    }

    @Deprecated
    public int getColor(int i) {
        return i == 0 ? this.color : 0xFFFFFF;
    }

}
