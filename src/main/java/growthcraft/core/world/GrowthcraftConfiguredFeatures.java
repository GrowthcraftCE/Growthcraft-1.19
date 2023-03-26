package growthcraft.core.world;

import com.google.common.base.Suppliers;
import growthcraft.core.init.GrowthcraftBlocks;
import growthcraft.core.init.config.GrowthcraftConfig;
import growthcraft.core.shared.Reference;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;
import java.util.function.Supplier;

public class GrowthcraftConfiguredFeatures {

    private static final int saltOreVeinSize = GrowthcraftConfig.getSaltOreGenVeinSize(); // Iron is 9, Diamond is 0.7

    public static final DeferredRegister<ConfiguredFeature<?, ?>> CONFIGURED_FEATURES = DeferredRegister.create(
            Registry.CONFIGURED_FEATURE_REGISTRY, Reference.MODID
    );

    public static final Supplier<List<OreConfiguration.TargetBlockState>> OVERWORLD_SALT_ORES = Suppliers.memoize(
            () -> List.of(
                    OreConfiguration.target(
                            OreFeatures.STONE_ORE_REPLACEABLES, GrowthcraftBlocks.SALT_ORE.get().defaultBlockState()
                    )
            )
    );

    public static final RegistryObject<ConfiguredFeature<?, ?>> SALT_ORE = CONFIGURED_FEATURES.register(
            Reference.UnlocalizedName.SALT_ORE,
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(OVERWORLD_SALT_ORES.get(), saltOreVeinSize))
    );

}
