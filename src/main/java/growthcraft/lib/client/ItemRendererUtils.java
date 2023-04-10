package growthcraft.lib.client;

import growthcraft.core.Growthcraft;
import net.minecraft.client.color.item.ItemColor;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.registries.RegistryObject;

import java.util.Collection;

public class ItemRendererUtils {

    public static void registerItems(RegisterColorHandlersEvent.Item handler, ItemColor itemColor, Collection<RegistryObject<Item>> items) {
        if (items.isEmpty()) return;
        items.stream()
                .filter(RegistryObject::isPresent)
                .map(RegistryObject::get)
                .forEach(item -> handler.register(itemColor, item));
    }

    public static void registerItem(RegisterColorHandlersEvent.Item handler, ItemColor itemColor, Item item) {
        Growthcraft.LOGGER.error("Calling registerItemColor from " + handler.getListenerList().toString());
       handler.register(itemColor, item);
    }

}
