package growthcraft.milk.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import growthcraft.lib.utils.CraftingUtils;
import growthcraft.milk.GrowthcraftMilk;
import growthcraft.milk.shared.Reference;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.fluids.FluidStack;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public abstract class MixingVatRecipe implements Recipe<SimpleContainer> {

    private final ResourceLocation recipeId;
    private final Category category;
    private final ItemStack activationTool;
    private final List<ItemStack> ingredients;
    private final FluidStack inputFluidStack;
    private final int processingTime;

    public MixingVatRecipe(ResourceLocation recipeId, Category category,
                           FluidStack inputFluidStack, List<ItemStack> ingredients,
                           int processingTime, ItemStack activationTool) {
        this.recipeId = recipeId;
        this.category = category;
        this.inputFluidStack = inputFluidStack;
        this.ingredients = ingredients;
        this.processingTime = processingTime;
        this.activationTool = activationTool;
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return true;
    }

    @Override
    public ResourceLocation getId() {
        return this.recipeId;
    }

    public Category getCategory() {
        return this.category;
    }

    public ItemStack getActivationTool() {
        return this.activationTool;
    }

    public boolean activationToolValid(ItemStack tool) {
        return this.activationTool.getItem() == tool.getItem();
    }

    public FluidStack getInputFluidStack() {
        return inputFluidStack;
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

    @Override
    public RecipeSerializer<?> getSerializer() {
        return Serializer.INSTANCE;
    }

    public enum Category {
        FLUID("fluid"), ITEM("item");

        private final String value;

        Category(final String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return value;
        }

        public static Category with(String value) {
            return switch (value) {
                case "fluid" -> FLUID;
                case "item" -> ITEM;
                default -> null;
            };
        }
    }

    public static class Serializer implements RecipeSerializer<MixingVatRecipe> {

        public static final MixingVatRecipe.Serializer INSTANCE = new MixingVatRecipe.Serializer();
        public static final ResourceLocation ID = new ResourceLocation(
                Reference.MODID,
                Reference.UnlocalizedName.MIXING_VAT_RECIPE);

        private static final int maxIngredients = 3;

        @Override
        public MixingVatRecipe fromJson(ResourceLocation recipeId, JsonObject json) {
            MixingVatRecipe.Category category
                    = MixingVatRecipe.Category.with(GsonHelper.getAsString(json, "result_type"));

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

            if (category == MixingVatRecipe.Category.ITEM) {
                ItemStack resultItemStack = CraftingHelper.getItemStack(
                        GsonHelper.getAsJsonObject(json, "result_item"), false);
                ItemStack resultActivationTool = CraftingHelper.getItemStack(
                        GsonHelper.getAsJsonObject(json, "result_activation_tool"), false);

                return new MixingVatItemRecipe(recipeId, MixingVatRecipe.Category.ITEM,
                        inputFluid, ingredients, processingTime, resultItemStack, activationTool, resultActivationTool);
            }

            if (category == MixingVatRecipe.Category.FLUID) {
                FluidStack reagentFluid = CraftingUtils.getFluidStack(
                        GsonHelper.getAsJsonObject(json, "reagent_fluid"));
                FluidStack resultFluid = CraftingUtils.getFluidStack(
                        GsonHelper.getAsJsonObject(json, "result_fluid"));
                FluidStack wasteFluid = CraftingUtils.getFluidStack(
                        GsonHelper.getAsJsonObject(json, "result_fluid_waste"));

                return new MixingVatFluidRecipe(recipeId, MixingVatRecipe.Category.FLUID,
                        inputFluid, reagentFluid, ingredients, processingTime, resultFluid, wasteFluid, activationTool);
            }

            return null;
        }

        @Override
        public @Nullable MixingVatRecipe fromNetwork(ResourceLocation recipeId, FriendlyByteBuf buffer) {
            try {
                MixingVatRecipe.Category category = MixingVatRecipe.Category.with(buffer.readUtf());

                int processingTime = buffer.readVarInt();
                FluidStack inputFluidStack = buffer.readFluidStack();
                ItemStack activationTool = buffer.readItem();

                int ingredientSize = buffer.readVarInt();
                List<ItemStack> ingredients = new ArrayList<>();
                for (int i = 0; i < ingredientSize; i++) {
                    ingredients.add(buffer.readItem());
                }

                if (category == MixingVatRecipe.Category.ITEM) {
                    ItemStack resultingItemStack = buffer.readItem();
                    ItemStack resultActivationTool = buffer.readItem();

                    return new MixingVatItemRecipe(recipeId, category, inputFluidStack, ingredients,
                            processingTime, resultingItemStack, activationTool, resultActivationTool);

                }

                if (category == MixingVatRecipe.Category.FLUID) {
                    FluidStack reagentFluidStack = buffer.readFluidStack();
                    FluidStack outputFluidStack = buffer.readFluidStack();
                    FluidStack wasteFluidStack = buffer.readFluidStack();

                    return new MixingVatFluidRecipe(recipeId, category, inputFluidStack, reagentFluidStack,
                            ingredients, processingTime, outputFluidStack, wasteFluidStack, activationTool);
                }

            } catch (Exception ex) {
                String message = String.format("Unable to read recipe (%s) from network buffer.", recipeId);
                GrowthcraftMilk.LOGGER.error(message);
                throw ex;
            }

            return null;
        }

        @Override
        public void toNetwork(FriendlyByteBuf buffer, MixingVatRecipe recipe) {
            buffer.writeUtf(recipe.getCategory().toString());
            buffer.writeVarInt(recipe.getProcessingTime());
            buffer.writeFluidStack(recipe.getInputFluidStack());
            buffer.writeItemStack(recipe.getActivationTool(), false);
            buffer.writeVarInt(recipe.getIngredientList().size());
            for (int i = 0; i < recipe.getIngredientList().size(); i++) {
                buffer.writeItemStack(recipe.getIngredientList().get(i), false);
            }

            // Depending on recipe category, we have to build the buffer differently.
            if (recipe.getCategory() == MixingVatRecipe.Category.ITEM) {
                MixingVatItemRecipe itemRecipe = (MixingVatItemRecipe) recipe;
                buffer.writeItemStack(itemRecipe.getResultItemStack(), false);
                buffer.writeItemStack(itemRecipe.getResultActivationTool(), false);
            } else if (recipe.getCategory() == MixingVatRecipe.Category.FLUID) {
                MixingVatFluidRecipe fluidRecipe = (MixingVatFluidRecipe) recipe;
                buffer.writeFluidStack(fluidRecipe.getReagentFluidStack());
                buffer.writeFluidStack(fluidRecipe.getOutputFluidStack());
                buffer.writeFluidStack(fluidRecipe.getWasteFluidStack());
            }
        }

    }
}
