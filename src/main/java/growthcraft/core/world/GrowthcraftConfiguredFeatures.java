package growthcraft.core.world;

import growthcraft.core.init.GrowthcraftBlocks;
import growthcraft.core.init.config.GrowthcraftConfig;
import growthcraft.core.shared.Reference;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

import java.util.List;

public class GrowthcraftConfiguredFeatures {
    private static final int SALT_ORE_GEN_VEIN_SIZE = GrowthcraftConfig.getSaltOreGenVeinSize(); // Iron is 9, Diamond is 0.7

    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_SALT_ORE_KEY = registerKey(Reference.UnlocalizedName.SALT_ORE);
    public static final ResourceKey<ConfiguredFeature<?, ?>> NETHER_SALT_ORE_KEY = registerKey(Reference.UnlocalizedName.SALT_ORE + "_nether");
    public static final ResourceKey<ConfiguredFeature<?, ?>> END_SALT_ORE_KEY = registerKey(Reference.UnlocalizedName.SALT_ORE + "_end");

    public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context) {
        RuleTest stoneReplaceable = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplaceable = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);
        RuleTest netherrackReplaceable = new BlockMatchTest(Blocks.NETHERRACK);
        RuleTest endstoneReplaceable = new BlockMatchTest(Blocks.END_STONE);

        List<OreConfiguration.TargetBlockState> overworldSaltOres = List.of(
                OreConfiguration.target(stoneReplaceable, GrowthcraftBlocks.SALT_ORE.get().defaultBlockState()),
                // TODO: Create a deepslate version of SALT_ORE
                OreConfiguration.target(deepslateReplaceable, GrowthcraftBlocks.SALT_ORE.get().defaultBlockState())
        );

        register(context, OVERWORLD_SALT_ORE_KEY, Feature.ORE, new OreConfiguration(
                overworldSaltOres, SALT_ORE_GEN_VEIN_SIZE));
        register(context, END_SALT_ORE_KEY, Feature.ORE, new OreConfiguration(endstoneReplaceable,
                GrowthcraftBlocks.SALT_ORE.get().defaultBlockState(), 9));
        register(context, NETHER_SALT_ORE_KEY, Feature.ORE, new OreConfiguration(netherrackReplaceable,
                GrowthcraftBlocks.SALT_ORE.get().defaultBlockState(), 9));
    }

    private static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(Reference.MODID, name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(
            BootstapContext<ConfiguredFeature<?, ?>> context,
            ResourceKey<ConfiguredFeature<?, ?>> key,
            F feature,
            FC configuration
    ) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }

    private GrowthcraftConfiguredFeatures() {
        /* Prevent generation of default public constructor. */
    }

}
