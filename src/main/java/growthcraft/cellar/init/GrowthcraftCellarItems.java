package growthcraft.cellar.init;

import growthcraft.cellar.item.CellarPotionItem;
import growthcraft.cellar.item.GrapeSeedsItem;
import growthcraft.cellar.item.HopsSeedsItem;
import growthcraft.cellar.shared.Reference;
import growthcraft.lib.item.GrowthcraftFoodItem;
import growthcraft.lib.item.GrowthcraftItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

public class GrowthcraftCellarItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(
            ForgeRegistries.ITEMS, Reference.MODID
    );

    public static final RegistryObject<GrowthcraftItem> KINDLING = ITEMS.register(
            Reference.UnlocalizedName.KINDLING, GrowthcraftItem::new
    );

    public static final RegistryObject<GrowthcraftItem> BREW_KETTLE_LID = ITEMS.register(
            Reference.UnlocalizedName.BREW_KETTLE_LID, GrowthcraftItem::new
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
