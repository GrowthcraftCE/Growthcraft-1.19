package growthcraft.cellar.init;

import growthcraft.cellar.recipe.CultureJarRecipe;
import growthcraft.cellar.shared.Reference;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class GrowthcraftCellarRecipes {

    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, Reference.MODID);

    private GrowthcraftCellarRecipes() {
        /* Private constructor to hide the implicit public one. */
    }

    public static final RegistryObject<RecipeSerializer<CultureJarRecipe>> CULTURE_JAR_RECIPE_SERIALIZER =
            SERIALIZERS.register(Reference.UnlocalizedName.CULTURE_JAR_RECIPE, () -> CultureJarRecipe.Serializer.INSTANCE);

    public static void register(IEventBus eventBus) {
        SERIALIZERS.register(eventBus);
    }


}
