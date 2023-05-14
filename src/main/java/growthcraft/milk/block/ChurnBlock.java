package growthcraft.milk.block;

import growthcraft.milk.GrowthcraftMilk;
import growthcraft.milk.block.entity.ChurnBlockEntity;
import growthcraft.milk.init.GrowthcraftMilkBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RenderShape;
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

import java.util.ArrayList;

public class ChurnBlock extends BaseEntityBlock {

    public static final VoxelShape LAYER0_BOUNDING_BOX = Block.box(2.0D, 0.0D, 2.0D, 14.0D, 5.0D, 14.0D);
    public static final VoxelShape LAYER1_BOUNDING_BOX = Block.box(3.0D, 5.0D, 3.0D, 13.0D, 10.0D, 13.0D);
    public static final VoxelShape LAYER2_BOUNDING_BOX = Block.box(4.0D, 10.0D, 4.0D, 12.0D, 16.0D, 12.0D);
    public static final VoxelShape PLUNGER_DOWN_BOUNDING_BOX = Block.box(7.0D, 4.0D, 7.0D, 9.0D, 20.0D, 9.0D);
    public static final VoxelShape PLUNGER_UP_BOUNDING_BOX = Block.box(7.0D, 4.0D, 7.0D, 9.0D, 28.0D, 9.0D);

    public static final BooleanProperty PLUNGED = BooleanProperty.create("plunged");

    public ChurnBlock() {
        this(getInitProperties());
    }

    public ChurnBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(PLUNGED, false));
    }

    private static Properties getInitProperties() {
        Properties properties = Properties.copy(Blocks.OAK_PLANKS);
        properties.noOcclusion();
        return properties;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return GrowthcraftMilkBlockEntities.CHURN_BLOCK_ENTITY.get().create(blockPos, blockState);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState blockState, BlockEntityType<T> blockEntityType) {
        return createTickerHelper(
                blockEntityType,
                GrowthcraftMilkBlockEntities.CHURN_BLOCK_ENTITY.get(),
                (worldLevel, pos, state, blockEntity) -> (blockEntity).tick()
        );
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos blockPos, CollisionContext context) {
        ArrayList<VoxelShape> voxelShapeArrayList = new ArrayList<>();
        voxelShapeArrayList.add(LAYER0_BOUNDING_BOX);
        voxelShapeArrayList.add(LAYER1_BOUNDING_BOX);
        voxelShapeArrayList.add(LAYER2_BOUNDING_BOX);

        if (Boolean.TRUE.equals(state.getValue(PLUNGED))) {
            voxelShapeArrayList.add(PLUNGER_DOWN_BOUNDING_BOX);
        } else {
            voxelShapeArrayList.add(PLUNGER_UP_BOUNDING_BOX);
        }

        VoxelShape[] voxelShapes = new VoxelShape[voxelShapeArrayList.size()];
        voxelShapes = voxelShapeArrayList.toArray(voxelShapes);

        return Shapes.or(LAYER0_BOUNDING_BOX, voxelShapes);
    }

    @Override
    public RenderShape getRenderShape(BlockState blockState) {
        return RenderShape.MODEL;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> blockStateBuilder) {
        super.createBlockStateDefinition(blockStateBuilder.add(PLUNGED));
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return super.getStateForPlacement(context).setValue(PLUNGED, false);
    }

    @Override
    public InteractionResult use(BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult) {
        if (!level.isClientSide && player.isCrouching()) {
            try {
                // Play sound
                level.playSound(player, blockPos, SoundEvents.BARREL_OPEN, SoundSource.BLOCKS, 1.0F, 1.0F);
                ChurnBlockEntity blockEntity = (ChurnBlockEntity) level.getBlockEntity(blockPos);
                NetworkHooks.openScreen(((ServerPlayer) player), blockEntity, blockPos);
            } catch (Exception ex) {
                GrowthcraftMilk.LOGGER.error(String.format("%s unable to open ChurnBlockEntity GUI at %s.", player.getDisplayName().getString(), blockPos));
                GrowthcraftMilk.LOGGER.error(ex.getMessage());
                GrowthcraftMilk.LOGGER.error(ex.fillInStackTrace());
            }
            return InteractionResult.SUCCESS;
        }

        // TODO: Refactor the logic move getItemInHand to initial condition.
        if (!level.isClientSide) {
            if (
                    FluidUtil.interactWithFluidHandler(player, interactionHand, level, blockPos, blockHitResult.getDirection())
                            || player.getItemInHand(interactionHand).getCapability(ForgeCapabilities.FLUID_HANDLER_ITEM).isPresent()
            ) {
                return InteractionResult.SUCCESS;
            }
        }

        if(!level.isClientSide) {
            ChurnBlockEntity blockEntity = (ChurnBlockEntity) level.getBlockEntity(blockPos);

            if(blockEntity.hasByProductItem()) {
                ItemStack itemStack = blockEntity.getInventoryHandler().getStackInSlot(0);
                if(!player.getInventory().add(itemStack)) {
                    player.drop(itemStack, false);
                }
            } else {
                blockEntity.togglePlungerState();
            }

        }


        return InteractionResult.SUCCESS;
    }

    @Override
    public PushReaction getPistonPushReaction(BlockState blockState) {
        return PushReaction.DESTROY;
    }
}
