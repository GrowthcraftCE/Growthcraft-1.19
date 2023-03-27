package growthcraft.core.init;

import growthcraft.core.block.RopeBlock;
import growthcraft.core.shared.Reference;
import growthcraft.lib.block.GrowthcraftBlock;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.function.Supplier;

public class GrowthcraftBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Reference.MODID);

    public static final RegistryObject<Block> ROPE_LINEN = registerBlock(
            Reference.UnlocalizedName.ROPE_LINEN, RopeBlock::new, true
    );

    public static final RegistryObject<Block> ROPE_LINEN_ACACIA_FENCE = registerBlock(
            Reference.UnlocalizedName.ROPE_LINEN_ACACIA_FENCE, RopeBlock::new, true
    );

    public static final RegistryObject<Block> ROPE_LINEN_BIRCH_FENCE = registerBlock(
            Reference.UnlocalizedName.ROPE_LINEN_BIRCH_FENCE, RopeBlock::new, true
    );

    public static final RegistryObject<Block> ROPE_LINEN_CRIMSON_FENCE = registerBlock(
            Reference.UnlocalizedName.ROPE_LINEN_CRIMSON_FENCE, RopeBlock::new, true
    );

    public static final RegistryObject<Block> ROPE_LINEN_JUNGLE_FENCE = registerBlock(
            Reference.UnlocalizedName.ROPE_LINEN_JUNGLE_FENCE, RopeBlock::new, true
    );

    public static final RegistryObject<Block> ROPE_LINEN_DARK_OAK_FENCE = registerBlock(
            Reference.UnlocalizedName.ROPE_LINEN_DARK_OAK_FENCE, RopeBlock::new, true
    );

    public static final RegistryObject<Block> ROPE_LINEN_WARPED_FENCE = registerBlock(
            Reference.UnlocalizedName.ROPE_LINEN_WARPED_FENCE, RopeBlock::new, true
    );

    public static final RegistryObject<Block> ROPE_LINEN_OAK_FENCE = registerBlock(
            Reference.UnlocalizedName.ROPE_LINEN_OAK_FENCE, RopeBlock::new, true
    );

    public static final RegistryObject<Block> ROPE_LINEN_SPRUCE_FENCE = registerBlock(
            Reference.UnlocalizedName.ROPE_LINEN_SPRUCE_FENCE, RopeBlock::new, true
    );

    public static final RegistryObject<Block> ROPE_LINEN_NETHER_BRICK_FENCE = registerBlock(
            Reference.UnlocalizedName.ROPE_LINEN_NETHER_BRICK_FENCE, RopeBlock::new, true
    );

    public static final RegistryObject<Block> SALT_BLOCK = registerBlock(
            Reference.UnlocalizedName.SALT_BLOCK,
            () -> new GrowthcraftBlock(Material.STONE, SoundType.STONE)
    );

    public static final RegistryObject<Block> SALT_ORE = registerBlock(
            Reference.UnlocalizedName.SALT_ORE,
            () -> new GrowthcraftBlock(BlockBehaviour.Properties.copy(Blocks.IRON_ORE))
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
        GrowthcraftItems.ITEMS.register(
                name,
                () -> new BlockItem(blockRegistryObject.get(), getDefaultItemProperties())
        );
    }

    private static Item.Properties getDefaultItemProperties() {
        Item.Properties properties = new Item.Properties();
        return properties;
    }

    public static boolean excludeBlockItemRegistry(ResourceLocation registryName) {
        ArrayList<String> excludeBlocks = new ArrayList<>();
        //excludeBlocks.add(Reference.MODID + ":" + Reference.UnlocalizedName.APPLE_TREE_FRUIT);
        return excludeBlocks.contains(registryName.toString());
    }

    private GrowthcraftBlocks() {
        /* Disable automatic default public constructor */
    }

}
