package growthcraft.rice.item;

import growthcraft.lib.item.GrowthcraftItem;
import growthcraft.rice.block.CultivatedFarmlandBlock;
import growthcraft.rice.init.GrowthcraftRiceBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;

public class RiceSeedItem extends GrowthcraftItem {

    public RiceSeedItem() {
        super();
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level world = context.getLevel();
        BlockPos blockpos = context.getClickedPos();
        Block block = world.getBlockState(blockpos).getBlock();

        if (block instanceof CultivatedFarmlandBlock) {
            world.setBlock(blockpos.above(), GrowthcraftRiceBlocks.RICE_CROP.get().defaultBlockState(), Block.UPDATE_ALL);
            context.getItemInHand().shrink(1);
            return InteractionResult.SUCCESS;
        }

        return InteractionResult.PASS;
    }
}
