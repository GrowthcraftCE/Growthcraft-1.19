package growthcraft.cellar.recipe;

import com.google.gson.JsonObject;
import growthcraft.cellar.GrowthcraftCellar;
import growthcraft.cellar.shared.Reference;
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
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class RoasterRecipe implements Recipe<SimpleContainer> {

    private final ResourceLocation recipeId;

    private final ItemStack inputItem;
    private final ItemStack resultItem;
    private final int processingTime;

    public RoasterRecipe(ResourceLocation recipeId, ItemStack input, ItemStack result, int processingTime) {
        this.recipeId = recipeId;
        this.inputItem = input;
        this.resultItem = result;
        this.processingTime = processingTime;
    }

    @Override
    public boolean matches(SimpleContainer simpleContainer, Level level) {
        return false;
    }

    public boolean matches(ItemStack input, int timer) {
        return this.inputItem.getItem() == input.getItem()
                && this.inputItem.getCount() <= input.getCount()
                && this.processingTime == timer;
    }

    @Override
    public ItemStack assemble(SimpleContainer simpleContainer) {
        return this.inputItem;
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getResultItem() {
        return this.resultItem;
    }

    @Override
    public @NotNull ResourceLocation getId() {
        return this.recipeId;
    }

    public ItemStack getInputItemStack() {
        return this.inputItem;
    }

    public int getRecipeProcessingTime() {
        return this.processingTime;
    }

    @Override
    public @NotNull RecipeSerializer<?> getSerializer() {
        return RoasterRecipe.Serializer.INSTANCE;
    }

    public @NotNull RecipeType<?> getType() {
        return RoasterRecipe.Type.INSTANCE;
    }

    public static class Type implements RecipeType<RoasterRecipe> {
        private Type() { /* Prevent default constructor */ }

        public static final RoasterRecipe.Type INSTANCE = new RoasterRecipe.Type();
        public static final String ID = Reference.UnlocalizedName.ROASTER_RECIPE;
    }

    public static class Serializer implements RecipeSerializer<RoasterRecipe> {
        private Serializer() { /* Prevent default constructor */ }

        public static final RoasterRecipe.Serializer INSTANCE = new RoasterRecipe.Serializer();
        public static final ResourceLocation ID = new ResourceLocation(
                Reference.MODID,
                Reference.UnlocalizedName.ROASTER_RECIPE);

        @Override
        public @NotNull RoasterRecipe fromJson(ResourceLocation recipeId, JsonObject json) {

            ItemStack inputItemStack = CraftingHelper.getItemStack(GsonHelper.getAsJsonObject(json, "input_item"), false);
            ItemStack resultItemStack = CraftingHelper.getItemStack(GsonHelper.getAsJsonObject(json, "result"), false);

            int processingTime = GsonHelper.getAsInt(json, "roasting_level", 1);

            return new RoasterRecipe(recipeId, inputItemStack, resultItemStack, processingTime);
        }

        @Override
        public @Nullable RoasterRecipe fromNetwork(ResourceLocation recipeId, FriendlyByteBuf buffer) {
            try {
                ItemStack input = buffer.readItem();
                ItemStack result = buffer.readItem();
                int processing = buffer.readVarInt();

                return new RoasterRecipe(recipeId, input, result, processing);
            } catch (Exception ex) {
                String message = String.format("Unable to read recipe (%s) from network buffer.", recipeId);
                GrowthcraftCellar.LOGGER.error(message);
                throw ex;
            }
        }

        @Override
        public void toNetwork(FriendlyByteBuf buffer, RoasterRecipe recipe) {
            buffer.writeItemStack(recipe.getInputItemStack(), false);
            buffer.writeItemStack(recipe.getResultItem(), false);
            buffer.writeVarInt(recipe.getRecipeProcessingTime());
        }

    }
}
