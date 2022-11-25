package growthcraft.apiary.init;

import growthcraft.apiary.block.BeeBoxBlock;
import growthcraft.apiary.shared.Reference;
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

public class GrowthcraftApiaryBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(
            ForgeRegistries.BLOCKS, Reference.MODID
    );

    public static final RegistryObject<BeeBoxBlock> BEE_BOX_ACACIA = BLOCKS.register(
            Reference.UnlocalizedName.BEE_BOX_ACACIA, BeeBoxBlock::new
    );

    public static final RegistryObject<BeeBoxBlock> BEE_BOX_BIRCH = BLOCKS.register(
            Reference.UnlocalizedName.BEE_BOX_BIRCH, BeeBoxBlock::new
    );

    public static final RegistryObject<BeeBoxBlock> BEE_BOX_CRIMSON = BLOCKS.register(
            Reference.UnlocalizedName.BEE_BOX_CRIMSON, BeeBoxBlock::new
    );

    public static final RegistryObject<BeeBoxBlock> BEE_BOX_DARK_OAK = BLOCKS.register(
            Reference.UnlocalizedName.BEE_BOX_DARK_OAK, BeeBoxBlock::new
    );

    public static final RegistryObject<BeeBoxBlock> BEE_BOX_JUNGLE = BLOCKS.register(
            Reference.UnlocalizedName.BEE_BOX_JUNGLE, BeeBoxBlock::new
    );
    public static final RegistryObject<BeeBoxBlock> BEE_BOX_OAK = BLOCKS.register(
            Reference.UnlocalizedName.BEE_BOX_OAK, BeeBoxBlock::new
    );

    public static final RegistryObject<BeeBoxBlock> BEE_BOX_SPRUCE = BLOCKS.register(
            Reference.UnlocalizedName.BEE_BOX_SPRUCE, BeeBoxBlock::new
    );
    public static final RegistryObject<BeeBoxBlock> BEE_BOX_WARPED = BLOCKS.register(
            Reference.UnlocalizedName.BEE_BOX_WARPED, BeeBoxBlock::new
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
        // Exclude Blocks
        //excludeBlocks.add(growthcraft.core.shared.Reference.MODID + ":" + growthcraft.core.shared.Reference.UnlocalizedName.ROPE_LINEN);
        // Exclude Fluid Blocks
        excludeBlocks.add(Reference.MODID + ":" + FluidUtils.getFluidNames(Reference.UnlocalizedName.WAX_BLACK).get(FluidUtils.BLOCK));
        excludeBlocks.add(Reference.MODID + ":" + FluidUtils.getFluidNames(Reference.UnlocalizedName.WAX_BLUE).get(FluidUtils.BLOCK));
        excludeBlocks.add(Reference.MODID + ":" + FluidUtils.getFluidNames(Reference.UnlocalizedName.WAX_BROWN).get(FluidUtils.BLOCK));
        excludeBlocks.add(Reference.MODID + ":" + FluidUtils.getFluidNames(Reference.UnlocalizedName.WAX_CYAN).get(FluidUtils.BLOCK));
        excludeBlocks.add(Reference.MODID + ":" + FluidUtils.getFluidNames(Reference.UnlocalizedName.WAX_GRAY).get(FluidUtils.BLOCK));
        excludeBlocks.add(Reference.MODID + ":" + FluidUtils.getFluidNames(Reference.UnlocalizedName.WAX_GREEN).get(FluidUtils.BLOCK));
        excludeBlocks.add(Reference.MODID + ":" + FluidUtils.getFluidNames(Reference.UnlocalizedName.WAX_LIGHT_BLUE).get(FluidUtils.BLOCK));
        excludeBlocks.add(Reference.MODID + ":" + FluidUtils.getFluidNames(Reference.UnlocalizedName.WAX_LIGHT_GRAY).get(FluidUtils.BLOCK));
        excludeBlocks.add(Reference.MODID + ":" + FluidUtils.getFluidNames(Reference.UnlocalizedName.WAX_LIME).get(FluidUtils.BLOCK));
        excludeBlocks.add(Reference.MODID + ":" + FluidUtils.getFluidNames(Reference.UnlocalizedName.WAX_MAGENTA).get(FluidUtils.BLOCK));
        excludeBlocks.add(Reference.MODID + ":" + FluidUtils.getFluidNames(Reference.UnlocalizedName.WAX_ORANGE).get(FluidUtils.BLOCK));
        excludeBlocks.add(Reference.MODID + ":" + FluidUtils.getFluidNames(Reference.UnlocalizedName.WAX_PINK).get(FluidUtils.BLOCK));
        excludeBlocks.add(Reference.MODID + ":" + FluidUtils.getFluidNames(Reference.UnlocalizedName.WAX_PURPLE).get(FluidUtils.BLOCK));
        excludeBlocks.add(Reference.MODID + ":" + FluidUtils.getFluidNames(Reference.UnlocalizedName.WAX_RED).get(FluidUtils.BLOCK));
        excludeBlocks.add(Reference.MODID + ":" + FluidUtils.getFluidNames(Reference.UnlocalizedName.WAX_WHITE).get(FluidUtils.BLOCK));
        excludeBlocks.add(Reference.MODID + ":" + FluidUtils.getFluidNames(Reference.UnlocalizedName.WAX_YELLOW).get(FluidUtils.BLOCK));
        excludeBlocks.add(Reference.MODID + ":" + FluidUtils.getFluidNames(Reference.UnlocalizedName.HONEY).get(FluidUtils.BLOCK));
        excludeBlocks.add(Reference.MODID + ":" + FluidUtils.getFluidNames(Reference.UnlocalizedName.HONEY_MEAD).get(FluidUtils.BLOCK));

        return excludeBlocks.contains(registryName.toString());
    }

}
