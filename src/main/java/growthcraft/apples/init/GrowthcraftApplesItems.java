package growthcraft.apples.init;

import com.google.common.collect.ImmutableList;
import growthcraft.apples.item.AppleSeedsItem;
import growthcraft.apples.shared.Reference;
import growthcraft.lib.item.GrowthcraftBucketItem;
import growthcraft.lib.utils.FluidUtils;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

public class GrowthcraftApplesItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(
            ForgeRegistries.ITEMS, Reference.MODID
    );

    public static final RegistryObject<AppleSeedsItem> APPLE_SEEDS = ITEMS.register(
            Reference.UnlocalizedName.APPLE_SEEDS,
            AppleSeedsItem::new
    );

    public static final RegistryObject<GrowthcraftBucketItem> BUCKET_APPLE_CIDER = ITEMS.register(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.APPLE_CIDER).get(FluidUtils.BUCKET),
            () -> new GrowthcraftBucketItem(
                    GrowthcraftApplesFluids.APPLE_CIDER_FLUID_STILL,
                    Reference.FluidColor.APPLE_CIDER_FLUID_COLOR.getColor()
            )
    );

    public static final RegistryObject<GrowthcraftBucketItem> BUCKET_APPLE_JUICE = ITEMS.register(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.APPLE_JUICE).get(FluidUtils.BUCKET),
            () -> new GrowthcraftBucketItem(
                    GrowthcraftApplesFluids.APPLE_JUICE_FLUID_STILL,
                    Reference.FluidColor.APPLE_JUICE_FLUID_COLOR.getColor()
            )
    );

    public static final List<RegistryObject<? extends Item>> BUCKETS = ImmutableList.of(
            BUCKET_APPLE_CIDER, BUCKET_APPLE_JUICE
    );

    public static void registerCompostables() {
        float f = 0.3F;
        float f1 = 0.5F;
        float f2 = 0.65F;
        float f3 = 0.85F;
        float f4 = 1.0F;

        ComposterBlock.COMPOSTABLES.put(GrowthcraftApplesItems.APPLE_SEEDS.get(), f1);
    }

    private GrowthcraftApplesItems() {
        /* Prevent default public constructor */
    }
}
