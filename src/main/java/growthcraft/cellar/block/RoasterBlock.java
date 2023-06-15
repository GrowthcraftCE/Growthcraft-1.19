package growthcraft.cellar.block;

import growthcraft.cellar.GrowthcraftCellar;
import growthcraft.cellar.block.entity.RoasterBlockEntity;
import growthcraft.cellar.init.GrowthcraftCellarBlockEntities;
import growthcraft.core.init.GrowthcraftTags;
import growthcraft.core.utils.BlockPropertiesUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
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
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.Nullable;

public class RoasterBlock extends BaseEntityBlock {

    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final BooleanProperty LIT = BooleanProperty.create("lit");
    public static final BooleanProperty SIGNAL_FIRE = BooleanProperty.create("signal_fire");

    public static final IntegerProperty ROASTING_LEVEL = IntegerProperty.create("roasting_level", 1, 8);

    public RoasterBlock() {
        this(getInitProperties());
    }

    protected RoasterBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(
                this.stateDefinition.any()
                        .setValue(FACING, Direction.NORTH)
                        .setValue(LIT, false)
                        .setValue(ROASTING_LEVEL, 1)
                        .setValue(SIGNAL_FIRE, false)
        );
    }

    private static Properties getInitProperties() {
        Properties properties = BlockBehaviour.Properties.copy(Blocks.FURNACE);
        properties.strength(1.5F);
        properties.noOcclusion();
        properties.isValidSpawn(BlockPropertiesUtils::never);
        properties.isRedstoneConductor(BlockPropertiesUtils::never);
        properties.isViewBlocking(BlockPropertiesUtils::never);
        return properties;
    }

    @Override
    public RenderShape getRenderShape(BlockState blockState) {
        return RenderShape.MODEL;
    }

    //TODO[9]: Add smoke particles if roaster is processing.
    // CampfireBlock makeparticles

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> blockStateBuilder) {
        blockStateBuilder.add(FACING, LIT, ROASTING_LEVEL, SIGNAL_FIRE);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return GrowthcraftCellarBlockEntities.ROASTER_BLOCK_ENTITY.get().create(blockPos, blockState);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState()
                .setValue(FACING, context.getHorizontalDirection().getOpposite())
                .setValue(LIT, false)
                .setValue(SIGNAL_FIRE, false);
    }

    @Override
    public PushReaction getPistonPushReaction(BlockState blockState) {
        return PushReaction.DESTROY;
    }

    public BlockState rotate(BlockState state, Rotation rotation) {
        return state.setValue(FACING, rotation.rotate(state.getValue(FACING)))
                .setValue(LIT, state.getValue(LIT))
                .setValue(ROASTING_LEVEL, state.getValue(ROASTING_LEVEL))
                .setValue(SIGNAL_FIRE, state.getValue(SIGNAL_FIRE));
    }

    public BlockState mirror(BlockState state, Mirror mirror) {
        return state.rotate(mirror.getRotation(state.getValue(FACING)))
                .setValue(LIT, state.getValue(LIT))
                .setValue(ROASTING_LEVEL, state.getValue(ROASTING_LEVEL))
                .setValue(SIGNAL_FIRE, state.getValue(SIGNAL_FIRE));
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState blockState, BlockEntityType<T> blockEntityType) {
        if (level.isClientSide) {
            return blockState.getValue(LIT) ? createTickerHelper(blockEntityType, GrowthcraftCellarBlockEntities.ROASTER_BLOCK_ENTITY.get(), RoasterBlockEntity::particleTick) : null;
        } else {
            return createTickerHelper(
                    blockEntityType,
                    GrowthcraftCellarBlockEntities.ROASTER_BLOCK_ENTITY.get(),
                    (worldLevel, pos, state, blockEntity) -> (blockEntity).tick()
            );
        }
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos blockPos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (!level.isClientSide) {
            if (player.isCrouching()) {
                // Open the GUI
                try {
                    // Play sound
                    level.playSound(player, blockPos, SoundEvents.IRON_DOOR_OPEN, SoundSource.BLOCKS, 1.0F, 1.0F);
                    RoasterBlockEntity blockEntity = (RoasterBlockEntity) level.getBlockEntity(blockPos);
                    NetworkHooks.openScreen(((ServerPlayer) player), blockEntity, blockPos);
                } catch (Exception ex) {
                    GrowthcraftCellar.LOGGER.error(String.format("%s unable to open RoasterBlockEntity GUI at %s.", player.getDisplayName().getString(), blockPos));
                    GrowthcraftCellar.LOGGER.error(ex.getMessage());
                    GrowthcraftCellar.LOGGER.error(ex.fillInStackTrace());
                }
                return InteractionResult.SUCCESS;
            } else if (player.getItemInHand(hand).is(GrowthcraftTags.Items.ROASTER_WRENCH)) {
                // Then cycle the roaster level
                RoasterBlockEntity blockEntity = (RoasterBlockEntity) level.getBlockEntity(blockPos);
                // If the tick current is 0 then we are not currently processing anything.
                if (blockEntity.getTickClock("current") == 0) {
                    blockEntity.incrementRoastingLevel();
                }
            }
        }

        return InteractionResult.SUCCESS;
    }


    @Override
    public void onRemove(BlockState blockState, Level level, BlockPos blockPos, BlockState newBlockState, boolean isMoving) {
        if (blockState.getBlock() != newBlockState.getBlock()) {
            try {
                RoasterBlockEntity blockEntity = (RoasterBlockEntity) level.getBlockEntity(blockPos);
                blockEntity.dropItems();
            } catch (Exception ex) {
                GrowthcraftCellar.LOGGER.error(String.format("Invalid blockEntity type at %s, expected RoasterBlockEntity", blockPos));
            }
        }
        super.onRemove(blockState, level, blockPos, newBlockState, isMoving);
    }

    public static void makeParticles(Level level, BlockPos blockPos, BlockState blockState) {
        try {
            RoasterBlockEntity blockEntity = (RoasterBlockEntity) level.getBlockEntity(blockPos);

            if (blockState.getValue(LIT) && blockEntity.getTickClock("current") > 0) {
                RandomSource randomsource = level.getRandom();
                SimpleParticleType simpleparticletype = ParticleTypes.CAMPFIRE_COSY_SMOKE;

                if (true) {
                    level.addAlwaysVisibleParticle(
                            simpleparticletype,
                            true,
                            (double) blockPos.getX() + 0.5D + randomsource.nextDouble() / 3.0D * (double) (randomsource.nextBoolean() ? 1 : -1),
                            (double) blockPos.getY() + randomsource.nextDouble() + randomsource.nextDouble(),
                            (double) blockPos.getZ() + 0.5D + randomsource.nextDouble() / 3.0D * (double) (randomsource.nextBoolean() ? 1 : -1),
                            0.0D,
                            0.01D,
                            0.0D
                    );
                }
                if (true) {
                    level.addParticle(
                            ParticleTypes.SMOKE,
                            (double) blockPos.getX() + 0.5D + randomsource.nextDouble() / 4.0D * (double) (randomsource.nextBoolean() ? 1 : -1),
                            (double) blockPos.getY() + 0.4D, (double) blockPos.getZ() + 0.5D + randomsource.nextDouble() / 4.0D * (double) (randomsource.nextBoolean() ? 1 : -1),
                            0.0D,
                            0.005D,
                            0.0D
                    );
                }
            }
        } catch (Exception e) {
            GrowthcraftCellar.LOGGER.error("RoasterBlockEntity at %d threw an Exception: %s", blockPos.toString(), e.getMessage());
        }

    }


}
