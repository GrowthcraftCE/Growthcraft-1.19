package growthcraft.cellar.block;

import growthcraft.cellar.init.GrowthcraftCellarBlocks;
import growthcraft.cellar.init.GrowthcraftCellarItems;
import growthcraft.cellar.init.config.GrowthcraftCellarConfig;
import growthcraft.core.block.RopeBlock;
import growthcraft.core.init.GrowthcraftTags;
import growthcraft.lib.block.GrowthcraftCropsRopeBlock;
import growthcraft.lib.utils.BlockStateUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

public class HopsCropBlock extends GrowthcraftCropsRopeBlock {

    protected static final VoxelShape[] CUSTOM_SHAPE_BY_AGE = new VoxelShape[]{
            Block.box(6.0D, 0.0D, 6.0D, 10.0D, 5.0D, 10.0D),
            Block.box(6.0D, 0.0D, 6.0D, 10.0D, 5.0D, 10.0D),
            Block.box(6.0D, 0.0D, 6.0D, 10.0D, 5.0D, 10.0D),
            Block.box(6.0D, 0.0D, 6.0D, 10.0D, 5.0D, 10.0D),
            Block.box(4.0D, 0.0D, 4.0D, 12.0D, 16.0D, 12.0D),
            Block.box(4.0D, 0.0D, 4.0D, 12.0D, 16.0D, 12.0D),
            Block.box(4.0D, 0.0D, 4.0D, 12.0D, 16.0D, 12.0D),
            Block.box(4.0D, 0.0D, 4.0D, 12.0D, 16.0D, 12.0D)
    };

    private static final int fruitMax = GrowthcraftCellarConfig.getHopsCropMaxFruitYield();
    private static final int fruitMin = GrowthcraftCellarConfig.getHopsCropMinFruitYield();

    public HopsCropBlock() {
        super();
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
        VoxelShape ropeVoxel = super.getShape(state, worldIn, pos, context);

        ArrayList<VoxelShape> voxelShapeArrayList = new ArrayList<VoxelShape>();
        Map<String, BlockState> blockMap = BlockStateUtils.getSurroundingBlockState(worldIn, pos);

        if (blockMap.get("north").is(GrowthcraftTags.Blocks.ROPE)) voxelShapeArrayList.add(NORTH_BOUNDING_BOX);
        if (blockMap.get("east").is(GrowthcraftTags.Blocks.ROPE)) voxelShapeArrayList.add(EAST_BOUNDING_BOX);
        if (blockMap.get("south").is(GrowthcraftTags.Blocks.ROPE)) voxelShapeArrayList.add(SOUTH_BOUNDING_BOX);
        if (blockMap.get("west").is(GrowthcraftTags.Blocks.ROPE)) voxelShapeArrayList.add(WEST_BOUNDING_BOX);
        if (blockMap.get("above").is(GrowthcraftTags.Blocks.ROPE)) voxelShapeArrayList.add(UP_BOUNDING_BOX);
        if (blockMap.get("below").is(GrowthcraftTags.Blocks.ROPE)) voxelShapeArrayList.add(DOWN_BOUNDING_BOX);

        voxelShapeArrayList.add(CUSTOM_SHAPE_BY_AGE[state.getValue(AGE)]);
        voxelShapeArrayList.add(ropeVoxel);

        VoxelShape[] voxelShapes = new VoxelShape[voxelShapeArrayList.size()];
        voxelShapes = voxelShapeArrayList.toArray(voxelShapes);

        return Shapes.or(KNOT_BOUNDING_BOX, voxelShapes);
    }

    @Override
    public @NotNull ItemStack getCloneItemStack(BlockGetter blockGetter, BlockPos blockPos, BlockState state) {
        return new ItemStack(this.getBaseSeedId());
    }

    protected ItemLike getBaseSeedId() {
        return GrowthcraftCellarItems.HOPS_SEED.get();
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (state.getValue(AGE) == this.getMaxAge()) {
            ItemEntity itemEntity = new ItemEntity(level, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(GrowthcraftCellarItems.HOPS.get()));
            level.addFreshEntity(itemEntity);
            level.setBlock(pos, this.getStateForAge(level, pos, this.getMaxAge() - 1), Block.UPDATE_CLIENTS);
        }
        return InteractionResult.PASS;
    }

    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, Random random) {
        super.randomTick(state, level, pos, random);
        this.tryGrowNewHopsVine(level, pos);
    }

    @Override
    public boolean isValidBonemealTarget(BlockGetter blockGetter, BlockPos pos, BlockState state, boolean isClientSide) {
        return super.isValidBonemealTarget(blockGetter, pos, state, isClientSide) || blockGetter.getBlockState(pos.above()).getBlock() instanceof RopeBlock;
    }

    @Override
    public void performBonemeal(ServerLevel level, Random random, BlockPos pos, BlockState state) {
        super.performBonemeal(level, random, pos, state);
        this.tryGrowNewHopsVine(level, pos);
    }

    private void tryGrowNewHopsVine(ServerLevel level, BlockPos pos) {
        BlockState blockState = level.getBlockState(pos);
        BlockState blockStateAbove = level.getBlockState(pos.above());

        if (this.isMaxAge(blockState)
                && blockStateAbove.is(GrowthcraftTags.Blocks.ROPE)
                && !(blockStateAbove.getBlock() instanceof HopsCropBlock)) {
            level.setBlock(pos.above(), GrowthcraftCellarBlocks.HOPS_VINE.get().defaultBlockState(), Block.UPDATE_ALL);
        }
    }

}
