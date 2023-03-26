package growthcraft.core.datagen.providers;

import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import java.util.List;
import java.util.Set;

/**
 * @credit Kaupenjoe for his DataGenerator tutorials
 */
public class GrowthcraftLootTableProvider {
    public static LootTableProvider create(PackOutput output) {
        return new LootTableProvider(
                output,
                Set.of(),
                List.of(new LootTableProvider.SubProviderEntry(GrowthcraftBlockLootTables::new, LootContextParamSets.BLOCK))
        );
    }

    private GrowthcraftLootTableProvider() {
        /* Prevent generation of default public constructor. */
    }
}
