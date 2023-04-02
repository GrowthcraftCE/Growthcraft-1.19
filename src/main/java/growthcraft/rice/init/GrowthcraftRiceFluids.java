package growthcraft.rice.init;

import growthcraft.lib.client.ClientFluidTypeExtensions;
import growthcraft.lib.utils.FluidUtils;
import growthcraft.rice.lib.fluid.GrowthcraftRiceFluidRegistryContainer;
import growthcraft.rice.shared.Reference;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class GrowthcraftRiceFluids {
    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(
            ForgeRegistries.FLUIDS, Reference.MODID
    );

    public static final DeferredRegister<FluidType> FLUID_TYPES = DeferredRegister.create(
            ForgeRegistries.Keys.FLUID_TYPES, Reference.MODID
    );

    public static final GrowthcraftRiceFluidRegistryContainer RICE_WATER = new GrowthcraftRiceFluidRegistryContainer(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.RICE_WATER).get(FluidUtils.STILL),
            FluidType.Properties.create().canSwim(true).canDrown(true).canPushEntity(true).supportsBoating(true),
            () -> GrowthcraftRiceFluidRegistryContainer.createExtension(
                    new ClientFluidTypeExtensions(
                            Reference.MODID,
                            FluidUtils.getFluidNames(Reference.UnlocalizedName.RICE_WATER).get(FluidUtils.STILL)
                    ).tint(Reference.FluidColor.RICE_WATER.toIntValue())
                            .fogColor(
                                    Reference.FluidColor.RICE_WATER.toFloatValues().get("red"),
                                    Reference.FluidColor.RICE_WATER.toFloatValues().get("green"),
                                    Reference.FluidColor.RICE_WATER.toFloatValues().get("blue")
                            )
            ),
            BlockBehaviour.Properties.copy(Blocks.WATER),
            new Item.Properties().stacksTo(1)
    );

    public static final GrowthcraftRiceFluidRegistryContainer RICE_WINE = new GrowthcraftRiceFluidRegistryContainer(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.RICE_WINE).get(FluidUtils.STILL),
            FluidType.Properties.create().canSwim(true).canDrown(true).canPushEntity(true).supportsBoating(true),
            () -> GrowthcraftRiceFluidRegistryContainer.createExtension(
                    new ClientFluidTypeExtensions(
                            Reference.MODID,
                            FluidUtils.getFluidNames(Reference.UnlocalizedName.RICE_WINE).get(FluidUtils.STILL)
                    ).tint(Reference.FluidColor.RICE_WINE.toIntValue())
                            .fogColor(
                                    Reference.FluidColor.RICE_WINE.toFloatValues().get("red"),
                                    Reference.FluidColor.RICE_WINE.toFloatValues().get("green"),
                                    Reference.FluidColor.RICE_WINE.toFloatValues().get("blue")
                            )
            ),
            BlockBehaviour.Properties.copy(Blocks.WATER),
            new Item.Properties().stacksTo(1)
    );

    public static final GrowthcraftRiceFluidRegistryContainer SAKE = new GrowthcraftRiceFluidRegistryContainer(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.SAKE).get(FluidUtils.STILL),
            FluidType.Properties.create().canSwim(true).canDrown(true).canPushEntity(true).supportsBoating(true),
            () -> GrowthcraftRiceFluidRegistryContainer.createExtension(
                    new ClientFluidTypeExtensions(
                            Reference.MODID,
                            FluidUtils.getFluidNames(Reference.UnlocalizedName.SAKE).get(FluidUtils.STILL)
                    ).tint(Reference.FluidColor.SAKE.toIntValue())
                            .fogColor(
                                    Reference.FluidColor.SAKE.toFloatValues().get("red"),
                                    Reference.FluidColor.SAKE.toFloatValues().get("green"),
                                    Reference.FluidColor.SAKE.toFloatValues().get("blue")
                            )
            ),
            BlockBehaviour.Properties.copy(Blocks.WATER),
            new Item.Properties().stacksTo(1)
    );

    private GrowthcraftRiceFluids() {
        /* Prevent default public constructor */
    }
}
