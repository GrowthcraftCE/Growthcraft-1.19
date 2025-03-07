package growthcraft.cellar.recipe;

import com.google.gson.JsonObject;
import growthcraft.cellar.GrowthcraftCellar;
import growthcraft.cellar.shared.Reference;
import growthcraft.lib.utils.CraftingUtils;
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
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class CultureJarRecipe implements Recipe<SimpleContainer> {

    private final ResourceLocation recipeId;
    private final FluidStack inputFluidStack;
    private final ItemStack inputItem;
    private final int processingTime;
    private final boolean requiresHeatSource;

    public CultureJarRecipe(ResourceLocation recipeId, FluidStack inputFluidStack, ItemStack inputItem, int processingTime, boolean requiresHeatSource) {
        this.recipeId = recipeId;
        this.inputFluidStack = inputFluidStack;
        this.inputItem = inputItem;
        this.processingTime = processingTime;
        this.requiresHeatSource = requiresHeatSource;
    }

    @Override
    public boolean matches(SimpleContainer simpleContainer, Level level) {
        return false;
    }

    public boolean matches(FluidStack fluidStack, ItemStack itemStack) {
        return this.inputFluidStack.getFluid() == fluidStack.getFluid()
                && this.inputItem.getItem() == itemStack.getItem();
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
        return this.inputItem;
    }

    @Override
    public @NotNull ResourceLocation getId() {
        return this.recipeId;
    }

    public FluidStack getInputFluidStack() {
        return this.inputFluidStack;
    }

    public ItemStack getInputItemStack() {
        return this.inputItem;
    }

    public boolean isHeatSourceRequired() {
        return this.requiresHeatSource;
    }

    public int getRecipeProcessingTime() {
        return this.processingTime;
    }

    @Override
    public @NotNull RecipeSerializer<?> getSerializer() {
        return Serializer.INSTANCE;
    }

    public @NotNull RecipeType<?> getType() {
        return Type.INSTANCE;
    }

    public static class Type implements RecipeType<CultureJarRecipe> {
        private Type() { /* Prevent default constructor */ }

        public static final CultureJarRecipe.Type INSTANCE = new CultureJarRecipe.Type();
        public static final String ID = Reference.UnlocalizedName.CULTURE_JAR_RECIPE;
    }

    public static class Serializer implements RecipeSerializer<CultureJarRecipe> {

        public static final CultureJarRecipe.Serializer INSTANCE = new CultureJarRecipe.Serializer();
        public static final ResourceLocation ID = new ResourceLocation(
                Reference.MODID,
                Reference.UnlocalizedName.CULTURE_JAR_RECIPE);

        @Override
        public @NotNull CultureJarRecipe fromJson(ResourceLocation recipeId, JsonObject json) {
            FluidStack inputFluid = CraftingUtils.getFluidStack(GsonHelper.getAsJsonObject(json, "input_fluid"));

            ItemStack inputItemStack = CraftingHelper.getItemStack(GsonHelper.getAsJsonObject(json, "input_item"), false);

            int processingTime = GsonHelper.getAsInt(json, "processing_time", 1200);
            boolean needsHeat = GsonHelper.getAsBoolean(json, "requires_heat_source");

            return new CultureJarRecipe(recipeId, inputFluid, inputItemStack, processingTime, needsHeat);
        }

        @Override
        public @Nullable CultureJarRecipe fromNetwork(ResourceLocation recipeId, FriendlyByteBuf buffer) {
            try {
                ItemStack item = buffer.readItem();
                FluidStack fluid = buffer.readFluidStack();
                int processing = buffer.readVarInt();
                boolean heat = buffer.readBoolean();

                return new CultureJarRecipe(recipeId, fluid, item, processing, heat);
            } catch (Exception ex) {
                String message = String.format("Unable to read recipe (%s) from network buffer.", recipeId);
                GrowthcraftCellar.LOGGER.error(message);
                throw ex;
            }
        }

        @Override
        public void toNetwork(FriendlyByteBuf buffer, CultureJarRecipe recipe) {
            buffer.writeItemStack(recipe.getInputItemStack(), false);
            buffer.writeFluidStack(recipe.getInputFluidStack());
            buffer.writeVarInt(recipe.getRecipeProcessingTime());
            buffer.writeBoolean(recipe.isHeatSourceRequired());
        }

    }
}
