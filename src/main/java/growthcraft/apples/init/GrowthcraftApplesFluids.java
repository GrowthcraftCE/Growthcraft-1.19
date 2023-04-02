package growthcraft.apples.init;

import growthcraft.apples.lib.fluid.GrowthcraftApplesFluidRegistryContainer;
import growthcraft.apples.shared.Reference;
import growthcraft.lib.client.ClientFluidTypeExtensions;
import growthcraft.lib.utils.FluidUtils;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class GrowthcraftApplesFluids {

    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(
            ForgeRegistries.FLUIDS, Reference.MODID
    );

    public static final DeferredRegister<FluidType> FLUID_TYPES = DeferredRegister.create(
            ForgeRegistries.Keys.FLUID_TYPES, Reference.MODID
    );

    public static final GrowthcraftApplesFluidRegistryContainer APPLE_CIDER_FLUID = new GrowthcraftApplesFluidRegistryContainer(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.APPLE_CIDER).get(FluidUtils.STILL),
            FluidType.Properties.create().canSwim(true).canDrown(true).canPushEntity(true).supportsBoating(true),
            () -> GrowthcraftApplesFluidRegistryContainer.createExtension(
                    new ClientFluidTypeExtensions(
                            Reference.MODID,
                            FluidUtils.getFluidNames(Reference.UnlocalizedName.APPLE_CIDER).get(FluidUtils.STILL)
                    ).tint(Reference.FluidColor.APPLE_CIDER_FLUID_COLOR.toIntValue())
                            .fogColor(
                                    Reference.FluidColor.APPLE_CIDER_FLUID_COLOR.toFloatValues().get("red"),
                                    Reference.FluidColor.APPLE_CIDER_FLUID_COLOR.toFloatValues().get("green"),
                                    Reference.FluidColor.APPLE_CIDER_FLUID_COLOR.toFloatValues().get("blue")
                            )
            ),
            BlockBehaviour.Properties.copy(Blocks.WATER),
            new Item.Properties().stacksTo(1)
    );

    public static final GrowthcraftApplesFluidRegistryContainer APPLE_JUICE_FLUID = new GrowthcraftApplesFluidRegistryContainer(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.APPLE_JUICE).get(FluidUtils.STILL),
            FluidType.Properties.create().canSwim(true).canDrown(true).canPushEntity(true).supportsBoating(true),
            () -> GrowthcraftApplesFluidRegistryContainer.createExtension(
                    new ClientFluidTypeExtensions(
                            Reference.MODID,
                            FluidUtils.getFluidNames(Reference.UnlocalizedName.APPLE_JUICE).get(FluidUtils.STILL)
                    ).tint(Reference.FluidColor.APPLE_JUICE_FLUID_COLOR.toIntValue())
                            .fogColor(
                                    Reference.FluidColor.APPLE_JUICE_FLUID_COLOR.toFloatValues().get("red"),
                                    Reference.FluidColor.APPLE_JUICE_FLUID_COLOR.toFloatValues().get("green"),
                                    Reference.FluidColor.APPLE_JUICE_FLUID_COLOR.toFloatValues().get("blue")
                            )
            ),
            BlockBehaviour.Properties.copy(Blocks.WATER),
            new Item.Properties().stacksTo(1)
    );

    private GrowthcraftApplesFluids() {
        /* Prevent default public constructor */
    }
}
