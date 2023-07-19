package growthcraft.apples.world;

import growthcraft.apples.init.GrowthcraftApplesBlocks;
import growthcraft.apples.shared.Reference;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;

public class GrowthcraftApplesConfiguredFeatures {

    public static final ResourceKey<ConfiguredFeature<?, ?>> APPLE_TREE_KEY = registerKey(Reference.UnlocalizedName.APPLE_TREE);
    public static final Holder<ConfiguredFeature<?, ?>> CONFIGURED_APPLE_TREE = register(APPLE_TREE_KEY,
            Feature.TREE,
            new TreeConfiguration.TreeConfigurationBuilder(
                    // Trunk block
                    BlockStateProvider.simple(GrowthcraftApplesBlocks.APPLE_WOOD_LOG.get()),
                    // Trunk placer (baseHeight, heightRandA, heightRandB)
                    new StraightTrunkPlacer(5, 2, 0),
                    // Leaves block
                    BlockStateProvider.simple(GrowthcraftApplesBlocks.APPLE_TREE_LEAVES.get()),
                    // Leaves placer (radius, offset, height)
                    new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 3),
                    // Spawn Area Saturation (limit, lowerSize, upperSize)
                    new TwoLayersFeatureSize(1, 0, 2)).build()
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registry.CONFIGURED_FEATURE_REGISTRY, new ResourceLocation(Reference.MODID, name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> Holder<ConfiguredFeature<?, ?>> register(
            ResourceKey<ConfiguredFeature<?, ?>> key,
            F feature,
            FC configuration) {
        return BuiltinRegistries.register(BuiltinRegistries.CONFIGURED_FEATURE, key, new ConfiguredFeature(feature, configuration));
    }

}
