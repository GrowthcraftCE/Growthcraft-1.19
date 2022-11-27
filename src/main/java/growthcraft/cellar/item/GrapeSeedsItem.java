package growthcraft.cellar.item;

import growthcraft.cellar.block.GrapeVineCropBlock;
import growthcraft.core.block.RopeBlock;
import growthcraft.lib.item.GrowthcraftItem;
import growthcraft.lib.utils.BlockStateUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FarmBlock;
import net.minecraft.world.level.block.state.BlockState;

import static growthcraft.lib.block.GrowthcraftCropsRopeBlock.*;

public class GrapeSeedsItem extends GrowthcraftItem {

    private GrapeVineCropBlock grapeVineCropBlock;

    public GrapeSeedsItem(Block grapeVineCropBlock, Block grapeVineLeavesCropBlock, Block grapeVineFruitBlock) {
        super();
        this.grapeVineCropBlock = (GrapeVineCropBlock) grapeVineCropBlock;
    }

    @Override
    public InteractionResult onItemUseFirst(ItemStack stack, UseOnContext context) {
        Level level = context.getLevel();
        BlockPos blockPos = context.getClickedPos();
        Block block = level.getBlockState(blockPos).getBlock();

        if (block instanceof FarmBlock && level.getBlockState(blockPos.above()).getBlock() instanceof RopeBlock) {
            BlockState newBlockState = this.grapeVineCropBlock.defaultBlockState();
            newBlockState.setValue(NORTH, BlockStateUtils.isRopeBlock(level.getBlockState(blockPos.north().above())));
            newBlockState.setValue(EAST, BlockStateUtils.isRopeBlock(level.getBlockState(blockPos.east().above())));
            newBlockState.setValue(SOUTH, BlockStateUtils.isRopeBlock(level.getBlockState(blockPos.south().above())));
            newBlockState.setValue(WEST, BlockStateUtils.isRopeBlock(level.getBlockState(blockPos.west().above())));
            newBlockState.setValue(UP, true);
            newBlockState.setValue(DOWN, false);

            level.setBlock(
                    blockPos.above(),
                    newBlockState,
                    Block.UPDATE_ALL
            );
            context.getItemInHand().shrink(1);
            return InteractionResult.SUCCESS;
        }

        return InteractionResult.PASS;
    }

    public GrapeVineCropBlock getGrapeVineCropBlock() {
        return this.grapeVineCropBlock;
    }

}
