package growthcraft.apples.datagen.providers;

import growthcraft.apples.shared.Reference;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.IronBarsBlock;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

/**
 * @credit Kaupenjoe for his DataGenerator tutorials
 */
public class GrowthcraftApplesBlockStateProvider extends BlockStateProvider {
    public GrowthcraftApplesBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, Reference.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        // Simple Cube Blocks with Simple BlockItem Models
        //this.blockWithItem(GrowthcraftDecoBlocks.STONE_BRICKS_GLOWING.get());
        //this.glassPanes();
        //this.slabs();
    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(
                blockRegistryObject.get(),
                cubeAll(blockRegistryObject.get())
        );
    }

    private void paneWithRenderType(Block block, String pane, String edge) {
        paneBlockWithRenderType(
                (IronBarsBlock) block,
                key(block).toString(),
                new ResourceLocation(pane),
                new ResourceLocation(edge),
                new ResourceLocation("translucent")
        );
    }

    private ResourceLocation key(Block block) {
        return ForgeRegistries.BLOCKS.getKey(block);
    }
}
