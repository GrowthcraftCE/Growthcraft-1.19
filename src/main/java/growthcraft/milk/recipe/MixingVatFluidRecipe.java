package growthcraft.milk.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import growthcraft.lib.utils.CraftingUtils;
import growthcraft.lib.utils.RecipeUtils;
import growthcraft.milk.GrowthcraftMilk;
import growthcraft.milk.shared.Reference;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.fluids.FluidStack;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class MixingVatFluidRecipe implements Recipe<SimpleContainer> {
    private final ResourceLocation recipeId;
    private final RecipeUtils.Category category;
    private final ItemStack activationTool;
    private final List<ItemStack> ingredients;
    private final FluidStack inputFluidStack;
    private final int processingTime;

    private final FluidStack reagentFluidStack;
    private final FluidStack outputFluidStack;
    private final FluidStack wasteFluidStack;

    public MixingVatFluidRecipe(ResourceLocation recipeId, RecipeUtils.Category category,
                                FluidStack inputFluidStack, FluidStack reagentFluidStack,
                                List<ItemStack> ingredients, int processingTime,
                                FluidStack outputFluidStack, FluidStack wasteFluidStack,
                                ItemStack activationTool) {
        this.recipeId = recipeId;
        this.category = category;
        this.inputFluidStack = inputFluidStack;
        this.reagentFluidStack = reagentFluidStack;
        this.ingredients = ingredients;
        this.processingTime = processingTime;
        this.outputFluidStack = outputFluidStack;
        this.wasteFluidStack = wasteFluidStack;
        this.activationTool = activationTool;
    }

    @Override
    public boolean matches(SimpleContainer container, Level level) {
        return false;
    }

    public boolean matches(FluidStack testBaseFluidStack, FluidStack testReagentFluidStack,
                           List<ItemStack> testIngredients) {

        FluidStack inputFluid = this.getInputFluidStack();
        FluidStack reagentFluid = this.reagentFluidStack;

        boolean inputFluidTypeMatches = testBaseFluidStack.getFluid() == this.getInputFluidStack().getFluid();
        boolean inputFluidAmountMatches = testBaseFluidStack.getAmount() == this.getInputFluidStack().getAmount();

        boolean reagentFluidTypeMatches = testReagentFluidStack.getFluid() == this.getReagentFluidStack().getFluid();
        boolean reagentFluidAmountMatches = testReagentFluidStack.getAmount() == this.getReagentFluidStack().getAmount();

        boolean fluidMatches = inputFluidTypeMatches && inputFluidAmountMatches
                && reagentFluidTypeMatches && reagentFluidAmountMatches;

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

    public FluidStack getInputFluidStack() {
        return this.inputFluidStack;
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
    public boolean canCraftInDimensions(int width, int height) {
        return true;
    }

    @Override
    public ResourceLocation getId() {
        return this.recipeId;
    }

    public RecipeUtils.Category getCategory() {
        return this.category;
    }

    public ItemStack getActivationTool() {
        return this.activationTool;
    }

    public boolean activationToolValid(ItemStack tool) {
        return this.activationTool.getItem() == tool.getItem();
    }

    public List<ItemStack> getIngredientList() {
        return ingredients;
    }

    public List<Item> getIngredientItems() {
        List<Item> ingredientItems = new ArrayList<>();
        this.getIngredientList().forEach(
                itemStack -> ingredientItems.add(itemStack.getItem())
        );
        return ingredientItems;
    }

    public int getProcessingTime() {
        return processingTime;
    }

    public boolean is(RecipeUtils.Category category) {
        return this.category == category;
    }

    @Override
    public RecipeType<?> getType() {
        return Type.INSTANCE;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return Serializer.INSTANCE;
    }

    public static class Type implements RecipeType<MixingVatFluidRecipe> {
        private Type() { /* Prevent default constructor */ }

        public static final MixingVatFluidRecipe.Type INSTANCE = new MixingVatFluidRecipe.Type();
        public static final String ID = Reference.UnlocalizedName.MIXING_VAT_FLUID_RECIPE;
    }

    public static class Serializer implements RecipeSerializer<MixingVatFluidRecipe> {

        public static final MixingVatFluidRecipe.Serializer INSTANCE = new MixingVatFluidRecipe.Serializer();
        public static final ResourceLocation ID = new ResourceLocation(
                Reference.MODID,
                Reference.UnlocalizedName.MIXING_VAT_FLUID_RECIPE);

        private static final int maxIngredients = 3;

        @Override
        public MixingVatFluidRecipe fromJson(ResourceLocation recipeId, JsonObject json) {
            RecipeUtils.Category category
                    = RecipeUtils.Category.with(GsonHelper.getAsString(json, "result_type"));

            int processingTime = GsonHelper.getAsInt(json, "processing_time", 1200);

            FluidStack inputFluid = CraftingUtils.getFluidStack(
                    GsonHelper.getAsJsonObject(json, "input_fluid"));

            ItemStack activationTool = CraftingHelper.getItemStack(
                    GsonHelper.getAsJsonObject(json, "activation_tool"), false);

            List<ItemStack> ingredients = new ArrayList<>();
            JsonArray jsonIngredients = GsonHelper.getAsJsonArray(json, "ingredients");

            if (jsonIngredients.size() <= maxIngredients) {
                for (int i = 0; i < jsonIngredients.size(); i++) {
                    ItemStack itemStack = CraftingHelper.getItemStack(jsonIngredients.get(i).getAsJsonObject(), false);
                    ingredients.add(itemStack);
                }
            }

            if (category == RecipeUtils.Category.FLUID) {
                FluidStack reagentFluid = CraftingUtils.getFluidStack(
                        GsonHelper.getAsJsonObject(json, "reagent_fluid"));
                FluidStack resultFluid = CraftingUtils.getFluidStack(
                        GsonHelper.getAsJsonObject(json, "result_fluid"));
                FluidStack wasteFluid = CraftingUtils.getFluidStack(
                        GsonHelper.getAsJsonObject(json, "result_fluid_waste"));

                return new MixingVatFluidRecipe(recipeId, RecipeUtils.Category.FLUID,
                        inputFluid, reagentFluid, ingredients, processingTime, resultFluid, wasteFluid, activationTool);
            }

            return null;
        }

        @Override
        public @Nullable MixingVatFluidRecipe fromNetwork(ResourceLocation recipeId, FriendlyByteBuf buffer) {
            try {
                RecipeUtils.Category category = RecipeUtils.Category.with(buffer.readUtf());

                int processingTime = buffer.readVarInt();
                FluidStack inputFluidStack = buffer.readFluidStack();
                ItemStack activationTool = buffer.readItem();

                int ingredientSize = buffer.readVarInt();

                List<ItemStack> ingredients = new ArrayList<>();
                for (int i = 0; i < ingredientSize; i++) {
                    ingredients.add(buffer.readItem());
                }

                FluidStack reagentFluidStack = buffer.readFluidStack();
                FluidStack outputFluidStack = buffer.readFluidStack();
                FluidStack wasteFluidStack = buffer.readFluidStack();

                return new MixingVatFluidRecipe(recipeId, category, inputFluidStack, reagentFluidStack,
                        ingredients, processingTime, outputFluidStack, wasteFluidStack, activationTool);

            } catch (Exception ex) {
                String message = String.format("Unable to read recipe (%s) from network buffer.", recipeId);
                GrowthcraftMilk.LOGGER.error(message);
                throw ex;
            }
        }

        public void toNetwork(FriendlyByteBuf buffer, MixingVatFluidRecipe recipe) {
            buffer.writeUtf(recipe.getCategory().toString());
            buffer.writeVarInt(recipe.getProcessingTime());
            buffer.writeFluidStack(recipe.getInputFluidStack());
            buffer.writeItemStack(recipe.getActivationTool(), false);
            buffer.writeVarInt(recipe.getIngredientList().size());

            for (int i = 0; i < recipe.getIngredientList().size(); i++) {
                buffer.writeItemStack(recipe.getIngredientList().get(i), false);
            }


            MixingVatFluidRecipe fluidRecipe = recipe;
            buffer.writeFluidStack(fluidRecipe.getReagentFluidStack());
            buffer.writeFluidStack(fluidRecipe.getOutputFluidStack());
            buffer.writeFluidStack(fluidRecipe.getWasteFluidStack());

        }

    }
}
