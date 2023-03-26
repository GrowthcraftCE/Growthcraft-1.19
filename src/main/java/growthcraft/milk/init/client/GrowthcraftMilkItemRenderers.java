package growthcraft.milk.init.client;

import growthcraft.milk.init.GrowthcraftMilkFluids;
import growthcraft.milk.shared.Reference;
import net.minecraft.client.color.item.ItemColor;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.registries.RegistryObject;

import java.util.Collection;

public class GrowthcraftMilkItemRenderers {
    public static void registerItemRenders(RegisterColorHandlersEvent.Item event) {
        registerItem(event, Reference.FluidColor.MILK.toItemColor(), GrowthcraftMilkFluids.MILK.bucket.get());
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

    private GrowthcraftMilkItemRenderers() {
        /* Prevent automatic public constructor */
    }
}
