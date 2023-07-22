package growthcraft.core.world;

import growthcraft.core.init.config.GrowthcraftConfig;
import growthcraft.core.shared.Reference;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
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
    public static final Holder<PlacedFeature> PLACED_SALT_ORE = register(SALT_ORE_PLACED_KEY,
            GrowthcraftConfiguredFeatures.CONFIGURED_OVERWORLD_SALT_ORE,
            GrowthcraftOrePlacement.commonOrePlacement(
                    GrowthcraftConfig.getSaltOreGenSpreadAmount(),
                    HeightRangePlacement.uniform(
                            VerticalAnchor.absolute(GrowthcraftConfig.getSaltOreGenHeightMin()),
                            VerticalAnchor.absolute(GrowthcraftConfig.getSaltOreGenHeightMax()))
            ));

    private static ResourceKey<PlacedFeature> createKey(String name) {
        return ResourceKey.create(Registry.PLACED_FEATURE_REGISTRY, new ResourceLocation(growthcraft.apples.shared.Reference.MODID, name));
    }

    private static Holder<PlacedFeature> register(ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration,
                                                  List<PlacementModifier> modifiers) {
        return BuiltinRegistries.register(BuiltinRegistries.PLACED_FEATURE, key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }

    private static Holder<PlacedFeature> register(ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration,
                                                  PlacementModifier... modifiers) {
        return register(key, configuration, List.of(modifiers));
    }

    private GrowthcraftPlacedFeatures() {
        /* Prevent generation of default public constructor. */
    }

    public static void load() {
        // Just to trigger class loading
    }

}
