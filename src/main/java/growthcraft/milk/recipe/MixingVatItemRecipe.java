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

public class MixingVatItemRecipe implements Recipe<SimpleContainer> {

    private final ResourceLocation recipeId;
    private final RecipeUtils.Category category;
    private final ItemStack activationTool;
    private final List<ItemStack> ingredients;
    private final FluidStack inputFluidStack;
    private final int processingTime;
    private final ItemStack resultItemStack;
    private final ItemStack resultActivationTool;

    public MixingVatItemRecipe(ResourceLocation recipeId, RecipeUtils.Category category,
                               FluidStack inputFluidStack, List<ItemStack> ingredients, int processingTime,
                               ItemStack resultItemStack, ItemStack activationTool,
                               ItemStack resultActivationTool) {
        this.recipeId = recipeId;
        this.category = category;
        this.inputFluidStack = inputFluidStack;
        this.ingredients = ingredients;
        this.processingTime = processingTime;
        this.resultItemStack = resultItemStack;
        this.activationTool = activationTool;
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

    public FluidStack getInputFluidStack() {
        return this.inputFluidStack;
    }

    public ItemStack getActivationTool() {
        return this.activationTool;
    }

    public int getProcessingTime() {
        return processingTime;
    }

    public List<ItemStack> getIngredientList() {
        return ingredients;
    }

    public ItemStack getResultItemStack() {
        return this.resultItemStack.copy();
    }

    public ItemStack getResultActivationTool() {
        return this.resultActivationTool;
    }


    public ItemStack assemble(SimpleContainer container, RegistryAccess registryAccess) {
        return resultItemStack;
    }


    public ItemStack getResultItem(RegistryAccess registryAccess) {
        return resultItemStack;
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

    public boolean is(RecipeUtils.Category category) {
        return this.category == category;
    }

    public RecipeType<?> getType() {
        return Type.INSTANCE;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return Serializer.INSTANCE;
    }

    public static class Type implements RecipeType<MixingVatItemRecipe> {
        private Type() { /* Prevent default constructor */ }

        public static final MixingVatItemRecipe.Type INSTANCE = new MixingVatItemRecipe.Type();
        public static final String ID = Reference.UnlocalizedName.MIXING_VAT_ITEM_RECIPE;
    }


    public static class Serializer implements RecipeSerializer<MixingVatItemRecipe> {

        public static final MixingVatItemRecipe.Serializer INSTANCE = new MixingVatItemRecipe.Serializer();
        public static final ResourceLocation ID = new ResourceLocation(
                Reference.MODID,
                Reference.UnlocalizedName.MIXING_VAT_RECIPE);

        private static final int maxIngredients = 3;

        @Override
        public MixingVatItemRecipe fromJson(ResourceLocation recipeId, JsonObject json) {
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

            if (category == RecipeUtils.Category.ITEM) {
                ItemStack resultItemStack = CraftingHelper.getItemStack(
                        GsonHelper.getAsJsonObject(json, "result_item"), false);
                ItemStack resultActivationTool = CraftingHelper.getItemStack(
                        GsonHelper.getAsJsonObject(json, "result_activation_tool"), false);

                return new MixingVatItemRecipe(recipeId, RecipeUtils.Category.ITEM,
                        inputFluid, ingredients, processingTime, resultItemStack, activationTool, resultActivationTool);
            }


            return null;
        }

        @Override
        public @Nullable MixingVatItemRecipe fromNetwork(ResourceLocation recipeId, FriendlyByteBuf buffer) {
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

                ItemStack resultingItemStack = buffer.readItem();
                ItemStack resultActivationTool = buffer.readItem();

                return new MixingVatItemRecipe(recipeId, category, inputFluidStack, ingredients,
                        processingTime, resultingItemStack, activationTool, resultActivationTool);
            } catch (Exception ex) {
                String message = String.format("Unable to read recipe (%s) from network buffer.", recipeId);
                GrowthcraftMilk.LOGGER.error(message);
                throw ex;
            }
        }

        @Override
        public void toNetwork(FriendlyByteBuf buffer, MixingVatItemRecipe recipe) {
            buffer.writeUtf(recipe.getCategory().toString());
            buffer.writeVarInt(recipe.getProcessingTime());
            buffer.writeFluidStack(recipe.getInputFluidStack());
            buffer.writeItemStack(recipe.getActivationTool(), false);
            buffer.writeVarInt(recipe.getIngredientList().size());

            for (int i = 0; i < recipe.getIngredientList().size(); i++) {
                buffer.writeItemStack(recipe.getIngredientList().get(i), false);
            }

            buffer.writeItemStack(recipe.getResultItemStack(), false);
            buffer.writeItemStack(recipe.getResultActivationTool(), false);

        }

    }
}
