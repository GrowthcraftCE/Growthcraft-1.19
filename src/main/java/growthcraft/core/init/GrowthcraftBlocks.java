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
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;

public class GrowthcraftBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Reference.MODID);

    public static final RegistryObject<RopeBlock> ROPE_LINEN = BLOCKS.register(
            Reference.UnlocalizedName.ROPE_LINEN, RopeBlock::new
    );

    public static final RegistryObject<RopeBlock> ROPE_LINEN_ACACIA_FENCE = BLOCKS.register(
            Reference.UnlocalizedName.ROPE_LINEN_ACACIA_FENCE, RopeBlock::new
    );

    public static final RegistryObject<RopeBlock> ROPE_LINEN_BIRCH_FENCE = BLOCKS.register(
            Reference.UnlocalizedName.ROPE_LINEN_BIRCH_FENCE, RopeBlock::new
    );

    public static final RegistryObject<RopeBlock> ROPE_LINEN_CRIMSON_FENCE = BLOCKS.register(
            Reference.UnlocalizedName.ROPE_LINEN_CRIMSON_FENCE, RopeBlock::new
    );

    public static final RegistryObject<RopeBlock> ROPE_LINEN_JUNGLE_FENCE = BLOCKS.register(
            Reference.UnlocalizedName.ROPE_LINEN_JUNGLE_FENCE, RopeBlock::new
    );

    public static final RegistryObject<RopeBlock> ROPE_LINEN_DARK_OAK_FENCE = BLOCKS.register(
            Reference.UnlocalizedName.ROPE_LINEN_DARK_OAK_FENCE, RopeBlock::new
    );

    public static final RegistryObject<RopeBlock> ROPE_LINEN_WARPED_FENCE = BLOCKS.register(
            Reference.UnlocalizedName.ROPE_LINEN_WARPED_FENCE, RopeBlock::new
    );

    public static final RegistryObject<RopeBlock> ROPE_LINEN_OAK_FENCE = BLOCKS.register(
        Reference.UnlocalizedName.ROPE_LINEN_OAK_FENCE, RopeBlock::new
    );

    public static final RegistryObject<RopeBlock> ROPE_LINEN_SPRUCE_FENCE = BLOCKS.register(
            Reference.UnlocalizedName.ROPE_LINEN_SPRUCE_FENCE, RopeBlock::new
    );

    public static final RegistryObject<RopeBlock> ROPE_LINEN_NETHER_BRICK_FENCE = BLOCKS.register(
            Reference.UnlocalizedName.ROPE_LINEN_NETHER_BRICK_FENCE, RopeBlock::new
    );

    public static final RegistryObject<Block> SALT_BLOCK = BLOCKS.register(
            Reference.UnlocalizedName.SALT_BLOCK, () -> new GrowthcraftBlock(Material.STONE, SoundType.STONE)
    );

    public static final RegistryObject<Block> SALT_ORE = BLOCKS.register(
            Reference.UnlocalizedName.SALT_ORE, () -> new GrowthcraftBlock(BlockBehaviour.Properties.copy(Blocks.IRON_ORE))
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
        excludeBlocks.add(Reference.MODID + ":" + Reference.UnlocalizedName.ROPE_LINEN);
        excludeBlocks.add(Reference.MODID + ":" + Reference.UnlocalizedName.ROPE_LINEN_ACACIA_FENCE);
        excludeBlocks.add(Reference.MODID + ":" + Reference.UnlocalizedName.ROPE_LINEN_BIRCH_FENCE);
        excludeBlocks.add(Reference.MODID + ":" + Reference.UnlocalizedName.ROPE_LINEN_CRIMSON_FENCE);
        excludeBlocks.add(Reference.MODID + ":" + Reference.UnlocalizedName.ROPE_LINEN_DARK_OAK_FENCE);
        excludeBlocks.add(Reference.MODID + ":" + Reference.UnlocalizedName.ROPE_LINEN_JUNGLE_FENCE);
        excludeBlocks.add(Reference.MODID + ":" + Reference.UnlocalizedName.ROPE_LINEN_NETHER_BRICK_FENCE);
        excludeBlocks.add(Reference.MODID + ":" + Reference.UnlocalizedName.ROPE_LINEN_OAK_FENCE);
        excludeBlocks.add(Reference.MODID + ":" + Reference.UnlocalizedName.ROPE_LINEN_SPRUCE_FENCE);
        excludeBlocks.add(Reference.MODID + ":" + Reference.UnlocalizedName.ROPE_LINEN_WARPED_FENCE);

        return excludeBlocks.contains(registryName.toString());
    }

    private GrowthcraftBlocks() {
        /* Disable automatic default public constructor */
    }

}
