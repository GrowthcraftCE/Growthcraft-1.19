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
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;

public class GrowthcraftBambooBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(
            ForgeRegistries.BLOCKS, Reference.MODID
    );

    public static final RegistryObject<GrowthcraftPlankBlock> BAMBOO_PLANK = BLOCKS.register(
            Reference.UnlocalizedName.BAMBOO_PLANK, () -> new GrowthcraftPlankBlock(Material.BAMBOO)
    );

    public static final RegistryObject<GrowthcraftStairsBlock> BAMBOO_PLANK_STAIRS = BLOCKS.register(
            Reference.UnlocalizedName.BAMBOO_PLANK_STAIRS,
            () -> new GrowthcraftStairsBlock(
                    () -> GrowthcraftBambooBlocks.BAMBOO_PLANK.get().defaultBlockState(),
                    BlockBehaviour.Properties.copy(GrowthcraftBambooBlocks.BAMBOO_PLANK.get())
            )
    );

    public static final RegistryObject<GrowthcraftButtonBlock> BAMBOO_PLANK_BUTTON = BLOCKS.register(
            Reference.UnlocalizedName.BAMBOO_PLANK_BUTTON, GrowthcraftButtonBlock::new
    );

    public static final RegistryObject<GrowthcraftDoorBlock> BAMBOO_PLANK_DOOR = BLOCKS.register(
            Reference.UnlocalizedName.BAMBOO_PLANK_DOOR, GrowthcraftDoorBlock::new
    );

    public static final RegistryObject<GrowthcraftFenceBlock> BAMBOO_PLANK_FENCE = BLOCKS.register(
            Reference.UnlocalizedName.BAMBOO_PLANK_FENCE, GrowthcraftFenceBlock::new
    );

    public static final RegistryObject<GrowthcraftFenceGateBlock> BAMBOO_PLANK_FENCE_GATE = BLOCKS.register(
            Reference.UnlocalizedName.BAMBOO_PLANK_FENCE_GATE, GrowthcraftFenceGateBlock::new
    );

    public static final RegistryObject<GrowthcraftPressurePlateBlock> BAMBOO_PLANK_PRESSURE_PLATE = BLOCKS.register(
            Reference.UnlocalizedName.BAMBOO_PLANK_PRESSURE_PLATE, GrowthcraftPressurePlateBlock::new
    );

    public static final RegistryObject<GrowthcraftSlabBlock> BAMBOO_PLANK_SLAB = BLOCKS.register(
            Reference.UnlocalizedName.BAMBOO_PLANK_SLAB, GrowthcraftSlabBlock::new
    );

    public static final RegistryObject<GrowthcraftTrapDoorBlock> BAMBOO_PLANK_TRAPDOOR = BLOCKS.register(
            Reference.UnlocalizedName.BAMBOO_PLANK_TRAPDOOR, GrowthcraftTrapDoorBlock::new
    );

    public static final RegistryObject<BambooLogBlock> BAMBOO_WOOD = BLOCKS.register(
            Reference.UnlocalizedName.BAMBOO_WOOD, BambooLogBlock::new
    );

    public static final RegistryObject<BambooLogBlock> BAMBOO_WOOD_LOG = BLOCKS.register(
            Reference.UnlocalizedName.BAMBOO_WOOD_LOG, BambooLogBlock::new
    );

    public static final RegistryObject<BambooLogBlock> BAMBOO_WOOD_LOG_STRIPPED = BLOCKS.register(
            Reference.UnlocalizedName.BAMBOO_WOOD_LOG_STRIPPED, BambooLogBlock::new
    );

    public static final RegistryObject<BambooLogBlock> BAMBOO_WOOD_STRIPPED = BLOCKS.register(
            Reference.UnlocalizedName.BAMBOO_WOOD_STRIPPED, BambooLogBlock::new
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
        //excludeBlocks.add(growthcraft.core.shared.Reference.MODID + ":" + growthcraft.core.shared.Reference.UnlocalizedName.ROPE_LINEN);

        return excludeBlocks.contains(registryName.toString());
    }


}