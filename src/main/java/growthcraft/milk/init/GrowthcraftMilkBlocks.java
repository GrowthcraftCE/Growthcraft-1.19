package growthcraft.milk.init;

import growthcraft.milk.block.PancheonBlock;
import growthcraft.milk.block.ThistleCropBlock;
import growthcraft.milk.shared.Reference;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class GrowthcraftMilkBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(
            ForgeRegistries.BLOCKS, Reference.MODID
    );


    // TODO: Cheese Press

    // TODO: Churn

    // TODO: Pancheon
    public static final RegistryObject<Block> PANCHEON = registerBlock(
            Reference.UnlocalizedName.PANCHEON,
            PancheonBlock::new
    );

    public static final RegistryObject<Block> THISTLE_CROP = registerBlock(
            Reference.UnlocalizedName.THISTLE_CROP,
            ThistleCropBlock::new,
            true
    );

    // TODO: CHEESE CURDS

    // TODO: Cheese Wheels

    private static RegistryObject<Block> registerBlock(String name, Supplier<Block> block) {
        return registerBlock(name, block, false);
    }

    private static RegistryObject<Block> registerBlock(String name, Supplier<Block> block, boolean excludeBlockItemRegistry) {
        RegistryObject<Block> registryObject = BLOCKS.register(name, block);
        if (!excludeBlockItemRegistry) {
            registerBlockItem(name, registryObject);
        }
        return registryObject;
    }

    private static void registerBlockItem(String name, RegistryObject<Block> blockRegistryObject) {
        GrowthcraftMilkItems.ITEMS.register(
                name,
                () -> new BlockItem(blockRegistryObject.get(), getDefaultItemProperties())
        );
    }

    private static Item.Properties getDefaultItemProperties() {
        Item.Properties properties = new Item.Properties();
        return properties;
    }

    private GrowthcraftMilkBlocks() {
        /* Disable default public constructor */
    }


}
