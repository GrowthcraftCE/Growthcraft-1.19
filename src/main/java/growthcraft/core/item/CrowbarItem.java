package growthcraft.core.item;

import growthcraft.lib.item.GrowthcraftItem;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Rotation;

public class CrowbarItem extends GrowthcraftItem {
    private static final int MAX_STACK_SIZE = 1;

    public CrowbarItem() {
        super(MAX_STACK_SIZE);
    }

    @Override
    public InteractionResult useOn(UseOnContext useOnContext) {
        Level level = useOnContext.getLevel();
        BlockPos useOnBlockPos = useOnContext.getClickedPos();
        Player player = useOnContext.getPlayer();

        if(level.isClientSide) return super.useOn(useOnContext);

        if(player.isCrouching()) {
            level.setBlock(useOnBlockPos, level.getBlockState(useOnBlockPos).getBlock().rotate(level.getBlockState(useOnBlockPos), level, useOnBlockPos, Rotation.CLOCKWISE_90), 11);
            return InteractionResult.SUCCESS;
        }

        return super.useOn(useOnContext);
    }
}
