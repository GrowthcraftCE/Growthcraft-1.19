package growthcraft.rice.lib.fluid;

import growthcraft.lib.client.FluidRegistryContainer;
import growthcraft.rice.init.GrowthcraftRiceBlocks;
import growthcraft.rice.init.GrowthcraftRiceFluids;
import growthcraft.rice.init.GrowthcraftRiceItems;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.client.extensions.common.IClientFluidTypeExtensions;
import net.minecraftforge.fluids.FluidType;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public class GrowthcraftRiceFluidRegistryContainer extends FluidRegistryContainer {

    public GrowthcraftRiceFluidRegistryContainer(
            String name,
            FluidType.Properties typeProperties,
            Supplier<IClientFluidTypeExtensions> clientExtensions,
            @Nullable AdditionalProperties additionalProperties,
            BlockBehaviour.Properties blockProperties,
            Item.Properties itemProperties) {
        super(name, typeProperties, clientExtensions, additionalProperties, blockProperties, itemProperties,
                GrowthcraftRiceFluids.FLUIDS, GrowthcraftRiceFluids.FLUID_TYPES,
                GrowthcraftRiceBlocks.BLOCKS, GrowthcraftRiceItems.ITEMS);
    }

    public GrowthcraftRiceFluidRegistryContainer(
            String name,
            FluidType.Properties typeProperties,
            Supplier<IClientFluidTypeExtensions> clientExtensions,
            BlockBehaviour.Properties blockProperties,
            Item.Properties itemProperties) {
        this(name, typeProperties, clientExtensions, null, blockProperties, itemProperties);
    }
}
