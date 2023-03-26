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

import static growthcraft.core.shared.Reference.CREATIVE_TAB;

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
                    .tint(Reference.FluidColor.HONEY.toIntValue())
                    .fogColor(0.76F, 0.54F, 0.15F)
            ),
            BlockBehaviour.Properties.copy(Blocks.WATER),
            new Item.Properties().tab(CREATIVE_TAB).stacksTo(1)
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
                                    Reference.FluidColor.HONEY_MEAD.getColor().getRed(),
                                    Reference.FluidColor.HONEY_MEAD.getColor().getGreen(),
                                    Reference.FluidColor.HONEY_MEAD.getColor().getBlue()
                            )
            ),
            BlockBehaviour.Properties.copy(Blocks.WATER),
            new Item.Properties().tab(CREATIVE_TAB).stacksTo(1)
    );

    private GrowthcraftApiaryFluids() {
        /* Prevent default public constructor */
    }
}
