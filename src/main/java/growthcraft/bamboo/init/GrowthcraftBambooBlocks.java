package growthcraft.bamboo.init;

import growthcraft.bamboo.block.BambooLogBlock;
import growthcraft.bamboo.shared.Reference;
import growthcraft.lib.block.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.function.Supplier;

public class GrowthcraftBambooBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(
            ForgeRegistries.BLOCKS, Reference.MODID
    );

    public static final RegistryObject<Block> BAMBOO_PLANK = registerBlock(
            Reference.UnlocalizedName.BAMBOO_PLANK, () -> new GrowthcraftPlankBlock(Material.BAMBOO)
    );

    public static final RegistryObject<Block> BAMBOO_PLANK_STAIRS = registerBlock(
            Reference.UnlocalizedName.BAMBOO_PLANK_STAIRS,
            () -> new GrowthcraftStairsBlock(
                    () -> GrowthcraftBambooBlocks.BAMBOO_PLANK.get().defaultBlockState(),
                    BlockBehaviour.Properties.copy(GrowthcraftBambooBlocks.BAMBOO_PLANK.get())
            )
    );

    public static final RegistryObject<Block> BAMBOO_PLANK_BUTTON = registerBlock(
            Reference.UnlocalizedName.BAMBOO_PLANK_BUTTON, GrowthcraftButtonBlock::new
    );

    public static final RegistryObject<Block> BAMBOO_PLANK_DOOR = registerBlock(
            Reference.UnlocalizedName.BAMBOO_PLANK_DOOR, GrowthcraftDoorBlock::new
    );

    public static final RegistryObject<Block> BAMBOO_PLANK_FENCE = registerBlock(
            Reference.UnlocalizedName.BAMBOO_PLANK_FENCE, GrowthcraftFenceBlock::new
    );

    public static final RegistryObject<Block> BAMBOO_PLANK_FENCE_GATE = registerBlock(
            Reference.UnlocalizedName.BAMBOO_PLANK_FENCE_GATE, GrowthcraftFenceGateBlock::new
    );

    public static final RegistryObject<Block> BAMBOO_PLANK_PRESSURE_PLATE = registerBlock(
            Reference.UnlocalizedName.BAMBOO_PLANK_PRESSURE_PLATE, GrowthcraftPressurePlateBlock::new
    );

    public static final RegistryObject<Block> BAMBOO_PLANK_SLAB = registerBlock(
            Reference.UnlocalizedName.BAMBOO_PLANK_SLAB, GrowthcraftSlabBlock::new
    );

    public static final RegistryObject<Block> BAMBOO_PLANK_TRAPDOOR = registerBlock(
            Reference.UnlocalizedName.BAMBOO_PLANK_TRAPDOOR, GrowthcraftTrapDoorBlock::new
    );

    public static final RegistryObject<Block> BAMBOO_WOOD = registerBlock(
            Reference.UnlocalizedName.BAMBOO_WOOD, BambooLogBlock::new
    );

    public static final RegistryObject<Block> BAMBOO_WOOD_LOG = registerBlock(
            Reference.UnlocalizedName.BAMBOO_WOOD_LOG, BambooLogBlock::new
    );

    public static final RegistryObject<Block> BAMBOO_WOOD_LOG_STRIPPED = registerBlock(
            Reference.UnlocalizedName.BAMBOO_WOOD_LOG_STRIPPED, BambooLogBlock::new
    );

    public static final RegistryObject<Block> BAMBOO_WOOD_STRIPPED = registerBlock(
            Reference.UnlocalizedName.BAMBOO_WOOD_STRIPPED, BambooLogBlock::new
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
        GrowthcraftBambooItems.ITEMS.register(
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