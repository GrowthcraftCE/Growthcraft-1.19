package growthcraft.apples.datagen.providers;

import growthcraft.apples.shared.Reference;
import growthcraft.apples.world.GrowthcraftApplesConfiguredFeatures;
import growthcraft.apples.world.GrowthcraftApplesPlacedFeatures;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

/**
 * @credit Kaupenjoe for his DataGenerator tutorials
 */
public class GrowthcraftApplesWorldGenProvider extends DatapackBuiltinEntriesProvider {

    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.CONFIGURED_FEATURE, GrowthcraftApplesConfiguredFeatures::bootstrap)
            .add(Registries.PLACED_FEATURE, GrowthcraftApplesPlacedFeatures::bootstrap);

    public GrowthcraftApplesWorldGenProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER, Set.of(Reference.MODID));
    }
}
