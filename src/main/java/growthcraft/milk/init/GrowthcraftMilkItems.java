package growthcraft.milk.init;

import growthcraft.lib.item.GrowthcraftBowlFoodItem;
import growthcraft.lib.item.GrowthcraftFoodItem;
import growthcraft.lib.item.GrowthcraftItem;
import growthcraft.lib.utils.CheeseUtils;
import growthcraft.milk.item.CheeseCurdsDrainedItem;
import growthcraft.milk.item.MilkingBucketItem;
import growthcraft.milk.item.ThistleSeedItem;
import growthcraft.milk.shared.Reference;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;

public class GrowthcraftMilkItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(
            ForgeRegistries.ITEMS, Reference.MODID
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

    public static final RegistryObject<GrowthcraftFoodItem> CHEESE_ASIAGO_SLICE = ITEMS.register(
            CheeseUtils.getCheeseNames(Reference.UnlocalizedName.ASIAGO).get(CheeseUtils.SLICE),
            () -> new GrowthcraftFoodItem(2, 0.3F, 64)
    );

    public static final RegistryObject<GrowthcraftFoodItem> CHEESE_CASU_MARZU_SLICE = ITEMS.register(
            CheeseUtils.getCheeseNames(Reference.UnlocalizedName.CASU_MARZU).get(CheeseUtils.SLICE),
            () -> new GrowthcraftFoodItem(2, 0.3F, 64)
    );

    public static final RegistryObject<GrowthcraftFoodItem> CHEESE_CHEDDAR_SLICE = ITEMS.register(
            CheeseUtils.getCheeseNames(Reference.UnlocalizedName.CHEDDAR).get(CheeseUtils.SLICE),
            () -> new GrowthcraftFoodItem(2, 0.3F, 64)
    );

    public static final RegistryObject<GrowthcraftItem> CHEESE_CLOTH = ITEMS.register(
            Reference.UnlocalizedName.CHEESE_CLOTH, GrowthcraftItem::new
    );

    public static final RegistryObject<GrowthcraftFoodItem> CHEESE_EMMENTALER_SLICE = ITEMS.register(
            CheeseUtils.getCheeseNames(Reference.UnlocalizedName.EMMENTALER).get(CheeseUtils.SLICE),
            () -> new GrowthcraftFoodItem(2, 0.3F, 64)
    );

    public static final RegistryObject<GrowthcraftFoodItem> CHEESE_GORGONZOLA_SLICE = ITEMS.register(
            CheeseUtils.getCheeseNames(Reference.UnlocalizedName.GORGONZOLA).get(CheeseUtils.SLICE),
            () -> new GrowthcraftFoodItem(2, 0.3F, 64)
    );

    public static final RegistryObject<GrowthcraftFoodItem> CHEESE_GOUDA_SLICE = ITEMS.register(
            CheeseUtils.getCheeseNames(Reference.UnlocalizedName.GOUDA).get(CheeseUtils.SLICE),
            () -> new GrowthcraftFoodItem(2, 0.3F, 64)
    );

    public static final RegistryObject<GrowthcraftFoodItem> CHEESE_MONTEREY_SLICE = ITEMS.register(
            CheeseUtils.getCheeseNames(Reference.UnlocalizedName.MONTEREY).get(CheeseUtils.SLICE),
            () -> new GrowthcraftFoodItem(2, 0.3F, 64)
    );

    public static final RegistryObject<GrowthcraftFoodItem> CHEESE_PARMESAN_SLICE = ITEMS.register(
            CheeseUtils.getCheeseNames(Reference.UnlocalizedName.PARMESAN).get(CheeseUtils.SLICE),
            () -> new GrowthcraftFoodItem(2, 0.3F, 64)
    );

    public static final RegistryObject<GrowthcraftFoodItem> CHEESE_PROVOLONE_SLICE = ITEMS.register(
            CheeseUtils.getCheeseNames(Reference.UnlocalizedName.PROVOLONE).get(CheeseUtils.SLICE),
            () -> new GrowthcraftFoodItem(2, 0.3F, 64)
    );

    public static final RegistryObject<GrowthcraftFoodItem> CHEESE_RICOTTA_SLICE = ITEMS.register(
            CheeseUtils.getCheeseNames(Reference.UnlocalizedName.RICOTTA).get(CheeseUtils.SLICE),
            () -> new GrowthcraftFoodItem(2, 0.4F, 16)
    );

    public static final RegistryObject<GrowthcraftBowlFoodItem> ICE_CREAM_APPLE = ITEMS.register(
            Reference.UnlocalizedName.ICE_CREAM_APPLE, () -> new GrowthcraftBowlFoodItem(2, 0.3F, 8)
    );

    public static final RegistryObject<GrowthcraftBowlFoodItem> ICE_CREAM_CHOCOLATE  = ITEMS.register(
            Reference.UnlocalizedName.ICE_CREAM_CHOCOLATE , () -> new GrowthcraftBowlFoodItem(2, 0.3F, 8)
    );

    public static final RegistryObject<GrowthcraftBowlFoodItem> ICE_CREAM_GRAPE_PURPLE   = ITEMS.register(
            Reference.UnlocalizedName.ICE_CREAM_GRAPE_PURPLE  , () -> new GrowthcraftBowlFoodItem(2, 0.3F, 8)
    );

    public static final RegistryObject<GrowthcraftBowlFoodItem> ICE_CREAM_GRAPE_RED   = ITEMS.register(
            Reference.UnlocalizedName.ICE_CREAM_GRAPE_RED  , () -> new GrowthcraftBowlFoodItem(2, 0.3F, 8)
    );

    public static final RegistryObject<GrowthcraftBowlFoodItem> ICE_CREAM_GRAPE_WHITE   = ITEMS.register(
            Reference.UnlocalizedName.ICE_CREAM_GRAPE_WHITE  , () -> new GrowthcraftBowlFoodItem(2, 0.3F, 8)
    );

    public static final RegistryObject<GrowthcraftBowlFoodItem> ICE_CREAM_PUMPKIN   = ITEMS.register(
            Reference.UnlocalizedName.ICE_CREAM_PUMPKIN  , () -> new GrowthcraftBowlFoodItem(2, 0.3F, 8)
    );

    public static final RegistryObject<GrowthcraftBowlFoodItem> ICE_CREAM_WATERMELON   = ITEMS.register(
            Reference.UnlocalizedName.ICE_CREAM_WATERMELON  , () -> new GrowthcraftBowlFoodItem(2, 0.3F, 8)
    );

    public static final RegistryObject<GrowthcraftBowlFoodItem> ICE_CREAM_HONEY   = ITEMS.register(
            Reference.UnlocalizedName.ICE_CREAM_HONEY  , () -> new GrowthcraftBowlFoodItem(2, 0.3F, 8)
    );

    public static final RegistryObject<MilkingBucketItem> MILKING_BUCKET_IRON = ITEMS.register(
            Reference.UnlocalizedName.MILKING_BUCKET_IRON,
            () -> new MilkingBucketItem(Fluids.EMPTY)
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

    public static final RegistryObject<GrowthcraftItem> THISTLE_SEED = ITEMS.register(
            Reference.UnlocalizedName.THISTLE_SEED,
            ThistleSeedItem::new
    );

    public static final RegistryObject<GrowthcraftBowlFoodItem> YOGURT_APPLE = ITEMS.register(
            Reference.UnlocalizedName.YOGURT_APPLE, () -> new GrowthcraftBowlFoodItem(2, 0.3F, 8)
    );

    public static final RegistryObject<GrowthcraftBowlFoodItem> YOGURT_CHOCOLATE = ITEMS.register(
            Reference.UnlocalizedName.YOGURT_CHOCOLATE, () -> new GrowthcraftBowlFoodItem(2, 0.3F, 8)
    );

    public static final RegistryObject<GrowthcraftBowlFoodItem> YOGURT_GRAPE_PURPLE = ITEMS.register(
            Reference.UnlocalizedName.YOGURT_GRAPE_PURPLE, () -> new GrowthcraftBowlFoodItem(2, 0.3F, 8)
    );

    public static final RegistryObject<GrowthcraftBowlFoodItem> YOGURT_GRAPE_RED = ITEMS.register(
            Reference.UnlocalizedName.YOGURT_GRAPE_RED, () -> new GrowthcraftBowlFoodItem(2, 0.3F, 8)
    );

    public static final RegistryObject<GrowthcraftBowlFoodItem> YOGURT_GRAPE_WHITE = ITEMS.register(
            Reference.UnlocalizedName.YOGURT_GRAPE_WHITE, () -> new GrowthcraftBowlFoodItem(2, 0.3F, 8)
    );

    public static final RegistryObject<GrowthcraftBowlFoodItem> YOGURT_HONEY = ITEMS.register(
            Reference.UnlocalizedName.YOGURT_HONEY, () -> new GrowthcraftBowlFoodItem(2, 0.3F, 8)
    );

    public static final RegistryObject<GrowthcraftBowlFoodItem> YOGURT_PLAIN = ITEMS.register(
            Reference.UnlocalizedName.YOGURT_PLAIN, () -> new GrowthcraftBowlFoodItem(2, 0.3F, 8)
    );

    public static final RegistryObject<GrowthcraftBowlFoodItem> YOGURT_PUMPKIN = ITEMS.register(
            Reference.UnlocalizedName.YOGURT_PUMPKIN, () -> new GrowthcraftBowlFoodItem(2, 0.3F, 8)
    );

    public static final RegistryObject<GrowthcraftBowlFoodItem> YOGURT_WATERMELON = ITEMS.register(
            Reference.UnlocalizedName.YOGURT_WATERMELON, () -> new GrowthcraftBowlFoodItem(2, 0.3F, 8)
    );

    public static final RegistryObject<CheeseCurdsDrainedItem> APPENZELLER_CHEESE_CURDS_DRAINED = ITEMS.register(
            CheeseUtils.getCheeseNames(Reference.UnlocalizedName.APPENZELLER).get(CheeseUtils.DRAINED),
            () -> new CheeseCurdsDrainedItem(Reference.ItemColor.APPENZELLER_CHEESE.getColor())
    );

    public static final RegistryObject<CheeseCurdsDrainedItem> ASIAGO_CHEESE_CURDS_DRAINED = ITEMS.register(
            CheeseUtils.getCheeseNames(Reference.UnlocalizedName.ASIAGO).get(CheeseUtils.DRAINED),
            () -> new CheeseCurdsDrainedItem(Reference.ItemColor.ASIAGO_CHEESE.getColor())
    );

    public static final RegistryObject<CheeseCurdsDrainedItem> CASU_MARZU_CHEESE_CURDS_DRAINED = ITEMS.register(
            CheeseUtils.getCheeseNames(Reference.UnlocalizedName.CASU_MARZU).get(CheeseUtils.DRAINED),
            () -> new CheeseCurdsDrainedItem(Reference.ItemColor.CASU_MARZU_CHEESE.getColor())
    );

    public static final RegistryObject<CheeseCurdsDrainedItem> CHEDDAR_CHEESE_CURDS_DRAINED = ITEMS.register(
            CheeseUtils.getCheeseNames(Reference.UnlocalizedName.CHEDDAR).get(CheeseUtils.DRAINED),
            () -> new CheeseCurdsDrainedItem(Reference.ItemColor.CHEDDAR_CHEESE.getColor())
    );

    public static final RegistryObject<CheeseCurdsDrainedItem> EMMENTALER_CHEESE_CURDS_DRAINED = ITEMS.register(
            CheeseUtils.getCheeseNames(Reference.UnlocalizedName.EMMENTALER).get(CheeseUtils.DRAINED),
            () -> new CheeseCurdsDrainedItem(Reference.ItemColor.EMMENTALER_CHEESE.getColor())
    );

    public static final RegistryObject<CheeseCurdsDrainedItem> GORGONZOLA_CHEESE_CURDS_DRAINED = ITEMS.register(
            CheeseUtils.getCheeseNames(Reference.UnlocalizedName.GORGONZOLA).get(CheeseUtils.DRAINED),
            () -> new CheeseCurdsDrainedItem(Reference.ItemColor.GORGONZOLA_CHEESE.getColor())
    );

    public static final RegistryObject<CheeseCurdsDrainedItem> GOUDA_CHEESE_CURDS_DRAINED = ITEMS.register(
            CheeseUtils.getCheeseNames(Reference.UnlocalizedName.GOUDA).get(CheeseUtils.DRAINED),
            () -> new CheeseCurdsDrainedItem(Reference.ItemColor.GOUDA_CHEESE.getColor())
    );

    public static final RegistryObject<CheeseCurdsDrainedItem> MONTEREY_CHEESE_CURDS_DRAINED = ITEMS.register(
            CheeseUtils.getCheeseNames(Reference.UnlocalizedName.MONTEREY).get(CheeseUtils.DRAINED),
            () -> new CheeseCurdsDrainedItem(Reference.ItemColor.MONTEREY_CHEESE.getColor())
    );

    public static final RegistryObject<CheeseCurdsDrainedItem> PARMESAN_CHEESE_CURDS_DRAINED = ITEMS.register(
            CheeseUtils.getCheeseNames(Reference.UnlocalizedName.PARMESAN).get(CheeseUtils.DRAINED),
            () -> new CheeseCurdsDrainedItem(Reference.ItemColor.PARMESAN_CHEESE.getColor())
    );

    public static final RegistryObject<CheeseCurdsDrainedItem> PROVOLONE_CHEESE_CURDS_DRAINED = ITEMS.register(
            CheeseUtils.getCheeseNames(Reference.UnlocalizedName.PROVOLONE).get(CheeseUtils.DRAINED),
            () -> new CheeseCurdsDrainedItem(Reference.ItemColor.PROVOLONE_CHEESE.getColor())
    );

    public static final RegistryObject<CheeseCurdsDrainedItem> RICOTTA_CHEESE_CURDS_DRAINED = ITEMS.register(
            CheeseUtils.getCheeseNames(Reference.UnlocalizedName.RICOTTA).get(CheeseUtils.DRAINED),
            () -> new CheeseCurdsDrainedItem(Reference.ItemColor.RICOTTA_CHEESE.getColor())
    );











    public static void registerCompostables() {
        float f = 0.3F;
        float f1 = 0.5F;
        float f2 = 0.65F;
        float f3 = 0.85F;
        float f4 = 1.0F;

        // ComposterBlock.COMPOSTABLES.put(GrowthcraftRiceItems.RICE.get(), f2);

    }

    public static boolean excludeItemRegistry(ResourceLocation registryName) {
        ArrayList<String> excludeItems = new ArrayList<>();
        //excludeItems.add(Reference.MODID + ":" + Reference.UnlocalizedName.APPLE_TREE_FRUIT);
        return excludeItems.contains(registryName.toString());
    }

    private GrowthcraftMilkItems() {
        /* Prevent default public constructor */
    }
}
