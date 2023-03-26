package growthcraft.apples.datagen.providers;

import growthcraft.apples.init.GrowthcraftApplesBlocks;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class GrowthcraftApplesBlockLootTables extends BlockLootSubProvider {

    // TODO: Add loot for APPLE_TREE_FRUIT
    // TODO: Add loot for BEE_BOX_APPLE that would dump inventory
    protected GrowthcraftApplesBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        this.simpleLootTables();
        this.silkTouchLootTables();
        this.slabLootTables();
    }

    private void simpleLootTables() {
        List<Block> blocks = new ArrayList<Block>();
        blocks.add(GrowthcraftApplesBlocks.APPLE_PLANK.get());
        blocks.add(GrowthcraftApplesBlocks.APPLE_PLANK_BUTTON.get());
        blocks.add(GrowthcraftApplesBlocks.APPLE_PLANK_DOOR.get());
        blocks.add(GrowthcraftApplesBlocks.APPLE_PLANK_FENCE.get());
        blocks.add(GrowthcraftApplesBlocks.APPLE_PLANK_FENCE_GATE.get());
        blocks.add(GrowthcraftApplesBlocks.APPLE_PLANK_PRESSURE_PLATE.get());
        blocks.add(GrowthcraftApplesBlocks.APPLE_PLANK_STAIRS.get());
        blocks.add(GrowthcraftApplesBlocks.APPLE_PLANK_TRAPDOOR.get());
        blocks.add(GrowthcraftApplesBlocks.APPLE_TREE_SAPLING.get());
        blocks.add(GrowthcraftApplesBlocks.APPLE_WOOD.get());
        blocks.add(GrowthcraftApplesBlocks.APPLE_WOOD_LOG.get());
        blocks.add(GrowthcraftApplesBlocks.APPLE_WOOD_LOG_STRIPPED.get());
        blocks.add(GrowthcraftApplesBlocks.APPLE_WOOD_STRIPPED.get());

        blocks.forEach( block -> {
            this.dropSelf(block);
        });

    }

    private void silkTouchLootTables() {
        List<Block> blocks = new ArrayList<Block>();
        //blocks.add(GrowthcraftApplesBlocks.APPLE_PLANK_DOOR.get());

        blocks.forEach( doorBlock -> {
            this.dropWhenSilkTouch(doorBlock);
        });
    }

    private void doorLootTables() {
        List<Block> blocks = new ArrayList<Block>();
        blocks.add(GrowthcraftApplesBlocks.APPLE_PLANK_DOOR.get());

        blocks.forEach( block -> {
            this.add(block, this::createDoorTable);
        });
    }

    private void slabLootTables() {
        List<Block> blocks = new ArrayList<Block>();
        blocks.add(GrowthcraftApplesBlocks.APPLE_PLANK_SLAB.get());

        blocks.forEach( block -> {
            this.add(block, this::createSlabItemTable);
        });
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return GrowthcraftApplesBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }

}
