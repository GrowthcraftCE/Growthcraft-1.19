package growthcraft.apiary.init;

import growthcraft.apiary.lib.turtywurty.registry.FluidRegistryContainer;
import growthcraft.apiary.shared.Reference;
import growthcraft.lib.utils.FluidUtils;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class GrowthcraftApiaryFluids {

    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(
            ForgeRegistries.FLUIDS, Reference.MODID
    );

    public static final DeferredRegister<FluidType> FLUID_TYPES = DeferredRegister.create(
            ForgeRegistries.Keys.FLUID_TYPES, Reference.MODID
    );

    public static final FluidRegistryContainer HONEY = new FluidRegistryContainer(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.HONEY).get(FluidUtils.STILL),
            FluidType.Properties.create().canSwim(true).canDrown(true).canPushEntity(true).supportsBoating(true),
            () -> FluidRegistryContainer.createExtension(
                    new FluidRegistryContainer.ClientExtensions(
                            Reference.MODID,
                            FluidUtils.getFluidNames(Reference.UnlocalizedName.HONEY).get(FluidUtils.STILL)
                    )
                    .tint(
                            Reference.FluidColor.HONEY.toIntValue()
                    )
                    .fogColor(
                            Reference.FluidColor.HONEY.toFloatValues().get("red"),
                            Reference.FluidColor.HONEY.toFloatValues().get("green"),
                            Reference.FluidColor.HONEY.toFloatValues().get("blue")
                    )
            ),
            BlockBehaviour.Properties.copy(Blocks.WATER),
            new Item.Properties().stacksTo(1)
    );

    public static final FluidRegistryContainer HONEY_MEAD = new FluidRegistryContainer(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.HONEY_MEAD).get(FluidUtils.STILL),
            FluidType.Properties.create().canSwim(true).canDrown(true).canPushEntity(true).supportsBoating(true),
            () -> FluidRegistryContainer.createExtension(
                    new FluidRegistryContainer.ClientExtensions(
                            Reference.MODID,
                            FluidUtils.getFluidNames(Reference.UnlocalizedName.HONEY_MEAD).get(FluidUtils.STILL)
                    ).tint(Reference.FluidColor.HONEY_MEAD.toIntValue())
                            .fogColor(
                                    Reference.FluidColor.HONEY_MEAD.toFloatValues().get("red"),
                                    Reference.FluidColor.HONEY_MEAD.toFloatValues().get("green"),
                                    Reference.FluidColor.HONEY_MEAD.toFloatValues().get("blue")
                            )
            ),
            BlockBehaviour.Properties.copy(Blocks.WATER),
            new Item.Properties().stacksTo(1)
    );

    private GrowthcraftApiaryFluids() {
        /* Prevent default public constructor */
    }
}
