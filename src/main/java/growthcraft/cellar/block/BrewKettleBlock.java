package growthcraft.cellar.block;

import growthcraft.cellar.GrowthcraftCellar;
import growthcraft.cellar.block.entity.BrewKettleBlockEntity;
import growthcraft.cellar.init.GrowthcraftCellarBlockEntities;
import growthcraft.core.utils.BlockPropertiesUtils;
import growthcraft.lib.utils.BlockStateUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
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

import static net.minecraft.world.phys.shapes.BooleanOp.OR;

public class BrewKettleBlock extends BaseEntityBlock {

    public static final BooleanProperty LIT = BooleanProperty.create("lit");
    public static final BooleanProperty HAS_LID = BooleanProperty.create("has_lid");

    private final VoxelShape[] VOXEL_SHAPES_LID = new VoxelShape[]{
            Block.box(0, 0, 2, 2, 3, 4),
            Block.box(1, 3, 1, 15, 4, 15),
            Block.box(1, 3, 0, 15, 16, 1),
            Block.box(15, 3, 0, 16, 16, 16),
            Block.box(1, 3, 15, 15, 16, 16),
            Block.box(0, 3, 0, 1, 16, 16),
            Block.box(0, 0, 0, 4, 3, 2),
            Block.box(12, 0, 0, 16, 3, 2),
            Block.box(12, 0, 14, 16, 3, 16),
            Block.box(0, 0, 14, 4, 3, 16),
            Block.box(14, 0, 2, 16, 3, 4),
            Block.box(14, 0, 12, 16, 3, 14),
            Block.box(1, 14, 1, 15, 15, 15),
            Block.box(0, 0, 12, 2, 3, 14)
    };

    public BrewKettleBlock() {
        this(getInitProperties());
    }

    public BrewKettleBlock(Properties properties) {
        super(properties);

        this.registerDefaultState(this.stateDefinition.any()
                .setValue(LIT, false)
                .setValue(HAS_LID, false)
        );
    }

    private static Properties getInitProperties() {
        Properties properties = Properties.copy(Blocks.FURNACE);
        properties.noOcclusion();
        properties.sound(SoundType.METAL);
        properties.isRedstoneConductor(BlockPropertiesUtils::never);
        return properties;
    }

    @Override
    public RenderShape getRenderShape(BlockState blockState) {
        return RenderShape.MODEL;
    }

    @Override
    public VoxelShape getShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext context) {
        return Arrays.stream(VOXEL_SHAPES_LID).reduce((v1, v2) -> Shapes.join(v1, v2, OR)).get();
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> blockStateBuilder) {
        blockStateBuilder.add(LIT, HAS_LID);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState()
                .setValue(LIT, BlockStateUtils.isHeated(context.getLevel(), context.getClickedPos()))
                .setValue(HAS_LID, false);
    }

    @Override
    public PushReaction getPistonPushReaction(BlockState blockState) {
        return PushReaction.DESTROY;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return GrowthcraftCellarBlockEntities.BREW_KETTLE_BLOCK_ENTITY.get().create(blockPos, blockState);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState blockState, BlockEntityType<T> blockEntityType) {
        if (level.isClientSide) {
            return blockState.getValue(LIT)
                    ? createTickerHelper(blockEntityType,
                        GrowthcraftCellarBlockEntities.BREW_KETTLE_BLOCK_ENTITY.get(), BrewKettleBlockEntity::particleTick)
                    : null;
        } else {
            return createTickerHelper(
                    blockEntityType,
                    GrowthcraftCellarBlockEntities.BREW_KETTLE_BLOCK_ENTITY.get(),
                    (worldLevel, pos, state, blockEntity) -> (blockEntity).tick()
            );
        }
    }

    @Override
    public InteractionResult use(BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult hitResult) {
        if (!level.isClientSide) {
            BrewKettleBlockEntity blockEntity = (BrewKettleBlockEntity) level.getBlockEntity(blockPos);

            if (FluidUtil.interactWithFluidHandler(player, interactionHand, level, blockPos, hitResult.getDirection())
                    || player.getItemInHand(interactionHand).getCapability(ForgeCapabilities.FLUID_HANDLER_ITEM).isPresent()) {
                return InteractionResult.SUCCESS;
            } else if (player.isCrouching()) {
                try {
                    // Play sound
                    level.playSound(player, blockPos, SoundEvents.CHEST_OPEN, SoundSource.BLOCKS, 1.0F, 1.0F);

                    NetworkHooks.openScreen(((ServerPlayer) player), blockEntity, blockPos);
                } catch (Exception ex) {
                    GrowthcraftCellar.LOGGER.error(String.format("%s unable to open BrewKettleBlockEntity GUI at %s.", player.getDisplayName().getString(), blockPos));
                    GrowthcraftCellar.LOGGER.error(ex.getMessage());
                    GrowthcraftCellar.LOGGER.error(ex.fillInStackTrace());
                }
            }
            return InteractionResult.SUCCESS;
        }

        return InteractionResult.SUCCESS;
    }

    @Override
    public boolean canSurvive(BlockState p_60525_, LevelReader p_60526_, BlockPos p_60527_) {
        return true;
    }

    public static void makeParticles(Level level, BlockPos blockPos, BlockState blockState) {
        try {
            BrewKettleBlockEntity blockEntity = (BrewKettleBlockEntity) level.getBlockEntity(blockPos);

            if (blockState.getValue(LIT) && blockEntity.getTickClock("current") > 0) {
                RandomSource randomsource = level.getRandom();
                SimpleParticleType simpleparticletype = ParticleTypes.CAMPFIRE_COSY_SMOKE;

                level.addAlwaysVisibleParticle(
                        simpleparticletype,
                        true,
                        (double) blockPos.getX() + 0.5D + randomsource.nextDouble() / 3.0D * (double) (randomsource.nextBoolean() ? 1 : -1),
                        (double) blockPos.getY() + randomsource.nextDouble() + randomsource.nextDouble(),
                        (double) blockPos.getZ() + 0.5D + randomsource.nextDouble() / 3.0D * (double) (randomsource.nextBoolean() ? 1 : -1),
                        0.0D,
                        0.07D,
                        0.0D
                );

                level.playSound(null, blockPos, SoundEvents.FURNACE_FIRE_CRACKLE, SoundSource.BLOCKS, 1.0F, 1.0F);
            }
        } catch (Exception e) {
            GrowthcraftCellar.LOGGER.error("BrewKettleBlockEntity at %d threw an Exception: %s", blockPos.toString(), e.getMessage());
        }

    }


}
