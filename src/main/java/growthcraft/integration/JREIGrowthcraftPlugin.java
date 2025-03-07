package growthcraft.integration;

import growthcraft.core.shared.Reference;
import growthcraft.milk.recipe.MixingVatFluidRecipe;
import growthcraft.milk.recipe.MixingVatItemRecipe;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeManager;
import org.spongepowered.asm.mixin.Mixin;

import java.util.List;
import java.util.Objects;

@JeiPlugin
public class JREIGrowthcraftPlugin implements IModPlugin {

    public static RecipeType<MixingVatItemRecipe> MIXING_VAT_ITEM_RECIPE =
            new RecipeType<>(MixingVatItemRecipeCategory.UID, MixingVatItemRecipe.class);
    public static RecipeType<MixingVatItemRecipe> MIXING_VAT_FLUID_RECIPE =
            new RecipeType<>(MixingVatFluidRecipeCategory.UID, MixingVatItemRecipe.class);

    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(Reference.MODID, "jei_plugin");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new
                MixingVatItemRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
        registration.addRecipeCategories(new
                MixingVatFluidRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        RecipeManager rm = Objects.requireNonNull(Minecraft.getInstance().level).getRecipeManager();

        List<MixingVatItemRecipe> recipesItemVat = rm.getAllRecipesFor(MixingVatItemRecipe.Type.INSTANCE);
        registration.addRecipes(MIXING_VAT_ITEM_RECIPE, recipesItemVat);
        List<MixingVatFluidRecipe> recipesFluidVat = rm.getAllRecipesFor(MixingVatFluidRecipe.Type.INSTANCE);
        registration.addRecipes(MIXING_VAT_FLUID_RECIPE, recipesItemVat);
    }
}
