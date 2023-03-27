package growthcraft.core.datagen.providers;

import growthcraft.core.init.GrowthcraftItems;
import growthcraft.core.shared.Reference;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

/**
 * @credit Kaupenjoe for his DataGenerator tutorials
 */
public class GrowthcraftItemModelProvider extends ItemModelProvider {

    public GrowthcraftItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, Reference.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        //simpleItem(ModItems.BLACK_OPAL);
        this.simpleBlockItems();
    }

    private void simpleBlockItems() {
        simpleItemGenerated(GrowthcraftItems.CROWBAR_BLACK);
        simpleItemGenerated(GrowthcraftItems.CROWBAR_BLUE);
        simpleItemGenerated(GrowthcraftItems.CROWBAR_BROWN);
        simpleItemGenerated(GrowthcraftItems.CROWBAR_CYAN);
        simpleItemGenerated(GrowthcraftItems.CROWBAR_GRAY);
        simpleItemGenerated(GrowthcraftItems.CROWBAR_GREEN);
        simpleItemGenerated(GrowthcraftItems.CROWBAR_LIGHT_BLUE);
        simpleItemGenerated(GrowthcraftItems.CROWBAR_LIGHT_GRAY);
        simpleItemGenerated(GrowthcraftItems.CROWBAR_LIME);
        simpleItemGenerated(GrowthcraftItems.CROWBAR_MAGENTA);
        simpleItemGenerated(GrowthcraftItems.CROWBAR_ORANGE);
        simpleItemGenerated(GrowthcraftItems.CROWBAR_PINK);
        simpleItemGenerated(GrowthcraftItems.CROWBAR_PURPLE);
        simpleItemGenerated(GrowthcraftItems.CROWBAR_RED);
        simpleItemGenerated(GrowthcraftItems.CROWBAR_WHITE);
        simpleItemGenerated(GrowthcraftItems.CROWBAR_YELLOW);
        simpleItemGenerated(GrowthcraftItems.ROPE_LINEN);
        simpleItemGenerated(GrowthcraftItems.SALT);
    }

    private ItemModelBuilder simpleBlockItem(RegistryObject<Block> block, Block mirroredBlock) {
        return simpleBlockItem(block, ForgeRegistries.BLOCKS.getKey(mirroredBlock));
    }

    private ItemModelBuilder simpleBlockItem(RegistryObject<Block> block, ResourceLocation parentBlockModel) {
        return withExistingParent(block.getId().getPath(), parentBlockModel.getNamespace() + ":block/" + parentBlockModel.getPath());
    }

    private ItemModelBuilder translucentPaneBlockItem(RegistryObject<Block> block) {
        return this.translucentPaneBlockItem(block, block.getId());
    }

    private ItemModelBuilder translucentPaneBlockItem(RegistryObject<Block> block, Block mirroredBlock) {
        return this.translucentPaneBlockItem(block, ForgeRegistries.BLOCKS.getKey(mirroredBlock));
    }

    private ItemModelBuilder translucentPaneBlockItem(RegistryObject<Block> block, ResourceLocation layer0) {
        return withExistingParent(block.getId().getPath(), new ResourceLocation("item/generated"))
                .renderType("translucent")
                .texture("layer0", layer0.getNamespace() + ":block/" + layer0.getPath());
    }

    private ItemModelBuilder saplingItem(RegistryObject<Block> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(Reference.MODID, "block/" + item.getId().getPath()));
    }

    private ItemModelBuilder simpleItemGenerated(RegistryObject<? extends Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(Reference.MODID, "item/" + item.getId().getPath()));
    }

    private ItemModelBuilder handheldItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/handheld")).texture("layer0",
                new ResourceLocation(Reference.MODID, "item/" + item.getId().getPath()));
    }
}
