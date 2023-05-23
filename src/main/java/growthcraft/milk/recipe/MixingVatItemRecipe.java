package growthcraft.milk.recipe;

import growthcraft.milk.shared.Reference;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraftforge.fluids.FluidStack;

import java.util.List;

public class MixingVatItemRecipe extends MixingVatRecipe {

    private final ItemStack resultItemStack;
    private final ItemStack resultActivationTool;

    public MixingVatItemRecipe(ResourceLocation recipeId, Category category,
                               FluidStack inputFluidStack, List<ItemStack> ingredients, int processingTime,
                               ItemStack resultItemStack, ItemStack activationTool,
                               ItemStack resultActivationTool) {
        super(recipeId, category, inputFluidStack, ingredients, processingTime, activationTool);
        this.resultItemStack = resultItemStack;
        this.resultActivationTool = resultActivationTool;
    }


    @Override
    public boolean matches(SimpleContainer p_44002_, Level p_44003_) {
        return false;
    }

    public boolean matches(FluidStack testFluidStack, List<ItemStack> testIngredients) {
        boolean fluidMatches = false;
        boolean itemMatches = false;

        if (testFluidStack.getFluid() == this.getInputFluidStack().getFluid()
                && testFluidStack.getAmount() == this.getInputFluidStack().getAmount()) {
            fluidMatches = true;
        }

        if (this.getIngredientList().size() == testIngredients.size()) {
            int itemCount = this.getIngredientList().size();
            int matchCount = 0;
            for (int i = 0; i < this.getIngredientList().size(); i++) {
                if (this.getIngredientList().get(i).getItem() == testIngredients.get(i).getItem() &&
                        this.getIngredientList().get(i).getCount() == testIngredients.get(i).getCount()) {
                    matchCount++;
                }
            }
            if (itemCount == matchCount) itemMatches = true;
        }

        return fluidMatches && itemMatches;
    }

    public boolean matchResult(ItemStack itemStack) {
        return this.resultItemStack.getItem() == itemStack.getItem() && this.resultItemStack.getCount() == itemStack.getCount();
    }

    public ItemStack getResultItemStack() {
        return this.resultItemStack.copy();
    }

    public ItemStack getResultActivationTool() {
        return this.resultActivationTool;
    }

    @Override
    public ItemStack assemble(SimpleContainer container, RegistryAccess registryAccess) {
        return resultItemStack;
    }

    @Override
    public ItemStack getResultItem(RegistryAccess registryAccess) {
        return resultItemStack;
    }

    @Override
    public RecipeType<?> getType() {
        return Type.INSTANCE;
    }

    public static class Type implements RecipeType<MixingVatRecipe> {
        private Type() { /* Prevent default constructor */ }

        public static final MixingVatItemRecipe.Type INSTANCE = new MixingVatItemRecipe.Type();
        public static final String ID = Reference.UnlocalizedName.MIXING_VAT_ITEM_RECIPE;
    }


}
