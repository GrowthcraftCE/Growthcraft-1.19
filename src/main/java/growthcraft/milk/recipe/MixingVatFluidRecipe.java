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

public class MixingVatFluidRecipe extends MixingVatRecipe {
    private final FluidStack reagentFluidStack;
    private final FluidStack outputFluidStack;
    private final FluidStack wasteFluidStack;

    public MixingVatFluidRecipe(ResourceLocation recipeId, Category category,
                                FluidStack inputFluidStack, FluidStack reagentFluidStack,
                                List<ItemStack> ingredients, int processingTime,
                                FluidStack outputFluidStack, FluidStack wasteFluidStack,
                                ItemStack activationTool) {
        super(recipeId, category, inputFluidStack, ingredients, processingTime, activationTool);
        this.reagentFluidStack = reagentFluidStack;
        this.outputFluidStack = outputFluidStack;
        this.wasteFluidStack = wasteFluidStack;
    }

    @Override
    public boolean matches(SimpleContainer container, Level level) {
        return false;
    }

    public boolean matches(FluidStack testBaseFluidStack, FluidStack testReagentFluidStack,
                           List<ItemStack> testIngredients) {

        boolean fluidMatches = testBaseFluidStack.getFluid() == this.getInputFluidStack().getFluid()
                && testBaseFluidStack.getAmount() == this.getInputFluidStack().getAmount()
                && testReagentFluidStack.getFluid() == this.getReagentFluidStack().getFluid()
                && testReagentFluidStack.getAmount() == this.getReagentFluidStack().getAmount();

        boolean ingredientMatches = false;

        if (this.getIngredientList().size() == testIngredients.size()) {
            int itemCount = this.getIngredientList().size();
            int matchCount = 0;
            for (int i = 0; i < this.getIngredientList().size(); i++) {
                if (this.getIngredientList().get(i).getItem() == testIngredients.get(i).getItem() &&
                        this.getIngredientList().get(i).getCount() == testIngredients.get(i).getCount()) {
                    matchCount++;
                }
            }
            ingredientMatches = itemCount == matchCount;
        }

        return fluidMatches && ingredientMatches;
    }

    public FluidStack getReagentFluidStack() {
        return this.reagentFluidStack;
    }

    public FluidStack getOutputFluidStack() {
        return this.outputFluidStack;
    }

    public FluidStack getWasteFluidStack() {
        return this.wasteFluidStack;
    }

    @Override
    public ItemStack assemble(SimpleContainer container, RegistryAccess registryAccess) {
        return this.outputFluidStack.getFluid().getBucket().getDefaultInstance();
    }

    @Override
    public ItemStack getResultItem(RegistryAccess registryAccess) {
        return this.outputFluidStack.getFluid().getBucket().getDefaultInstance();
    }

    @Override
    public RecipeType<?> getType() {
        return Type.INSTANCE;
    }

    public static class Type implements RecipeType<MixingVatRecipe> {
        private Type() { /* Prevent default constructor */ }

        public static final MixingVatFluidRecipe.Type INSTANCE = new MixingVatFluidRecipe.Type();
        public static final String ID = Reference.UnlocalizedName.MIXING_VAT_FLUID_RECIPE;
    }
}
