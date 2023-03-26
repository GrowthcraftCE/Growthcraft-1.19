package growthcraft.apiary.init;

import growthcraft.apiary.shared.Reference;
import growthcraft.lib.item.GrowthcraftItem;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;

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


    public static final RegistryObject<GrowthcraftItem> HONEY_COMB_EMPTY = ITEMS.register(
            Reference.UnlocalizedName.HONEY_COMB_EMPTY, GrowthcraftItem::new
    );

    public static final RegistryObject<GrowthcraftItem> HONEY_COMB_FULL = ITEMS.register(
            Reference.UnlocalizedName.HONEY_COMB_FULL, GrowthcraftItem::new
    );

    private GrowthcraftApiaryItems() {
        /* Prevent default public constructor */
    }

    public static boolean excludeItemRegistry(ResourceLocation registryName) {
        ArrayList<String> excludeItems = new ArrayList<>();
        //excludeItems.add(Reference.MODID + ":" + Reference.UnlocalizedName.APPLE_TREE_FRUIT);
        return excludeItems.contains(registryName.toString());
    }
}
