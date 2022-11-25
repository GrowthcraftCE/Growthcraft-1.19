package growthcraft.apples.world.feature.tree;

import growthcraft.apples.init.GrowthcraftApplesBlocks;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.WeightedPlacedFeature;
import net.minecraft.world.level.levelgen.feature.configurations.RandomFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import java.util.List;

public class AppleTreeFeatures {

    // StraightTrunkPlacer( baseHieght, randomA, randomB )
    // BlobFoliagePlacer( radius, offset, height )
    public static final Holder<ConfiguredFeature<TreeConfiguration, ?>> APPLE_TREE = FeatureUtils.register(
            "apple_tree",
            Feature.TREE,
            new TreeConfiguration.TreeConfigurationBuilder(
                    BlockStateProvider.simple(GrowthcraftApplesBlocks.APPLE_WOOD_LOG.get()),
                    new StraightTrunkPlacer(6, 1, 1),
                    BlockStateProvider.simple(GrowthcraftApplesBlocks.APPLE_TREE_LEAVES.get()),
                    new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 3),
                    new TwoLayersFeatureSize(1, 0, 2)
            ).build()
    );

    public static final Holder<PlacedFeature> APPLE_TREE_CHECKED = PlacementUtils.register(
            "apple_tree_checked",
            APPLE_TREE,
            PlacementUtils.filteredByBlockSurvival(GrowthcraftApplesBlocks.APPLE_TREE_SAPLING.get())
    );

    public static final Holder<ConfiguredFeature<RandomFeatureConfiguration, ?>> APPLE_TREE_SPAWN = FeatureUtils.register(
            "apple_tree_spawn",
            Feature.RANDOM_SELECTOR,
            new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(APPLE_TREE_CHECKED, 0.5F)), APPLE_TREE_CHECKED)
    );

    public static final Holder<PlacedFeature> APPLE_TREE_PLACED = PlacementUtils.register(
            "apple_tree_placed",
            APPLE_TREE_SPAWN,
            VegetationPlacements.treePlacement(
                    PlacementUtils.countExtra(0, 0.05f, 1)
            )
    );
}
