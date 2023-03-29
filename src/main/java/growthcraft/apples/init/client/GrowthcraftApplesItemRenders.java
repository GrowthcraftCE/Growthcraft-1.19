package growthcraft.apples.init.client;

import growthcraft.apples.init.GrowthcraftApplesFluids;
import growthcraft.apples.shared.Reference;
import growthcraft.lib.client.GrowthcraftItemColor;
import net.minecraft.client.color.item.ItemColor;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.registries.RegistryObject;

import java.util.Collection;

public class GrowthcraftApplesItemRenders {
    public static void registerItemRenders(RegisterColorHandlersEvent.Item event) {
        GrowthcraftItemColor itemColor = new GrowthcraftItemColor();

        registerItem(event, Reference.FluidColor.APPLE_CIDER_FLUID_COLOR.toItemColor(), GrowthcraftApplesFluids.APPLE_CIDER_FLUID.bucket.get());
        registerItem(event, Reference.FluidColor.APPLE_JUICE_FLUID_COLOR.toItemColor(), GrowthcraftApplesFluids.APPLE_JUICE_FLUID.bucket.get());
    }

    private static void registerItems(RegisterColorHandlersEvent.Item handler, ItemColor itemColor, Collection<RegistryObject<Item>> items) {
        if (items.isEmpty()) return;
        items.stream()
                .filter(RegistryObject::isPresent)
                .map(RegistryObject::get)
                .forEach(item -> handler.register(itemColor, item));
    }

    private static void registerItem(RegisterColorHandlersEvent.Item handler, ItemColor itemColor, Item item) {
        handler.register(itemColor, item);
    }

}
