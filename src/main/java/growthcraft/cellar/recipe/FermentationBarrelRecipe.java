package growthcraft.cellar.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import growthcraft.cellar.GrowthcraftCellar;
import growthcraft.cellar.shared.Reference;
import growthcraft.lib.utils.CraftingUtils;
import growthcraft.lib.utils.EffectUtils;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.*;

public class FermentationBarrelRecipe implements Recipe<SimpleContainer> {

    private final ResourceLocation recipeId;
    private final FluidStack inputFluidStack;
    private final FluidStack outputFluidStack;
    private final ItemStack inputItemStack;
    private final int processingTime;
    private final Color color;
    private final ItemStack potionItemStack;

    public FermentationBarrelRecipe(ResourceLocation recipeId, FluidStack inputFluidStack, ItemStack inputItemStack,
                                    FluidStack outputFluidStack, int processingTime, ItemStack bottle, Color color) {
        this.recipeId = recipeId;
        this.inputFluidStack = inputFluidStack;
        this.outputFluidStack = outputFluidStack;
        this.inputItemStack = inputItemStack;
        this.processingTime = processingTime;
        this.potionItemStack = bottle;
        this.color = color;

        this.potionItemStack.setHoverName(
                this.potionItemStack.getDisplayName().copy()
                        .append(" ")
                        .append(Component.translatable(this.outputFluidStack.getTranslationKey()))
        );

    }

    @Override
    public boolean matches(SimpleContainer simpleContainer, Level level) {
        return false;
    }

    /**
     * Determine if an ItemStack and a FluidStack matches this recipe. Automatically checks if
     * the ingredients are a multiplier of the matching ingredients.
     *
     * @param matchInputItemStack
     * @param matchInputFluidStack
     * @return
     */
    public boolean matches(ItemStack matchInputItemStack, FluidStack matchInputFluidStack) {

        boolean inputFluidMatches = this.inputFluidStack.getFluid() == matchInputFluidStack.getFluid()
                && this.inputFluidStack.getAmount() <= matchInputFluidStack.getAmount()
                && this.getOutputMultiplier(matchInputFluidStack) > 0;

        boolean inputItemMatches = this.inputItemStack.getItem() == matchInputItemStack.getItem()
                && this.inputItemStack.getCount() <= matchInputItemStack.getCount() * this.getOutputMultiplier(matchInputFluidStack);

        return inputItemMatches && inputFluidMatches;
    }

    /**
     * Determines the output multiplier based on the amount of fluid in the tank.
     *
     * @param fluidStackInTank
     * @return
     */
    public int getOutputMultiplier(FluidStack fluidStackInTank) {
        return fluidStackInTank.getAmount() % this.inputFluidStack.getAmount() == 0
                ? fluidStackInTank.getAmount() / this.inputFluidStack.getAmount()
                : 0;
    }

    /**
     * Determine if a FluidStack matches this recipe output.
     *
     * @param fluidStack
     * @return
     */
    public boolean matches(FluidStack fluidStack) {
        return this.outputFluidStack.getFluid() == fluidStack.getFluid();
    }

    /**
     * Determine id an ItemStack and two FluidStacks match this recipe.
     *
     * @deprecated Method not specific enough, use {@link #matches(ItemStack, FluidStack)} instead.
     *
     * @param inputItemStack
     * @param inputFluidStack
     * @param outputFluidStack
     * @return
     */
    @Deprecated(since = "8.0.10", forRemoval = true)
    public boolean matches(ItemStack inputItemStack, FluidStack inputFluidStack, FluidStack outputFluidStack) {
        boolean inputMatches = matches(inputItemStack, inputFluidStack);
        boolean outputMatches = this.outputFluidStack.getFluid() == outputFluidStack.getFluid();
        return inputMatches && outputMatches;
    }

    @Override
    public ItemStack assemble(SimpleContainer simpleContainer, RegistryAccess registryAccess) {
        return this.inputItemStack;
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getResultItem(RegistryAccess registryAccess) {
        return this.outputFluidStack.getFluid().getBucket().getDefaultInstance();
    }

    public FluidStack getIngredientFluidStack() {
        return this.inputFluidStack;
    }

    public ItemStack getIngredientItemStack() {
        return this.inputItemStack;
    }

    public FluidStack getResultingFluid() {
        return this.outputFluidStack;
    }

    public int getProcessingTime() {
        return this.processingTime;
    }

    public boolean hasEffects() {
        return this.potionItemStack.getItem().isFoil(this.potionItemStack);
    }

    public Color getColor() {
        return this.color;
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        return NonNullList.of(Ingredient.EMPTY, Ingredient.of(this.inputItemStack));
    }

    public ItemStack getBottleItemStack() {
        return this.potionItemStack;
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

    public static class Type implements RecipeType<FermentationBarrelRecipe> {
        private Type() { /* Prevent default constructor */ }

        public static final FermentationBarrelRecipe.Type INSTANCE = new FermentationBarrelRecipe.Type();
        public static final String ID = Reference.UnlocalizedName.FERMENT_BARREL_RECIPE;
    }

    public static class Serializer implements RecipeSerializer<FermentationBarrelRecipe> {

        public static final FermentationBarrelRecipe.Serializer INSTANCE = new FermentationBarrelRecipe.Serializer();
        public static final ResourceLocation ID = new ResourceLocation(
                Reference.MODID,
                Reference.UnlocalizedName.FERMENT_BARREL_RECIPE);

        @Override
        public @NotNull FermentationBarrelRecipe fromJson(ResourceLocation recipeId, JsonObject json) {
            FluidStack inputFluid = CraftingUtils.getFluidStack(GsonHelper.getAsJsonObject(json, "ingredient_fluid"));
            ItemStack inputItemStack = CraftingHelper.getItemStack(GsonHelper.getAsJsonObject(json, "ingredient_item"), false);

            FluidStack outputFluid = CraftingUtils.getFluidStack(GsonHelper.getAsJsonObject(json, "result"));
            ItemStack bottleItemStack = CraftingHelper.getItemStack(GsonHelper.getAsJsonObject(json, "bottle"), false);

            int processingTime = GsonHelper.getAsInt(json, "processing_time", 1200);

            Color color = new Color(Integer.decode(GsonHelper.getAsString(json, "color")));

            JsonArray recipeEffects = GsonHelper.getAsJsonArray(json, "effects");
            for (int i = 0; i < recipeEffects.size(); i++) {
                String effectRegistryName = GsonHelper.getAsString(recipeEffects.get(i).getAsJsonObject(), "effect");
                int effectDuration = GsonHelper.getAsInt(recipeEffects.get(i).getAsJsonObject(), "duration", 200);
                int effectAmplifier = GsonHelper.getAsInt(recipeEffects.get(i).getAsJsonObject(), "amplifier", 0);

                MobEffect effect = ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation(effectRegistryName));

                EffectUtils.addEffect(bottleItemStack, effect, effectDuration, effectAmplifier);
            }

            return new FermentationBarrelRecipe(recipeId, inputFluid, inputItemStack, outputFluid, processingTime, bottleItemStack, color);
        }

        @Override
        public @Nullable FermentationBarrelRecipe fromNetwork(ResourceLocation recipeId, FriendlyByteBuf buffer) {
            try {
                ItemStack inputItemStack = buffer.readItem();
                FluidStack inputFluid = buffer.readFluidStack();
                FluidStack outputFluid = buffer.readFluidStack();
                ItemStack bottleItemStack = buffer.readItem();
                int processingTime = buffer.readVarInt();
                Color color = new Color(buffer.readVarInt());

                return new FermentationBarrelRecipe(recipeId, inputFluid, inputItemStack, outputFluid, processingTime, bottleItemStack, color);
            } catch (Exception ex) {
                String message = String.format("Unable to read recipe (%s) from network buffer.", recipeId);
                GrowthcraftCellar.LOGGER.error(message);
                throw ex;
            }
        }

        @Override
        public void toNetwork(FriendlyByteBuf buffer, FermentationBarrelRecipe recipe) {
            buffer.writeItemStack(recipe.getIngredientItemStack(), false);
            buffer.writeFluidStack(recipe.getIngredientFluidStack());
            buffer.writeFluidStack(recipe.getResultingFluid());
            buffer.writeItemStack(recipe.getBottleItemStack(), false);
            buffer.writeVarInt(recipe.getProcessingTime());
            buffer.writeVarInt(recipe.getColor().hashCode());
        }

    }
}
