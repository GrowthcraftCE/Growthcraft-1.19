package growthcraft.core.datagen.providers;

import growthcraft.core.init.GrowthcraftBlocks;
import growthcraft.core.init.GrowthcraftItems;
import growthcraft.core.shared.Reference;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import javax.annotation.Nullable;
import java.util.function.Consumer;

/**
 * @credit Kaupenjoe for his DataGenerator tutorials
 */
public class GrowthcraftRecipeProvider extends RecipeProvider implements IConditionBuilder {

    public GrowthcraftRecipeProvider(PackOutput packOutput) {
        super(packOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> recipeConsumer) {
        this.crowbarRecipes(recipeConsumer);

        nineBlockStorageRecipes(recipeConsumer,
                RecipeCategory.MISC, GrowthcraftItems.SALT.get(),
                RecipeCategory.MISC, GrowthcraftBlocks.SALT_BLOCK.get()
        );
    }

    private void crowbarRecipes(Consumer<FinishedRecipe> recipeConsumer) {
        buildCrowbarRecipe(recipeConsumer, GrowthcraftItems.CROWBAR_BLACK.get(), Items.BLACK_CARPET);
        buildCrowbarRecipe(recipeConsumer, GrowthcraftItems.CROWBAR_BLUE.get(), Items.BLUE_CARPET);
        buildCrowbarRecipe(recipeConsumer, GrowthcraftItems.CROWBAR_BROWN.get(), Items.BROWN_CARPET);
        buildCrowbarRecipe(recipeConsumer, GrowthcraftItems.CROWBAR_CYAN.get(), Items.CYAN_CARPET);
        buildCrowbarRecipe(recipeConsumer, GrowthcraftItems.CROWBAR_GREEN.get(), Items.GREEN_CARPET);
        buildCrowbarRecipe(recipeConsumer, GrowthcraftItems.CROWBAR_GRAY.get(), Items.GRAY_CARPET);
        buildCrowbarRecipe(recipeConsumer, GrowthcraftItems.CROWBAR_LIGHT_BLUE.get(), Items.LIGHT_BLUE_CARPET);
        buildCrowbarRecipe(recipeConsumer, GrowthcraftItems.CROWBAR_LIGHT_GRAY.get(), Items.LIGHT_GRAY_CARPET);
        buildCrowbarRecipe(recipeConsumer, GrowthcraftItems.CROWBAR_LIME.get(), Items.LIME_CARPET);
        buildCrowbarRecipe(recipeConsumer, GrowthcraftItems.CROWBAR_MAGENTA.get(), Items.MAGENTA_CARPET);
        buildCrowbarRecipe(recipeConsumer, GrowthcraftItems.CROWBAR_ORANGE.get(), Items.ORANGE_CARPET);
        buildCrowbarRecipe(recipeConsumer, GrowthcraftItems.CROWBAR_PINK.get(), Items.PINK_CARPET);
        buildCrowbarRecipe(recipeConsumer, GrowthcraftItems.CROWBAR_PURPLE.get(), Items.PURPLE_CARPET);
        buildCrowbarRecipe(recipeConsumer, GrowthcraftItems.CROWBAR_RED.get(), Items.RED_CARPET);
        buildCrowbarRecipe(recipeConsumer, GrowthcraftItems.CROWBAR_WHITE.get(), Items.WHITE_CARPET);
        buildCrowbarRecipe(recipeConsumer, GrowthcraftItems.CROWBAR_YELLOW.get(), Items.YELLOW_CARPET);
    }

    public void buildCrowbarRecipe(Consumer<FinishedRecipe> recipeConsumer, ItemLike result, ItemLike ingredient) {
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, result, 1)
                .define('A', Items.IRON_NUGGET)
                .define('B', Items.IRON_INGOT)
                .define('C', ingredient)
                .pattern("  A")
                .pattern("CBC")
                .pattern("A  ")
                .unlockedBy("has_item", has(ingredient))
                .save(recipeConsumer);
    }

    protected static void nineBlockStorageRecipes(Consumer<FinishedRecipe> recipeConsumer, RecipeCategory itemRecipeCategory, ItemLike item, RecipeCategory blockRecipeCategory, ItemLike block) {
        nineBlockStorageRecipes(recipeConsumer, itemRecipeCategory, item, blockRecipeCategory, block, getSimpleRecipeName(block), (String)null, getSimpleRecipeName(item), (String)null);
    }

    protected static void nineBlockStorageRecipes(Consumer<FinishedRecipe> recipeConsumer, RecipeCategory itemRecipeCategory, ItemLike item,
                                                  RecipeCategory blockRecipeCategory, ItemLike block, String blockRecipeLocation, @Nullable String blockRecipeGroup,
                                                  String itemRecipeLocation, @Nullable String itemRecipeGroup) {
        ShapelessRecipeBuilder.shapeless(itemRecipeCategory, item, 9).requires(block).group(itemRecipeGroup).unlockedBy(getHasName(block), has(block))
                .save(recipeConsumer, new ResourceLocation(Reference.MODID, itemRecipeLocation));
        ShapedRecipeBuilder.shaped(blockRecipeCategory, block).define('#', item).pattern("###").pattern("###").pattern("###").group(blockRecipeGroup)
                .unlockedBy(getHasName(item), has(item)).save(recipeConsumer, new ResourceLocation(Reference.MODID, blockRecipeLocation));
    }



}
