package growthcraft.apiary.init;

import growthcraft.apiary.block.BeeBoxBlock;
import growthcraft.apiary.shared.Reference;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.function.Supplier;

import static growthcraft.core.shared.Reference.CREATIVE_TAB;

public class GrowthcraftApiaryBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(
            ForgeRegistries.BLOCKS, Reference.MODID
    );

    public static final RegistryObject<Block> BEE_BOX_ACACIA = registerBlock(
            Reference.UnlocalizedName.BEE_BOX_ACACIA, BeeBoxBlock::new
    );

    public static final RegistryObject<Block> BEE_BOX_BIRCH = registerBlock(
            Reference.UnlocalizedName.BEE_BOX_BIRCH, BeeBoxBlock::new
    );

    public static final RegistryObject<Block> BEE_BOX_CRIMSON = registerBlock(
            Reference.UnlocalizedName.BEE_BOX_CRIMSON, BeeBoxBlock::new
    );

    public static final RegistryObject<Block> BEE_BOX_DARK_OAK = registerBlock(
            Reference.UnlocalizedName.BEE_BOX_DARK_OAK, BeeBoxBlock::new
    );

    public static final RegistryObject<Block> BEE_BOX_JUNGLE = registerBlock(
            Reference.UnlocalizedName.BEE_BOX_JUNGLE, BeeBoxBlock::new
    );
    public static final RegistryObject<Block> BEE_BOX_OAK = registerBlock(
            Reference.UnlocalizedName.BEE_BOX_OAK, BeeBoxBlock::new
    );

    public static final RegistryObject<Block> BEE_BOX_SPRUCE = registerBlock(
            Reference.UnlocalizedName.BEE_BOX_SPRUCE, BeeBoxBlock::new
    );
    public static final RegistryObject<Block> BEE_BOX_WARPED = registerBlock(
            Reference.UnlocalizedName.BEE_BOX_WARPED, BeeBoxBlock::new
    );

    private static RegistryObject<Block> registerBlock(String name, Supplier<Block> block) {
        RegistryObject<Block> registryObject = BLOCKS.register(name, block);
        if (!excludeBlockItemRegistry(registryObject.getId())) {
            registerBlockItem(name, registryObject);
        }
        return registryObject;
    }

    private static void registerBlockItem(String name, RegistryObject<Block> blockRegistryObject) {
        GrowthcraftApiaryItems.ITEMS.register(
                name,
                () -> new BlockItem(blockRegistryObject.get(), getDefaultItemProperties())
        );
    }

    private static Item.Properties getDefaultItemProperties() {
        Item.Properties properties = new Item.Properties();
        properties.tab(CREATIVE_TAB);
        return properties;
    }

    private static boolean excludeBlockItemRegistry(ResourceLocation registryName) {
        ArrayList<String> excludeBlocks = new ArrayList<>();
        //excludeBlocks.add(Reference.MODID + ":" + Reference.UnlocalizedName.APPLE_TREE_FRUIT);
        return excludeBlocks.contains(registryName.toString());
    }

}
