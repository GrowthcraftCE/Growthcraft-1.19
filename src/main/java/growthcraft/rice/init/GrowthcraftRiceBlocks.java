package growthcraft.rice.init;

import growthcraft.lib.utils.FluidUtils;
import growthcraft.rice.block.CultivatedFarmlandBlock;
import growthcraft.rice.block.RiceCropBlock;
import growthcraft.rice.shared.Reference;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;

public class GrowthcraftRiceBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(
            ForgeRegistries.BLOCKS, Reference.MODID
    );

    public static final RegistryObject<CultivatedFarmlandBlock> CULTIVATED_FARMLAND = BLOCKS.register(
            Reference.UnlocalizedName.CULTIVATED_FARMLAND, CultivatedFarmlandBlock::new
    );

    public static final RegistryObject<RiceCropBlock> RICE_CROP = BLOCKS.register(
            Reference.UnlocalizedName.RICE_CROP, RiceCropBlock::new
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
        // Exclude any blocks that do not need to be accessible via the Creative tab
        //excludeBlocks.add(Reference.MODID + ":" + Reference.UnlocalizedName.APPLE_TREE_FRUIT);
        // Exclude Fluid Blocks
        excludeBlocks.add(Reference.MODID + ":" + FluidUtils.getFluidNames(Reference.UnlocalizedName.RICE_WATER).get(FluidUtils.BLOCK));
        excludeBlocks.add(Reference.MODID + ":" + FluidUtils.getFluidNames(Reference.UnlocalizedName.RICE_WINE).get(FluidUtils.BLOCK));
        excludeBlocks.add(Reference.MODID + ":" + FluidUtils.getFluidNames(Reference.UnlocalizedName.SAKE).get(FluidUtils.BLOCK));
        return excludeBlocks.contains(registryName.toString());
    }

    private GrowthcraftRiceBlocks() {
        /* Disable default public constructor */
    }
}
