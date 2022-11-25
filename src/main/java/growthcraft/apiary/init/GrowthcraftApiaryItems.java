package growthcraft.apiary.init;

import com.google.common.collect.ImmutableList;
import growthcraft.apiary.shared.Reference;
import growthcraft.lib.item.GrowthcraftBucketItem;
import growthcraft.lib.item.GrowthcraftItem;
import growthcraft.lib.utils.FluidUtils;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

public class GrowthcraftApiaryItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(
            ForgeRegistries.ITEMS, Reference.MODID
    );

    public static final RegistryObject<GrowthcraftItem> BEE = ITEMS.register(
            Reference.UnlocalizedName.BEE, GrowthcraftItem::new
    );

    public static final RegistryObject<GrowthcraftItem> BEES_WAX = ITEMS.register(
            Reference.UnlocalizedName.BEES_WAX, GrowthcraftItem::new
    );

    public static final RegistryObject<GrowthcraftItem> BEES_WAX_BLACK = ITEMS.register(
            Reference.UnlocalizedName.BEES_WAX_BLACK, GrowthcraftItem::new
    );

    public static final RegistryObject<GrowthcraftItem> BEES_WAX_BLUE = ITEMS.register(
            Reference.UnlocalizedName.BEES_WAX_BLUE, GrowthcraftItem::new
    );

    public static final RegistryObject<GrowthcraftItem> BEES_WAX_BROWN = ITEMS.register(
            Reference.UnlocalizedName.BEES_WAX_BROWN, GrowthcraftItem::new
    );

    public static final RegistryObject<GrowthcraftItem> BEES_WAX_CYAN = ITEMS.register(
            Reference.UnlocalizedName.BEES_WAX_CYAN, GrowthcraftItem::new
    );

    public static final RegistryObject<GrowthcraftItem> BEES_WAX_GRAY = ITEMS.register(
            Reference.UnlocalizedName.BEES_WAX_GRAY, GrowthcraftItem::new
    );

    public static final RegistryObject<GrowthcraftItem> BEES_WAX_GREEN = ITEMS.register(
            Reference.UnlocalizedName.BEES_WAX_GREEN, GrowthcraftItem::new
    );

    public static final RegistryObject<GrowthcraftItem> BEES_WAX_LIGHT_BLUE = ITEMS.register(
            Reference.UnlocalizedName.BEES_WAX_LIGHT_BLUE, GrowthcraftItem::new
    );

    public static final RegistryObject<GrowthcraftItem> BEES_WAX_LIGHT_GRAY = ITEMS.register(
            Reference.UnlocalizedName.BEES_WAX_LIGHT_GRAY, GrowthcraftItem::new
    );

    public static final RegistryObject<GrowthcraftItem> BEES_WAX_LIME = ITEMS.register(
            Reference.UnlocalizedName.BEES_WAX_LIME, GrowthcraftItem::new
    );

    public static final RegistryObject<GrowthcraftItem> BEES_WAX_MAGENTA = ITEMS.register(
            Reference.UnlocalizedName.BEES_WAX_MAGENTA, GrowthcraftItem::new
    );

    public static final RegistryObject<GrowthcraftItem> BEES_WAX_ORANGE = ITEMS.register(
            Reference.UnlocalizedName.BEES_WAX_ORANGE, GrowthcraftItem::new
    );

    public static final RegistryObject<GrowthcraftItem> BEES_WAX_PINK = ITEMS.register(
            Reference.UnlocalizedName.BEES_WAX_PINK, GrowthcraftItem::new
    );

    public static final RegistryObject<GrowthcraftItem> BEES_WAX_PURPLE = ITEMS.register(
            Reference.UnlocalizedName.BEES_WAX_PURPLE, GrowthcraftItem::new
    );

    public static final RegistryObject<GrowthcraftItem> BEES_WAX_RED = ITEMS.register(
            Reference.UnlocalizedName.BEES_WAX_RED, GrowthcraftItem::new
    );

    public static final RegistryObject<GrowthcraftItem> BEES_WAX_WHITE = ITEMS.register(
            Reference.UnlocalizedName.BEES_WAX_WHITE, GrowthcraftItem::new
    );

    public static final RegistryObject<GrowthcraftItem> BEES_WAX_YELLOW = ITEMS.register(
            Reference.UnlocalizedName.BEES_WAX_YELLOW, GrowthcraftItem::new
    );

    public static final RegistryObject<GrowthcraftBucketItem> BUCKET_HONEY = ITEMS.register(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.HONEY).get(FluidUtils.BUCKET),
            () -> new GrowthcraftBucketItem(
                    GrowthcraftApiaryFluids.HONEY_FLUID_STILL,
                    Reference.FluidColor.HONEY_FLUID_COLOR.getColor()
            )
    );

    public static final RegistryObject<GrowthcraftBucketItem> BUCKET_HONEY_MEAD = ITEMS.register(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.HONEY_MEAD).get(FluidUtils.BUCKET),
            () -> new GrowthcraftBucketItem(
                    GrowthcraftApiaryFluids.HONEY_MEAD_FLUID_STILL,
                    Reference.FluidColor.HONEY_MEAD_FLUID_COLOR.getColor()
            )
    );

    public static final RegistryObject<GrowthcraftBucketItem> BUCKET_WAX_BLACK = ITEMS.register(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.WAX_BLACK).get(FluidUtils.BUCKET),
            () -> new GrowthcraftBucketItem(
                    GrowthcraftApiaryFluids.WAX_BLACK_FLUID_STILL,
                    Reference.FluidColor.WAX_BLACK_COLOR.getColor()
            )
    );

    public static final RegistryObject<GrowthcraftBucketItem> BUCKET_WAX_BLUE = ITEMS.register(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.WAX_BLUE).get(FluidUtils.BUCKET),
            () -> new GrowthcraftBucketItem(
                    GrowthcraftApiaryFluids.WAX_BLUE_FLUID_STILL,
                    Reference.FluidColor.WAX_BLUE_COLOR.getColor()
            )
    );

    public static final RegistryObject<GrowthcraftBucketItem> BUCKET_WAX_BROWN = ITEMS.register(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.WAX_BROWN).get(FluidUtils.BUCKET),
            () -> new GrowthcraftBucketItem(
                    GrowthcraftApiaryFluids.WAX_BROWN_FLUID_STILL,
                    Reference.FluidColor.WAX_BROWN_COLOR.getColor()
            )
    );

    public static final RegistryObject<GrowthcraftBucketItem> BUCKET_WAX_CYAN = ITEMS.register(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.WAX_CYAN).get(FluidUtils.BUCKET),
            () -> new GrowthcraftBucketItem(
                    GrowthcraftApiaryFluids.WAX_CYAN_FLUID_STILL,
                    Reference.FluidColor.WAX_CYAN_COLOR.getColor()
            )
    );

    public static final RegistryObject<GrowthcraftBucketItem> BUCKET_WAX_GRAY = ITEMS.register(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.WAX_GRAY).get(FluidUtils.BUCKET),
            () -> new GrowthcraftBucketItem(
                    GrowthcraftApiaryFluids.WAX_GRAY_FLUID_STILL,
                    Reference.FluidColor.WAX_GRAY_COLOR.getColor()
            )
    );

    public static final RegistryObject<GrowthcraftBucketItem> BUCKET_WAX_GREEN = ITEMS.register(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.WAX_GREEN).get(FluidUtils.BUCKET),
            () -> new GrowthcraftBucketItem(
                    GrowthcraftApiaryFluids.WAX_GREEN_FLUID_STILL,
                    Reference.FluidColor.WAX_GREEN_COLOR.getColor()
            )
    );

    public static final RegistryObject<GrowthcraftBucketItem> BUCKET_WAX_LIGHT_BLUE = ITEMS.register(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.WAX_LIGHT_BLUE).get(FluidUtils.BUCKET),
            () -> new GrowthcraftBucketItem(
                    GrowthcraftApiaryFluids.WAX_LIGHT_BLUE_FLUID_STILL,
                    Reference.FluidColor.WAX_LIGHT_BLUE_COLOR.getColor()
            )
    );

    public static final RegistryObject<GrowthcraftBucketItem> BUCKET_WAX_LIGHT_GRAY = ITEMS.register(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.WAX_LIGHT_GRAY).get(FluidUtils.BUCKET),
            () -> new GrowthcraftBucketItem(
                    GrowthcraftApiaryFluids.WAX_LIGHT_GRAY_FLUID_STILL,
                    Reference.FluidColor.WAX_LIGHT_GRAY_COLOR.getColor()
            )
    );

    public static final RegistryObject<GrowthcraftBucketItem> BUCKET_WAX_LIME = ITEMS.register(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.WAX_LIME).get(FluidUtils.BUCKET),
            () -> new GrowthcraftBucketItem(
                    GrowthcraftApiaryFluids.WAX_LIME_FLUID_STILL,
                    Reference.FluidColor.WAX_LIME_COLOR.getColor()
            )
    );

    public static final RegistryObject<GrowthcraftBucketItem> BUCKET_WAX_MAGENTA = ITEMS.register(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.WAX_MAGENTA).get(FluidUtils.BUCKET),
            () -> new GrowthcraftBucketItem(
                    GrowthcraftApiaryFluids.WAX_MAGENTA_FLUID_STILL,
                    Reference.FluidColor.WAX_MAGENTA_COLOR.getColor()
            )
    );

    public static final RegistryObject<GrowthcraftBucketItem> BUCKET_WAX_ORANGE = ITEMS.register(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.WAX_ORANGE).get(FluidUtils.BUCKET),
            () -> new GrowthcraftBucketItem(
                    GrowthcraftApiaryFluids.WAX_ORANGE_FLUID_STILL,
                    Reference.FluidColor.WAX_ORANGE_COLOR.getColor()
            )
    );

    public static final RegistryObject<GrowthcraftBucketItem> BUCKET_WAX_PINK = ITEMS.register(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.WAX_PINK).get(FluidUtils.BUCKET),
            () -> new GrowthcraftBucketItem(
                    GrowthcraftApiaryFluids.WAX_PINK_FLUID_STILL,
                    Reference.FluidColor.WAX_PINK_COLOR.getColor()
            )
    );

    public static final RegistryObject<GrowthcraftBucketItem> BUCKET_WAX_PURPLE = ITEMS.register(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.WAX_PURPLE).get(FluidUtils.BUCKET),
            () -> new GrowthcraftBucketItem(
                    GrowthcraftApiaryFluids.WAX_PURPLE_FLUID_STILL,
                    Reference.FluidColor.WAX_PURPLE_COLOR.getColor()
            )
    );

    public static final RegistryObject<GrowthcraftBucketItem> BUCKET_WAX_RED = ITEMS.register(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.WAX_RED).get(FluidUtils.BUCKET),
            () -> new GrowthcraftBucketItem(
                    GrowthcraftApiaryFluids.WAX_RED_FLUID_STILL,
                    Reference.FluidColor.WAX_RED_COLOR.getColor()
            )
    );

    public static final RegistryObject<GrowthcraftBucketItem> BUCKET_WAX_WHITE = ITEMS.register(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.WAX_WHITE).get(FluidUtils.BUCKET),
            () -> new GrowthcraftBucketItem(
                    GrowthcraftApiaryFluids.WAX_WHITE_FLUID_STILL,
                    Reference.FluidColor.WAX_WHITE_COLOR.getColor()
            )
    );

    public static final RegistryObject<GrowthcraftBucketItem> BUCKET_WAX_YELLOW = ITEMS.register(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.WAX_YELLOW).get(FluidUtils.BUCKET),
            () -> new GrowthcraftBucketItem(
                    GrowthcraftApiaryFluids.WAX_YELLOW_FLUID_STILL,
                    Reference.FluidColor.WAX_YELLOW_COLOR.getColor()
            )
    );

    public static final RegistryObject<GrowthcraftItem> HONEY_COMB_EMPTY = ITEMS.register(
            Reference.UnlocalizedName.HONEY_COMB_EMPTY, GrowthcraftItem::new
    );

    public static final RegistryObject<GrowthcraftItem> HONEY_COMB_FULL = ITEMS.register(
            Reference.UnlocalizedName.HONEY_COMB_FULL, GrowthcraftItem::new
    );

    public static final List<RegistryObject<? extends Item>> BUCKETS = ImmutableList.of(
        BUCKET_HONEY, BUCKET_HONEY_MEAD, BUCKET_WAX_BLACK, BUCKET_WAX_BLUE, BUCKET_WAX_BROWN,
        BUCKET_WAX_CYAN, BUCKET_WAX_GRAY, BUCKET_WAX_GREEN, BUCKET_WAX_LIGHT_BLUE, BUCKET_WAX_LIGHT_GRAY,
        BUCKET_WAX_LIME, BUCKET_WAX_MAGENTA, BUCKET_WAX_ORANGE, BUCKET_WAX_PINK, BUCKET_WAX_PURPLE, BUCKET_WAX_RED,
        BUCKET_WAX_WHITE, BUCKET_WAX_YELLOW
    );

    private GrowthcraftApiaryItems() {
        /* Prevent default public constructor */
    }
}
