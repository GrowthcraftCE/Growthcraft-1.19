package growthcraft.rice.init;

import growthcraft.lib.utils.FluidUtils;
import growthcraft.rice.fluid.RiceWaterFluid;
import growthcraft.rice.fluid.RiceWineFluid;
import growthcraft.rice.fluid.SakeFluid;
import growthcraft.rice.shared.Reference;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class GrowthcraftRiceFluids {
    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(
            ForgeRegistries.FLUIDS, Reference.MODID
    );

    public static final RegistryObject<RiceWaterFluid.Source> RICE_WATER_FLUID_STILL = FLUIDS.register(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.RICE_WATER).get(FluidUtils.STILL), RiceWaterFluid.Source::new);
    public static final RegistryObject<RiceWaterFluid.Flowing> RICE_WATER_FLUID_FLOWING = FLUIDS.register(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.RICE_WATER).get(FluidUtils.FLOWING), RiceWaterFluid.Flowing::new);
    public static final RegistryObject<LiquidBlock> RICE_WATER_FLUID_BLOCK =
            GrowthcraftRiceBlocks.BLOCKS.register(FluidUtils.getFluidNames(Reference.UnlocalizedName.RICE_WATER).get(FluidUtils.BLOCK),
                    () -> new LiquidBlock(RICE_WATER_FLUID_STILL, BlockBehaviour.Properties.of(Material.WATER)));

    public static final RegistryObject<RiceWineFluid.Source> RICE_WINE_FLUID_STILL = FLUIDS.register(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.RICE_WINE).get(FluidUtils.STILL), RiceWineFluid.Source::new);
    public static final RegistryObject<RiceWineFluid.Flowing> RICE_WINE_FLUID_FLOWING = FLUIDS.register(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.RICE_WINE).get(FluidUtils.FLOWING), RiceWineFluid.Flowing::new);
    public static final RegistryObject<LiquidBlock> RICE_WINE_FLUID_BLOCK =
            GrowthcraftRiceBlocks.BLOCKS.register(FluidUtils.getFluidNames(Reference.UnlocalizedName.RICE_WINE).get(FluidUtils.BLOCK),
                    () -> new LiquidBlock(RICE_WINE_FLUID_STILL, BlockBehaviour.Properties.of(Material.WATER)));

    public static final RegistryObject<SakeFluid.Source> SAKE_FLUID_STILL = FLUIDS.register(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.SAKE).get(FluidUtils.STILL), SakeFluid.Source::new);
    public static final RegistryObject<SakeFluid.Flowing> SAKE_FLUID_FLOWING = FLUIDS.register(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.SAKE).get(FluidUtils.FLOWING), SakeFluid.Flowing::new);
    public static final RegistryObject<LiquidBlock> SAKE_FLUID_BLOCK =
            GrowthcraftRiceBlocks.BLOCKS.register(FluidUtils.getFluidNames(Reference.UnlocalizedName.SAKE).get(FluidUtils.BLOCK),
                    () -> new LiquidBlock(SAKE_FLUID_STILL, BlockBehaviour.Properties.of(Material.WATER)));

    private GrowthcraftRiceFluids() {
        /* Prevent default public constructor */
    }
}
