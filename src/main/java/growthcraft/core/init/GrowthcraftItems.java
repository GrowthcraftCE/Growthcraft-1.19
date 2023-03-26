package growthcraft.core.init;

import growthcraft.core.block.RopeBlock;
import growthcraft.core.item.CrowbarItem;
import growthcraft.core.item.RopeItem;
import growthcraft.core.shared.Reference;
import growthcraft.lib.item.GrowthcraftItem;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;

public class GrowthcraftItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Reference.MODID);

    public static final RegistryObject<CrowbarItem> CROWBAR_BLACK = ITEMS.register(
            Reference.UnlocalizedName.CROWBAR_BLACK, CrowbarItem::new
    );

    public static final RegistryObject<CrowbarItem> CROWBAR_BLUE = ITEMS.register(
            Reference.UnlocalizedName.CROWBAR_BLUE, CrowbarItem::new
    );

    public static final RegistryObject<CrowbarItem> CROWBAR_BROWN = ITEMS.register(
            Reference.UnlocalizedName.CROWBAR_BROWN, CrowbarItem::new
    );

    public static final RegistryObject<CrowbarItem> CROWBAR_CYAN = ITEMS.register(
            Reference.UnlocalizedName.CROWBAR_CYAN, CrowbarItem::new
    );

    public static final RegistryObject<CrowbarItem> CROWBAR_GRAY = ITEMS.register(
            Reference.UnlocalizedName.CROWBAR_GRAY, CrowbarItem::new
    );

    public static final RegistryObject<CrowbarItem> CROWBAR_GREEN = ITEMS.register(
            Reference.UnlocalizedName.CROWBAR_GREEN, CrowbarItem::new
    );

    public static final RegistryObject<CrowbarItem> CROWBAR_LIGHT_BLUE = ITEMS.register(
            Reference.UnlocalizedName.CROWBAR_LIGHT_BLUE, CrowbarItem::new
    );

    public static final RegistryObject<CrowbarItem> CROWBAR_LIGHT_GRAY = ITEMS.register(
            Reference.UnlocalizedName.CROWBAR_LIGHT_GRAY, CrowbarItem::new
    );

    public static final RegistryObject<CrowbarItem> CROWBAR_LIME = ITEMS.register(
            Reference.UnlocalizedName.CROWBAR_LIME, CrowbarItem::new
    );

    public static final RegistryObject<CrowbarItem> CROWBAR_MAGENTA = ITEMS.register(
            Reference.UnlocalizedName.CROWBAR_MAGENTA, CrowbarItem::new
    );

    public static final RegistryObject<CrowbarItem> CROWBAR_ORANGE = ITEMS.register(
            Reference.UnlocalizedName.CROWBAR_ORANGE, CrowbarItem::new
    );

    public static final RegistryObject<CrowbarItem> CROWBAR_PINK = ITEMS.register(
            Reference.UnlocalizedName.CROWBAR_PINK, CrowbarItem::new
    );

    public static final RegistryObject<CrowbarItem> CROWBAR_PURPLE = ITEMS.register(
            Reference.UnlocalizedName.CROWBAR_PURPLE, CrowbarItem::new
    );

    public static final RegistryObject<CrowbarItem> CROWBAR_RED = ITEMS.register(
            Reference.UnlocalizedName.CROWBAR_RED, CrowbarItem::new
    );

    public static final RegistryObject<CrowbarItem> CROWBAR_WHITE = ITEMS.register(
            Reference.UnlocalizedName.CROWBAR_WHITE, CrowbarItem::new
    );

    public static final RegistryObject<CrowbarItem> CROWBAR_YELLOW = ITEMS.register(
            Reference.UnlocalizedName.CROWBAR_YELLOW, CrowbarItem::new
    );

    public static final RegistryObject<RopeItem> ROPE_LINEN = ITEMS.register(
            Reference.UnlocalizedName.ROPE_LINEN, () -> new RopeItem((RopeBlock) GrowthcraftBlocks.ROPE_LINEN.get())
    );

    public static final RegistryObject<GrowthcraftItem> SALT = ITEMS.register(
            Reference.UnlocalizedName.SALT_ITEM, GrowthcraftItem::new
    );

    public static boolean excludeItemRegistry(ResourceLocation registryName) {
        ArrayList<String> excludeItems = new ArrayList<>();
        //excludeBlocks.add(Reference.MODID + ":" + Reference.UnlocalizedName.APPLE_TREE_FRUIT);
        return excludeItems.contains(registryName.toString());
    }

}
