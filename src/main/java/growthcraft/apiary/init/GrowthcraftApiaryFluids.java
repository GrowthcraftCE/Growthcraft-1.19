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

    public static final FluidRegistryContainer HONEY_FLUID = new FluidRegistryContainer(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.HONEY).get(FluidUtils.STILL),
            FluidType.Properties.create().canSwim(true).canDrown(true).canPushEntity(true).supportsBoating(true),
            () -> FluidRegistryContainer.createExtension(
                    new FluidRegistryContainer.ClientExtensions(
                            Reference.MODID,
                            FluidUtils.getFluidNames(Reference.UnlocalizedName.HONEY).get(FluidUtils.STILL)
                    ).tint(0xFF44AA)
                            .fogColor(1.0f, 0.2f, 0.5f)
            ),
            BlockBehaviour.Properties.copy(Blocks.WATER),
            new Item.Properties()
                    .tab(CREATIVE_TAB)
                    .stacksTo(1)
    );

    private GrowthcraftApiaryFluids() {
        /* Prevent default public constructor */
    }
}
