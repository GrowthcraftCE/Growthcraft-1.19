package growthcraft.apples.init;

import growthcraft.apples.lib.turtywurty.registry.FluidRegistryContainer;
import growthcraft.apples.shared.Reference;
import growthcraft.lib.utils.FluidUtils;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static growthcraft.core.shared.Reference.CREATIVE_TAB;

public class GrowthcraftApplesFluids {

    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(
            ForgeRegistries.FLUIDS, Reference.MODID
    );

    public static final DeferredRegister<FluidType> FLUID_TYPES = DeferredRegister.create(
            ForgeRegistries.Keys.FLUID_TYPES, Reference.MODID
    );

    public static final FluidRegistryContainer APPLE_CIDER_FLUID = new FluidRegistryContainer(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.APPLE_CIDER).get(FluidUtils.STILL),
            FluidType.Properties.create().canSwim(true).canDrown(true).canPushEntity(true).supportsBoating(true),
            () -> FluidRegistryContainer.createExtension(
                    new FluidRegistryContainer.ClientExtensions(
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
            new Item.Properties().tab(CREATIVE_TAB).stacksTo(1)
    );

    public static final FluidRegistryContainer APPLE_JUICE_FLUID = new FluidRegistryContainer(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.APPLE_JUICE).get(FluidUtils.STILL),
            FluidType.Properties.create().canSwim(true).canDrown(true).canPushEntity(true).supportsBoating(true),
            () -> FluidRegistryContainer.createExtension(
                    new FluidRegistryContainer.ClientExtensions(
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
            new Item.Properties()
                    .tab(CREATIVE_TAB)
                    .stacksTo(1)
    );

    private GrowthcraftApplesFluids() {
        /* Prevent default public constructor */
    }
}
