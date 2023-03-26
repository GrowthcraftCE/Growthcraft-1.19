package growthcraft.core.datagen.providers;

import growthcraft.core.shared.Reference;
import growthcraft.core.world.GrowthcraftPlacedFeatures;
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
public class GrowthcraftWorldGenProvider extends DatapackBuiltinEntriesProvider {

    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.PLACED_FEATURE, GrowthcraftPlacedFeatures::bootstrap);

    public GrowthcraftWorldGenProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER, Set.of(Reference.MODID));
    }
}
