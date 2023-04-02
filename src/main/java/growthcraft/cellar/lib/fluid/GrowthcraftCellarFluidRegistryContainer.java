package growthcraft.cellar.lib.fluid;

import growthcraft.cellar.init.GrowthcraftCellarBlocks;
import growthcraft.cellar.init.GrowthcraftCellarFluids;
import growthcraft.cellar.init.GrowthcraftCellarItems;
import growthcraft.lib.client.FluidRegistryContainer;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.client.extensions.common.IClientFluidTypeExtensions;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.registries.DeferredRegister;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public class GrowthcraftCellarFluidRegistryContainer extends FluidRegistryContainer {

    public DeferredRegister<Fluid> FLUID_REGISTRY = GrowthcraftCellarFluids.FLUIDS;
    public DeferredRegister<Block> BLOCK_REGISTRY = GrowthcraftCellarBlocks.BLOCKS;
    public DeferredRegister<Item> ITEM_REGISTRY = GrowthcraftCellarItems.ITEMS;

    public GrowthcraftCellarFluidRegistryContainer(
            String name,
            FluidType.Properties typeProperties,
            Supplier<IClientFluidTypeExtensions> clientExtensions,
            @Nullable AdditionalProperties additionalProperties,
            BlockBehaviour.Properties blockProperties,
            Item.Properties itemProperties) {
        super(name, typeProperties, clientExtensions, additionalProperties, blockProperties, itemProperties);
    }

    public GrowthcraftCellarFluidRegistryContainer(
            String name,
            FluidType.Properties typeProperties,
            Supplier<IClientFluidTypeExtensions> clientExtensions,
            BlockBehaviour.Properties blockProperties,
            Item.Properties itemProperties) {
        this(name, typeProperties, clientExtensions, null, blockProperties, itemProperties);
    }
}
