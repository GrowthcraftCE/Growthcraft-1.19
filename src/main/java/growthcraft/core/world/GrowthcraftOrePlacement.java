package growthcraft.core.world;

import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

public class GrowthcraftOrePlacement {
    public static List<PlacementModifier> orePlacement(PlacementModifier countModifier, PlacementModifier placementModifier) {
        return List.of(countModifier, InSquarePlacement.spread(), placementModifier, BiomeFilter.biome());
    }

    public static List<PlacementModifier> commonOrePlacement(int count, PlacementModifier placementModifier) {
        return orePlacement(CountPlacement.of(count), placementModifier);
    }

    public static List<PlacementModifier> rareOrePlacement(int count, PlacementModifier placementModifier) {
        return orePlacement(RarityFilter.onAverageOnceEvery(count), placementModifier);
    }

    private GrowthcraftOrePlacement() {
        /* Prevent generation of default public constructor. */
    }
}
