package growthcraft.core.world;

import growthcraft.core.Growthcraft;
import growthcraft.core.shared.Reference;
import net.minecraft.core.Registry;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

public class GrowthcraftPlacedFeatures {

    public static final DeferredRegister<PlacedFeature> PLACED_FEATURES = DeferredRegister.create(
            Registry.PLACED_FEATURE_REGISTRY, Reference.MODID
    );

    public static final RegistryObject<PlacedFeature> SALT_ORE_PLACED = PLACED_FEATURES.register(
            Reference.UnlocalizedName.SALT_ORE,
            () -> new PlacedFeature(GrowthcraftConfiguredFeatures.SALT_ORE.getHolder().get(),
                    commonOrePlacement(
                            7, // VeinsPerChunk
                            HeightRangePlacement.triangle(
                                    VerticalAnchor.aboveBottom(-80), // minLevel
                                    VerticalAnchor.aboveBottom(160) // maxLevel
                            )
                    )
            )
    );

    public static List<PlacementModifier> orePlacement(PlacementModifier countPerChunk, PlacementModifier height) {
        Growthcraft.LOGGER.error("orePlacemnet called");
        return List.of(countPerChunk, InSquarePlacement.spread(), height, BiomeFilter.biome());
    }

    public static List<PlacementModifier> commonOrePlacement(int countPerChunk, PlacementModifier height) {
        return orePlacement(CountPlacement.of(countPerChunk), height);
    }

}
