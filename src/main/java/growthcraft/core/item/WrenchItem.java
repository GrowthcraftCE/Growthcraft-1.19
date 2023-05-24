package growthcraft.core.item;

import growthcraft.lib.item.GrowthcraftItem;
import growthcraft.milk.block.CheesePressBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class WrenchItem extends GrowthcraftItem {
    private static final int MAX_STACK_SIZE = 1;

    public WrenchItem() {
        super(MAX_STACK_SIZE);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        BlockPos blockpos = context.getClickedPos();
        Player player = context.getPlayer();
        BlockState blockstate = level.getBlockState(blockpos);

        if(blockstate.getBlock() instanceof CheesePressBlock) {
            blockstate.getBlock().use(blockstate, level, blockpos, player, context.getHand(), null);
        }

        return super.useOn(context);
    }
}
