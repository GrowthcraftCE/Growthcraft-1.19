package growthcraft.milk.block;

import growthcraft.core.init.GrowthcraftItems;
import growthcraft.milk.GrowthcraftMilk;
import growthcraft.milk.block.entity.CheesePressBlockEntity;
import growthcraft.milk.init.GrowthcraftMilkBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
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
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class CheesePressBlock extends BaseEntityBlock {
    //TODO[63]: Implement CheesePressBlock

    public static final IntegerProperty ROTATION = IntegerProperty.create("rotation", 0, 7);

    public static final VoxelShape BOUNDING_BOX = Block.box(
            0.01F, 0.0F, 0.01F,
            15.98F, 15.98F, 15.98
    );

    public CheesePressBlock() {
        this(getInitProperties());
    }

    protected CheesePressBlock(Properties properties) {
        super(properties);
    }

    private static Properties getInitProperties() {
        Properties properties = Properties.copy(Blocks.CHEST);
        properties.noOcclusion();
        return properties;
    }

    @Override
    public RenderShape getRenderShape(BlockState p_49232_) {
        return RenderShape.MODEL;
    }

    @Override
    public VoxelShape getShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext context) {
        return BOUNDING_BOX;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> stateBuilder) {
        super.createBlockStateDefinition(stateBuilder.add(ROTATION));
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(ROTATION, 0);
    }

    @Override
    public PushReaction getPistonPushReaction(BlockState p_60584_) {
        return PushReaction.DESTROY;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return GrowthcraftMilkBlockEntities.CHEESE_PRESS_BLOCK_ENTITY.get().create(blockPos, blockState);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState blockState, BlockEntityType<T> entityType) {
        if (level.isClientSide) {
            return blockState.getValue(ROTATION) == 7 ? createTickerHelper(entityType, GrowthcraftMilkBlockEntities.CHEESE_PRESS_BLOCK_ENTITY.get(), CheesePressBlockEntity::particleTick) : null;
        } else {
            return createTickerHelper(
                    entityType,
                    GrowthcraftMilkBlockEntities.CHEESE_PRESS_BLOCK_ENTITY.get(),
                    (worldLevel, pos, state, blockEntity) -> (blockEntity).tick()
            );
        }
    }

    @Override
    public InteractionResult use(BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult) {
        // If player has the wrench, then tighten or loosen the ROTATION. Crouching tightens the vice, otherwise loosen it.
        if(level != null && !level.isClientSide) {
            CheesePressBlockEntity blockEntity = (CheesePressBlockEntity) level.getBlockEntity(blockPos);
            if(blockEntity == null) return InteractionResult.FAIL;

            if(player.getItemInHand(interactionHand).getItem() == GrowthcraftItems.WRENCH.get()) {
                if(!player.isCrouching()) {
                    // Then tighten the cheese press.
                    level.playSound(null, blockPos, SoundEvents.CHAIN_PLACE, SoundSource.BLOCKS);
                    level.setBlock(blockPos, blockState.setValue(ROTATION, blockEntity.doRotation(true)), Block.UPDATE_ALL_IMMEDIATE);
                } else {
                    // Then loosen the cheese press and extract the result item.
                    level.playSound(null, blockPos, SoundEvents.CHAIN_BREAK, SoundSource.BLOCKS);
                    level.setBlock(blockPos, blockState.setValue(ROTATION, blockEntity.doRotation(false)), Block.UPDATE_ALL_IMMEDIATE);

                    ItemStack resultItemStack = blockEntity.getItemStackHandler().getStackInSlot(1).copy();
                    if(!resultItemStack.isEmpty() && !player.getInventory().add(resultItemStack)) {
                        ItemEntity itemEntity = new ItemEntity(level, blockPos.getX(), blockPos.getY(), blockPos.getZ(), resultItemStack);
                        level.addFreshEntity(itemEntity);
                    }
                }

                return InteractionResult.SUCCESS;
            } else if (!player.getItemInHand(interactionHand).isEmpty() && blockEntity.isOpen()
                        && blockEntity.getItemStackHandler().getStackInSlot(0).isEmpty()){
                ItemStack itemStackToInsert = player.getItemInHand(interactionHand).copy();
                itemStackToInsert.setCount(1);
                blockEntity.getItemStackHandler().insertItem(0, itemStackToInsert, false);
                player.getItemInHand(interactionHand).shrink(1);

                return InteractionResult.SUCCESS;
            }

        }

        return InteractionResult.PASS;
    }

    @Override
    public void onRemove(BlockState blockState, Level level, BlockPos blockPos, BlockState newBlockState, boolean isMoving) {
        if (blockState.getBlock() != newBlockState.getBlock()) {
            try {
                CheesePressBlockEntity blockEntity = (CheesePressBlockEntity) level.getBlockEntity(blockPos);
                blockEntity.dropItems();
            } catch (Exception ex) {
                GrowthcraftMilk.LOGGER.error(String.format("Invalid blockEntity type at %s, expected RoasterBlockEntity", blockPos));
            }
        }
        super.onRemove(blockState, level, blockPos, newBlockState, isMoving);
    }
}
