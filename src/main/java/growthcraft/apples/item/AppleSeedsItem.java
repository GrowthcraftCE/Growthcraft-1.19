package growthcraft.apples.item;

import growthcraft.apples.init.GrowthcraftApplesBlocks;
import growthcraft.lib.item.GrowthcraftItem;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.GrassBlock;
import net.minecraft.world.level.block.state.BlockState;

public class AppleSeedsItem extends GrowthcraftItem {

    public AppleSeedsItem() {
        super();
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        BlockPos blockpos = context.getClickedPos();
        BlockState blockstate = level.getBlockState(blockpos);
        Block targetBlock = blockstate.getBlock();
        if (targetBlock != null && targetBlock instanceof GrassBlock) {
            Player playerentity = context.getPlayer();
            level.playSound(playerentity, blockpos, SoundEvents.CROP_PLANTED, SoundSource.BLOCKS, 1.0F, 1.0F);
            if (!level.isClientSide) {
                level.setBlock(blockpos.above(), GrowthcraftApplesBlocks.APPLE_TREE_SAPLING.get().defaultBlockState(), 11);
                if (playerentity != null) {
                    context.getItemInHand().shrink(1);
                }
            }
            return InteractionResult.SUCCESS;
        } else {
            return InteractionResult.PASS;
        }
    }
}
