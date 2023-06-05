package growthcraft.core.item;

import growthcraft.apples.init.GrowthcraftApplesBlocks;
import growthcraft.bamboo.init.GrowthcraftBambooBlocks;
import growthcraft.core.block.RopeBlock;
import growthcraft.core.block.entity.RopeBlockEntity;
import growthcraft.core.init.GrowthcraftBlocks;
import growthcraft.lib.item.GrowthcraftBlockItem;
import growthcraft.lib.utils.BlockStateUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Map;

import static growthcraft.core.block.RopeBlock.*;

public class RopeItem extends GrowthcraftBlockItem {

    public RopeItem(RopeBlock block) {
        super(block);
    }

    @Override
    public InteractionResult useOn(UseOnContext useOnContext) {
        Level level = useOnContext.getLevel();

        BlockPos blockPos = useOnContext.getClickedPos();
        BlockState blockState = level.getBlockState(blockPos);

        // TODO: Make Rope Fence Posts more dynamic
        if (blockState.is(BlockTags.FENCES)) {
            BlockState state = GrowthcraftBlocks.ROPE_LINEN_OAK_FENCE.get().defaultBlockState();

            if (blockState.getBlock() == Blocks.ACACIA_FENCE) {
                state = GrowthcraftBlocks.ROPE_LINEN_ACACIA_FENCE.get().defaultBlockState();
            } else if(blockState.getBlock() == Blocks.BIRCH_FENCE) {
                state = GrowthcraftBlocks.ROPE_LINEN_BIRCH_FENCE.get().defaultBlockState();
            } else if(blockState.getBlock() == Blocks.CRIMSON_FENCE) {
                state = GrowthcraftBlocks.ROPE_LINEN_CRIMSON_FENCE.get().defaultBlockState();
            } else if(blockState.getBlock() == Blocks.DARK_OAK_FENCE) {
                state = GrowthcraftBlocks.ROPE_LINEN_DARK_OAK_FENCE.get().defaultBlockState();
            } else if(blockState.getBlock() == Blocks.JUNGLE_FENCE) {
                state = GrowthcraftBlocks.ROPE_LINEN_JUNGLE_FENCE.get().defaultBlockState();
            } else if (blockState.getBlock() == Blocks.NETHER_BRICK_FENCE) {
                state = GrowthcraftBlocks.ROPE_LINEN_NETHER_BRICK_FENCE.get().defaultBlockState();
            } else if(blockState.getBlock() == Blocks.OAK_FENCE) {
                state = GrowthcraftBlocks.ROPE_LINEN_OAK_FENCE.get().defaultBlockState();
            } else if(blockState.getBlock() == Blocks.SPRUCE_FENCE) {
                state = GrowthcraftBlocks.ROPE_LINEN_SPRUCE_FENCE.get().defaultBlockState();
            } else if(blockState.getBlock() == Blocks.WARPED_FENCE) {
                state = GrowthcraftBlocks.ROPE_LINEN_WARPED_FENCE.get().defaultBlockState();
            } else if (blockState.getBlock() == GrowthcraftApplesBlocks.APPLE_PLANK_FENCE.get()) {
                state = GrowthcraftApplesBlocks.APPLE_PLANK_FENCE_ROPE_LINEN.get().defaultBlockState();
            } else if (blockState.getBlock() == GrowthcraftBambooBlocks.BAMBOO_PLANK_FENCE.get()) {
                state = GrowthcraftBambooBlocks.BAMBOO_PLANK_FENCE_ROPE_LINEN.get().defaultBlockState();
            }

            Map<String, Boolean> surroundingStateMap =
                    BlockStateUtils.getSurroundingRopeConnections(level, blockPos);

            level.setBlock(blockPos, state.setValue(KNOT, true)
                    .setValue(NORTH, surroundingStateMap.get("north"))
                    .setValue(EAST, surroundingStateMap.get("east"))
                    .setValue(SOUTH, surroundingStateMap.get("south"))
                    .setValue(WEST, surroundingStateMap.get("west"))
                    .setValue(UP, surroundingStateMap.get("above"))
                    .setValue(DOWN, surroundingStateMap.get("below")),
                    Block.UPDATE_ALL);

            RopeBlockEntity ropeBlockEntity = (RopeBlockEntity) level.getBlockEntity(blockPos);
            if (ropeBlockEntity != null) {
                ropeBlockEntity.setFenceItemStack(new ItemStack(blockState.getBlock(), 1));
                useOnContext.getItemInHand().shrink(1);
                return InteractionResult.SUCCESS;
            }

        } else {
            return this.place(new BlockPlaceContext(useOnContext));
        }

        return InteractionResult.PASS;
    }
}
