package growthcraft.integration;


import growthcraft.core.shared.Reference;
import growthcraft.milk.init.GrowthcraftMilkBlocks;
import growthcraft.milk.recipe.MixingVatItemRecipe;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

public class MixingVatFluidRecipeCategory implements IRecipeCategory<MixingVatItemRecipe> {

    public final static ResourceLocation UID = new ResourceLocation(Reference.MODID, "vat_item_mixing");
    public final static ResourceLocation TEXTURE =
            new ResourceLocation(Reference.MODID, "resources/assets/growthcraft_milk/textures/gui/mixing_vat_screen.png");

    private final IDrawable background;
    private final IDrawable icon;

    public MixingVatFluidRecipeCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 0, 0, 176, 85);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(GrowthcraftMilkBlocks.MIXING_VAT.get()));
    }

    @Override
    public RecipeType<MixingVatItemRecipe> getRecipeType() {
        return JREIGrowthcraftPlugin.MIXING_VAT_ITEM_RECIPE;
    }

    @Override
    public Component getTitle() {
        return Component.literal("Mixing Vat");
    }

    @Override
    public IDrawable getBackground() {
        return this.background;
    }

    @Override
    public IDrawable getIcon() {
        return this.icon;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, MixingVatItemRecipe recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, 71, 18).addIngredients(recipe.getIngredients().get(0));
        builder.addSlot(RecipeIngredientRole.INPUT, 71, 36).addIngredients(recipe.getIngredients().get(1));
        builder.addSlot(RecipeIngredientRole.INPUT, 71, 54).addIngredients(recipe.getIngredients().get(2));

        builder.addSlot(RecipeIngredientRole.OUTPUT, 124, 18).addItemStack(recipe.getResultItem());
    }
}
