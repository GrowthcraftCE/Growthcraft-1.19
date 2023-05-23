package growthcraft.milk.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
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
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> stateBuilder) {
        super.createBlockStateDefinition(stateBuilder.add(ROTATION));
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(ROTATION, 0);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos p_153215_, BlockState p_153216_) {
        //TODO[63]: Instantiate CheesePressBlockEntity
        return null;
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState blockState, BlockEntityType<T> entityType) {
        return super.getTicker(level, blockState, entityType);
    }

    @Override
    public InteractionResult use(BlockState p_60503_, Level p_60504_, BlockPos p_60505_, Player p_60506_, InteractionHand p_60507_, BlockHitResult p_60508_) {
        // If player has the wrench, then tighten or loosen the ROTATION. Crouching tightens the vice, otherwise loosen it.

        return super.use(p_60503_, p_60504_, p_60505_, p_60506_, p_60507_, p_60508_);
    }
}
