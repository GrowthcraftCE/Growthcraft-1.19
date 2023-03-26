package growthcraft.core.datagen.providers;

import growthcraft.core.init.GrowthcraftBlocks;
import growthcraft.core.init.GrowthcraftItems;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class GrowthcraftBlockLootTables extends BlockLootSubProvider {

    protected GrowthcraftBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        this.simpleLootTables();
        this.oreDropLootTables();
    }

    private void oreDropLootTables() {
        add(GrowthcraftBlocks.SALT_ORE.get(),
                block -> createOreDrop(GrowthcraftBlocks.SALT_ORE.get(), GrowthcraftItems.SALT.get())
        );
    }

    private void simpleLootTables() {
        List<Block> blocks = new ArrayList<>();
        blocks.add(GrowthcraftBlocks.ROPE_LINEN.get());
        blocks.add(GrowthcraftBlocks.ROPE_LINEN_ACACIA_FENCE.get());
        blocks.add(GrowthcraftBlocks.ROPE_LINEN_BIRCH_FENCE.get());
        blocks.add(GrowthcraftBlocks.ROPE_LINEN_CRIMSON_FENCE.get());
        blocks.add(GrowthcraftBlocks.ROPE_LINEN_DARK_OAK_FENCE.get());
        blocks.add(GrowthcraftBlocks.ROPE_LINEN_JUNGLE_FENCE.get());
        blocks.add(GrowthcraftBlocks.ROPE_LINEN_NETHER_BRICK_FENCE.get());
        blocks.add(GrowthcraftBlocks.ROPE_LINEN_OAK_FENCE.get());
        blocks.add(GrowthcraftBlocks.ROPE_LINEN_SPRUCE_FENCE.get());
        blocks.add(GrowthcraftBlocks.ROPE_LINEN_WARPED_FENCE.get());
        blocks.add(GrowthcraftBlocks.SALT_BLOCK.get());

        // Iterate over our list of blocks.
        blocks.forEach(this::dropSelf);

    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return GrowthcraftBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }

}
