package growthcraft.milk.lib.fluid;

import growthcraft.lib.fluid.FluidRegistryContainer;
import growthcraft.milk.init.GrowthcraftMilkBlocks;
import growthcraft.milk.init.GrowthcraftMilkFluids;
import growthcraft.milk.init.GrowthcraftMilkItems;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.client.extensions.common.IClientFluidTypeExtensions;
import net.minecraftforge.fluids.FluidType;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public class GrowthcraftMilkFluidRegistryContainer extends FluidRegistryContainer {
    public GrowthcraftMilkFluidRegistryContainer(
            String name,
            FluidType.Properties typeProperties,
            Supplier<IClientFluidTypeExtensions> clientExtensions,
            @Nullable AdditionalProperties additionalProperties,
            BlockBehaviour.Properties blockProperties,
            Item.Properties itemProperties) {
        super(name, typeProperties, clientExtensions, additionalProperties, blockProperties, itemProperties,
                GrowthcraftMilkFluids.FLUIDS, GrowthcraftMilkFluids.FLUID_TYPES,
                GrowthcraftMilkBlocks.BLOCKS, GrowthcraftMilkItems.ITEMS);
    }

    public GrowthcraftMilkFluidRegistryContainer(
            String name,
            FluidType.Properties typeProperties,
            Supplier<IClientFluidTypeExtensions> clientExtensions,
            BlockBehaviour.Properties blockProperties,
            Item.Properties itemProperties) {
        this(name, typeProperties, clientExtensions, null, blockProperties, itemProperties);
    }
}
