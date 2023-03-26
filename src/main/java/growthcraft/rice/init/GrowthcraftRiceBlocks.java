package growthcraft.rice.init;

import growthcraft.rice.block.CultivatedFarmlandBlock;
import growthcraft.rice.block.RiceCropBlock;
import growthcraft.rice.shared.Reference;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

import static growthcraft.core.shared.Reference.CREATIVE_TAB;

public class GrowthcraftRiceBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(
            ForgeRegistries.BLOCKS, Reference.MODID
    );

    public static final RegistryObject<Block> CULTIVATED_FARMLAND = registerBlock(
            Reference.UnlocalizedName.CULTIVATED_FARMLAND, CultivatedFarmlandBlock::new
    );

    public static final RegistryObject<Block> RICE_CROP = registerBlock(
            Reference.UnlocalizedName.RICE_CROP, RiceCropBlock::new
    );

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
        GrowthcraftRiceItems.ITEMS.register(
                name,
                () -> new BlockItem(blockRegistryObject.get(), getDefaultItemProperties())
        );
    }

    private static Item.Properties getDefaultItemProperties() {
        Item.Properties properties = new Item.Properties();
        properties.tab(CREATIVE_TAB);
        return properties;
    }

    private GrowthcraftRiceBlocks() {
        /* Disable default public constructor */
    }
}
