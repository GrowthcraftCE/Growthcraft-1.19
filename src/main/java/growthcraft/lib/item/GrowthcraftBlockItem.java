package growthcraft.lib.item;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;

public class GrowthcraftBlockItem extends BlockItem {

    public GrowthcraftBlockItem(Block block) {
        this(block, getInitProperties(64));
    }

    public GrowthcraftBlockItem(Block block, int stackSize) {
        this(block, getInitProperties(stackSize));
    }

    public GrowthcraftBlockItem(Block block, Properties properties) {
        super(block, properties);
    }

    private static Properties getInitProperties(int maxStackSize) {
        Properties properties = new Properties();
        properties.stacksTo(maxStackSize);
        return properties;
    }
}
