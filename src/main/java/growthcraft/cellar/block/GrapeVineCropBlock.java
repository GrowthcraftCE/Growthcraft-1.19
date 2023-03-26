package growthcraft.cellar.block;

import growthcraft.core.block.RopeBlock;
import growthcraft.lib.block.GrowthcraftCropsRopeBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.ArrayList;

public class GrapeVineCropBlock extends GrowthcraftCropsRopeBlock {

    protected static final VoxelShape[] CUSTOM_SHAPE_BY_AGE = new VoxelShape[]{
            Block.box(7.0D, 0.0D, 7.0D, 9.0D, 5.0D, 9.0D),
            Block.box(7.0D, 0.0D, 7.0D, 9.0D, 5.0D, 9.0D),
            Block.box(7.0D, 0.0D, 7.0D, 9.0D, 5.0D, 9.0D),
            Block.box(7.0D, 0.0D, 7.0D, 9.0D, 5.0D, 9.0D),
            Block.box(6.0D, 0.0D, 6.0D, 10.0D, 16.0D, 10.0D),
            Block.box(6.0D, 0.0D, 6.0D, 10.0D, 16.0D, 10.0D),
            Block.box(6.0D, 0.0D, 6.0D, 10.0D, 16.0D, 10.0D),
            Block.box(6.0D, 0.0D, 6.0D, 10.0D, 16.0D, 10.0D),
    };
    private final GrapeVineLeavesCropBlock grapeVineLeavesCropBlock;

    public GrapeVineCropBlock(GrapeVineLeavesCropBlock grapeVineLeavesCropBlock) {
        super();
        this.grapeVineLeavesCropBlock = grapeVineLeavesCropBlock;
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter blockGetter, BlockPos pos, CollisionContext context) {
        VoxelShape ropeVoxel = super.getShape(state, blockGetter, pos, context);

        ArrayList<VoxelShape> voxelShapeArrayList = new ArrayList<VoxelShape>();
        voxelShapeArrayList.add(CUSTOM_SHAPE_BY_AGE[state.getValue(AGE)]);
        voxelShapeArrayList.add(ropeVoxel);

        VoxelShape[] voxelShapes = new VoxelShape[voxelShapeArrayList.size()];
        voxelShapes = voxelShapeArrayList.toArray(voxelShapes);

        return Shapes.or(KNOT_BOUNDING_BOX, voxelShapes);
    }

    @Override
    public boolean isRandomlyTicking(BlockState state) {
        return true;
    }

    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        super.randomTick(state, level, pos, random);

        BlockPos oneAboveBlockPos = pos.above();
        BlockPos twoAboveBlockPos = pos.above(2);

        BlockState oneAboveBlockState = level.getBlockState(oneAboveBlockPos);
        BlockState twoAboveBlockState = level.getBlockState(twoAboveBlockPos);

        if (state.getValue(AGE) == this.getMaxAge() && oneAboveBlockState.getBlock() instanceof RopeBlock) {
            if (twoAboveBlockState.getBlock() instanceof RopeBlock) {
                // Then keep growing vines.
                level.setBlock(pos.above(), state.getBlock().defaultBlockState(), Block.UPDATE_ALL);
            } else {
                // Else, we need to grow a GrapeVineLeavesCropBlock.
                level.setBlock(pos.above(), this.getGrapeVineLeavesCropBlock().defaultBlockState(), Block.UPDATE_ALL_IMMEDIATE);
            }
        }
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        return InteractionResult.PASS;
    }

    public GrapeVineLeavesCropBlock getGrapeVineLeavesCropBlock() {
        return this.grapeVineLeavesCropBlock;
    }
}
