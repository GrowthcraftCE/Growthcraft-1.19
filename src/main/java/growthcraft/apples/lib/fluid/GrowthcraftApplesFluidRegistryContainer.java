package growthcraft.apples.lib.fluid;

import growthcraft.apples.init.GrowthcraftApplesBlocks;
import growthcraft.apples.init.GrowthcraftApplesFluids;
import growthcraft.apples.init.GrowthcraftApplesItems;
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

public class GrowthcraftApplesFluidRegistryContainer extends FluidRegistryContainer {

    public DeferredRegister<Fluid> FLUID_REGISTRY = GrowthcraftApplesFluids.FLUIDS;
    public DeferredRegister<Block> BLOCK_REGISTRY = GrowthcraftApplesBlocks.BLOCKS;
    public DeferredRegister<Item> ITEM_REGISTRY = GrowthcraftApplesItems.ITEMS;

    public GrowthcraftApplesFluidRegistryContainer(
            String name,
            FluidType.Properties typeProperties,
            Supplier<IClientFluidTypeExtensions> clientExtensions,
            @Nullable AdditionalProperties additionalProperties,
            BlockBehaviour.Properties blockProperties,
            Item.Properties itemProperties) {
        super(name, typeProperties, clientExtensions, additionalProperties, blockProperties, itemProperties);
    }

    public GrowthcraftApplesFluidRegistryContainer(
            String name,
            FluidType.Properties typeProperties,
            Supplier<IClientFluidTypeExtensions> clientExtensions,
            BlockBehaviour.Properties blockProperties,
            Item.Properties itemProperties) {
        this(name, typeProperties, clientExtensions, null, blockProperties, itemProperties);
    }
}
