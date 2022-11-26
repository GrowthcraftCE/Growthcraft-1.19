package growthcraft.apples.world;

import growthcraft.apples.init.GrowthcraftApplesBlocks;
import growthcraft.apples.shared.Reference;
import net.minecraft.core.Registry;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class GrowthcraftApplesConfiguredFeatures {
    public static final DeferredRegister<ConfiguredFeature<?, ?>> CONFIGURED_FEATURES =
            DeferredRegister.create(Registry.CONFIGURED_FEATURE_REGISTRY, Reference.MODID);

    public static final RegistryObject<ConfiguredFeature<?, ?>> APPLE_TREE_FEATURE = CONFIGURED_FEATURES.register(
            Reference.UnlocalizedName.APPLE_TREE,
            () -> new ConfiguredFeature<>(
                    Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
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
            )
    );


}
