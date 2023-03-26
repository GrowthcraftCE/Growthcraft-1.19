package growthcraft.core.datagen.providers;

import growthcraft.core.init.GrowthcraftBlocks;
import growthcraft.core.shared.Reference;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

/**
 * @credit Kaupenjoe for his DataGenerator tutorials
 */
public class GrowthcraftBlockStateProvider extends BlockStateProvider {
    public GrowthcraftBlockStateProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, Reference.MODID, existingFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        // Simple Cube Blocks with Simple BlockItem Models
        //blockWithItem(GrowthcraftBlocks.SALT_BLOCK);
        blockWithItem(GrowthcraftBlocks.SALT_ORE);
    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(
                blockRegistryObject.get(),
                cubeAll(blockRegistryObject.get())
        );
    }

}
