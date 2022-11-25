package growthcraft.cellar.init;

import growthcraft.cellar.item.CellarPotionItem;
import growthcraft.cellar.item.GrapeSeedsItem;
import growthcraft.cellar.item.HopsSeedsItem;
import growthcraft.cellar.shared.Reference;
import growthcraft.lib.item.GrowthcraftBucketItem;
import growthcraft.lib.item.GrowthcraftFoodItem;
import growthcraft.lib.item.GrowthcraftItem;
import growthcraft.lib.utils.FluidUtils;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

public class GrowthcraftCellarItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(
            ForgeRegistries.ITEMS, Reference.MODID
    );

    public static final RegistryObject<GrowthcraftItem> BREW_KETTLE_LID = ITEMS.register(
            Reference.UnlocalizedName.BREW_KETTLE_LID, GrowthcraftItem::new
    );

    public static final RegistryObject<GrowthcraftBucketItem> BUCKET_AMBER_ALE = ITEMS.register(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.AMBER_ALE).get(FluidUtils.BUCKET),
            () -> new GrowthcraftBucketItem(
                    GrowthcraftCellarFluids.AMBER_ALE_FLUID_STILL,
                    Reference.FluidColor.AMBER_ALE.getColor()
            )
    );

    public static final RegistryObject<GrowthcraftBucketItem> BUCKET_AMBER_LAGER = ITEMS.register(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.AMBER_LAGER).get(FluidUtils.BUCKET),
            () -> new GrowthcraftBucketItem(
                    GrowthcraftCellarFluids.AMBER_LAGER_FLUID_STILL,
                    Reference.FluidColor.AMBER_LAGER.getColor()
            )
    );

    public static final RegistryObject<GrowthcraftBucketItem> BUCKET_AMBER_WORT = ITEMS.register(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.AMBER_WORT).get(FluidUtils.BUCKET),
            () -> new GrowthcraftBucketItem(
                    GrowthcraftCellarFluids.AMBER_WORT_FLUID_STILL,
                    Reference.FluidColor.AMBER_WORT_FLUID_COLOR.getColor()
            )
    );

    public static final RegistryObject<GrowthcraftBucketItem> BUCKET_BROWN_ALE = ITEMS.register(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.BROWN_ALE).get(FluidUtils.BUCKET),
            () -> new GrowthcraftBucketItem(
                    GrowthcraftCellarFluids.BROWN_ALE_FLUID_STILL,
                    Reference.FluidColor.BROWN_ALE_FLUID_COLOR.getColor()
            )
    );

    public static final RegistryObject<GrowthcraftBucketItem> BUCKET_BROWN_LAGER = ITEMS.register(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.BROWN_LAGER).get(FluidUtils.BUCKET),
            () -> new GrowthcraftBucketItem(
                    GrowthcraftCellarFluids.BROWN_LAGER_FLUID_STILL,
                    Reference.FluidColor.BROWN_LAGER.getColor()
            )
    );

    public static final RegistryObject<GrowthcraftBucketItem> BUCKET_BROWN_WORT = ITEMS.register(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.BROWN_WORT).get(FluidUtils.BUCKET),
            () -> new GrowthcraftBucketItem(
                    GrowthcraftCellarFluids.BROWN_WORT_FLUID_STILL,
                    Reference.FluidColor.BROWN_WORT_FLUID_COLOR.getColor()
            )
    );

    public static final RegistryObject<GrowthcraftBucketItem> BUCKET_COPPER_ALE = ITEMS.register(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.COPPER_ALE).get(FluidUtils.BUCKET),
            () -> new GrowthcraftBucketItem(
                    GrowthcraftCellarFluids.COPPER_ALE_FLUID_STILL,
                    Reference.FluidColor.COPPER_ALE_FLUID_COLOR.getColor()
            )
    );

    public static final RegistryObject<GrowthcraftBucketItem> BUCKET_COPPER_LAGER = ITEMS.register(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.COPPER_LAGER).get(FluidUtils.BUCKET),
            () -> new GrowthcraftBucketItem(
                    GrowthcraftCellarFluids.COPPER_LAGER_FLUID_STILL,
                    Reference.FluidColor.COPPER_LAGER_FLUID_COLOR.getColor()
            )
    );

    public static final RegistryObject<GrowthcraftBucketItem> BUCKET_COPPER_WORT = ITEMS.register(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.COPPER_WORT).get(FluidUtils.BUCKET),
            () -> new GrowthcraftBucketItem(
                    GrowthcraftCellarFluids.COPPER_WORT_FLUID_STILL,
                    Reference.FluidColor.COPPER_WORT_FLUID_COLOR.getColor()
            )
    );

    public static final RegistryObject<GrowthcraftBucketItem> BUCKET_DARK_LAGER = ITEMS.register(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.DARK_LAGER).get(FluidUtils.BUCKET),
            () -> new GrowthcraftBucketItem(
                    GrowthcraftCellarFluids.DARK_LAGER_FLUID_STILL,
                    Reference.FluidColor.DARK_LAGER_FLUID_COLOR.getColor()
            )
    );

    public static final RegistryObject<GrowthcraftBucketItem> BUCKET_DARK_WORT = ITEMS.register(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.DARK_WORT).get(FluidUtils.BUCKET),
            () -> new GrowthcraftBucketItem(
                    GrowthcraftCellarFluids.DARK_WORT_FLUID_STILL,
                    Reference.FluidColor.DARK_WORT_FLUID_COLOR.getColor()
            )
    );

    public static final RegistryObject<GrowthcraftBucketItem> BUCKET_DEEP_AMBER_WORT = ITEMS.register(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.DEEP_AMBER_WORT).get(FluidUtils.BUCKET),
            () -> new GrowthcraftBucketItem(
                    GrowthcraftCellarFluids.DEEP_AMBER_WORT_FLUID_STILL,
                    Reference.FluidColor.DEEP_AMBER_WORT_FLUID_COLOR.getColor()
            )
    );

    public static final RegistryObject<GrowthcraftBucketItem> BUCKET_DEEP_COPPER_WORT = ITEMS.register(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.DEEP_COPPER_WORT).get(FluidUtils.BUCKET),
            () -> new GrowthcraftBucketItem(
                    GrowthcraftCellarFluids.DEEP_COPPER_WORT_FLUID_STILL,
                    Reference.FluidColor.DEEP_COPPER_WORT_FLUID_COLOR.getColor()
            )
    );

    public static final RegistryObject<GrowthcraftBucketItem> BUCKET_GOLDEN_WORT = ITEMS.register(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.GOLDEN_WORT).get(FluidUtils.BUCKET),
            () -> new GrowthcraftBucketItem(
                    GrowthcraftCellarFluids.GOLDEN_WORT_FLUID_STILL,
                    Reference.FluidColor.GOLDEN_WORT_FLUID_COLOR.getColor()
            )
    );

    public static final RegistryObject<GrowthcraftBucketItem> BUCKET_IPA_ALE = ITEMS.register(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.IPA_ALE).get(FluidUtils.BUCKET),
            () -> new GrowthcraftBucketItem(
                    GrowthcraftCellarFluids.IPA_ALE_FLUID_STILL,
                    Reference.FluidColor.IPA_ALE_FLUID_COLOR.getColor()
            )
    );

    public static final RegistryObject<GrowthcraftBucketItem> BUCKET_OLD_PORT_ALE = ITEMS.register(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.OLD_PORT_ALE).get(FluidUtils.BUCKET),
            () -> new GrowthcraftBucketItem(
                    GrowthcraftCellarFluids.OLD_PORT_ALE_FLUID_STILL,
                    Reference.FluidColor.OLD_PORT_ALE_FLUID_COLOR.getColor()
            )
    );

    public static final RegistryObject<GrowthcraftBucketItem> BUCKET_PALE_ALE = ITEMS.register(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.PALE_ALE).get(FluidUtils.BUCKET),
            () -> new GrowthcraftBucketItem(
                    GrowthcraftCellarFluids.PALE_ALE_FLUID_STILL,
                    Reference.FluidColor.PALE_ALE_FLUID_COLOR.getColor()
            )
    );

    public static final RegistryObject<GrowthcraftBucketItem> BUCKET_PALE_GOLDEN_WORT = ITEMS.register(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.PALE_GOLDEN_WORT).get(FluidUtils.BUCKET),
            () -> new GrowthcraftBucketItem(
                    GrowthcraftCellarFluids.PALE_GOLDEN_WORT_FLUID_STILL,
                    Reference.FluidColor.PALE_GOLDEN_WORT_FLUID_COLOR.getColor()
            )
    );

    public static final RegistryObject<GrowthcraftBucketItem> BUCKET_PALE_LAGER = ITEMS.register(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.PALE_LAGER).get(FluidUtils.BUCKET),
            () -> new GrowthcraftBucketItem(
                    GrowthcraftCellarFluids.PALE_LAGER_FLUID_STILL,
                    Reference.FluidColor.PALE_LAGER_FLUID_COLOR.getColor()
            )
    );

    public static final RegistryObject<GrowthcraftBucketItem> BUCKET_PILSNER_LAGER = ITEMS.register(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.PILSNER_LAGER).get(FluidUtils.BUCKET),
            () -> new GrowthcraftBucketItem(
                    GrowthcraftCellarFluids.PILSNER_LAGER_FLUID_STILL,
                    Reference.FluidColor.PILSNER_LAGER_FLUID_COLOR.getColor()
            )
    );

    public static final RegistryObject<GrowthcraftBucketItem> BUCKET_PURPLE_GRAPE_JUICE = ITEMS.register(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.PURPLE_GRAPE_JUICE).get(FluidUtils.BUCKET),
            () -> new GrowthcraftBucketItem(
                    GrowthcraftCellarFluids.PURPLE_GRAPE_JUICE_FLUID_STILL,
                    Reference.FluidColor.PURPLE_GRAPE_JUICE.getColor()
            )
    );

    public static final RegistryObject<GrowthcraftBucketItem> BUCKET_PURPLE_GRAPE_WINE = ITEMS.register(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.PURPLE_GRAPE_WINE).get(FluidUtils.BUCKET),
            () -> new GrowthcraftBucketItem(
                    GrowthcraftCellarFluids.PURPLE_GRAPE_WINE_FLUID_STILL,
                    Reference.FluidColor.PURPLE_GRAPE_WINE.getColor()
            )
    );

    public static final RegistryObject<GrowthcraftBucketItem> BUCKET_RED_GRAPE_JUICE = ITEMS.register(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.RED_GRAPE_JUICE).get(FluidUtils.BUCKET),
            () -> new GrowthcraftBucketItem(
                    GrowthcraftCellarFluids.RED_GRAPE_JUICE_FLUID_STILL,
                    Reference.FluidColor.RED_GRAPE_JUICE.getColor()
            )
    );

    public static final RegistryObject<GrowthcraftBucketItem> BUCKET_RED_GRAPE_WINE = ITEMS.register(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.RED_GRAPE_WINE).get(FluidUtils.BUCKET),
            () -> new GrowthcraftBucketItem(
                    GrowthcraftCellarFluids.RED_GRAPE_WINE_FLUID_STILL,
                    Reference.FluidColor.RED_GRAPE_WINE.getColor()
            )
    );

    public static final RegistryObject<GrowthcraftBucketItem> BUCKET_STOUT_ALE = ITEMS.register(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.STOUT_ALE).get(FluidUtils.BUCKET),
            () -> new GrowthcraftBucketItem(
                    GrowthcraftCellarFluids.STOUT_ALE_FLUID_STILL,
                    Reference.FluidColor.STOUT_ALE.getColor()
            )
    );

    public static final RegistryObject<GrowthcraftBucketItem> BUCKET_VIENNA_LAGER = ITEMS.register(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.VIENNA_LAGER).get(FluidUtils.BUCKET),
            () -> new GrowthcraftBucketItem(
                    GrowthcraftCellarFluids.VIENNA_LAGER_FLUID_STILL,
                    Reference.FluidColor.VIENNA_LAGER.getColor()
            )
    );

    public static final RegistryObject<GrowthcraftBucketItem> BUCKET_WHITE_GRAPE_JUICE = ITEMS.register(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.WHITE_GRAPE_JUICE).get(FluidUtils.BUCKET),
            () -> new GrowthcraftBucketItem(
                    GrowthcraftCellarFluids.WHITE_GRAPE_JUICE_FLUID_STILL,
                    Reference.FluidColor.WHITE_GRAPE_JUICE.getColor()
            )
    );

    public static final RegistryObject<GrowthcraftBucketItem> BUCKET_WHITE_GRAPE_WINE = ITEMS.register(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.WHITE_GRAPE_WINE).get(FluidUtils.BUCKET),
            () -> new GrowthcraftBucketItem(
                    GrowthcraftCellarFluids.WHITE_GRAPE_WINE_FLUID_STILL,
                    Reference.FluidColor.WHITE_GRAPE_WINE.getColor()
            )
    );

    public static final RegistryObject<GrowthcraftBucketItem> BUCKET_WORT = ITEMS.register(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.WORT).get(FluidUtils.BUCKET),
            () -> new GrowthcraftBucketItem(
                    GrowthcraftCellarFluids.WORT_FLUID_STILL,
                    Reference.FluidColor.WORT.getColor()
            )
    );

    public static final RegistryObject<GrowthcraftBucketItem> BUCKET_HOPPED_GOLDEN_WORT = ITEMS.register(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.HOPPED_GOLDEN_WORT).get(FluidUtils.BUCKET),
            () -> new GrowthcraftBucketItem(
                    GrowthcraftCellarFluids.HOPPED_GOLDEN_WORT_FLUID_STILL,
                    Reference.FluidColor.HOPPED_GOLDEN_WORT_FLUID_COLOR.getColor()
            )
    );

    public static final RegistryObject<GrowthcraftItem> GRAIN = ITEMS.register(
            Reference.UnlocalizedName.GRAIN, () -> new GrowthcraftItem(64, Reference.GrainColor.GRAIN.getColor())
    );

    public static final RegistryObject<GrowthcraftItem> GRAIN_AMBER = ITEMS.register(
            Reference.UnlocalizedName.GRAIN_AMBER, () -> new GrowthcraftItem(64, Reference.GrainColor.GRAIN_AMBER.getColor())
    );

    public static final RegistryObject<GrowthcraftItem> GRAIN_BROWN = ITEMS.register(
            Reference.UnlocalizedName.GRAIN_BROWN, () -> new GrowthcraftItem(64, Reference.GrainColor.GRAIN_BROWN.getColor())
    );

    public static final RegistryObject<GrowthcraftItem> GRAIN_COPPER = ITEMS.register(
            Reference.UnlocalizedName.GRAIN_COPPER, () -> new GrowthcraftItem(64, Reference.GrainColor.GRAIN_COPPER.getColor())
    );

    public static final RegistryObject<GrowthcraftItem> GRAIN_DARK = ITEMS.register(
            Reference.UnlocalizedName.GRAIN_DARK, () -> new GrowthcraftItem(64, Reference.GrainColor.GRAIN_DARK.getColor())
    );

    public static final RegistryObject<GrowthcraftItem> GRAIN_DEEP_AMBER = ITEMS.register(
            Reference.UnlocalizedName.GRAIN_DEEP_AMBER, () -> new GrowthcraftItem(64, Reference.GrainColor.GRAIN_DEEP_AMBER.getColor())
    );

    public static final RegistryObject<GrowthcraftItem> GRAIN_DEEP_COPPER = ITEMS.register(
            Reference.UnlocalizedName.GRAIN_DEEP_COPPER, () -> new GrowthcraftItem(64, Reference.GrainColor.GRAIN_DEEP_COPPER.getColor())
    );

    public static final RegistryObject<GrowthcraftItem> GRAIN_GOLDEN = ITEMS.register(
            Reference.UnlocalizedName.GRAIN_GOLDEN, () -> new GrowthcraftItem(64, Reference.GrainColor.GRAIN_GOLDEN.getColor())
    );

    public static final RegistryObject<GrowthcraftItem> GRAIN_PALE_GOLDEN = ITEMS.register(
            Reference.UnlocalizedName.GRAIN_PALE_GOLDEN, () -> new GrowthcraftItem(64, Reference.GrainColor.GRAIN_PALE_GOLDEN.getColor())
    );

    public static final RegistryObject<GrowthcraftFoodItem> GRAPE_PURPLE = ITEMS.register(
            Reference.UnlocalizedName.GRAPE_PURPLE, GrowthcraftFoodItem::new
    );

    public static final RegistryObject<GrapeSeedsItem> PURPLE_RED_SEEDS = ITEMS.register(
            Reference.UnlocalizedName.GRAPE_SEEDS_PURPLE,
            () -> new GrapeSeedsItem(
                    GrowthcraftCellarBlocks.PURPLE_GRAPE_VINE.get(),
                    GrowthcraftCellarBlocks.PURPLE_GRAPE_VINE_LEAVES.get(),
                    GrowthcraftCellarBlocks.PURPLE_GRAPE_VINE_FRUIT.get())
    );

    public static final RegistryObject<GrowthcraftFoodItem> GRAPE_RED = ITEMS.register(
            Reference.UnlocalizedName.GRAPE_RED, GrowthcraftFoodItem::new
    );

    // TODO: GRAPE_RED_SEEDS
    public static final RegistryObject<GrapeSeedsItem> GRAPE_RED_SEEDS = ITEMS.register(
            Reference.UnlocalizedName.GRAPE_SEEDS_RED,
            () -> new GrapeSeedsItem(
                    GrowthcraftCellarBlocks.RED_GRAPE_VINE.get(),
                    GrowthcraftCellarBlocks.RED_GRAPE_VINE_LEAVES.get(),
                    GrowthcraftCellarBlocks.RED_GRAPE_VINE_FRUIT.get())
    );

    public static final RegistryObject<GrowthcraftFoodItem> GRAPE_WHITE = ITEMS.register(
            Reference.UnlocalizedName.GRAPE_WHITE, GrowthcraftFoodItem::new
    );

    public static final RegistryObject<GrapeSeedsItem> WHITE_RED_SEEDS = ITEMS.register(
            Reference.UnlocalizedName.GRAPE_SEEDS_WHITE,
            () -> new GrapeSeedsItem(
                    GrowthcraftCellarBlocks.WHITE_GRAPE_VINE.get(),
                    GrowthcraftCellarBlocks.WHITE_GRAPE_VINE_LEAVES.get(),
                    GrowthcraftCellarBlocks.WHITE_GRAPE_VINE_FRUIT.get())
    );

    public static final RegistryObject<GrowthcraftFoodItem> HOPS = ITEMS.register(
            Reference.UnlocalizedName.HOPS, GrowthcraftFoodItem::new
    );

    public static final RegistryObject<HopsSeedsItem> HOPS_SEED = ITEMS.register(
            Reference.UnlocalizedName.HOPS_SEEDS, HopsSeedsItem::new
    );

    public static final RegistryObject<CellarPotionItem> POTION_ALE = ITEMS.register(
            Reference.UnlocalizedName.POTION_ALE, CellarPotionItem::new
    );

    public static final RegistryObject<CellarPotionItem> POTION_LAGER = ITEMS.register(
            Reference.UnlocalizedName.POTION_LAGER, CellarPotionItem::new
    );

    public static final RegistryObject<CellarPotionItem> POTION_WINE = ITEMS.register(
            Reference.UnlocalizedName.POTION_WINE, CellarPotionItem::new
    );

    public static final RegistryObject<GrowthcraftItem> YEAST_BAYANUS = ITEMS.register(
            Reference.UnlocalizedName.YEAST_BAYANUS, GrowthcraftItem::new
    );


    public static final RegistryObject<GrowthcraftItem> YEAST_BAYANUS_ETHEREAL = ITEMS.register(
            Reference.UnlocalizedName.YEAST_BAYANUS_ETHEREAL, GrowthcraftItem::new
    );

    public static final RegistryObject<GrowthcraftItem> YEAST_BREWERS = ITEMS.register(
            Reference.UnlocalizedName.YEAST_BREWERS, GrowthcraftItem::new
    );

    public static final RegistryObject<GrowthcraftItem> YEAST_BREWERS_ETHEREAL = ITEMS.register(
            Reference.UnlocalizedName.YEAST_BREWERS_ETHEREAL, GrowthcraftItem::new
    );

    public static final RegistryObject<GrowthcraftItem> YEAST_ETHEREAL = ITEMS.register(
            Reference.UnlocalizedName.YEAST_ETHEREAL, GrowthcraftItem::new
    );

    public static final RegistryObject<GrowthcraftItem> YEAST_LAGER = ITEMS.register(
            Reference.UnlocalizedName.YEAST_LAGER, GrowthcraftItem::new
    );

    public static final RegistryObject<GrowthcraftItem> YEAST_LAGER_ETHEREAL = ITEMS.register(
            Reference.UnlocalizedName.YEAST_LAGER_ETHEREAL, GrowthcraftItem::new
    );

    public static final List<RegistryObject<? extends Item>> BUCKETS = List.of(
            BUCKET_AMBER_ALE, BUCKET_AMBER_LAGER, BUCKET_AMBER_WORT, BUCKET_BROWN_ALE, BUCKET_BROWN_LAGER,
            BUCKET_BROWN_WORT, BUCKET_BROWN_ALE, BUCKET_COPPER_LAGER, BUCKET_COPPER_WORT, BUCKET_DARK_LAGER,
            BUCKET_DARK_WORT, BUCKET_DEEP_AMBER_WORT, BUCKET_DEEP_COPPER_WORT, BUCKET_GOLDEN_WORT, BUCKET_IPA_ALE,
            BUCKET_OLD_PORT_ALE, BUCKET_PALE_ALE, BUCKET_PALE_GOLDEN_WORT, BUCKET_PALE_LAGER, BUCKET_PILSNER_LAGER,
            BUCKET_PURPLE_GRAPE_JUICE, BUCKET_PURPLE_GRAPE_WINE, BUCKET_RED_GRAPE_JUICE, BUCKET_RED_GRAPE_WINE,
            BUCKET_STOUT_ALE, BUCKET_VIENNA_LAGER, BUCKET_WHITE_GRAPE_JUICE, BUCKET_WHITE_GRAPE_WINE, BUCKET_WORT,
            BUCKET_HOPPED_GOLDEN_WORT, POTION_ALE, POTION_LAGER, POTION_WINE
    );

    public static final List<RegistryObject<? extends Item>> POTIONS = List.of(
            POTION_ALE, POTION_LAGER, POTION_WINE
    );

    public static final List<RegistryObject<? extends Item>> GRAINS = List.of(
            GRAIN_AMBER, GRAIN_BROWN, GRAIN_COPPER, GRAIN_DARK, GRAIN_DEEP_AMBER,
            GRAIN_DEEP_COPPER, GRAIN_GOLDEN, GRAIN_PALE_GOLDEN
    );

    public static void registerCompostables() {
        float f = 0.3F;
        float f1 = 0.5F;
        float f2 = 0.65F;
        float f3 = 0.85F;
        float f4 = 1.0F;

        // ComposterBlock.COMPOSTABLES.put(GrowthcraftRiceItems.RICE.get(), f2);

    }

    private GrowthcraftCellarItems() {
        /* Prevent default public constructor */
    }
}
