package growthcraft.milk.init;

import growthcraft.lib.utils.FluidUtils;
import growthcraft.milk.fluid.*;
import growthcraft.milk.shared.Reference;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class GrowthcraftMilkFluids {
    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(
            ForgeRegistries.FLUIDS, Reference.MODID
    );

    public static final RegistryObject<ButterMilkFluid.Source> BUTTER_MILK_FLUID_STILL = FLUIDS.register(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.BUTTER_MILK).get(FluidUtils.STILL), ButterMilkFluid.Source::new);
    public static final RegistryObject<ButterMilkFluid.Flowing> BUTTER_MILK_FLUID_FLOWING = FLUIDS.register(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.BUTTER_MILK).get(FluidUtils.FLOWING), ButterMilkFluid.Flowing::new);
    public static final RegistryObject<LiquidBlock> BUTTER_MILK_FLUID_BLOCK =
            GrowthcraftMilkBlocks.BLOCKS.register(FluidUtils.getFluidNames(Reference.UnlocalizedName.BUTTER_MILK).get(FluidUtils.BLOCK),
                    () -> new LiquidBlock(BUTTER_MILK_FLUID_STILL, BlockBehaviour.Properties.of(Material.WATER)));

    public static final RegistryObject<CheeseBaseFluid.Source> CHEESE_BASE_FLUID_STILL = FLUIDS.register(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.CHEESE_BASE).get(FluidUtils.STILL), CheeseBaseFluid.Source::new);
    public static final RegistryObject<CheeseBaseFluid.Flowing> CHEESE_BASE_FLUID_FLOWING = FLUIDS.register(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.CHEESE_BASE).get(FluidUtils.FLOWING), CheeseBaseFluid.Flowing::new);
    public static final RegistryObject<LiquidBlock> CHEESE_BASE_FLUID_BLOCK =
            GrowthcraftMilkBlocks.BLOCKS.register(FluidUtils.getFluidNames(Reference.UnlocalizedName.CHEESE_BASE).get(FluidUtils.BLOCK),
                    () -> new LiquidBlock(CHEESE_BASE_FLUID_STILL, BlockBehaviour.Properties.of(Material.WATER)));

    public static final RegistryObject<CondensedMilkFluid.Source> CONDENSED_MILK_FLUID_STILL = FLUIDS.register(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.CONDENSED_MILK).get(FluidUtils.STILL), CondensedMilkFluid.Source::new);
    public static final RegistryObject<CondensedMilkFluid.Flowing> CONDENSED_MILK_FLUID_FLOWING = FLUIDS.register(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.CONDENSED_MILK).get(FluidUtils.FLOWING), CondensedMilkFluid.Flowing::new);
    public static final RegistryObject<LiquidBlock> CONDENSED_MILK_FLUID_BLOCK =
            GrowthcraftMilkBlocks.BLOCKS.register(FluidUtils.getFluidNames(Reference.UnlocalizedName.CONDENSED_MILK).get(FluidUtils.BLOCK),
                    () -> new LiquidBlock(CONDENSED_MILK_FLUID_STILL, BlockBehaviour.Properties.of(Material.WATER)));

    public static final RegistryObject<CreamFluid.Source> CREAM_FLUID_STILL = FLUIDS.register(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.CREAM).get(FluidUtils.STILL), CreamFluid.Source::new);
    public static final RegistryObject<CreamFluid.Flowing> CREAM_FLUID_FLOWING = FLUIDS.register(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.CREAM).get(FluidUtils.FLOWING), CreamFluid.Flowing::new);
    public static final RegistryObject<LiquidBlock> CREAM_FLUID_BLOCK =
            GrowthcraftMilkBlocks.BLOCKS.register(FluidUtils.getFluidNames(Reference.UnlocalizedName.CREAM).get(FluidUtils.BLOCK),
                    () -> new LiquidBlock(CREAM_FLUID_STILL, BlockBehaviour.Properties.of(Material.WATER)));

    public static final RegistryObject<CulturedMilkFluid.Source> CULTURED_MILK_FLUID_STILL = FLUIDS.register(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.CULTURED_MILK).get(FluidUtils.STILL), CulturedMilkFluid.Source::new);
    public static final RegistryObject<CulturedMilkFluid.Flowing> CULTURED_MILK_FLUID_FLOWING = FLUIDS.register(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.CULTURED_MILK).get(FluidUtils.FLOWING), CulturedMilkFluid.Flowing::new);
    public static final RegistryObject<LiquidBlock> CULTURED_MILK_FLUID_BLOCK =
            GrowthcraftMilkBlocks.BLOCKS.register(FluidUtils.getFluidNames(Reference.UnlocalizedName.CULTURED_MILK).get(FluidUtils.BLOCK),
                    () -> new LiquidBlock(CULTURED_MILK_FLUID_STILL, BlockBehaviour.Properties.of(Material.WATER)));

    public static final RegistryObject<KumisFluid.Source> KUMIS_FLUID_STILL = FLUIDS.register(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.KUMIS).get(FluidUtils.STILL), KumisFluid.Source::new);
    public static final RegistryObject<KumisFluid.Flowing> KUMIS_FLUID_FLOWING = FLUIDS.register(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.KUMIS).get(FluidUtils.FLOWING), KumisFluid.Flowing::new);
    public static final RegistryObject<LiquidBlock> KUMIS_FLUID_BLOCK =
            GrowthcraftMilkBlocks.BLOCKS.register(FluidUtils.getFluidNames(Reference.UnlocalizedName.KUMIS).get(FluidUtils.BLOCK),
                    () -> new LiquidBlock(KUMIS_FLUID_STILL, BlockBehaviour.Properties.of(Material.WATER)));

    public static final RegistryObject<MilkFluid.Source> MILK_FLUID_STILL = FLUIDS.register(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.MILK).get(FluidUtils.STILL), MilkFluid.Source::new);
    public static final RegistryObject<MilkFluid.Flowing> MILK_FLUID_FLOWING = FLUIDS.register(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.MILK).get(FluidUtils.FLOWING), MilkFluid.Flowing::new);
    public static final RegistryObject<LiquidBlock> MILK_FLUID_BLOCK =
            GrowthcraftMilkBlocks.BLOCKS.register(FluidUtils.getFluidNames(Reference.UnlocalizedName.MILK).get(FluidUtils.BLOCK),
                    () -> new LiquidBlock(MILK_FLUID_STILL, BlockBehaviour.Properties.of(Material.WATER)));

    public static final RegistryObject<RennetFluid.Source> RENNET_FLUID_STILL = FLUIDS.register(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.RENNET).get(FluidUtils.STILL), RennetFluid.Source::new);
    public static final RegistryObject<RennetFluid.Flowing> RENNET_FLUID_FLOWING = FLUIDS.register(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.RENNET).get(FluidUtils.FLOWING), RennetFluid.Flowing::new);
    public static final RegistryObject<LiquidBlock> RENNET_FLUID_BLOCK =
            GrowthcraftMilkBlocks.BLOCKS.register(FluidUtils.getFluidNames(Reference.UnlocalizedName.RENNET).get(FluidUtils.BLOCK),
                    () -> new LiquidBlock(RENNET_FLUID_STILL, BlockBehaviour.Properties.of(Material.WATER)));

    public static final RegistryObject<SkimMilkFluid.Source> SKIM_MILK_FLUID_STILL = FLUIDS.register(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.SKIM_MILK).get(FluidUtils.STILL), SkimMilkFluid.Source::new);
    public static final RegistryObject<SkimMilkFluid.Flowing> SKIM_MILK_FLUID_FLOWING = FLUIDS.register(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.SKIM_MILK).get(FluidUtils.FLOWING), SkimMilkFluid.Flowing::new);
    public static final RegistryObject<LiquidBlock> SKIM_MILK_FLUID_BLOCK =
            GrowthcraftMilkBlocks.BLOCKS.register(FluidUtils.getFluidNames(Reference.UnlocalizedName.SKIM_MILK).get(FluidUtils.BLOCK),
                    () -> new LiquidBlock(SKIM_MILK_FLUID_STILL, BlockBehaviour.Properties.of(Material.WATER)));

    public static final RegistryObject<WheyFluid.Source> WHEY_FLUID_STILL = FLUIDS.register(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.WHEY).get(FluidUtils.STILL), WheyFluid.Source::new);
    public static final RegistryObject<WheyFluid.Flowing> WHEY_FLUID_FLOWING = FLUIDS.register(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.WHEY).get(FluidUtils.FLOWING), WheyFluid.Flowing::new);
    public static final RegistryObject<LiquidBlock> WHEY_FLUID_BLOCK =
            GrowthcraftMilkBlocks.BLOCKS.register(FluidUtils.getFluidNames(Reference.UnlocalizedName.WHEY).get(FluidUtils.BLOCK),
                    () -> new LiquidBlock(WHEY_FLUID_STILL, BlockBehaviour.Properties.of(Material.WATER)));

    private GrowthcraftMilkFluids() {
        /* Prevent default public constructor */
    }
}
