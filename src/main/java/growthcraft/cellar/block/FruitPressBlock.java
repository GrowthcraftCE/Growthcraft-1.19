package growthcraft.cellar.block;

import growthcraft.cellar.GrowthcraftCellar;
import growthcraft.cellar.block.entity.FruitPressBlockEntity;
import growthcraft.cellar.init.GrowthcraftCellarBlockEntities;
import growthcraft.cellar.init.GrowthcraftCellarBlocks;
import growthcraft.core.utils.BlockPropertiesUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;

import static growthcraft.cellar.block.FruitPressPistonBlock.PRESSED;
import static net.minecraft.world.phys.shapes.BooleanOp.OR;

public class FruitPressBlock extends BaseEntityBlock {
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

    private final VoxelShape[] VOXEL_SHAPES = new VoxelShape[]{
            Block.box(1, 0, 1, 15, 3, 15),
            Block.box(0, 3, 0, 16, 7, 16),
            Block.box(1, 7, 1, 15, 16, 15)
    };

    public FruitPressBlock() {
        super(getInitProperties());
    }

    public FruitPressBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
    }

    private static Properties getInitProperties() {
        Properties properties = Properties.copy(Blocks.BARREL);
        properties.noOcclusion();
        properties.sound(SoundType.CHAIN);
        properties.isRedstoneConductor(BlockPropertiesUtils::never);
        return properties;
    }

    @Override
    public RenderShape getRenderShape(BlockState p_49232_) {
        return RenderShape.MODEL;
    }

    @Override
    public VoxelShape getShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext context) {
        return Arrays.stream(VOXEL_SHAPES).reduce((v1, v2) -> Shapes.join(v1, v2, OR)).get();
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> blockStateBuilder) {
        blockStateBuilder.add(FACING);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    @Override
    public PushReaction getPistonPushReaction(BlockState p_60584_) {
        return PushReaction.DESTROY;
    }

    @Override
    public BlockState rotate(BlockState state, LevelAccessor level, BlockPos pos, Rotation rotation) {
        return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, Mirror mirror) {
        return state.rotate(mirror.getRotation(state.getValue(FACING)));
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return GrowthcraftCellarBlockEntities.FRUIT_PRESS_BLOCK_ENTITY.get().create(blockPos, blockState);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState blockState, BlockEntityType<T> entityType) {
        if (level.isClientSide) {
            return createTickerHelper(
                    entityType,
                    GrowthcraftCellarBlockEntities.FRUIT_PRESS_BLOCK_ENTITY.get(),
                    FruitPressBlockEntity::particleTick);
        } else {
            return createTickerHelper(
                    entityType,
                    GrowthcraftCellarBlockEntities.FRUIT_PRESS_BLOCK_ENTITY.get(),
                    (worldLevel, pos, state, blockEntity) -> (blockEntity).tick()
            );
        }
    }

    @Override
    public InteractionResult use(BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult hitResult) {
        if (!level.isClientSide) {
            FruitPressBlockEntity blockEntity = (FruitPressBlockEntity) level.getBlockEntity(blockPos);

            if (FluidUtil.interactWithFluidHandler(player, interactionHand, level, blockPos, hitResult.getDirection()) || player.getItemInHand(interactionHand).getCapability(ForgeCapabilities.FLUID_HANDLER_ITEM).isPresent()) {
                return InteractionResult.SUCCESS;
            } else if (player.isCrouching() && !level.getBlockState(blockPos.above()).getValue(PRESSED)) {
                try {
                    // Play sound
                    level.playSound(player, blockPos, SoundEvents.BARREL_OPEN, SoundSource.BLOCKS, 1.0F, 1.0F);
                    NetworkHooks.openScreen(((ServerPlayer) player), blockEntity, blockPos);
                } catch (Exception ex) {
                    GrowthcraftCellar.LOGGER.error(String.format("%s unable to open FruitPressBlockEntity GUI at %s.", player.getDisplayName().getString(), blockPos));
                    GrowthcraftCellar.LOGGER.error(ex.getMessage());
                    GrowthcraftCellar.LOGGER.error(ex.fillInStackTrace());
                }
            }
            return InteractionResult.SUCCESS;
        }

        return InteractionResult.SUCCESS;
    }

    @Override
    public boolean canSurvive(BlockState blockState, LevelReader levelReader, BlockPos blockPos) {
        return levelReader.getBlockState(blockPos.above()).isAir();
    }

    @Override
    public void setPlacedBy(Level level, BlockPos blockPos, BlockState blockState, @Nullable LivingEntity livingEntity, ItemStack itemStack) {
        level.setBlock(blockPos.above(), GrowthcraftCellarBlocks.FRUIT_PRESS_PISTON.get().defaultBlockState().setValue(FACING, blockState.getValue(FACING)).setValue(PRESSED, false), Block.UPDATE_ALL_IMMEDIATE);
    }

    @Override
    public boolean onDestroyedByPlayer(BlockState state, Level level, BlockPos pos, Player player, boolean willHarvest, FluidState fluid) {
        if (level.getBlockState(pos.above()).getBlock() instanceof FruitPressPistonBlock) {
            level.destroyBlock(pos.above(), false);
        }
        return super.onDestroyedByPlayer(state, level, pos, player, willHarvest, fluid);
    }

    public static void makeParticles(Level level, BlockPos blockPos, BlockState blockState) {
        RandomSource randomSource = level.getRandom();

        FruitPressBlockEntity blockEntity = (FruitPressBlockEntity) level.getBlockEntity(blockPos);

        if (level.getBlockState(blockPos.above()).getValue(PRESSED) && blockEntity.getTickClock("current") > 0) {
            double d0 = (double) blockPos.getX() + randomSource.nextDouble();
            //double d1 = (double) blockPos.getY() - 0.05D;
            double d1 = blockPos.getY();
            double d2 = (double) blockPos.getZ() + randomSource.nextDouble();

            level.addParticle(ParticleTypes.FALLING_HONEY, d0, d1, d2, 0.0D, 0.0D, 0.0D);
        }

    }
}
