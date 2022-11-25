package growthcraft.apples.init;

import growthcraft.apples.block.AppleBeeBoxBlock;
import growthcraft.apples.block.AppleTreeFruit;
import growthcraft.apples.block.AppleTreeLeaves;
import growthcraft.apples.shared.Reference;
import growthcraft.apples.world.feature.tree.AppleTreeGrower;
import growthcraft.lib.block.*;
import growthcraft.lib.utils.FluidUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;

public class GrowthcraftApplesBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(
            ForgeRegistries.BLOCKS, Reference.MODID
    );

    public static final RegistryObject<GrowthcraftPlankBlock> APPLE_PLANK = BLOCKS.register(
            Reference.UnlocalizedName.APPLE_PLANK,
            GrowthcraftPlankBlock::new
    );

    public static final RegistryObject<GrowthcraftButtonBlock> APPLE_PLANK_BUTTON = BLOCKS.register(
            Reference.UnlocalizedName.APPLE_PLANK_BUTTON,
            GrowthcraftButtonBlock::new
    );

    public static final RegistryObject<GrowthcraftDoorBlock> APPLE_PLANK_DOOR = BLOCKS.register(
            Reference.UnlocalizedName.APPLE_PLANK_DOOR,
            GrowthcraftDoorBlock::new
    );

    public static final RegistryObject<GrowthcraftFenceBlock> APPLE_PLANK_FENCE = BLOCKS.register(
            Reference.UnlocalizedName.APPLE_PLANK_FENCE,
            GrowthcraftFenceBlock::new
    );

    public static final RegistryObject<GrowthcraftFenceGateBlock> APPLE_PLANK_FENCE_GATE = BLOCKS.register(
            Reference.UnlocalizedName.APPLE_PLANK_FENCE_GATE,
            GrowthcraftFenceGateBlock::new
    );

    public static final RegistryObject<GrowthcraftPressurePlateBlock> APPLE_PLANK_PRESSURE_PLATE = BLOCKS.register(
            Reference.UnlocalizedName.APPLE_PLANK_PRESSURE_PLATE,
            GrowthcraftPressurePlateBlock::new
    );

    public static final RegistryObject<GrowthcraftSlabBlock> APPLE_PLANK_SLAB = BLOCKS.register(
            Reference.UnlocalizedName.APPLE_PLANK_SLAB,
            GrowthcraftSlabBlock::new
    );

    public static final RegistryObject<GrowthcraftStairsBlock> APPLE_PLANK_STAIRS = BLOCKS.register(
            Reference.UnlocalizedName.APPLE_PLANK_STAIRS,
            GrowthcraftStairsBlock::new
    );

    public static final RegistryObject<GrowthcraftTrapDoorBlock> APPLE_PLANK_TRAPDOOR = BLOCKS.register(
            Reference.UnlocalizedName.APPLE_PLANK_TRAPDOOR,
            GrowthcraftTrapDoorBlock::new
    );

    public static final RegistryObject<AppleTreeFruit> APPLE_TREE_FRUIT = BLOCKS.register(
            Reference.UnlocalizedName.APPLE_TREE_FRUIT,
            AppleTreeFruit::new
    );

    public static final RegistryObject<Block> APPLE_TREE_LEAVES = BLOCKS.register(
            Reference.UnlocalizedName.APPLE_TREE_LEAVES,
            AppleTreeLeaves::new
    );

    public static final RegistryObject<GrowthcraftSaplingBlock> APPLE_TREE_SAPLING = BLOCKS.register(
            Reference.UnlocalizedName.APPLE_TREE_SAPLING,
            () -> new GrowthcraftSaplingBlock(
                new AppleTreeGrower()
            )
    );

    public static final RegistryObject<GrowthcraftLogBlock> APPLE_WOOD = BLOCKS.register(
            Reference.UnlocalizedName.APPLE_WOOD,
            GrowthcraftLogBlock::new
    );

    public static final RegistryObject<GrowthcraftLogBlock> APPLE_WOOD_LOG = BLOCKS.register(
            Reference.UnlocalizedName.APPLE_WOOD_LOG,
            GrowthcraftLogBlock::new
    );

    public static final RegistryObject<GrowthcraftLogBlock> APPLE_WOOD_LOG_STRIPPED = BLOCKS.register(
            Reference.UnlocalizedName.APPLE_WOOD_LOG_STRIPPED,
            GrowthcraftLogBlock::new
    );

    public static final RegistryObject<GrowthcraftLogBlock> APPLE_WOOD_STRIPPED = BLOCKS.register(
            Reference.UnlocalizedName.APPLE_WOOD_STRIPPED,
            GrowthcraftLogBlock::new
    );

    public static final RegistryObject<AppleBeeBoxBlock> BEE_BOX_APPLE = BLOCKS.register(
            Reference.UnlocalizedName.BEE_BOX_APPLE, AppleBeeBoxBlock::new
    );

    public static void registerBlockItems(IForgeRegistry<Item> itemRegistry, Item.Properties properties) {
        BLOCKS.getEntries().stream().map(RegistryObject::get).forEach(block -> {
            if (block.getRegistryName() != null && !excludeBlockItemRegistry(block.getRegistryName())) {
                final BlockItem blockItem = new BlockItem(block, properties);
                blockItem.setRegistryName(block.getRegistryName());
                itemRegistry.register(blockItem);
            }
        });
    }

    private static boolean excludeBlockItemRegistry(ResourceLocation registryName) {
        ArrayList<String> excludeBlocks = new ArrayList<>();
        excludeBlocks.add(Reference.MODID + ":" + Reference.UnlocalizedName.APPLE_TREE_FRUIT);
        excludeBlocks.add(Reference.MODID + ":" + FluidUtils.getFluidNames(Reference.UnlocalizedName.APPLE_CIDER).get(FluidUtils.BLOCK));
        excludeBlocks.add(Reference.MODID + ":" + FluidUtils.getFluidNames(Reference.UnlocalizedName.APPLE_JUICE).get(FluidUtils.BLOCK));
        return excludeBlocks.contains(registryName.toString());
    }
}
