package growthcraft.cellar.init;

import growthcraft.cellar.recipe.*;
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

    public static final RegistryObject<RecipeSerializer<BrewKettleRecipe>> BREW_KETTLE_RECIPE_SERIAL =
            SERIALIZERS.register(Reference.UnlocalizedName.BREW_KETTLE_RECIPE,
                    () -> BrewKettleRecipe.Serializer.INSTANCE);

    public static final RegistryObject<RecipeSerializer<CultureJarRecipe>> CULTURE_JAR_RECIPE_SERIALIZER =
            SERIALIZERS.register(Reference.UnlocalizedName.CULTURE_JAR_RECIPE,
                    () -> CultureJarRecipe.Serializer.INSTANCE);

    public static final RegistryObject<RecipeSerializer<CultureJarStarterRecipe>> CULTURE_JAR_STARTER_RECIPE_SERIAL =
            SERIALIZERS.register(Reference.UnlocalizedName.CULTURE_JAR_STARTER_RECIPE,
                    () -> CultureJarStarterRecipe.Serializer.INSTANCE);

    public static final RegistryObject<RecipeSerializer<FermentationBarrelRecipe>> FERMENT_BARREL_RECIPE_SERIAL =
            SERIALIZERS.register(Reference.UnlocalizedName.FERMENT_BARREL_RECIPE,
                    () -> FermentationBarrelRecipe.Serializer.INSTANCE);

    public static final RegistryObject<RecipeSerializer<FruitPressRecipe>> FRUIT_PRESS_RECIPE_SERIAL =
            SERIALIZERS.register(Reference.UnlocalizedName.FRUIT_PRESS_RECIPE,
                    () -> FruitPressRecipe.Serializer.INSTANCE);

    public static final RegistryObject<RecipeSerializer<RoasterRecipe>> ROASTER_RECIPE_SERIAL =
            SERIALIZERS.register(Reference.UnlocalizedName.ROASTER_RECIPE,
                    () -> RoasterRecipe.Serializer.INSTANCE);



    public static void register(IEventBus eventBus) {
        SERIALIZERS.register(eventBus);
    }


}
