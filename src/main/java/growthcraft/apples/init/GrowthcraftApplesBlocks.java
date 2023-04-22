package growthcraft.apples.init;

import growthcraft.apples.block.AppleBeeBoxBlock;
import growthcraft.apples.block.AppleRopeBlock;
import growthcraft.apples.block.AppleTreeFruit;
import growthcraft.apples.block.AppleTreeLeaves;
import growthcraft.apples.shared.Reference;
import growthcraft.apples.world.feature.tree.AppleTreeGrower;
import growthcraft.lib.block.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.function.Supplier;

public class GrowthcraftApplesBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(
            ForgeRegistries.BLOCKS, Reference.MODID
    );

    public static final RegistryObject<Block> APPLE_PLANK = registerBlock(
            Reference.UnlocalizedName.APPLE_PLANK,
            GrowthcraftPlankBlock::new
    );

    public static final RegistryObject<Block> APPLE_PLANK_BUTTON = registerBlock(
            Reference.UnlocalizedName.APPLE_PLANK_BUTTON,
            GrowthcraftButtonBlock::new
    );

    public static final RegistryObject<Block> APPLE_PLANK_DOOR = registerBlock(
            Reference.UnlocalizedName.APPLE_PLANK_DOOR,
            GrowthcraftDoorBlock::new
    );

    public static final RegistryObject<Block> APPLE_PLANK_FENCE = registerBlock(
            Reference.UnlocalizedName.APPLE_PLANK_FENCE,
            GrowthcraftFenceBlock::new
    );

    public static final RegistryObject<Block> APPLE_PLANK_FENCE_GATE = registerBlock(
            Reference.UnlocalizedName.APPLE_PLANK_FENCE_GATE,
            GrowthcraftFenceGateBlock::new
    );

    public static final RegistryObject<Block> APPLE_PLANK_PRESSURE_PLATE = registerBlock(
            Reference.UnlocalizedName.APPLE_PLANK_PRESSURE_PLATE,
            GrowthcraftPressurePlateBlock::new
    );

    public static final RegistryObject<Block> APPLE_PLANK_SLAB = registerBlock(
            Reference.UnlocalizedName.APPLE_PLANK_SLAB,
            GrowthcraftSlabBlock::new
    );

    public static final RegistryObject<Block> APPLE_PLANK_STAIRS = registerBlock(
            Reference.UnlocalizedName.APPLE_PLANK_STAIRS,
            GrowthcraftStairsBlock::new
    );

    public static final RegistryObject<Block> APPLE_PLANK_TRAPDOOR = registerBlock(
            Reference.UnlocalizedName.APPLE_PLANK_TRAPDOOR,
            GrowthcraftTrapDoorBlock::new
    );

    public static final RegistryObject<Block> APPLE_TREE_FRUIT = registerBlock(
            Reference.UnlocalizedName.APPLE_TREE_FRUIT,
            AppleTreeFruit::new,
            true
    );

    public static final RegistryObject<Block> APPLE_TREE_LEAVES = registerBlock(
            Reference.UnlocalizedName.APPLE_TREE_LEAVES,
            AppleTreeLeaves::new
    );

    public static final RegistryObject<Block> APPLE_TREE_SAPLING = registerBlock(
            Reference.UnlocalizedName.APPLE_TREE_SAPLING,
            () -> new GrowthcraftSaplingBlock(
                    new AppleTreeGrower()
            )
    );

    public static final RegistryObject<Block> APPLE_WOOD = registerBlock(
            Reference.UnlocalizedName.APPLE_WOOD,
            GrowthcraftLogBlock::new
    );

    public static final RegistryObject<Block> APPLE_WOOD_LOG = registerBlock(
            Reference.UnlocalizedName.APPLE_WOOD_LOG,
            GrowthcraftLogBlock::new
    );

    public static final RegistryObject<Block> APPLE_WOOD_LOG_STRIPPED = registerBlock(
            Reference.UnlocalizedName.APPLE_WOOD_LOG_STRIPPED,
            GrowthcraftLogBlock::new
    );

    public static final RegistryObject<Block> APPLE_WOOD_STRIPPED = registerBlock(
            Reference.UnlocalizedName.APPLE_WOOD_STRIPPED,
            GrowthcraftLogBlock::new
    );

    public static final RegistryObject<Block> APPLE_PLANK_FENCE_ROPE_LINEN = registerBlock(
            Reference.UnlocalizedName.APPLE_PLANK_FENCE_ROPE_LINEN,
            AppleRopeBlock::new,
            true
    );

    public static final RegistryObject<Block> BEE_BOX_APPLE = registerBlock(
            Reference.UnlocalizedName.BEE_BOX_APPLE, AppleBeeBoxBlock::new
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
        GrowthcraftApplesItems.ITEMS.register(
                name,
                () -> new BlockItem(blockRegistryObject.get(), getDefaultItemProperties())
        );
    }
  
    private static Item.Properties getDefaultItemProperties() {
        Item.Properties properties = new Item.Properties();
        return properties;
    }

    @Deprecated
    private static boolean excludeBlockItemRegistry(ResourceLocation registryName) {
        ArrayList<String> excludeBlocks = new ArrayList<>();
        //excludeBlocks.add(Reference.MODID + ":" + Reference.UnlocalizedName.APPLE_TREE_FRUIT);
        return excludeBlocks.contains(registryName.toString());
    }
}
