package growthcraft.cellar.recipe;

import com.google.gson.JsonObject;
import growthcraft.cellar.GrowthcraftCellar;
import growthcraft.cellar.shared.Reference;
import growthcraft.lib.utils.CraftingUtils;
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
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class FruitPressRecipe implements Recipe<SimpleContainer> {

    private final ResourceLocation recipeId;
    private final ItemStack inputItemStack;
    private final FluidStack outputFluidStack;
    private final int processingTime;

    public FruitPressRecipe(ResourceLocation recipeId,
                            ItemStack inputItemStack, FluidStack outputFluidStack,
                            int processingTime) {
        this.recipeId = recipeId;
        this.inputItemStack = inputItemStack;
        this.outputFluidStack = outputFluidStack;
        this.processingTime = processingTime;
    }

    @Override
    public boolean matches(SimpleContainer container, Level level) {
        return false;
    }

    public boolean matches(ItemStack itemStack) {
        return this.inputItemStack.getItem() == itemStack.getItem();
    }

    public boolean matches(ItemStack itemStack, FluidStack fluidStack) {
        return this.matches(itemStack) &&
                this.outputFluidStack.getFluid() == fluidStack.getFluid();
    }

    @Override
    public ItemStack assemble(SimpleContainer container, RegistryAccess registryAccess) {
        return this.outputFluidStack.getFluid().getBucket().getDefaultInstance().copy();
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getResultItem(RegistryAccess registryAccess) {
        return this.outputFluidStack.getFluid().getBucket().getDefaultInstance().copy();
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

    public int getProcessingTime() {
        return this.processingTime;
    }

    public FluidStack getResultingFluid() {
        return this.outputFluidStack;
    }

    public ItemStack getIngredientItemStack() {
        return this.inputItemStack;
    }

    public static class Type implements RecipeType<FruitPressRecipe> {
        private Type() { /* Prevent default constructor */ }

        public static final FruitPressRecipe.Type INSTANCE = new FruitPressRecipe.Type();
        public static final String ID = Reference.UnlocalizedName.FRUIT_PRESS_RECIPE;
    }


    public static class Serializer implements RecipeSerializer<FruitPressRecipe> {

        public static final FruitPressRecipe.Serializer INSTANCE = new FruitPressRecipe.Serializer();
        public static final ResourceLocation ID = new ResourceLocation(
                Reference.MODID,
                Reference.UnlocalizedName.FRUIT_PRESS_RECIPE);

        @Override
        public @NotNull FruitPressRecipe fromJson(ResourceLocation recipeId, JsonObject json) {
            ItemStack inputItemStack = CraftingHelper.getItemStack(GsonHelper.getAsJsonObject(json, "input_item"), false);

            FluidStack outputFluid = CraftingUtils.getFluidStack(GsonHelper.getAsJsonObject(json, "output_fluid"));

            int processingTime = GsonHelper.getAsInt(json, "processing_time", 1200);

            return new FruitPressRecipe(recipeId, inputItemStack, outputFluid, processingTime);
        }

        @Override
        public @Nullable FruitPressRecipe fromNetwork(ResourceLocation recipeId, FriendlyByteBuf buffer) {
            try {
                ItemStack inputItemStack = buffer.readItem();
                FluidStack outputFluid = buffer.readFluidStack();
                int processingTime = buffer.readVarInt();

                return new FruitPressRecipe(recipeId, inputItemStack, outputFluid, processingTime);
            } catch (Exception ex) {
                String message = String.format("Unable to read recipe (%s) from network buffer.", recipeId);
                GrowthcraftCellar.LOGGER.error(message);
                throw ex;
            }
        }

        @Override
        public void toNetwork(FriendlyByteBuf buffer, FruitPressRecipe recipe) {
            buffer.writeItemStack(recipe.getIngredientItemStack(), false);
            buffer.writeFluidStack(recipe.getResultingFluid());
            buffer.writeVarInt(recipe.getProcessingTime());
        }

    }


}
