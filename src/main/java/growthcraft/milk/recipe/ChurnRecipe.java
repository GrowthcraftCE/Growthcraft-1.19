package growthcraft.milk.recipe;

import com.google.gson.JsonObject;
import growthcraft.lib.utils.CraftingUtils;
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
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ChurnRecipe implements Recipe<SimpleContainer> {

    private final ResourceLocation recipeId;
    private final FluidStack inputFluidStack;
    private final FluidStack outputFluidStack;
    private final ItemStack resultItemStack;
    private final int byProductChance;
    private final int plunges;

    public ChurnRecipe(ResourceLocation recipeId, FluidStack inputFluidStack, FluidStack outputFluidStack, ItemStack result, int byProductChance, int plunges) {
        this.recipeId = recipeId;
        this.inputFluidStack = inputFluidStack;
        this.outputFluidStack = outputFluidStack;
        this.resultItemStack = result;
        this.plunges = plunges;
        this.byProductChance = byProductChance;
    }

    @Override
    public ItemStack assemble(SimpleContainer simpleContainer, RegistryAccess registryAccess) {
        return this.resultItemStack;
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return true;
    }

    @Override
    public boolean matches(SimpleContainer simpleContainer, Level level) {
        return false;
    }

    public boolean matches(FluidStack fluidStack) {
        return this.inputFluidStack.getFluid() == fluidStack.getFluid()
                && this.inputFluidStack.getAmount() <= fluidStack.getAmount();
    }

    public FluidStack getInputFluidStack() {
        return inputFluidStack;
    }

    public FluidStack getOutputFluidStack() {
        return this.outputFluidStack;
    }

    public ItemStack getResultItemStack() {
        return this.resultItemStack;
    }

    public int getPlungesNeeded() {
        return this.plunges;
    }

    @Override
    public ItemStack getResultItem(RegistryAccess registryAccess) {
        return this.resultItemStack;
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

    public int getByProductChance() {
        return this.byProductChance;
    }

    public static class Type implements RecipeType<ChurnRecipe> {
        private Type() { /* Prevent default constructor */ }

        public static final ChurnRecipe.Type INSTANCE = new ChurnRecipe.Type();
        public static final String ID = Reference.UnlocalizedName.CHURN_RECIPE;
    }


    public static class Serializer implements RecipeSerializer<ChurnRecipe> {

        public static final ChurnRecipe.Serializer INSTANCE = new ChurnRecipe.Serializer();
        public static final ResourceLocation ID = new ResourceLocation(
                Reference.MODID,
                Reference.UnlocalizedName.CHURN_RECIPE);

        @Override
        public @NotNull ChurnRecipe fromJson(ResourceLocation recipeId, JsonObject json) {
            FluidStack inputFluid = CraftingUtils.getFluidStack(GsonHelper.getAsJsonObject(json, "input_fluid"));
            FluidStack outputFluid = CraftingUtils.getFluidStack(GsonHelper.getAsJsonObject(json, "output_fluid"));

            ItemStack byProduct = CraftingHelper.getItemStack(GsonHelper.getAsJsonObject(json, "by_product"), false);

            int plunges = GsonHelper.getAsInt(json, "plunges", 7);
            int byProductChance = GsonHelper.getAsInt(json, "by_product_chance", 100);

            return new ChurnRecipe(recipeId, inputFluid, outputFluid, byProduct, byProductChance, plunges);
        }

        @Override
        public @Nullable ChurnRecipe fromNetwork(ResourceLocation recipeId, FriendlyByteBuf buffer) {
            try {
                ItemStack byProduct = buffer.readItem();
                FluidStack inputFluid = buffer.readFluidStack();
                FluidStack outputFluid = buffer.readFluidStack();
                int plungesNeeded = buffer.readVarInt();
                int byProductChange = buffer.readVarInt();

                return new ChurnRecipe(recipeId, inputFluid, outputFluid, byProduct, byProductChange, plungesNeeded);
            } catch (Exception ex) {
                String message = String.format("Unable to read recipe (%s) from network buffer.", recipeId);
                GrowthcraftMilk.LOGGER.error(message);
                throw ex;
            }
        }

        @Override
        public void toNetwork(FriendlyByteBuf buffer, ChurnRecipe recipe) {
            buffer.writeItemStack(recipe.getResultItemStack(), false);
            buffer.writeFluidStack(recipe.getInputFluidStack());
            buffer.writeFluidStack(recipe.getOutputFluidStack());
            buffer.writeVarInt(recipe.getPlungesNeeded());
            buffer.writeVarInt(recipe.getByProductChance());

        }

    }
}
