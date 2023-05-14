package growthcraft.milk.init;

import growthcraft.milk.recipe.ChurnRecipe;
import growthcraft.milk.recipe.PancheonRecipe;
import growthcraft.milk.shared.Reference;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class GrowthcraftMilkRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, Reference.MODID);

    private GrowthcraftMilkRecipes() {
        /* Private constructor to hide the implicit public one. */
    }

    public static final RegistryObject<RecipeSerializer<PancheonRecipe>> PANCHEON_RECIPE_SERIALIZER =
            SERIALIZERS.register(Reference.UnlocalizedName.PANCHEON_RECIPE, () -> PancheonRecipe.Serializer.INSTANCE);

    public static final RegistryObject<RecipeSerializer<ChurnRecipe>> CHURN_RECIPE_SERIALIZER =
            SERIALIZERS.register(Reference.UnlocalizedName.CHURN_RECIPE, () -> ChurnRecipe.Serializer.INSTANCE);

    public static void register(IEventBus eventBus) {
        SERIALIZERS.register(eventBus);
    }
}
