package growthcraft.lib.utils;

import growthcraft.core.block.RopeBlock;
import growthcraft.core.init.GrowthcraftTags;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import java.util.HashMap;
import java.util.Map;

public class BlockStateUtils {

    public static Map<String, BlockState> getSurroundingBlockState(BlockGetter blockGetter, BlockPos pos) {
        Map<String, BlockState> blockStateMap = new HashMap<>();

        blockStateMap.put("north", blockGetter.getBlockState(pos.north()));
        blockStateMap.put("north-east", blockGetter.getBlockState(pos.north().east()));
        blockStateMap.put("east", blockGetter.getBlockState(pos.east()));
        blockStateMap.put("south-east", blockGetter.getBlockState(pos.south().east()));
        blockStateMap.put("south", blockGetter.getBlockState(pos.south()));
        blockStateMap.put("south-west", blockGetter.getBlockState(pos.south().west()));
        blockStateMap.put("west", blockGetter.getBlockState(pos.west()));
        blockStateMap.put("north-west", blockGetter.getBlockState(pos.north().west()));
        blockStateMap.put("above", blockGetter.getBlockState(pos.above()));
        blockStateMap.put("below", blockGetter.getBlockState(pos.below()));

        return blockStateMap;
    }

    public static Map<String, BlockState> getSurroundingBlockState(Level level, BlockPos pos) {
        Map<String, BlockState> blockStateMap = new HashMap<>();

        blockStateMap.put("north", level.getBlockState(pos.north()));
        blockStateMap.put("north-east", level.getBlockState(pos.north().east()));
        blockStateMap.put("east", level.getBlockState(pos.east()));
        blockStateMap.put("south-east", level.getBlockState(pos.south().east()));
        blockStateMap.put("south", level.getBlockState(pos.south()));
        blockStateMap.put("south-west", level.getBlockState(pos.south().west()));
        blockStateMap.put("west", level.getBlockState(pos.west()));
        blockStateMap.put("north-west", level.getBlockState(pos.north().west()));
        blockStateMap.put("above", level.getBlockState(pos.above()));
        blockStateMap.put("below", level.getBlockState(pos.below()));

        return blockStateMap;
    }


    public static boolean isRopeBlock(BlockState blockState) {
        return blockState.getBlock() instanceof RopeBlock || blockState.is(GrowthcraftTags.Blocks.ROPE);
    }

    public static boolean isRopeConnected(BlockGetter world, BlockPos pos) {
        Map<String, BlockState> blockMap = BlockStateUtils.getSurroundingBlockState(world, pos);

        if (BlockStateUtils.isRopeBlock(blockMap.get("north"))) return true;
        if (BlockStateUtils.isRopeBlock(blockMap.get("east"))) return true;
        if (BlockStateUtils.isRopeBlock(blockMap.get("south"))) return true;
        if (BlockStateUtils.isRopeBlock(blockMap.get("west"))) return true;
        if (BlockStateUtils.isRopeBlock(blockMap.get("above"))) return true;
        return BlockStateUtils.isRopeBlock(blockMap.get("below"));
    }

    public static Map<String, Boolean> getSurroundingRopeConnections(BlockGetter blockGetter, BlockPos pos) {
        Map<String, Boolean> blockStateMap = new HashMap<>();

        blockStateMap.put("north", blockGetter.getBlockState(pos.north()).is(GrowthcraftTags.Blocks.ROPE));
        blockStateMap.put("north-east", blockGetter.getBlockState(pos.north().east()).is(GrowthcraftTags.Blocks.ROPE));
        blockStateMap.put("east", blockGetter.getBlockState(pos.east()).is(GrowthcraftTags.Blocks.ROPE));
        blockStateMap.put("south-east", blockGetter.getBlockState(pos.south().east()).is(GrowthcraftTags.Blocks.ROPE));
        blockStateMap.put("south", blockGetter.getBlockState(pos.south()).is(GrowthcraftTags.Blocks.ROPE));
        blockStateMap.put("south-west", blockGetter.getBlockState(pos.south().west()).is(GrowthcraftTags.Blocks.ROPE));
        blockStateMap.put("west", blockGetter.getBlockState(pos.west()).is(GrowthcraftTags.Blocks.ROPE));
        blockStateMap.put("north-west", blockGetter.getBlockState(pos.north().west()).is(GrowthcraftTags.Blocks.ROPE));
        blockStateMap.put("above", blockGetter.getBlockState(pos.above()).is(GrowthcraftTags.Blocks.ROPE));
        blockStateMap.put("below", blockGetter.getBlockState(pos.below()).is(GrowthcraftTags.Blocks.ROPE));

        return blockStateMap;
    }
}
