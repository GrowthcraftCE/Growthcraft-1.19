package growthcraft.core.item;

import growthcraft.lib.item.GrowthcraftItem;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.context.UseOnContext;

public class WrenchItem extends GrowthcraftItem {
    private static final int MAX_STACK_SIZE = 1;

    public WrenchItem() {
        super(MAX_STACK_SIZE);
    }

    @Override
    public InteractionResult useOn(UseOnContext useOnContext) {



        return super.useOn(useOnContext);
    }
}
