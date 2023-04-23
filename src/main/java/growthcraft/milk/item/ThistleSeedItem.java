package growthcraft.milk.item;

import growthcraft.lib.item.GrowthcraftItem;
import growthcraft.milk.init.GrowthcraftMilkBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

public class ThistleSeedItem extends GrowthcraftItem {

    public ThistleSeedItem() {
        super();
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        BlockPos blockPos = context.getClickedPos();
        Block tagetBlock = level.getBlockState(blockPos).getBlock();

        if(tagetBlock == Blocks.FARMLAND) {
            Player player = context.getPlayer();

            // Trigger a sound event
            level.playSound(player, blockPos,
                    SoundEvents.CROP_PLANTED, SoundSource.BLOCKS,
                    1.0F, 1.0F
            );

            // Server-side only
            if(!level.isClientSide) {
                level.setBlock(
                        blockPos.above(),
                        GrowthcraftMilkBlocks.THISTLE_CROP.get().defaultBlockState(),
                        Block.UPDATE_ALL_IMMEDIATE
                );
                if(player != null) {
                    context.getItemInHand().shrink(1);
                }
            }

            return InteractionResult.SUCCESS;
        } else {
            return super.useOn(context);
        }
    }
}
