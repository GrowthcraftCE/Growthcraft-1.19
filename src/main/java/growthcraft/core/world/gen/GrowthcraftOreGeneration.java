package growthcraft.core.world.gen;

import growthcraft.core.init.GrowthcraftBlocks;
import growthcraft.core.init.config.GrowthcraftConfig;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import net.minecraftforge.common.Tags;
import net.minecraftforge.event.world.BiomeLoadingEvent;

public class GrowthcraftOreGeneration {
    // Test for generating only in end stone within The End.
    public static final RuleTest IN_ENDSTONE = new TagMatchTest(Tags.Blocks.END_STONES);

    public static Holder<PlacedFeature> OVERWORLD_SALT_ORE_PLACED_FEATURE;

    public static void registerConfiguredFeatures() {

        OreConfiguration overworldConfig = new OreConfiguration(
                OreFeatures.STONE_ORE_REPLACEABLES,
                GrowthcraftBlocks.SALT_ORE.get().defaultBlockState(),
                GrowthcraftConfig.getSaltOreGenVeinSize()
        );

        OVERWORLD_SALT_ORE_PLACED_FEATURE = registerPlacedFeature("overworld_salt_ore", new ConfiguredFeature<>(Feature.ORE, overworldConfig),
                CountPlacement.of(GrowthcraftConfig.getSaltOreGenSpreadAmount()),
                InSquarePlacement.spread(),
                BiomeFilter.biome(),
                HeightRangePlacement.uniform(VerticalAnchor.absolute(GrowthcraftConfig.getSaltOreGenHeightMin()), VerticalAnchor.absolute(GrowthcraftConfig.getSaltOreGenHeightMax())));
    }

    private static <C extends FeatureConfiguration, F extends Feature<C>> Holder<PlacedFeature> registerPlacedFeature(String registryName, ConfiguredFeature<C, F> feature, PlacementModifier... placementModifiers) {
        return PlacementUtils.register(registryName, Holder.direct(feature), placementModifiers);
    }

    public static void onBiomeLoadingEvent(BiomeLoadingEvent event) {
        if (event.getCategory() == Biome.BiomeCategory.NETHER) {
            //event.getGeneration().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, NETHER_OREGEN);
        } else if (event.getCategory() == Biome.BiomeCategory.THEEND) {
            //event.getGeneration().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, END_OREGEN);
        } else {
            event.getGeneration().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, OVERWORLD_SALT_ORE_PLACED_FEATURE);
        }
    }
}
