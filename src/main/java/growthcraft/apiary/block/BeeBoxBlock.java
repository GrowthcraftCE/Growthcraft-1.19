package growthcraft.apiary.block;

import growthcraft.apiary.GrowthcraftApiary;
import growthcraft.apiary.block.entity.BeeBoxBlockEntity;
import growthcraft.apiary.init.GrowthcraftApiaryBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static net.minecraft.world.level.block.state.properties.BlockStateProperties.HORIZONTAL_FACING;

public class BeeBoxBlock extends BaseEntityBlock {
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

    private static final VoxelShape VOXEL_SHAPE = Block.box(
            2.0D, 0.0D, 2.0D,
            14.0D, 16.0D, 14.0D
    );

    public BeeBoxBlock() {
        this(getInitProperties());
    }

    public BeeBoxBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
    }

    private static Properties getInitProperties() {
        Properties properties = BlockBehaviour.Properties.copy(Blocks.BEEHIVE);
        properties.strength(1.5F);
        properties.noOcclusion();
        return properties;
    }

    @Override
    public RenderShape getRenderShape(BlockState blockState) {
        return RenderShape.MODEL;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> blockStateBuilder) {
        blockStateBuilder.add(HORIZONTAL_FACING);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    @Override
    public @NotNull PushReaction getPistonPushReaction(@NotNull BlockState blockState) {
        return PushReaction.DESTROY;
    }

    public BlockState rotate(BlockState state, Rotation rotation) {
        return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
    }

    public BlockState mirror(BlockState state, Mirror mirror) {
        return state.rotate(mirror.getRotation(state.getValue(FACING)));
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return GrowthcraftApiaryBlockEntities.BEE_BOX_BLOCK_ENTITY.get().create(pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> blockEntityType) {
        return createTickerHelper(
                blockEntityType,
                GrowthcraftApiaryBlockEntities.BEE_BOX_BLOCK_ENTITY.get(),
                (worldLevel, pos, blockState, blockEntity) -> (blockEntity).tick()
        );
    }

    @Override
    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean isMoving) {
        if (state.getBlock() != newState.getBlock()) {
            try {
                BeeBoxBlockEntity blockEntity = (BeeBoxBlockEntity) level.getBlockEntity(pos);
                blockEntity.dropItems();
            } catch (Exception ex) {
                GrowthcraftApiary.LOGGER.error(String.format("Invalid blockEntity type at %s, expected BeeBoxBlockEntity", pos.toString()));
            }
        }
        super.onRemove(state, level, pos, newState, isMoving);
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if(!level.isClientSide) {
            level.playSound(player, pos, SoundEvents.BARREL_OPEN, SoundSource.BLOCKS, 1.0F, 1.0F);
            try {
                BeeBoxBlockEntity blockEntity = (BeeBoxBlockEntity) level.getBlockEntity(pos);
                NetworkHooks.openScreen(((ServerPlayer) player), blockEntity, pos);
            } catch (Exception ex) {
                GrowthcraftApiary.LOGGER.error(String.format("%s unable to open BeeBoxBlockEntity GUI at %s.",
                        player.getDisplayName().getString(), pos));
                GrowthcraftApiary.LOGGER.error(ex.getMessage());
            }
        }
        return InteractionResult.SUCCESS;
    }
}
