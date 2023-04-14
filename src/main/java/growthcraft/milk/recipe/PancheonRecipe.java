package growthcraft.milk.recipe;

import com.google.gson.JsonArray;
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
import net.minecraftforge.fluids.FluidStack;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;

public class PancheonRecipe implements Recipe<SimpleContainer> {

    private final ResourceLocation recipeId;
    private final FluidStack inputFluidStack;
    private final FluidStack outputFluidStack1;
    private final FluidStack outputFluidStack2;
    private final int processingTime;

    private static final String INPUT_0 = "input0";
    private static final String OUTPUT_0 = "output0";
    private static final String OUTPUT_1 = "output1";


    public PancheonRecipe(ResourceLocation recipeId, FluidStack inputFluidStack, FluidStack outputFluidStack1, FluidStack outputFluidStack2, int processingTime) {
        this.recipeId = recipeId;
        this.inputFluidStack = inputFluidStack;
        this.outputFluidStack1 = outputFluidStack1;
        this.outputFluidStack2 = outputFluidStack2;
        this.processingTime = processingTime;
    }

    @Override
    public boolean matches(SimpleContainer simpleContainer, Level level) {
        return false;
    }

    public boolean matches(FluidStack fluidStack) {
        return this.inputFluidStack.getFluid() == fluidStack.getFluid();
    }

    @Override
    public ItemStack assemble(SimpleContainer simpleContainer, RegistryAccess registryAccess) {
        return this.outputFluidStack1.getFluid().getBucket().getDefaultInstance();
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getResultItem(RegistryAccess registryAccess) {
        return this.getFluidStack(OUTPUT_0).getFluid().getBucket().getDefaultInstance();
    }

    public List<FluidStack> getResultFluidStacks() {
       return Arrays.asList(this.getFluidStack(OUTPUT_0).copy(), this.getFluidStack(OUTPUT_1).copy());
    }

    @Override
    public ResourceLocation getId() {
        return this.recipeId;
    }

    public FluidStack getFluidStack(String tankId) {
        switch(tankId) {
            case "output0":
                return this.outputFluidStack1;
            case "output1":
                return this.outputFluidStack2;
            default:
                return this.inputFluidStack.copy();
        }
    }

    public int getRecipeProcessingTime() {
        return this.processingTime;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return Serializer.INSTANCE;
    }

    @Override
    public RecipeType<?> getType() {
        return Type.INSTANCE;
    }

    public static class Type implements RecipeType<PancheonRecipe> {
        private Type() { /* Prevent default constructor */ }
        public static final Type INSTANCE = new Type();
        public static final String ID = Reference.UnlocalizedName.PANCHEON_RECIPE;
    }

    public static class Serializer implements RecipeSerializer<PancheonRecipe> {

        public static final Serializer INSTANCE = new Serializer();
        public static final ResourceLocation ID = new ResourceLocation(
                Reference.MODID,
                Reference.UnlocalizedName.PANCHEON_RECIPE);

        @Override
        public PancheonRecipe fromJson(ResourceLocation recipeId, JsonObject json) {
            FluidStack inputFluid = CraftingUtils.getFluidStack(GsonHelper.getAsJsonObject(json, "input_fluid"));
            FluidStack outputFluid1;
            FluidStack outputFluid2;

            JsonArray outputFluids = GsonHelper.getAsJsonArray(json, "output_fluids");

            outputFluid1 = CraftingUtils.getFluidStack(outputFluids.get(0).getAsJsonObject());
            outputFluid2 = CraftingUtils.getFluidStack(outputFluids.get(1).getAsJsonObject());

            int processingTime = GsonHelper.getAsInt(json, "processing_time", 1200);

            return new PancheonRecipe(recipeId, inputFluid, outputFluid1, outputFluid2, processingTime);        }

        @Override
        public @Nullable PancheonRecipe fromNetwork(ResourceLocation recipeId, FriendlyByteBuf buffer) {
            try {
                FluidStack inputFluid = buffer.readFluidStack();
                FluidStack outputFluid1 = buffer.readFluidStack();
                FluidStack outputFluid2 = buffer.readFluidStack();
                int processingTime = buffer.readVarInt();
                return new PancheonRecipe(recipeId, inputFluid, outputFluid1, outputFluid2, processingTime);
            } catch (Exception ex) {
                String message = String.format("Unable to read recipe (%s) from network buffer.", recipeId);
                GrowthcraftMilk.LOGGER.error(message);
                throw ex;
            }        }

        @Override
        public void toNetwork(FriendlyByteBuf buffer, PancheonRecipe recipe) {
            buffer.writeFluidStack(recipe.getFluidStack(INPUT_0));
            buffer.writeFluidStack(recipe.getFluidStack(OUTPUT_0));
            buffer.writeFluidStack(recipe.getFluidStack(OUTPUT_1));
            buffer.writeVarInt(recipe.getRecipeProcessingTime());
        }

    }


}
