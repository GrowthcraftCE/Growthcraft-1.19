package growthcraft.apples.block;

import growthcraft.apples.init.GrowthcraftApplesBlocks;
import growthcraft.core.utils.BlockPropertiesUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;

import java.util.ArrayList;
import java.util.List;

public class AppleTreeLeaves extends LeavesBlock {

    private static final int APPLE_CHECK_AREA = 3;
    private static final int MAX_APPLES_IN_AREA = 5;
    private static final int TICK_DELAY = 1;

    public AppleTreeLeaves() {
        this(getInitProperties());
    }

    public AppleTreeLeaves(Properties p_54422_) {
        super(p_54422_);
    }

    @Override
    public boolean isRandomlyTicking(BlockState p_54449_) {
        return true;
    }

    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        super.randomTick(state, level, pos, random);
        if (level.getBlockState(pos.below()).isAir()) {
            this.tryGrowAppleFruit(level, pos, random);
        }
    }

    private void tryGrowAppleFruit(ServerLevel level, BlockPos pos, RandomSource random) {

        BlockPos posLowerBound = pos.below(1).south(APPLE_CHECK_AREA).west(APPLE_CHECK_AREA);
        BlockPos posUpperBound = pos.below(1).north(APPLE_CHECK_AREA).east(APPLE_CHECK_AREA);
        int applesInArea = 0;

        List<BlockPos> validGrowthPos = new ArrayList<>();

        for (BlockPos p : BlockPos.betweenClosed(posLowerBound, posUpperBound)) {
            BlockPos surroundingPos = p.immutable();
            if (level.getBlockState(surroundingPos).getBlock() == GrowthcraftApplesBlocks.APPLE_TREE_FRUIT.get()) {
                applesInArea++;
            } else if (level.getBlockState(surroundingPos).isAir() && level.getBlockState(surroundingPos.above()).getBlock() == GrowthcraftApplesBlocks.APPLE_TREE_LEAVES.get()) {
                validGrowthPos.add(surroundingPos);
            }
        }

        if (applesInArea < MAX_APPLES_IN_AREA && validGrowthPos.size() > 0) {
            BlockPos spawnBlockPos = validGrowthPos.get(random.nextInt(validGrowthPos.size()));

            if (level.getBlockState(spawnBlockPos).isAir() && level.getBlockState(spawnBlockPos.above()).getBlock() == GrowthcraftApplesBlocks.APPLE_TREE_LEAVES.get()) {
                level.setBlock(spawnBlockPos, GrowthcraftApplesBlocks.APPLE_TREE_FRUIT.get().defaultBlockState(), 2);
            }
        }

    }

    private static Properties getInitProperties() {
        Properties properties = Properties.of(Material.LEAVES);
        properties.strength(0.2F);
        properties.randomTicks();
        properties.sound(SoundType.GRASS);
        properties.noOcclusion();
        properties.isViewBlocking(BlockPropertiesUtils::never);
        properties.isSuffocating(BlockPropertiesUtils::never);
        return properties;
    }
}
