package growthcraft.apples.world;

import growthcraft.apples.init.GrowthcraftApplesBlocks;
import growthcraft.apples.shared.Reference;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;

import java.util.List;

public class GrowthcraftApplesPlacedFeatures {

    public static final ResourceKey<PlacedFeature> APPLE_TREE_PLACED_KEY = createKey(Reference.UnlocalizedName.APPLE_TREE + "_placed");

    public static final Holder<PlacedFeature> PLACED_APPLE_TREE = register(APPLE_TREE_PLACED_KEY,
            GrowthcraftApplesConfiguredFeatures.CONFIGURED_APPLE_TREE,
            VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, 0.1f, 2),
                    GrowthcraftApplesBlocks.APPLE_TREE_SAPLING.get()));


    private static ResourceKey<PlacedFeature> createKey(String name) {
        return ResourceKey.create(Registry.PLACED_FEATURE_REGISTRY, new ResourceLocation(Reference.MODID, name));
    }

    private static Holder<PlacedFeature> register(ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration,
                                                  List<PlacementModifier> modifiers) {
        return BuiltinRegistries.register(BuiltinRegistries.PLACED_FEATURE, key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }

    private static Holder<PlacedFeature> register(ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration,
                                 PlacementModifier... modifiers) {
        return register(key, configuration, List.of(modifiers));
    }

    public static void load() {
        // Just to trigger class loading
    }

}
