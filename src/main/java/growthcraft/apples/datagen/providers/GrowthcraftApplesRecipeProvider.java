package growthcraft.apples.datagen.providers;

import growthcraft.apples.shared.Reference;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import java.util.function.Consumer;

/**
 * @credit Kaupenjoe for his DataGenerator tutorials
 */
public class GrowthcraftApplesRecipeProvider extends RecipeProvider implements IConditionBuilder {


    public GrowthcraftApplesRecipeProvider(PackOutput packOutput) {
        super(packOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> recipeConsumer) {
        this.carpetedStairsRecipes(recipeConsumer);
        this.glowingRecipes(recipeConsumer);
        this.partialCarpetedStairsRecipes(recipeConsumer);
        this.slabRecipes(recipeConsumer);
        this.stairRecipes(recipeConsumer);
        this.stoneCuttingRecipes(recipeConsumer);
    }

    private void stoneCuttingRecipes(Consumer<FinishedRecipe> recipeConsumer) {
       // this.buildStonecuttingRecipe(recipeConsumer, GrowthcraftDecoBlocks.STAIRS_CONCRETE_BLACK.get(), Blocks.BLACK_CONCRETE, 1);

    }

    private void slabRecipes(Consumer<FinishedRecipe> recipeConsumer) {
        //this.buildSlabRecipe(recipeConsumer, GrowthcraftDecoBlocks.GLASS_PANEL_BLACK.get(), GrowthcraftDecoBlocks.SLAB_GLASS_BLACK.get());

    }

    private void stairRecipes(Consumer<FinishedRecipe> recipeConsumer) {
        //this.buildStairRecipe(recipeConsumer, GrowthcraftDecoBlocks.STAIR_WOOL_BLACK.get(), Blocks.BLACK_WOOL);


    }

    private void glowingRecipes(Consumer<FinishedRecipe> recipeConsumer) {

        //this.buildGlowingRecipe(recipeConsumer, GrowthcraftDecoBlocks.GLASS_STAINED_BLACK_PANE_GLOWING.get(), Blocks.BLACK_STAINED_GLASS_PANE);

    }

    private void carpetedStairsRecipes(Consumer<FinishedRecipe> recipeConsumer) {
        //this.buildCarpetedStairRecipe(recipeConsumer, GrowthcraftDecoBlocks.STAIR_ACACIA_CARPET_BLACK.get(), Blocks.ACACIA_STAIRS, Blocks.BLACK_CARPET);

    }

    private void partialCarpetedStairsRecipes(Consumer<FinishedRecipe> recipeConsumer) {
        //this.buildPartialCarpetedStairRecipe(recipeConsumer, GrowthcraftDecoBlocks.STAIR_ACACIA_CARPET_PARTIAL_BLACK.get(), Blocks.ACACIA_STAIRS, Blocks.BLACK_CARPET);


    }

    public void buildSlabRecipe(Consumer<FinishedRecipe> recipeConsumer, ItemLike result, ItemLike ingredient) {
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, result, 6)
                .define('A', ingredient)
                .pattern("AAA")
                .unlockedBy("has_item", has(ingredient))
                .save(recipeConsumer);
    }

    public void buildSlabRecipe(Consumer<FinishedRecipe> recipeConsumer, ItemLike result, ItemLike ingredient, String recipePostfixName) {
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, result, 6)
                .define('A', ingredient)
                .pattern("AAA")
                .unlockedBy("has_item", has(ingredient))
                .save(recipeConsumer, new ResourceLocation(Reference.MODID, RecipeProvider.getItemName(result)).toString() + "_" + recipePostfixName);
    }

    public void buildStairRecipe(Consumer<FinishedRecipe> recipeConsumer, ItemLike result, ItemLike ingredient) {
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, result, 4)
                .define('A', ingredient)
                .pattern("A  ")
                .pattern("AA ")
                .pattern("AAA")
                .unlockedBy("has_item", has(ingredient))
                .save(recipeConsumer);
    }

    public void buildStonecuttingRecipe(Consumer<FinishedRecipe> recipeConsumer, ItemLike result, ItemLike ingredient, int resultCount) {
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(ingredient), RecipeCategory.DECORATIONS, result, resultCount)
                .unlockedBy(getHasName(ingredient), has(ingredient))
                .save(recipeConsumer, new ResourceLocation(Reference.MODID, getConversionRecipeName(result, ingredient)).toString() + "_stonecutting");
    }

}
