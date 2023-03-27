package growthcraft.core.world;

import growthcraft.core.init.config.GrowthcraftConfig;
import growthcraft.core.shared.Reference;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;

import java.util.List;

public class GrowthcraftPlacedFeatures {

    public static final ResourceKey<PlacedFeature> SALT_ORE_PLACED_KEY = createKey(Reference.UnlocalizedName.SALT_ORE_PLACED);

    public static void bootstrap(BootstapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        register(context, SALT_ORE_PLACED_KEY, configuredFeatures.getOrThrow(GrowthcraftConfiguredFeatures.OVERWORLD_SALT_ORE_KEY),
                GrowthcraftOrePlacement.commonOrePlacement(
                        GrowthcraftConfig.getSaltOreGenSpreadAmount(),
                        HeightRangePlacement.uniform(
                                VerticalAnchor.absolute(GrowthcraftConfig.getSaltOreGenHeightMin()),
                                VerticalAnchor.absolute(GrowthcraftConfig.getSaltOreGenHeightMax()))
                ));
    }

    private static ResourceKey<PlacedFeature> createKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation(Reference.MODID, name));
    }

    private static void register(
            BootstapContext<PlacedFeature> context,
            ResourceKey<PlacedFeature> key,
            Holder<ConfiguredFeature<?, ?>> configuration,
            List<PlacementModifier> modifiers
    ) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }

    private static void register(
            BootstapContext<PlacedFeature> context,
            ResourceKey<PlacedFeature> key,
            Holder<ConfiguredFeature<?, ?>> configuration,
            PlacementModifier... modifiers
    ) {
        register(context, key, configuration, List.of(modifiers));
    }

    private GrowthcraftPlacedFeatures() {
        /* Prevent generation of default public constructor. */
    }

}
