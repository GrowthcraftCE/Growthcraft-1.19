package growthcraft.bamboo.init;

import growthcraft.bamboo.shared.Reference;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class GrowthcraftBambooItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(
            ForgeRegistries.ITEMS, Reference.MODID
    );

    public static void registerCompostables() {
        float f = 0.3F;
        float f1 = 0.5F;
        float f2 = 0.65F;
        float f3 = 0.85F;
        float f4 = 1.0F;

        // ComposterBlock.COMPOSTABLES.put(GrowthcraftRiceItems.RICE.get(), f2);

    }

    private GrowthcraftBambooItems() {
        /* Prevent default public constructor */
    }

}
