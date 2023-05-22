package growthcraft.milk.recipe;

import com.google.gson.JsonObject;
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
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class CheesePressRecipe implements Recipe<SimpleContainer> {
    private final ItemStack inputItemStack;
    private final ItemStack outputItemStack;
    private final ItemStack sliceItemStack;
    private final int processingTime;
    private final ResourceLocation recipeId;

    public CheesePressRecipe(ResourceLocation recipeId, ItemStack input, ItemStack result, ItemStack slice, int ticks) {
        this.recipeId = recipeId;
        this.inputItemStack = input;
        this.outputItemStack = result;
        this.sliceItemStack = slice;
        this.processingTime = ticks;
    }

    @Override
    public boolean matches(SimpleContainer container, Level level) {
        return false;
    }

    public boolean matches(ItemStack itemStack) {
        return this.inputItemStack.getItem() == itemStack.getItem()
                && this.inputItemStack.getCount() == itemStack.getCount();
    }

    public boolean matchesOutput(ItemStack itemStack) {
        return this.outputItemStack.getItem() == itemStack.getItem()
                && this.outputItemStack.getCount() == itemStack.getCount();
    }

    @Override
    public ItemStack assemble(SimpleContainer container, RegistryAccess registryAccess) {
        return this.outputItemStack;
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getResultItem(RegistryAccess registryAccess) {
        return this.getResultItemStack();
    }

    public ItemStack getResultItemStack() {
        return this.outputItemStack;
    }

    public ItemStack getInputItemStack() {
        return inputItemStack;
    }

    public ItemStack getSliceItemStack() {
        return sliceItemStack;
    }

    public int getProcessingTime() {
        return processingTime;
    }

    @Override
    public ResourceLocation getId() {
        return this.recipeId;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return Serializer.INSTANCE;
    }

    @Override
    public RecipeType<?> getType() {
        return Type.INSTANCE;
    }

    public static class Type implements RecipeType<CheesePressRecipe> {
        private Type() { /* Prevent default constructor */ }

        public static final CheesePressRecipe.Type INSTANCE = new CheesePressRecipe.Type();
        public static final String ID = Reference.UnlocalizedName.CHEESE_PRESS_RECIPE;
    }



    public static class Serializer implements RecipeSerializer<CheesePressRecipe> {

        public static final CheesePressRecipe.Serializer INSTANCE = new CheesePressRecipe.Serializer();
        public static final ResourceLocation ID = new ResourceLocation(
                Reference.MODID,
                Reference.UnlocalizedName.CHEESE_PRESS_RECIPE);

        @Override
        public @NotNull CheesePressRecipe fromJson(ResourceLocation recipeId, JsonObject json) {
            ItemStack ingredientItemStack = CraftingHelper.getItemStack(GsonHelper.getAsJsonObject(json, "ingredient"), false);
            ItemStack resultItemStack = CraftingHelper.getItemStack(GsonHelper.getAsJsonObject(json, "result_item"), false);
            ItemStack sliceItemStack = CraftingHelper.getItemStack(GsonHelper.getAsJsonObject(json, "result_item"), false);

            int processingTime = GsonHelper.getAsInt(json, "processing_time", 1);

            return new CheesePressRecipe(recipeId, ingredientItemStack, resultItemStack, sliceItemStack, processingTime);
        }

        @Override
        public @Nullable CheesePressRecipe fromNetwork(ResourceLocation recipeId, FriendlyByteBuf buffer) {
            try {
                ItemStack ingredientItemStack = buffer.readItem();
                ItemStack resultItemStack = buffer.readItem();
                ItemStack sliceItemStack = buffer.readItem();
                int processingTime = buffer.readVarInt();

                return new CheesePressRecipe(recipeId, ingredientItemStack, resultItemStack, sliceItemStack, processingTime);
            } catch (Exception ex) {
                String message = String.format("Unable to read recipe (%s) from network buffer.", recipeId);
                GrowthcraftMilk.LOGGER.error(message);
                throw ex;
            }
        }

        @Override
        public void toNetwork(FriendlyByteBuf buffer, CheesePressRecipe recipe) {
            buffer.writeItemStack(recipe.getInputItemStack(), false);
            buffer.writeItemStack(recipe.getResultItemStack(), false);
            buffer.writeItemStack(recipe.getSliceItemStack(), false);
            buffer.writeVarInt(recipe.getProcessingTime());

        }

    }


}
