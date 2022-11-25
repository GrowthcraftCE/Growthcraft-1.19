package growthcraft.milk.init;

import growthcraft.lib.item.GrowthcraftBucketItem;
import growthcraft.lib.item.GrowthcraftFoodItem;
import growthcraft.lib.item.GrowthcraftItem;
import growthcraft.lib.utils.CheeseUtils;
import growthcraft.lib.utils.FluidUtils;
import growthcraft.milk.item.MilkingBucketItem;
import growthcraft.milk.shared.Reference;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

public class GrowthcraftMilkItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(
            ForgeRegistries.ITEMS, Reference.MODID
    );

    public static final RegistryObject<GrowthcraftBucketItem> BUCKET_BUTTER_MILK = ITEMS.register(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.BUTTER_MILK).get(FluidUtils.BUCKET),
            () -> new GrowthcraftBucketItem(
                    GrowthcraftMilkFluids.BUTTER_MILK_FLUID_STILL,
                    Reference.FluidColor.BUTTER_MILK_FLUID_COLOR.getColor()
            )
    );

    public static final RegistryObject<GrowthcraftBucketItem> BUCKET_CHEESE_BASE = ITEMS.register(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.CHEESE_BASE).get(FluidUtils.BUCKET),
            () -> new GrowthcraftBucketItem(
                    GrowthcraftMilkFluids.CHEESE_BASE_FLUID_STILL,
                    Reference.FluidColor.CHEESE_BASE_FLUID_COLOR.getColor()
            )
    );

    public static final RegistryObject<GrowthcraftBucketItem> BUCKET_CONDENSED_MILK = ITEMS.register(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.CONDENSED_MILK).get(FluidUtils.BUCKET),
            () -> new GrowthcraftBucketItem(
                    GrowthcraftMilkFluids.CONDENSED_MILK_FLUID_STILL,
                    Reference.FluidColor.CONDENSED_MILK_FLUID_COLOR.getColor()
            )
    );

    public static final RegistryObject<GrowthcraftBucketItem> BUCKET_CREAM = ITEMS.register(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.CREAM).get(FluidUtils.BUCKET),
            () -> new GrowthcraftBucketItem(
                    GrowthcraftMilkFluids.CREAM_FLUID_STILL,
                    Reference.FluidColor.CREAM_FLUID_COLOR.getColor()
            )
    );

    public static final RegistryObject<GrowthcraftBucketItem> BUCKET_CULTURED_MILK = ITEMS.register(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.CULTURED_MILK).get(FluidUtils.BUCKET),
            () -> new GrowthcraftBucketItem(
                    GrowthcraftMilkFluids.CULTURED_MILK_FLUID_STILL,
                    Reference.FluidColor.CULTURED_MILK_FLUID_COLOR.getColor()
            )
    );

    public static final RegistryObject<GrowthcraftBucketItem> BUCKET_KUMIS = ITEMS.register(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.KUMIS).get(FluidUtils.BUCKET),
            () -> new GrowthcraftBucketItem(
                    GrowthcraftMilkFluids.KUMIS_FLUID_STILL,
                    Reference.FluidColor.KUMIS_FLUID_COLOR.getColor()
            )
    );

    public static final RegistryObject<GrowthcraftBucketItem> BUCKET_MILK = ITEMS.register(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.MILK).get(FluidUtils.BUCKET),
            () -> new GrowthcraftBucketItem(
                    GrowthcraftMilkFluids.MILK_FLUID_STILL,
                    Reference.FluidColor.MILK_FLUID_COLOR.getColor()
            )
    );

    public static final RegistryObject<GrowthcraftBucketItem> BUCKET_RENNET = ITEMS.register(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.RENNET).get(FluidUtils.BUCKET),
            () -> new GrowthcraftBucketItem(
                    GrowthcraftMilkFluids.RENNET_FLUID_STILL,
                    Reference.FluidColor.RENNET_FLUID_COLOR.getColor()
            )
    );

    public static final RegistryObject<GrowthcraftBucketItem> BUCKET_SKIM_MILK = ITEMS.register(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.SKIM_MILK).get(FluidUtils.BUCKET),
            () -> new GrowthcraftBucketItem(
                    GrowthcraftMilkFluids.SKIM_MILK_FLUID_STILL,
                    Reference.FluidColor.SKIM_MILK_FLUID_COLOR.getColor()
            )
    );

    public static final RegistryObject<GrowthcraftBucketItem> BUCKET_WHEY = ITEMS.register(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.WHEY).get(FluidUtils.BUCKET),
            () -> new GrowthcraftBucketItem(
                    GrowthcraftMilkFluids.WHEY_FLUID_STILL,
                    Reference.FluidColor.WHEY_FLUID_COLOR.getColor()
            )
    );

    public static final RegistryObject<GrowthcraftItem> BUTTER = ITEMS.register(
            Reference.UnlocalizedName.BUTTER, GrowthcraftItem::new
    );

    public static final RegistryObject<GrowthcraftItem> BUTTER_SALTED = ITEMS.register(
            Reference.UnlocalizedName.BUTTER_SALTED, GrowthcraftItem::new
    );

    public static final RegistryObject<GrowthcraftFoodItem> CHEESE_APPENZELLER_SLICE = ITEMS.register(
            CheeseUtils.getCheeseNames(Reference.UnlocalizedName.APPENZELLER).get(CheeseUtils.SLICE),
            () -> new GrowthcraftFoodItem(2, 0.3F, 64)
    );

    // TODO: CHEESE_APPENZELLER_CURDS

    public static final RegistryObject<GrowthcraftFoodItem> CHEESE_ASIAGO_SLICE = ITEMS.register(
            CheeseUtils.getCheeseNames(Reference.UnlocalizedName.ASIAGO).get(CheeseUtils.SLICE),
            () -> new GrowthcraftFoodItem(2, 0.3F, 64)
    );

    // TODO: CHEESE_ASIAGO_CURDS

    public static final RegistryObject<GrowthcraftFoodItem> CHEESE_CASU_MARZU_SLICE = ITEMS.register(
            CheeseUtils.getCheeseNames(Reference.UnlocalizedName.CASU_MARZU).get(CheeseUtils.SLICE),
            () -> new GrowthcraftFoodItem(2, 0.3F, 64)
    );

    // TODO: CHEESE_CASU_MARZU_CURDS

    public static final RegistryObject<GrowthcraftFoodItem> CHEESE_CHEDDAR_SLICE = ITEMS.register(
            CheeseUtils.getCheeseNames(Reference.UnlocalizedName.CHEDDAR).get(CheeseUtils.SLICE),
            () -> new GrowthcraftFoodItem(2, 0.3F, 64)
    );

    // TODO: CHEESE_CHEDDAR_CURDS

    public static final RegistryObject<GrowthcraftItem> CHEESE_CLOTH = ITEMS.register(
            Reference.UnlocalizedName.CHEESE_CLOTH, GrowthcraftItem::new
    );

    public static final RegistryObject<GrowthcraftFoodItem> CHEESE_EMMENTALER_SLICE = ITEMS.register(
            CheeseUtils.getCheeseNames(Reference.UnlocalizedName.EMMENTALER).get(CheeseUtils.SLICE),
            () -> new GrowthcraftFoodItem(2, 0.3F, 64)
    );

    // TODO: CHEESE_EMMENTALER_CURDS

    public static final RegistryObject<GrowthcraftFoodItem> CHEESE_GORGONZOLA_SLICE = ITEMS.register(
            CheeseUtils.getCheeseNames(Reference.UnlocalizedName.GORGONZOLA).get(CheeseUtils.SLICE),
            () -> new GrowthcraftFoodItem(2, 0.3F, 64)
    );

    // TODO: CHEESE_GORGONZOLA_CURDS

    public static final RegistryObject<GrowthcraftFoodItem> CHEESE_GOUDA_SLICE = ITEMS.register(
            CheeseUtils.getCheeseNames(Reference.UnlocalizedName.GOUDA).get(CheeseUtils.SLICE),
            () -> new GrowthcraftFoodItem(2, 0.3F, 64)
    );

    // TODO: CHEESE_GOUDA_CURDS

    public static final RegistryObject<GrowthcraftFoodItem> CHEESE_MONTEREY_SLICE = ITEMS.register(
            CheeseUtils.getCheeseNames(Reference.UnlocalizedName.MONTEREY).get(CheeseUtils.SLICE),
            () -> new GrowthcraftFoodItem(2, 0.3F, 64)
    );

    // TODO: CHEESE_MONTEREY_CURDS

    public static final RegistryObject<GrowthcraftFoodItem> CHEESE_PARMESAN_SLICE = ITEMS.register(
            CheeseUtils.getCheeseNames(Reference.UnlocalizedName.PARMESAN).get(CheeseUtils.SLICE),
            () -> new GrowthcraftFoodItem(2, 0.3F, 64)
    );

    // TODO: CHEESE_PARMESAN_CURDS

    public static final RegistryObject<GrowthcraftFoodItem> CHEESE_PROVOLONE_SLICE = ITEMS.register(
            CheeseUtils.getCheeseNames(Reference.UnlocalizedName.PROVOLONE).get(CheeseUtils.SLICE),
            () -> new GrowthcraftFoodItem(2, 0.3F, 64)
    );

    // TODO: CHEESE_PROVOLONE_CURDS

    public static final RegistryObject<GrowthcraftFoodItem> CHEESE_RICOTTA_SLICE = ITEMS.register(
            CheeseUtils.getCheeseNames(Reference.UnlocalizedName.RICOTTA).get(CheeseUtils.SLICE),
            () -> new GrowthcraftFoodItem(2, 0.4F, 16)
    );

    // TODO: CHEESE_RICOTTA_CURDS

    public static final RegistryObject<GrowthcraftFoodItem> ICE_CREAM_APPLE = ITEMS.register(
            Reference.UnlocalizedName.ICE_CREAM_APPLE, () -> new GrowthcraftFoodItem(2, 0.3F, 8, true)
    );

    public static final RegistryObject<GrowthcraftFoodItem> ICE_CREAM_CHOCOLATE  = ITEMS.register(
            Reference.UnlocalizedName.ICE_CREAM_CHOCOLATE , () -> new GrowthcraftFoodItem(2, 0.3F, 8, true)
    );

    public static final RegistryObject<GrowthcraftFoodItem> ICE_CREAM_GRAPE_PURPLE   = ITEMS.register(
            Reference.UnlocalizedName.ICE_CREAM_GRAPE_PURPLE  , () -> new GrowthcraftFoodItem(2, 0.3F, 8, true)
    );

    public static final RegistryObject<GrowthcraftFoodItem> ICE_CREAM_GRAPE_RED   = ITEMS.register(
            Reference.UnlocalizedName.ICE_CREAM_GRAPE_RED  , () -> new GrowthcraftFoodItem(2, 0.3F, 8, true)
    );

    public static final RegistryObject<GrowthcraftFoodItem> ICE_CREAM_GRAPE_WHITE   = ITEMS.register(
            Reference.UnlocalizedName.ICE_CREAM_GRAPE_WHITE  , () -> new GrowthcraftFoodItem(2, 0.3F, 8, true)
    );

    public static final RegistryObject<GrowthcraftFoodItem> ICE_CREAM_PUMPKIN   = ITEMS.register(
            Reference.UnlocalizedName.ICE_CREAM_PUMPKIN  , () -> new GrowthcraftFoodItem(2, 0.3F, 8, true)
    );

    public static final RegistryObject<GrowthcraftFoodItem> ICE_CREAM_WATERMELON   = ITEMS.register(
            Reference.UnlocalizedName.ICE_CREAM_WATERMELON  , () -> new GrowthcraftFoodItem(2, 0.3F, 8, true)
    );

    public static final RegistryObject<GrowthcraftFoodItem> ICE_CREAM_HONEY   = ITEMS.register(
            Reference.UnlocalizedName.ICE_CREAM_HONEY  , () -> new GrowthcraftFoodItem(2, 0.3F, 8, true)
    );

    public static final RegistryObject<MilkingBucketItem> MILKING_BUCKET_IRON = ITEMS.register(
            Reference.UnlocalizedName.MILKING_BUCKET_IRON,
            MilkingBucketItem::new
    );

    public static final RegistryObject<GrowthcraftItem> STARTER_CULTURE = ITEMS.register(
            Reference.UnlocalizedName.STARTER_CULTURE, GrowthcraftItem::new
    );

    public static final RegistryObject<GrowthcraftItem> STOMACH = ITEMS.register(
            Reference.UnlocalizedName.STOMACH, GrowthcraftItem::new
    );

    public static final RegistryObject<GrowthcraftItem> THISTLE = ITEMS.register(
            Reference.UnlocalizedName.THISTLE, GrowthcraftItem::new
    );

    // TODO: THISTLE_SEED

    public static final RegistryObject<GrowthcraftFoodItem> YOGURT_APPLE = ITEMS.register(
            Reference.UnlocalizedName.YOGURT_APPLE, () -> new GrowthcraftFoodItem(2, 0.3F, 8, true)
    );

    public static final RegistryObject<GrowthcraftFoodItem> YOGURT_CHOCOLATE = ITEMS.register(
            Reference.UnlocalizedName.YOGURT_CHOCOLATE, () -> new GrowthcraftFoodItem(2, 0.3F, 8, true)
    );

    public static final RegistryObject<GrowthcraftFoodItem> YOGURT_GRAPE_PURPLE = ITEMS.register(
            Reference.UnlocalizedName.YOGURT_GRAPE_PURPLE, () -> new GrowthcraftFoodItem(2, 0.3F, 8, true)
    );

    public static final RegistryObject<GrowthcraftFoodItem> YOGURT_GRAPE_RED = ITEMS.register(
            Reference.UnlocalizedName.YOGURT_GRAPE_RED, () -> new GrowthcraftFoodItem(2, 0.3F, 8, true)
    );

    public static final RegistryObject<GrowthcraftFoodItem> YOGURT_GRAPE_WHITE = ITEMS.register(
            Reference.UnlocalizedName.YOGURT_GRAPE_WHITE, () -> new GrowthcraftFoodItem(2, 0.3F, 8, true)
    );

    public static final RegistryObject<GrowthcraftFoodItem> YOGURT_HONEY = ITEMS.register(
            Reference.UnlocalizedName.YOGURT_HONEY, () -> new GrowthcraftFoodItem(2, 0.3F, 8, true)
    );

    public static final RegistryObject<GrowthcraftFoodItem> YOGURT_PLAIN = ITEMS.register(
            Reference.UnlocalizedName.YOGURT_PLAIN, () -> new GrowthcraftFoodItem(2, 0.3F, 8, true)
    );

    public static final RegistryObject<GrowthcraftFoodItem> YOGURT_PUMPKIN = ITEMS.register(
            Reference.UnlocalizedName.YOGURT_PUMPKIN, () -> new GrowthcraftFoodItem(2, 0.3F, 8, true)
    );

    public static final RegistryObject<GrowthcraftFoodItem> YOGURT_WATERMELON = ITEMS.register(
            Reference.UnlocalizedName.YOGURT_WATERMELON, () -> new GrowthcraftFoodItem(2, 0.3F, 8, true)
    );

    public static final List<RegistryObject<? extends Item>> BUCKETS = List.of(
            BUCKET_BUTTER_MILK, BUCKET_CHEESE_BASE, BUCKET_CONDENSED_MILK, BUCKET_CREAM,
            BUCKET_CULTURED_MILK, BUCKET_KUMIS, BUCKET_MILK, BUCKET_RENNET, BUCKET_SKIM_MILK,
            BUCKET_WHEY
    );

    public static void registerCompostables() {
        float f = 0.3F;
        float f1 = 0.5F;
        float f2 = 0.65F;
        float f3 = 0.85F;
        float f4 = 1.0F;

        // ComposterBlock.COMPOSTABLES.put(GrowthcraftRiceItems.RICE.get(), f2);

    }

    private GrowthcraftMilkItems() {
        /* Prevent default public constructor */
    }
}
