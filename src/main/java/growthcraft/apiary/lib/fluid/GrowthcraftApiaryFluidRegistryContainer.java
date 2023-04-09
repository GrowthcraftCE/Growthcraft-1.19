package growthcraft.apiary.lib.fluid;

import growthcraft.apiary.init.GrowthcraftApiaryBlocks;
import growthcraft.apiary.init.GrowthcraftApiaryFluids;
import growthcraft.apiary.init.GrowthcraftApiaryItems;
import growthcraft.lib.client.FluidRegistryContainer;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.client.extensions.common.IClientFluidTypeExtensions;
import net.minecraftforge.fluids.FluidType;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public class GrowthcraftApiaryFluidRegistryContainer extends FluidRegistryContainer {

    public GrowthcraftApiaryFluidRegistryContainer(
            String name,
            FluidType.Properties typeProperties,
            Supplier<IClientFluidTypeExtensions> clientExtensions,
            @Nullable AdditionalProperties additionalProperties,
            BlockBehaviour.Properties blockProperties,
            Item.Properties itemProperties) {
        super(name, typeProperties, clientExtensions, additionalProperties, blockProperties, itemProperties,
                GrowthcraftApiaryFluids.FLUIDS,GrowthcraftApiaryFluids.FLUID_TYPES,
                GrowthcraftApiaryBlocks.BLOCKS,GrowthcraftApiaryItems.ITEMS);
    }

    public GrowthcraftApiaryFluidRegistryContainer(
            String name,
            FluidType.Properties typeProperties,
            Supplier<IClientFluidTypeExtensions> clientExtensions,
            BlockBehaviour.Properties blockProperties,
            Item.Properties itemProperties) {
        this(name, typeProperties, clientExtensions, null, blockProperties, itemProperties);
    }

}
