package growthcraft.cellar.item;

import growthcraft.cellar.init.GrowthcraftCellarBlocks;
import growthcraft.lib.item.GrowthcraftItem;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FarmBlock;

public class HopsSeedsItem extends GrowthcraftItem {

    public HopsSeedsItem() {
        super();
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        BlockPos blockPos = context.getClickedPos();
        Block block = level.getBlockState(blockPos).getBlock();

        if (block instanceof FarmBlock) {
            level.setBlock(blockPos.above(), GrowthcraftCellarBlocks.HOPS_VINE.get().defaultBlockState(), Block.UPDATE_ALL);
            context.getItemInHand().shrink(1);
            return InteractionResult.SUCCESS;
        }

        return InteractionResult.PASS;
    }
}
