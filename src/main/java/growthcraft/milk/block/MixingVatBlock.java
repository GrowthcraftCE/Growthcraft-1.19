package growthcraft.milk.block;

import growthcraft.core.utils.BlockPropertiesUtils;
import growthcraft.lib.utils.BlockStateUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import org.jetbrains.annotations.Nullable;

public class MixingVatBlock extends BaseEntityBlock {
    //TODO[5]: Implement MixingVatBlock
    public static final BooleanProperty LIT = BooleanProperty.create("lit");

    public MixingVatBlock() {
        this(getInitProperties());
    }

    public MixingVatBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(LIT, false));

    }

    private static Properties getInitProperties() {
        Properties properties = Properties.copy(Blocks.STONE);
        properties.noOcclusion();
        properties.sound(SoundType.METAL);
        properties.isRedstoneConductor(BlockPropertiesUtils::never);
        properties.isViewBlocking(BlockPropertiesUtils::never);
        return properties;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> stateBuilder) {
        super.createBlockStateDefinition(stateBuilder.add(LIT));
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState()
                .setValue(LIT, BlockStateUtils.isHeated(context.getLevel(), context.getClickedPos()));
    }

    @Override
    public RenderShape getRenderShape(BlockState blockState) {
        return RenderShape.MODEL;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return null;
    }

    @Nullable
    @Override
    public MenuProvider getMenuProvider(BlockState p_49234_, Level p_49235_, BlockPos p_49236_) {
        return super.getMenuProvider(p_49234_, p_49235_, p_49236_);
    }
}
