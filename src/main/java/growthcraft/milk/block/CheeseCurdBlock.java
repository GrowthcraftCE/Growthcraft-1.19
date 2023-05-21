package growthcraft.milk.block;

import growthcraft.core.utils.BlockPropertiesUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.IForgeShearable;
import net.minecraftforge.common.Tags;

import java.awt.*;

public class CheeseCurdBlock extends Block implements IForgeShearable {
    //TODO[11]: Implement CheeseCurdBlock

    public static final IntegerProperty AGE = BlockStateProperties.AGE_7;
    private final int color;

    public static final VoxelShape VOXEL_SHAPE = Block.box(
            4.0D, 2.0D, 4.0D,
            12.0D, 8.0D, 12.0D
    );

    public CheeseCurdBlock(Color color) {
        super(getInitProperties());
        this.color = color.getRGB();
    }

    private static Properties getInitProperties() {
        Properties properties = Properties.copy(Blocks.WHITE_WOOL);
        properties.noOcclusion();
        properties.randomTicks();
        properties.isValidSpawn(BlockPropertiesUtils::never);
        properties.isRedstoneConductor(BlockPropertiesUtils::never);
        properties.isViewBlocking(BlockPropertiesUtils::never);
        return properties;
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    public VoxelShape getShape(BlockState p_60555_, BlockGetter p_60556_, BlockPos p_60557_, CollisionContext p_60558_) {
        return VOXEL_SHAPE;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> stateBuilder) {
        super.createBlockStateDefinition(stateBuilder.add(AGE));
    }

    public IntegerProperty getAgeProperty() {
        return AGE;
    }

    protected int getAge(BlockState state) {
        return state.getValue(this.getAgeProperty());
    }

    public int getMaxAge() {
        return 7;
    }

    public boolean isMaxAge(BlockState state) {
        return state.getValue(this.getAgeProperty()) >= this.getMaxAge();
    }

    public BlockState getStateForAge(int age) {
        return this.defaultBlockState().setValue(this.getAgeProperty(), age);
    }

    @Override
    public boolean canSurvive(BlockState blockState, LevelReader levelReader, BlockPos blockPos) {
        return !levelReader.getBlockState(blockPos.above()).isAir();
    }

    @Override
    public boolean isRandomlyTicking(BlockState blockState) {
        return !this.isMaxAge(blockState);
    }

    @Override
    public void randomTick(BlockState blockState, ServerLevel level, BlockPos blockPos, RandomSource randomSource) {
        super.randomTick(blockState, level, blockPos, randomSource);

        if (!level.isAreaLoaded(blockPos, 1)) { return; }

        int i = this.getAge(blockState);
        level.setBlock(blockPos, this.getStateForAge(i + 1), Block.UPDATE_ALL_IMMEDIATE);
    }

    @Override
    public InteractionResult use(BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult) {
        ItemStack heldItemStack = player.getItemInHand(interactionHand);

        if(level != null && !level.isClientSide && heldItemStack.is(Tags.Items.SHEARS)) {
            if(blockState.getValue(AGE) != this.getMaxAge()) {
                ItemStack itemStack = new ItemStack(blockState.getBlock(), 1);
                ItemEntity itemEntity = new ItemEntity(level, blockPos.getX(), blockPos.getY(), blockPos.getZ(), itemStack);
                level.addFreshEntity(itemEntity);
                // Destory the current block.
                level.destroyBlock(blockPos, true);
                return InteractionResult.SUCCESS;
            } else if (blockState.getValue(AGE) == 7)  {
                // Simply destroy the block and let the loot table figure out the drops.
                level.destroyBlock(blockPos, true);
                return InteractionResult.SUCCESS;
            }
        }

        return super.use(blockState, level, blockPos, player, interactionHand, blockHitResult);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void animateTick(BlockState blockState, Level level, BlockPos blockPos, RandomSource randomSource) {
        if (blockState.getValue(AGE) < 7) {
            if (randomSource.nextInt(16) <= 4) {
                BlockPos blockPosBelow = blockPos.below();
                if (level.getBlockState(blockPosBelow).isAir()) {

                    double d0 = (double) blockPos.getX() + randomSource.nextDouble() / 2.0D;
                    double d1 = (double) blockPos.getY() - 0.05D;
                    double d2 = (double) blockPos.getZ() + randomSource.nextDouble() / 2.0D;

                    level.addParticle(ParticleTypes.FALLING_HONEY,
                            d0, d1, d2,
                            0.0D, 0.0D, 0.0D
                    );
                }
            }
        }
    }
}
