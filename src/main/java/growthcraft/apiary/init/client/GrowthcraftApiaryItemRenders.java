package growthcraft.apiary.init.client;

import growthcraft.apiary.init.GrowthcraftApiaryFluids;
import growthcraft.apiary.shared.Reference;
import net.minecraft.client.color.item.ItemColor;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.registries.RegistryObject;

import java.util.Collection;

public class GrowthcraftApiaryItemRenders {
    public static void registerItemRenders(RegisterColorHandlersEvent.Item event) {
        registerItem(event, Reference.FluidColor.HONEY.toItemColor(), GrowthcraftApiaryFluids.HONEY.bucket.get());
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
