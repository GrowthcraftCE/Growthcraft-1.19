package growthcraft.rice.lib.fluid;

import growthcraft.lib.client.FluidRegistryContainer;
import growthcraft.rice.init.GrowthcraftRiceBlocks;
import growthcraft.rice.init.GrowthcraftRiceFluids;
import growthcraft.rice.init.GrowthcraftRiceItems;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.client.extensions.common.IClientFluidTypeExtensions;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.registries.DeferredRegister;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public class GrowthcraftRiceFluidRegistryContainer extends FluidRegistryContainer {

    public DeferredRegister<Fluid> FLUID_REGISTRY = GrowthcraftRiceFluids.FLUIDS;
    public DeferredRegister<Block> BLOCK_REGISTRY = GrowthcraftRiceBlocks.BLOCKS;
    public DeferredRegister<Item> ITEM_REGISTRY = GrowthcraftRiceItems.ITEMS;

    public GrowthcraftRiceFluidRegistryContainer(
            String name,
            FluidType.Properties typeProperties,
            Supplier<IClientFluidTypeExtensions> clientExtensions,
            @Nullable AdditionalProperties additionalProperties,
            BlockBehaviour.Properties blockProperties,
            Item.Properties itemProperties) {
        super(name, typeProperties, clientExtensions, additionalProperties, blockProperties, itemProperties);
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
