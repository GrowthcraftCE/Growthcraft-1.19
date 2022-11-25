package growthcraft.rice.init;

import com.google.common.collect.ImmutableList;
import growthcraft.lib.item.GrowthcraftBucketItem;
import growthcraft.lib.item.GrowthcraftFoodItem;
import growthcraft.lib.item.GrowthcraftItem;
import growthcraft.lib.utils.FluidUtils;
import growthcraft.rice.item.CultivatorItem;
import growthcraft.rice.item.RiceSeedItem;
import growthcraft.rice.shared.Reference;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

public class GrowthcraftRiceItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(
            ForgeRegistries.ITEMS, Reference.MODID
    );

    public static final RegistryObject<GrowthcraftBucketItem> BUCKET_RICE_WATER = ITEMS.register(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.RICE_WATER).get(FluidUtils.BUCKET),
            () -> new GrowthcraftBucketItem(
                    GrowthcraftRiceFluids.RICE_WATER_FLUID_STILL,
                    Reference.FluidColor.RICE_WATER_FLUID_COLOR.getColor()
            )
    );

    public static final RegistryObject<GrowthcraftBucketItem> BUCKET_RICE_WINE = ITEMS.register(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.RICE_WINE).get(FluidUtils.BUCKET),
            () -> new GrowthcraftBucketItem(
                    GrowthcraftRiceFluids.RICE_WINE_FLUID_STILL,
                    Reference.FluidColor.RICE_WINE_FLUID_COLOR.getColor()
            )
    );

    public static final RegistryObject<GrowthcraftBucketItem> BUCKET_SAKE = ITEMS.register(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.SAKE).get(FluidUtils.BUCKET),
            () -> new GrowthcraftBucketItem(
                    GrowthcraftRiceFluids.SAKE_FLUID_STILL,
                    Reference.FluidColor.SAKE_FLUID_COLOR.getColor()
            )
    );

    public static final RegistryObject<CultivatorItem> CULTIVATOR = ITEMS.register(
      Reference.UnlocalizedName.CULTIVATOR,
      CultivatorItem::new
    );

    public static final RegistryObject<GrowthcraftItem> KNIFE = ITEMS.register(
            Reference.UnlocalizedName.KNIFE,
            GrowthcraftItem::new
    );

    public static final RegistryObject<RiceSeedItem> RICE = ITEMS.register(
            Reference.UnlocalizedName.RICE,
            RiceSeedItem::new
    );

    public static final RegistryObject<GrowthcraftFoodItem> RICE_COOKED = ITEMS.register(
            Reference.UnlocalizedName.RICE_COOKED,
            GrowthcraftFoodItem::new
    );

    public static final RegistryObject<GrowthcraftItem> RICE_STALK = ITEMS.register(
            Reference.UnlocalizedName.RICE_STALK,
            GrowthcraftItem::new
    );

    public static final RegistryObject<GrowthcraftFoodItem> SUSHI_ROLL = ITEMS.register(
            Reference.UnlocalizedName.SUSHI_ROLL,
            GrowthcraftFoodItem::new
    );

    public static final List<RegistryObject<? extends Item>> BUCKETS = ImmutableList.of(
        BUCKET_RICE_WATER, BUCKET_RICE_WINE, BUCKET_SAKE
    );

    public static void registerCompostables() {
        float f = 0.3F;
        float f1 = 0.5F;
        float f2 = 0.65F;
        float f3 = 0.85F;
        float f4 = 1.0F;

        // Add rice as a compostable
        ComposterBlock.COMPOSTABLES.put(GrowthcraftRiceItems.RICE.get(), f2);
        ComposterBlock.COMPOSTABLES.put(GrowthcraftRiceItems.RICE_COOKED.get(), f3);
        ComposterBlock.COMPOSTABLES.put(GrowthcraftRiceItems.RICE_STALK.get(), f1);
        ComposterBlock.COMPOSTABLES.put(GrowthcraftRiceItems.SUSHI_ROLL.get(), f4);

    }

    private GrowthcraftRiceItems() {
        /* Prevent default public constructor */
    }
}
