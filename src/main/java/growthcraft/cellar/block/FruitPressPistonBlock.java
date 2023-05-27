package growthcraft.cellar.block;

import growthcraft.cellar.GrowthcraftCellar;
import growthcraft.cellar.block.entity.FruitPressBlockEntity;
import growthcraft.cellar.init.GrowthcraftCellarBlocks;
import growthcraft.core.utils.BlockPropertiesUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static net.minecraft.world.phys.shapes.BooleanOp.OR;

public class FruitPressPistonBlock  extends Block {

    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final BooleanProperty PRESSED = BooleanProperty.create("pressed");

    private VoxelShape[] VOXEL_SHAPES_UP = new VoxelShape[] {
            Block.box(0, 15, 0, 16, 16, 16),
            Block.box(3, 0, 3, 13, 16, 13)
    };

    private VoxelShape[] VOXEL_SHAPES_DOWN = new VoxelShape[] {
            Block.box(0, 15, 0, 16, 16, 16),
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
        properties.isRedstoneConductor(BlockPropertiesUtils::never);
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
    public boolean onDestroyedByPlayer(BlockState state, Level level, BlockPos pos, Player player, boolean willHarvest, FluidState fluid) {
        if(level.getBlockState(pos.below()).getBlock() instanceof FruitPressBlock) {
            level.destroyBlock(pos.below(), false);
            ItemEntity itemEntity = new ItemEntity(level, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(GrowthcraftCellarBlocks.FRUIT_PRESS.get().asItem()));
            level.addFreshEntity(itemEntity);
        }
        return super.onDestroyedByPlayer(state, level, pos, player, willHarvest, fluid);
    }

    @Override
    public List<ItemStack> getDrops(BlockState p_60537_, LootContext.Builder p_60538_) {
        return Collections.emptyList();
    }

    @Override
    public boolean canConnectRedstone(BlockState state, BlockGetter level, BlockPos pos, @Nullable Direction direction) {
        return false;
    }

    @Override
    public InteractionResult use(BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult) {
        FruitPressBlockEntity blockEntity = (FruitPressBlockEntity) level.getBlockEntity(blockPos.below());

        // Handle connecting a lever on the top.
        if(!level.isClientSide && player.getItemInHand(interactionHand).getItem() == Items.LEVER) {
            level.setBlock(blockPos.above(), Blocks.LEVER.getStateDefinition().any().setValue(FACING, blockState.getValue(FACING)), Block.UPDATE_ALL_IMMEDIATE);
            player.getItemInHand(interactionHand).shrink(1);
        } else if (!level.isClientSide && player.isCrouching() && !blockState.getValue(PRESSED)) {
            try {
                // Play sound
                level.playSound(player, blockPos, SoundEvents.BARREL_OPEN, SoundSource.BLOCKS, 1.0F, 1.0F);
                NetworkHooks.openScreen(((ServerPlayer) player), blockEntity, blockPos.below());
            } catch (Exception ex) {
                GrowthcraftCellar.LOGGER.error(String.format("%s unable to open FruitPressBlockEntity GUI at %s.", player.getDisplayName().getString(), blockPos));
                GrowthcraftCellar.LOGGER.error(ex.getMessage());
                GrowthcraftCellar.LOGGER.error(ex.fillInStackTrace());
            }
        }


        return super.use(blockState, level, blockPos, player, interactionHand, blockHitResult);
    }

    public boolean isPowered(Level level, BlockPos pos) {
        return level.getBestNeighborSignal(pos) == 15;
    }

    @Override
    public void neighborChanged(BlockState blockState, Level level, BlockPos blockPos, Block neighborBlock, BlockPos neighborBlockPos, boolean isMoving) {
        super.neighborChanged(blockState, level, blockPos, neighborBlock, neighborBlockPos, isMoving);

        if(!level.isClientSide) {
            boolean flag = level.hasNeighborSignal(blockPos);
            if(blockState.getValue(PRESSED) != flag) {
                level.setBlock(blockPos, blockState.setValue(PRESSED, flag), Block.UPDATE_CLIENTS);
            }
        }
    }

    public static boolean canSupportRigidBlock(BlockGetter p_49937_, BlockPos p_49938_) {
        GrowthcraftCellar.LOGGER.warn("Calling canSupportRigidBlock");
        return p_49937_.getBlockState(p_49938_).isFaceSturdy(p_49937_, p_49938_, Direction.UP, SupportType.RIGID);
    }
}
