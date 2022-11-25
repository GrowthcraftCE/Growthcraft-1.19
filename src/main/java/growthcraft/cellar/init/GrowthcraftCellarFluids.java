package growthcraft.cellar.init;

import growthcraft.cellar.fluid.*;
import growthcraft.cellar.shared.Reference;
import growthcraft.lib.utils.FluidUtils;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class GrowthcraftCellarFluids {
    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(
            ForgeRegistries.FLUIDS, Reference.MODID
    );

    public static final RegistryObject<AmberAleFluid.Source> AMBER_ALE_FLUID_STILL = FLUIDS.register(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.AMBER_ALE).get(FluidUtils.STILL), AmberAleFluid.Source::new);
    public static final RegistryObject<AmberAleFluid.Flowing> AMBER_ALE_FLUID_FLOWING = FLUIDS.register(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.AMBER_ALE).get(FluidUtils.FLOWING), AmberAleFluid.Flowing::new);
    public static final RegistryObject<LiquidBlock> AMBER_ALE_FLUID_BLOCK =
            GrowthcraftCellarBlocks.BLOCKS.register(FluidUtils.getFluidNames(Reference.UnlocalizedName.AMBER_ALE).get(FluidUtils.BLOCK),
                    () -> new LiquidBlock(AMBER_ALE_FLUID_STILL, BlockBehaviour.Properties.of(Material.WATER)));

    public static final RegistryObject<AmberLagerFluid.Source> AMBER_LAGER_FLUID_STILL = FLUIDS.register(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.AMBER_LAGER).get(FluidUtils.STILL), AmberLagerFluid.Source::new);
    public static final RegistryObject<AmberLagerFluid.Flowing> AMBER_LAGER_FLUID_FLOWING = FLUIDS.register(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.AMBER_LAGER).get(FluidUtils.FLOWING), AmberLagerFluid.Flowing::new);
    public static final RegistryObject<LiquidBlock> AMBER_LAGER_FLUID_BLOCK =
            GrowthcraftCellarBlocks.BLOCKS.register(FluidUtils.getFluidNames(Reference.UnlocalizedName.AMBER_LAGER).get(FluidUtils.BLOCK),
                    () -> new LiquidBlock(AMBER_LAGER_FLUID_STILL, BlockBehaviour.Properties.of(Material.WATER)));

    public static final RegistryObject<AmberWortFluid.Source> AMBER_WORT_FLUID_STILL = FLUIDS.register(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.AMBER_WORT).get(FluidUtils.STILL), AmberWortFluid.Source::new);
    public static final RegistryObject<AmberWortFluid.Flowing> AMBER_WORT_FLUID_FLOWING = FLUIDS.register(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.AMBER_WORT).get(FluidUtils.FLOWING), AmberWortFluid.Flowing::new);
    public static final RegistryObject<LiquidBlock> AMBER_WORT_FLUID_BLOCK =
            GrowthcraftCellarBlocks.BLOCKS.register(FluidUtils.getFluidNames(Reference.UnlocalizedName.AMBER_WORT).get(FluidUtils.BLOCK),
                    () -> new LiquidBlock(AMBER_WORT_FLUID_STILL, BlockBehaviour.Properties.of(Material.WATER)));

    public static final RegistryObject<BrownAleFluid.Source> BROWN_ALE_FLUID_STILL = FLUIDS.register(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.BROWN_ALE).get(FluidUtils.STILL), BrownAleFluid.Source::new);
    public static final RegistryObject<BrownAleFluid.Flowing> BROWN_ALE_FLUID_FLOWING = FLUIDS.register(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.BROWN_ALE).get(FluidUtils.FLOWING), BrownAleFluid.Flowing::new);
    public static final RegistryObject<LiquidBlock> BROWN_ALE_FLUID_BLOCK =
            GrowthcraftCellarBlocks.BLOCKS.register(FluidUtils.getFluidNames(Reference.UnlocalizedName.BROWN_ALE).get(FluidUtils.BLOCK),
                    () -> new LiquidBlock(BROWN_ALE_FLUID_STILL, BlockBehaviour.Properties.of(Material.WATER)));

    public static final RegistryObject<BrownLagerFluid.Source> BROWN_LAGER_FLUID_STILL = FLUIDS.register(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.BROWN_LAGER).get(FluidUtils.STILL), BrownLagerFluid.Source::new);
    public static final RegistryObject<BrownLagerFluid.Flowing> BROWN_LAGER_FLUID_FLOWING = FLUIDS.register(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.BROWN_LAGER).get(FluidUtils.FLOWING), BrownLagerFluid.Flowing::new);
    public static final RegistryObject<LiquidBlock> BROWN_LAGER_FLUID_BLOCK =
            GrowthcraftCellarBlocks.BLOCKS.register(FluidUtils.getFluidNames(Reference.UnlocalizedName.BROWN_LAGER).get(FluidUtils.BLOCK),
                    () -> new LiquidBlock(BROWN_LAGER_FLUID_STILL, BlockBehaviour.Properties.of(Material.WATER)));

    public static final RegistryObject<BrownWortFluid.Source> BROWN_WORT_FLUID_STILL = FLUIDS.register(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.BROWN_WORT).get(FluidUtils.STILL), BrownWortFluid.Source::new);
    public static final RegistryObject<BrownWortFluid.Flowing> BROWN_WORT_FLUID_FLOWING = FLUIDS.register(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.BROWN_WORT).get(FluidUtils.FLOWING), BrownWortFluid.Flowing::new);
    public static final RegistryObject<LiquidBlock> BROWN_WORT_FLUID_BLOCK =
            GrowthcraftCellarBlocks.BLOCKS.register(FluidUtils.getFluidNames(Reference.UnlocalizedName.BROWN_WORT).get(FluidUtils.BLOCK),
                    () -> new LiquidBlock(BROWN_WORT_FLUID_STILL, BlockBehaviour.Properties.of(Material.WATER)));

    public static final RegistryObject<CopperAleFluid.Source> COPPER_ALE_FLUID_STILL = FLUIDS.register(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.COPPER_ALE).get(FluidUtils.STILL), CopperAleFluid.Source::new);
    public static final RegistryObject<CopperAleFluid.Flowing> COPPER_ALE_FLUID_FLOWING = FLUIDS.register(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.COPPER_ALE).get(FluidUtils.FLOWING), CopperAleFluid.Flowing::new);
    public static final RegistryObject<LiquidBlock> COPPER_ALE_FLUID_BLOCK =
            GrowthcraftCellarBlocks.BLOCKS.register(FluidUtils.getFluidNames(Reference.UnlocalizedName.COPPER_ALE).get(FluidUtils.BLOCK),
                    () -> new LiquidBlock(COPPER_ALE_FLUID_STILL, BlockBehaviour.Properties.of(Material.WATER)));

    public static final RegistryObject<CopperLagerFluid.Source> COPPER_LAGER_FLUID_STILL = FLUIDS.register(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.COPPER_LAGER).get(FluidUtils.STILL), CopperLagerFluid.Source::new);
    public static final RegistryObject<CopperLagerFluid.Flowing> COPPER_LAGER_FLUID_FLOWING = FLUIDS.register(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.COPPER_LAGER).get(FluidUtils.FLOWING), CopperLagerFluid.Flowing::new);
    public static final RegistryObject<LiquidBlock> COPPER_LAGER_FLUID_BLOCK =
            GrowthcraftCellarBlocks.BLOCKS.register(FluidUtils.getFluidNames(Reference.UnlocalizedName.COPPER_LAGER).get(FluidUtils.BLOCK),
                    () -> new LiquidBlock(COPPER_LAGER_FLUID_STILL, BlockBehaviour.Properties.of(Material.WATER)));

    public static final RegistryObject<CopperWortFluid.Source> COPPER_WORT_FLUID_STILL = FLUIDS.register(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.COPPER_WORT).get(FluidUtils.STILL), CopperWortFluid.Source::new);
    public static final RegistryObject<CopperWortFluid.Flowing> COPPER_WORT_FLUID_FLOWING = FLUIDS.register(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.COPPER_WORT).get(FluidUtils.FLOWING), CopperWortFluid.Flowing::new);
    public static final RegistryObject<LiquidBlock> COPPER_WORT_FLUID_BLOCK =
            GrowthcraftCellarBlocks.BLOCKS.register(FluidUtils.getFluidNames(Reference.UnlocalizedName.COPPER_WORT).get(FluidUtils.BLOCK),
                    () -> new LiquidBlock(COPPER_WORT_FLUID_STILL, BlockBehaviour.Properties.of(Material.WATER)));

    public static final RegistryObject<DarkLagerFluid.Source> DARK_LAGER_FLUID_STILL = FLUIDS.register(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.DARK_LAGER).get(FluidUtils.STILL), DarkLagerFluid.Source::new);
    public static final RegistryObject<DarkLagerFluid.Flowing> DARK_LAGER_FLUID_FLOWING = FLUIDS.register(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.DARK_LAGER).get(FluidUtils.FLOWING), DarkLagerFluid.Flowing::new);
    public static final RegistryObject<LiquidBlock> DARK_LAGER_FLUID_BLOCK =
            GrowthcraftCellarBlocks.BLOCKS.register(FluidUtils.getFluidNames(Reference.UnlocalizedName.DARK_LAGER).get(FluidUtils.BLOCK),
                    () -> new LiquidBlock(DARK_LAGER_FLUID_STILL, BlockBehaviour.Properties.of(Material.WATER)));

    public static final RegistryObject<DarkWortFluid.Source> DARK_WORT_FLUID_STILL = FLUIDS.register(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.DARK_WORT).get(FluidUtils.STILL), DarkWortFluid.Source::new);
    public static final RegistryObject<DarkWortFluid.Flowing> DARK_WORT_FLUID_FLOWING = FLUIDS.register(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.DARK_WORT).get(FluidUtils.FLOWING), DarkWortFluid.Flowing::new);
    public static final RegistryObject<LiquidBlock> DARK_WORT_FLUID_BLOCK =
            GrowthcraftCellarBlocks.BLOCKS.register(FluidUtils.getFluidNames(Reference.UnlocalizedName.DARK_WORT).get(FluidUtils.BLOCK),
                    () -> new LiquidBlock(DARK_WORT_FLUID_STILL, BlockBehaviour.Properties.of(Material.WATER)));

    public static final RegistryObject<DeepAmberWort.Source> DEEP_AMBER_WORT_FLUID_STILL = FLUIDS.register(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.DEEP_AMBER_WORT).get(FluidUtils.STILL), DeepAmberWort.Source::new);
    public static final RegistryObject<DeepAmberWort.Flowing> DEEP_AMBER_WORT_FLUID_FLOWING = FLUIDS.register(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.DEEP_AMBER_WORT).get(FluidUtils.FLOWING), DeepAmberWort.Flowing::new);
    public static final RegistryObject<LiquidBlock> DEEP_AMBER_WORT_FLUID_BLOCK =
            GrowthcraftCellarBlocks.BLOCKS.register(FluidUtils.getFluidNames(Reference.UnlocalizedName.DEEP_AMBER_WORT).get(FluidUtils.BLOCK),
                    () -> new LiquidBlock(DEEP_AMBER_WORT_FLUID_STILL, BlockBehaviour.Properties.of(Material.WATER)));

    public static final RegistryObject<DeepCopperWort.Source> DEEP_COPPER_WORT_FLUID_STILL = FLUIDS.register(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.DEEP_COPPER_WORT).get(FluidUtils.STILL), DeepCopperWort.Source::new);
    public static final RegistryObject<DeepCopperWort.Flowing> DEEP_COPPER_WORT_FLUID_FLOWING = FLUIDS.register(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.DEEP_COPPER_WORT).get(FluidUtils.FLOWING), DeepCopperWort.Flowing::new);
    public static final RegistryObject<LiquidBlock> DEEP_COPPER_WORT_FLUID_BLOCK =
            GrowthcraftCellarBlocks.BLOCKS.register(FluidUtils.getFluidNames(Reference.UnlocalizedName.DEEP_COPPER_WORT).get(FluidUtils.BLOCK),
                    () -> new LiquidBlock(DEEP_COPPER_WORT_FLUID_STILL, BlockBehaviour.Properties.of(Material.WATER)));

    public static final RegistryObject<GoldenWort.Source> GOLDEN_WORT_FLUID_STILL = FLUIDS.register(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.GOLDEN_WORT).get(FluidUtils.STILL), GoldenWort.Source::new);
    public static final RegistryObject<GoldenWort.Flowing> GOLDEN_WORT_FLUID_FLOWING = FLUIDS.register(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.GOLDEN_WORT).get(FluidUtils.FLOWING), GoldenWort.Flowing::new);
    public static final RegistryObject<LiquidBlock> GOLDEN_WORT_FLUID_BLOCK =
            GrowthcraftCellarBlocks.BLOCKS.register(FluidUtils.getFluidNames(Reference.UnlocalizedName.GOLDEN_WORT).get(FluidUtils.BLOCK),
                    () -> new LiquidBlock(GOLDEN_WORT_FLUID_STILL, BlockBehaviour.Properties.of(Material.WATER)));

    public static final RegistryObject<HoppedGoldenWort.Source> HOPPED_GOLDEN_WORT_FLUID_STILL = FLUIDS.register(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.HOPPED_GOLDEN_WORT).get(FluidUtils.STILL), HoppedGoldenWort.Source::new);
    public static final RegistryObject<HoppedGoldenWort.Flowing> HOPPED_GOLDEN_WORT_FLUID_FLOWING = FLUIDS.register(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.HOPPED_GOLDEN_WORT).get(FluidUtils.FLOWING), HoppedGoldenWort.Flowing::new);
    public static final RegistryObject<LiquidBlock> HOPPED_GOLDEN_WORT_FLUID_BLOCK =
            GrowthcraftCellarBlocks.BLOCKS.register(FluidUtils.getFluidNames(Reference.UnlocalizedName.HOPPED_GOLDEN_WORT).get(FluidUtils.BLOCK),
                    () -> new LiquidBlock(HOPPED_GOLDEN_WORT_FLUID_STILL, BlockBehaviour.Properties.of(Material.WATER)));

    public static final RegistryObject<IpaAleFluid.Source> IPA_ALE_FLUID_STILL = FLUIDS.register(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.IPA_ALE).get(FluidUtils.STILL), IpaAleFluid.Source::new);
    public static final RegistryObject<IpaAleFluid.Flowing> IPA_ALE_FLUID_FLOWING = FLUIDS.register(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.IPA_ALE).get(FluidUtils.FLOWING), IpaAleFluid.Flowing::new);
    public static final RegistryObject<LiquidBlock> IPA_ALE_FLUID_BLOCK =
            GrowthcraftCellarBlocks.BLOCKS.register(FluidUtils.getFluidNames(Reference.UnlocalizedName.IPA_ALE).get(FluidUtils.BLOCK),
                    () -> new LiquidBlock(IPA_ALE_FLUID_STILL, BlockBehaviour.Properties.of(Material.WATER)));

    public static final RegistryObject<OldPortFluid.Source> OLD_PORT_ALE_FLUID_STILL = FLUIDS.register(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.OLD_PORT_ALE).get(FluidUtils.STILL), OldPortFluid.Source::new);
    public static final RegistryObject<OldPortFluid.Flowing> OLD_PORT_ALE_FLUID_FLOWING = FLUIDS.register(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.OLD_PORT_ALE).get(FluidUtils.FLOWING), OldPortFluid.Flowing::new);
    public static final RegistryObject<LiquidBlock> OLD_PORT_ALE_FLUID_BLOCK =
            GrowthcraftCellarBlocks.BLOCKS.register(FluidUtils.getFluidNames(Reference.UnlocalizedName.OLD_PORT_ALE).get(FluidUtils.BLOCK),
                    () -> new LiquidBlock(OLD_PORT_ALE_FLUID_STILL, BlockBehaviour.Properties.of(Material.WATER)));

    public static final RegistryObject<PaleAleFluid.Source> PALE_ALE_FLUID_STILL = FLUIDS.register(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.PALE_ALE).get(FluidUtils.STILL), PaleAleFluid.Source::new);
    public static final RegistryObject<PaleAleFluid.Flowing> PALE_ALE_FLUID_FLOWING = FLUIDS.register(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.PALE_ALE).get(FluidUtils.FLOWING), PaleAleFluid.Flowing::new);
    public static final RegistryObject<LiquidBlock> PALE_ALE_FLUID_BLOCK =
            GrowthcraftCellarBlocks.BLOCKS.register(FluidUtils.getFluidNames(Reference.UnlocalizedName.PALE_ALE).get(FluidUtils.BLOCK),
                    () -> new LiquidBlock(PALE_ALE_FLUID_STILL, BlockBehaviour.Properties.of(Material.WATER)));

    public static final RegistryObject<PaleGoldenWort.Source> PALE_GOLDEN_WORT_FLUID_STILL = FLUIDS.register(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.PALE_GOLDEN_WORT).get(FluidUtils.STILL), PaleGoldenWort.Source::new);
    public static final RegistryObject<PaleGoldenWort.Flowing> PALE_GOLDEN_WORT_FLUID_FLOWING = FLUIDS.register(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.PALE_GOLDEN_WORT).get(FluidUtils.FLOWING), PaleGoldenWort.Flowing::new);
    public static final RegistryObject<LiquidBlock> PALE_GOLDEN_WORT_FLUID_BLOCK =
            GrowthcraftCellarBlocks.BLOCKS.register(FluidUtils.getFluidNames(Reference.UnlocalizedName.PALE_GOLDEN_WORT).get(FluidUtils.BLOCK),
                    () -> new LiquidBlock(PALE_GOLDEN_WORT_FLUID_STILL, BlockBehaviour.Properties.of(Material.WATER)));

    public static final RegistryObject<PaleLagerFluid.Source> PALE_LAGER_FLUID_STILL = FLUIDS.register(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.PALE_LAGER).get(FluidUtils.STILL), PaleLagerFluid.Source::new);
    public static final RegistryObject<PaleLagerFluid.Flowing> PALE_LAGER_FLUID_FLOWING = FLUIDS.register(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.PALE_LAGER).get(FluidUtils.FLOWING), PaleLagerFluid.Flowing::new);
    public static final RegistryObject<LiquidBlock> PALE_LAGER_FLUID_BLOCK =
            GrowthcraftCellarBlocks.BLOCKS.register(FluidUtils.getFluidNames(Reference.UnlocalizedName.PALE_LAGER).get(FluidUtils.BLOCK),
                    () -> new LiquidBlock(PALE_LAGER_FLUID_STILL, BlockBehaviour.Properties.of(Material.WATER)));

    public static final RegistryObject<PilsnerLagerFluid.Source> PILSNER_LAGER_FLUID_STILL = FLUIDS.register(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.PILSNER_LAGER).get(FluidUtils.STILL), PilsnerLagerFluid.Source::new);
    public static final RegistryObject<PilsnerLagerFluid.Flowing> PILSNER_LAGER_FLUID_FLOWING = FLUIDS.register(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.PILSNER_LAGER).get(FluidUtils.FLOWING), PilsnerLagerFluid.Flowing::new);
    public static final RegistryObject<LiquidBlock> PILSNER_LAGER_FLUID_BLOCK =
            GrowthcraftCellarBlocks.BLOCKS.register(FluidUtils.getFluidNames(Reference.UnlocalizedName.PILSNER_LAGER).get(FluidUtils.BLOCK),
                    () -> new LiquidBlock(PILSNER_LAGER_FLUID_STILL, BlockBehaviour.Properties.of(Material.WATER)));

    public static final RegistryObject<PurpleGrapeJuiceFluid.Source> PURPLE_GRAPE_JUICE_FLUID_STILL = FLUIDS.register(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.PURPLE_GRAPE_JUICE).get(FluidUtils.STILL), PurpleGrapeJuiceFluid.Source::new);
    public static final RegistryObject<PurpleGrapeJuiceFluid.Flowing> PURPLE_GRAPE_JUICE_FLUID_FLOWING = FLUIDS.register(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.PURPLE_GRAPE_JUICE).get(FluidUtils.FLOWING), PurpleGrapeJuiceFluid.Flowing::new);
    public static final RegistryObject<LiquidBlock> PURPLE_GRAPE_JUICE_FLUID_BLOCK =
            GrowthcraftCellarBlocks.BLOCKS.register(FluidUtils.getFluidNames(Reference.UnlocalizedName.PURPLE_GRAPE_JUICE).get(FluidUtils.BLOCK),
                    () -> new LiquidBlock(PURPLE_GRAPE_JUICE_FLUID_STILL, BlockBehaviour.Properties.of(Material.WATER)));

    public static final RegistryObject<PurpleGrapeWineFluid.Source> PURPLE_GRAPE_WINE_FLUID_STILL = FLUIDS.register(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.PURPLE_GRAPE_WINE).get(FluidUtils.STILL), PurpleGrapeWineFluid.Source::new);
    public static final RegistryObject<PurpleGrapeWineFluid.Flowing> PURPLE_GRAPE_WINE_FLUID_FLOWING = FLUIDS.register(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.PURPLE_GRAPE_WINE).get(FluidUtils.FLOWING), PurpleGrapeWineFluid.Flowing::new);
    public static final RegistryObject<LiquidBlock> PURPLE_GRAPE_WINE_FLUID_BLOCK =
            GrowthcraftCellarBlocks.BLOCKS.register(FluidUtils.getFluidNames(Reference.UnlocalizedName.PURPLE_GRAPE_WINE).get(FluidUtils.BLOCK),
                    () -> new LiquidBlock(PURPLE_GRAPE_WINE_FLUID_STILL, BlockBehaviour.Properties.of(Material.WATER)));

    public static final RegistryObject<RedGrapeJuiceFluid.Source> RED_GRAPE_JUICE_FLUID_STILL = FLUIDS.register(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.RED_GRAPE_JUICE).get(FluidUtils.STILL), RedGrapeJuiceFluid.Source::new);
    public static final RegistryObject<RedGrapeJuiceFluid.Flowing> RED_GRAPE_JUICE_FLUID_FLOWING = FLUIDS.register(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.RED_GRAPE_JUICE).get(FluidUtils.FLOWING), RedGrapeJuiceFluid.Flowing::new);
    public static final RegistryObject<LiquidBlock> RED_GRAPE_JUICE_FLUID_BLOCK =
            GrowthcraftCellarBlocks.BLOCKS.register(FluidUtils.getFluidNames(Reference.UnlocalizedName.RED_GRAPE_JUICE).get(FluidUtils.BLOCK),
                    () -> new LiquidBlock(RED_GRAPE_JUICE_FLUID_STILL, BlockBehaviour.Properties.of(Material.WATER)));

    public static final RegistryObject<RedGrapeWineFluid.Source> RED_GRAPE_WINE_FLUID_STILL = FLUIDS.register(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.RED_GRAPE_WINE).get(FluidUtils.STILL), RedGrapeWineFluid.Source::new);
    public static final RegistryObject<RedGrapeWineFluid.Flowing> RED_GRAPE_WINE_FLUID_FLOWING = FLUIDS.register(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.RED_GRAPE_WINE).get(FluidUtils.FLOWING), RedGrapeWineFluid.Flowing::new);
    public static final RegistryObject<LiquidBlock> RED_GRAPE_WINE_FLUID_BLOCK =
            GrowthcraftCellarBlocks.BLOCKS.register(FluidUtils.getFluidNames(Reference.UnlocalizedName.RED_GRAPE_WINE).get(FluidUtils.BLOCK),
                    () -> new LiquidBlock(RED_GRAPE_WINE_FLUID_STILL, BlockBehaviour.Properties.of(Material.WATER)));

    public static final RegistryObject<StoutAleFluid.Source> STOUT_ALE_FLUID_STILL = FLUIDS.register(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.STOUT_ALE).get(FluidUtils.STILL), StoutAleFluid.Source::new);
    public static final RegistryObject<StoutAleFluid.Flowing> STOUT_ALE_FLUID_FLOWING = FLUIDS.register(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.STOUT_ALE).get(FluidUtils.FLOWING), StoutAleFluid.Flowing::new);
    public static final RegistryObject<LiquidBlock> STOUT_ALE_FLUID_BLOCK =
            GrowthcraftCellarBlocks.BLOCKS.register(FluidUtils.getFluidNames(Reference.UnlocalizedName.STOUT_ALE).get(FluidUtils.BLOCK),
                    () -> new LiquidBlock(STOUT_ALE_FLUID_STILL, BlockBehaviour.Properties.of(Material.WATER)));

    public static final RegistryObject<ViennaLagerFluid.Source> VIENNA_LAGER_FLUID_STILL = FLUIDS.register(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.VIENNA_LAGER).get(FluidUtils.STILL), ViennaLagerFluid.Source::new);
    public static final RegistryObject<ViennaLagerFluid.Flowing> VIENNA_LAGER_FLUID_FLOWING = FLUIDS.register(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.VIENNA_LAGER).get(FluidUtils.FLOWING), ViennaLagerFluid.Flowing::new);
    public static final RegistryObject<LiquidBlock> VIENNA_LAGER_FLUID_BLOCK =
            GrowthcraftCellarBlocks.BLOCKS.register(FluidUtils.getFluidNames(Reference.UnlocalizedName.VIENNA_LAGER).get(FluidUtils.BLOCK),
                    () -> new LiquidBlock(VIENNA_LAGER_FLUID_STILL, BlockBehaviour.Properties.of(Material.WATER)));

    public static final RegistryObject<WhiteGrapeJuiceFluid.Source> WHITE_GRAPE_JUICE_FLUID_STILL = FLUIDS.register(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.WHITE_GRAPE_JUICE).get(FluidUtils.STILL), WhiteGrapeJuiceFluid.Source::new);
    public static final RegistryObject<WhiteGrapeJuiceFluid.Flowing> WHITE_GRAPE_JUICE_FLUID_FLOWING = FLUIDS.register(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.WHITE_GRAPE_JUICE).get(FluidUtils.FLOWING), WhiteGrapeJuiceFluid.Flowing::new);
    public static final RegistryObject<LiquidBlock> WHITE_GRAPE_JUICE_FLUID_BLOCK =
            GrowthcraftCellarBlocks.BLOCKS.register(FluidUtils.getFluidNames(Reference.UnlocalizedName.WHITE_GRAPE_JUICE).get(FluidUtils.BLOCK),
                    () -> new LiquidBlock(WHITE_GRAPE_JUICE_FLUID_STILL, BlockBehaviour.Properties.of(Material.WATER)));

    public static final RegistryObject<WhiteGrapeWineFluid.Source> WHITE_GRAPE_WINE_FLUID_STILL = FLUIDS.register(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.WHITE_GRAPE_WINE).get(FluidUtils.STILL), WhiteGrapeWineFluid.Source::new);
    public static final RegistryObject<WhiteGrapeWineFluid.Flowing> WHITE_GRAPE_WINE_FLUID_FLOWING = FLUIDS.register(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.WHITE_GRAPE_WINE).get(FluidUtils.FLOWING), WhiteGrapeWineFluid.Flowing::new);
    public static final RegistryObject<LiquidBlock> WHITE_GRAPE_WINE_FLUID_BLOCK =
            GrowthcraftCellarBlocks.BLOCKS.register(FluidUtils.getFluidNames(Reference.UnlocalizedName.WHITE_GRAPE_WINE).get(FluidUtils.BLOCK),
                    () -> new LiquidBlock(WHITE_GRAPE_WINE_FLUID_STILL, BlockBehaviour.Properties.of(Material.WATER)));

    public static final RegistryObject<WortFluid.Source> WORT_FLUID_STILL = FLUIDS.register(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.WORT).get(FluidUtils.STILL), WortFluid.Source::new);
    public static final RegistryObject<WortFluid.Flowing> WORT_FLUID_FLOWING = FLUIDS.register(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.WORT).get(FluidUtils.FLOWING), WortFluid.Flowing::new);
    public static final RegistryObject<LiquidBlock> WORT_FLUID_BLOCK =
            GrowthcraftCellarBlocks.BLOCKS.register(FluidUtils.getFluidNames(Reference.UnlocalizedName.WORT).get(FluidUtils.BLOCK),
                    () -> new LiquidBlock(WORT_FLUID_STILL, BlockBehaviour.Properties.of(Material.WATER)));

    private GrowthcraftCellarFluids() {
        /* Prevent default public constructor */
    }
}
