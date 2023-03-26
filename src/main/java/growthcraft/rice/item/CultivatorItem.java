package growthcraft.rice.item;

import growthcraft.rice.init.GrowthcraftRiceBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ToolActions;

public class CultivatorItem extends HoeItem {

    public CultivatorItem() {
        super(Tiers.IRON, 6, 0.0F, getInitProperties());
    }

    private static Item.Properties getInitProperties() {
        Item.Properties properties = new Item.Properties();
        properties.stacksTo(1);
        return properties;
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        BlockPos blockpos = context.getClickedPos();
        Player player = context.getPlayer();

        // TODO: WARN: CultivatorItem and ForgeEventFactory.onToolUse
        //int hook = net.minecraftforge.event.ForgeEventFactory.onToolUse(context);
        //if (hook != 0) return hook > 0 ? InteractionResult.SUCCESS : InteractionResult.FAIL;

        if (context.getClickedFace() != Direction.DOWN && level.getBlockState(blockpos.above()).isAir()) {
            BlockState blockstate = level.getBlockState(blockpos).getToolModifiedState(context, ToolActions.HOE_TILL, false);

            if (blockstate != null) {
                if (blockstate.getBlock() == Blocks.FARMLAND) {
                    level.playSound(player, blockpos, SoundEvents.HOE_TILL, SoundSource.BLOCKS, 1.0F, 1.0F);
                    level.setBlock(blockpos, blockstate, Block.UPDATE_IMMEDIATE);
                }
                return InteractionResult.sidedSuccess(level.isClientSide);
            } else if (level.getBlockState(blockpos).getBlock() == Blocks.FARMLAND) {
                level.playSound(player, blockpos, SoundEvents.HOE_TILL, SoundSource.BLOCKS, 1.0F, 1.0F);
                level.setBlock(blockpos, GrowthcraftRiceBlocks.CULTIVATED_FARMLAND.get().defaultBlockState(), Block.UPDATE_IMMEDIATE);
            }
        }

        return InteractionResult.PASS;
    }
}
