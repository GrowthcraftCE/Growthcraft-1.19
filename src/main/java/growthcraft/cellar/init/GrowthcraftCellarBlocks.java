package growthcraft.cellar.init;

import growthcraft.apiary.init.GrowthcraftApiaryItems;
import growthcraft.cellar.block.GrapeVineCropBlock;
import growthcraft.cellar.block.GrapeVineFruitBlock;
import growthcraft.cellar.block.GrapeVineLeavesCropBlock;
import growthcraft.cellar.block.HopsCropBlock;
import growthcraft.cellar.shared.Reference;
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

public class GrowthcraftCellarBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(
            ForgeRegistries.BLOCKS, Reference.MODID
    );

    // TODO: BREW_KETTLE

    // TODO: CULTURE_JAR

    // TODO: FERMENT_BARREL_OAK

    // TODO: FRUIT_PRESS
    // TODO: FRUIT_PRESS_PISTON

    public static final RegistryObject<Block> RED_GRAPE_VINE_FRUIT = registerBlock(
            Reference.UnlocalizedName.RED_GRAPE_VINE_FRUIT,
            GrapeVineFruitBlock::new, true
    );
    public static final RegistryObject<Block> RED_GRAPE_VINE_LEAVES = registerBlock(
            Reference.UnlocalizedName.RED_GRAPE_VINE_LEAVES,
            () -> new GrapeVineLeavesCropBlock((GrapeVineFruitBlock) RED_GRAPE_VINE_FRUIT.get()), true
    );
    public static final RegistryObject<Block> RED_GRAPE_VINE = registerBlock(
            Reference.UnlocalizedName.RED_GRAPE_VINE,
            () -> new GrapeVineCropBlock((GrapeVineLeavesCropBlock) RED_GRAPE_VINE_LEAVES.get()), true
    );

    public static final RegistryObject<Block> PURPLE_GRAPE_VINE_FRUIT = registerBlock(
            Reference.UnlocalizedName.PURPLE_GRAPE_VINE_FRUIT,
            GrapeVineFruitBlock::new, true
    );
    public static final RegistryObject<Block> PURPLE_GRAPE_VINE_LEAVES = registerBlock(
            Reference.UnlocalizedName.PURPLE_GRAPE_VINE_LEAVES,
            () -> new GrapeVineLeavesCropBlock((GrapeVineFruitBlock) PURPLE_GRAPE_VINE_FRUIT.get()), true
    );
    public static final RegistryObject<Block> PURPLE_GRAPE_VINE = registerBlock(
            Reference.UnlocalizedName.PURPLE_GRAPE_VINE,
            () -> new GrapeVineCropBlock((GrapeVineLeavesCropBlock) PURPLE_GRAPE_VINE_LEAVES.get()), true
    );

    public static final RegistryObject<Block> WHITE_GRAPE_VINE_FRUIT = registerBlock(
            Reference.UnlocalizedName.WHITE_GRAPE_VINE_FRUIT,
            GrapeVineFruitBlock::new, true
    );
    public static final RegistryObject<Block> WHITE_GRAPE_VINE_LEAVES = registerBlock(
            Reference.UnlocalizedName.WHITE_GRAPE_VINE_LEAVES,
            () -> new GrapeVineLeavesCropBlock((GrapeVineFruitBlock) WHITE_GRAPE_VINE_FRUIT.get()), true
    );
    public static final RegistryObject<Block> WHITE_GRAPE_VINE = registerBlock(
            Reference.UnlocalizedName.WHITE_GRAPE_VINE,
            () -> new GrapeVineCropBlock((GrapeVineLeavesCropBlock) WHITE_GRAPE_VINE_LEAVES.get()), true
    );

    public static final RegistryObject<Block> HOPS_VINE = registerBlock(
            Reference.UnlocalizedName.HOPS_VINE, HopsCropBlock::new, true
    );

    // TODO: ROASTER

    private static RegistryObject<Block>  registerBlock(String name, Supplier<Block> block) {
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


    private static boolean excludeBlockItemRegistryOld(ResourceLocation registryName) {
        ArrayList<String> excludeBlocks = new ArrayList<>();
        // Exclude any blocks that do not need to be accessible via the Creative tab
        //excludeBlocks.add(Reference.MODID + ":" + Reference.UnlocalizedName.APPLE_TREE_FRUIT);

        // Exclude Crop Blocks
        excludeBlocks.add(Reference.MODID + ":" + Reference.UnlocalizedName.HOPS_VINE);
        excludeBlocks.add(Reference.MODID + ":" + Reference.UnlocalizedName.RED_GRAPE_VINE);
        excludeBlocks.add(Reference.MODID + ":" + Reference.UnlocalizedName.RED_GRAPE_VINE_FRUIT);
        excludeBlocks.add(Reference.MODID + ":" + Reference.UnlocalizedName.RED_GRAPE_VINE_LEAVES);
        excludeBlocks.add(Reference.MODID + ":" + Reference.UnlocalizedName.PURPLE_GRAPE_VINE);
        excludeBlocks.add(Reference.MODID + ":" + Reference.UnlocalizedName.PURPLE_GRAPE_VINE_FRUIT);
        excludeBlocks.add(Reference.MODID + ":" + Reference.UnlocalizedName.PURPLE_GRAPE_VINE_LEAVES);
        excludeBlocks.add(Reference.MODID + ":" + Reference.UnlocalizedName.WHITE_GRAPE_VINE);
        excludeBlocks.add(Reference.MODID + ":" + Reference.UnlocalizedName.WHITE_GRAPE_VINE_FRUIT);
        excludeBlocks.add(Reference.MODID + ":" + Reference.UnlocalizedName.WHITE_GRAPE_VINE_LEAVES);

        return excludeBlocks.contains(registryName.toString());
    }

    private GrowthcraftCellarBlocks() {
        /* Disable default public constructor */
    }


}
