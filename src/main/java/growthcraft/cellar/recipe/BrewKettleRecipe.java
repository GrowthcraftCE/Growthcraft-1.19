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

public class BrewKettleRecipe implements Recipe<SimpleContainer> {

    private final ResourceLocation recipeId;
    private final FluidStack inputFluidStack;
    private final ItemStack inputItemStack;
    private final FluidStack outputFluidStack;
    private final ItemStack byProduct;
    private final boolean requiresLid;
    private final boolean requiresHeat;
    private final int byProductChance;
    private final int processingTime;


    public BrewKettleRecipe(ResourceLocation recipeId, FluidStack inputFluidStack, ItemStack inputItem, FluidStack outputFluidStack, ItemStack byProduct, int byProductChance, int processingTime, boolean requiresHeat, boolean requiresLid) {
        this.recipeId = recipeId;
        this.inputFluidStack = inputFluidStack;
        this.inputItemStack = inputItem;
        this.outputFluidStack = outputFluidStack;
        this.byProduct = byProduct;
        this.processingTime = processingTime;
        this.requiresLid = requiresLid;
        this.requiresHeat = requiresHeat;
        this.byProductChance = byProductChance;
    }

    @Override
    public boolean matches(SimpleContainer container, Level level) {
        return false;
    }

    public boolean matches(ItemStack itemStack, FluidStack fluidStack, boolean needsLid, boolean needsHeat) {
        return this.inputItemStack.getItem() == itemStack.getItem()
                && this.inputItemStack.getCount() <= itemStack.getCount()
                && this.inputFluidStack.getFluid() == fluidStack.getFluid()
                && this.inputFluidStack.getAmount() <= fluidStack.getAmount()
                && this.requiresLid == needsLid && this.requiresHeat == needsHeat;
    }

    @Override
    public ItemStack assemble(SimpleContainer container, RegistryAccess registryAccess) {
        return this.outputFluidStack.getFluid().getBucket().getDefaultInstance();
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return false;
    }

    @Override
    public ItemStack getResultItem(RegistryAccess registryAccess) {
        return this.outputFluidStack.getFluid().getBucket().getDefaultInstance();
    }

    @Override
    public ResourceLocation getId() {
        return this.recipeId;
    }

    public FluidStack getInputFluidStack() {
        return inputFluidStack;
    }

    public FluidStack getOutputFluidStack() {
        return outputFluidStack;
    }

    public ItemStack getInputItemStack() {
        return inputItemStack;
    }

    public ItemStack getByProduct() {
        return byProduct;
    }

    public int getByProductChance() {
        return byProductChance;
    }

    public int getProcessingTime() {
        return this.processingTime;
    }

    public boolean isLidRequired() {
        return requiresLid;
    }

    public boolean isHeatRequired() {
        return this.requiresHeat;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return Serializer.INSTANCE;
    }

    @Override
    public RecipeType<?> getType() {
        return Type.INSTANCE;
    }

    public static class Type implements RecipeType<BrewKettleRecipe> {
        private Type() { /* Prevent default constructor */ }

        public static final BrewKettleRecipe.Type INSTANCE = new BrewKettleRecipe.Type();
        public static final String ID = Reference.UnlocalizedName.BREW_KETTLE_RECIPE;
    }


    public static class Serializer implements RecipeSerializer<BrewKettleRecipe> {

        public static final BrewKettleRecipe.Serializer INSTANCE = new BrewKettleRecipe.Serializer();
        public static final ResourceLocation ID = new ResourceLocation(
                Reference.MODID,
                Reference.UnlocalizedName.BREW_KETTLE_RECIPE);

        @Override
        public @NotNull BrewKettleRecipe fromJson(ResourceLocation recipeId, JsonObject json) {

            ItemStack inputItemStack = CraftingHelper.getItemStack(GsonHelper.getAsJsonObject(json, "input_item"), false);
            ItemStack byProductItemStack = CraftingHelper.getItemStack(GsonHelper.getAsJsonObject(json, "by_product"), false);

            FluidStack inputFluid = CraftingUtils.getFluidStack(GsonHelper.getAsJsonObject(json, "input_fluid"));
            FluidStack outputFluid = CraftingUtils.getFluidStack(GsonHelper.getAsJsonObject(json, "output_fluid"));

            boolean requiresHeat = GsonHelper.getAsBoolean(json, "requires_heat");
            boolean requiresLid = GsonHelper.getAsBoolean(json, "requires_heat");

            int processingTime = GsonHelper.getAsInt(json, "processing_time", 600);
            int byProductChance = GsonHelper.getAsInt(json, "processing_time", 10);

            return new BrewKettleRecipe(recipeId, inputFluid, inputItemStack,
                    outputFluid, byProductItemStack, byProductChance, processingTime,
                    requiresHeat, requiresLid);
        }

        @Override
        public @Nullable BrewKettleRecipe fromNetwork(ResourceLocation recipeId, FriendlyByteBuf buffer) {
            try {
                ItemStack inputItemStack = buffer.readItem();
                ItemStack byProductItemStack = buffer.readItem();
                FluidStack inputFluidStack = buffer.readFluidStack();
                FluidStack outputFluidStack = buffer.readFluidStack();
                boolean requiresHeat = buffer.readBoolean();
                boolean requiresLid = buffer.readBoolean();
                int processingTime = buffer.readVarInt();
                int byProductChance = buffer.readVarInt();

                return new BrewKettleRecipe(recipeId, inputFluidStack, inputItemStack,
                        outputFluidStack, byProductItemStack, byProductChance, processingTime,
                        requiresHeat, requiresLid);
            } catch (Exception ex) {
                String message = String.format("Unable to read recipe (%s) from network buffer.", recipeId);
                GrowthcraftCellar.LOGGER.error(message);
                throw ex;
            }
        }

        @Override
        public void toNetwork(FriendlyByteBuf buffer, BrewKettleRecipe recipe) {
            buffer.writeItemStack(recipe.getInputItemStack(), false);
            buffer.writeItemStack(recipe.getByProduct(), false);
            buffer.writeFluidStack(recipe.getInputFluidStack());
            buffer.writeFluidStack(recipe.getOutputFluidStack());
            buffer.writeBoolean(recipe.isHeatRequired());
            buffer.writeBoolean(recipe.isLidRequired());
            buffer.writeVarInt(recipe.getProcessingTime());
            buffer.writeVarInt(recipe.getByProductChance());
        }

    }
}
