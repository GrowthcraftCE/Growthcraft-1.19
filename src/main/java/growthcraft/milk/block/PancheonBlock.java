package growthcraft.milk.block;

import growthcraft.milk.GrowthcraftMilk;
import growthcraft.milk.block.entity.PancheonBlockEntity;
import growthcraft.milk.init.GrowthcraftMilkBlockEntities;
import growthcraft.milk.init.GrowthcraftMilkFluids;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
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
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.Nullable;

public class PancheonBlock extends BaseEntityBlock {

    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

    public static final VoxelShape BASE_BOUNDING_BOX = Block.box(
            1.0D, 0.0D, 1.0D,
            15.0F, 1.0D, 15.0F
    );
    public static final VoxelShape UPPER_BOUNDING_BOX = Block.box(
            0.0D, 1.0D, 0.0D,
            16.0, 5.0D, 16.0D
    );

    public PancheonBlock() {
        super(getInitProperties());
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
    }

    private static Properties getInitProperties() {
        Properties properties = Properties.copy(Blocks.STONE);
        properties.strength(1.5F);
        properties.noOcclusion();
        return properties;
    }

    @Override
    public RenderShape getRenderShape(BlockState blockState) {
        return RenderShape.MODEL;
    }

    @Override
    public VoxelShape getShape(BlockState p_60555_, BlockGetter p_60556_, BlockPos p_60557_, CollisionContext p_60558_) {
        return Shapes.or(BASE_BOUNDING_BOX, UPPER_BOUNDING_BOX);
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
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState state) {
        return GrowthcraftMilkBlockEntities.PANCHEON_BLOCK_ENTITY.get().create(blockPos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> blockEntityType) {
        return createTickerHelper(
                blockEntityType,
                GrowthcraftMilkBlockEntities.PANCHEON_BLOCK_ENTITY.get(),
                (worldLevel, pos, blockState, blockEntity) -> (blockEntity).tick()
        );
    }

    @Override
    public InteractionResult use(BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult) {
        if (!level.isClientSide && player.isCrouching()) {
            try {
                // Play sound
                level.playSound(player, blockPos, SoundEvents.BARREL_OPEN, SoundSource.BLOCKS, 1.0F, 1.0F);
                // Open the GUI
                PancheonBlockEntity blockEntity = (PancheonBlockEntity) level.getBlockEntity(blockPos);
                NetworkHooks.openScreen(((ServerPlayer) player), blockEntity, blockPos);
            } catch (Exception ex) {
                GrowthcraftMilk.LOGGER.error(String.format("%s unable to open PancheonBlockEntity GUI at %s.", player.getDisplayName().getString(), blockPos));
                GrowthcraftMilk.LOGGER.error(ex.getMessage());
                GrowthcraftMilk.LOGGER.error(ex.fillInStackTrace());
            }
            return InteractionResult.SUCCESS;
        }

        if (!level.isClientSide) {
            if (player.getItemInHand(interactionHand).getItem() == Items.MILK_BUCKET) {
                PancheonBlockEntity blockEntity = (PancheonBlockEntity) level.getBlockEntity(blockPos);

                int capacity = blockEntity.getFluidTank(0).getCapacity();
                int amount = blockEntity.getFluidTank(0).getFluidAmount();
                int remainingFill = capacity - amount;

                if(blockEntity.getFluidTank(0).isEmpty()
                    || (remainingFill >= 1000
                        && blockEntity.getFluidStackInTank(0).getFluid().getFluidType() == GrowthcraftMilkFluids.MILK.source.get().getFluidType())
                ) {
                    FluidStack fluidStack = new FluidStack( GrowthcraftMilkFluids.MILK.source.get().getSource(), 1000);
                    blockEntity.getFluidTank(0).fill(fluidStack, IFluidHandler.FluidAction.EXECUTE);
                    player.setItemInHand(interactionHand, new ItemStack(Items.BUCKET));
                }
            } else if (
                    FluidUtil.interactWithFluidHandler(player, interactionHand, level, blockPos, blockHitResult.getDirection())
                    || player.getItemInHand(interactionHand).getCapability(ForgeCapabilities.FLUID_HANDLER_ITEM).isPresent()
            ) {
                GrowthcraftMilk.LOGGER.error("Can interactWithFluidHandler or has FLUID_HANDLER_ITEM");
                return InteractionResult.SUCCESS;
            }
        }

        return InteractionResult.SUCCESS;
    }

}
