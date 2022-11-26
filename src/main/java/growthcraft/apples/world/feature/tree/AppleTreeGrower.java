package growthcraft.apples.world.feature.tree;

import growthcraft.apples.world.GrowthcraftApplesConfiguredFeatures;
import net.minecraft.core.Holder;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import org.jetbrains.annotations.Nullable;

public class AppleTreeGrower extends AbstractTreeGrower {

    @Nullable
    @Override
    protected Holder<? extends ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource randomSource, boolean largeHive) {
        return GrowthcraftApplesConfiguredFeatures.APPLE_TREE_FEATURE.getHolder().get();
    }
}
