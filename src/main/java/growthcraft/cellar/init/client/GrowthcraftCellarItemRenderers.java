package growthcraft.cellar.init.client;

import growthcraft.cellar.init.GrowthcraftCellarFluids;
import growthcraft.cellar.init.GrowthcraftCellarItems;
import growthcraft.cellar.shared.Reference;
import growthcraft.lib.client.GrowthcraftItemColor;
import net.minecraft.client.color.item.ItemColor;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.registries.RegistryObject;

import java.util.Collection;

public class GrowthcraftCellarItemRenderers {

    public static void registerItemRenders(RegisterColorHandlersEvent.Item event) {
        GrowthcraftItemColor itemColor = new GrowthcraftItemColor();

        for (RegistryObject<? extends Item> grain : GrowthcraftCellarItems.GRAINS) {
            event.getItemColors().register(itemColor, grain::get);
        }

        for (RegistryObject<? extends Item> potion : GrowthcraftCellarItems.POTIONS) {
            event.getItemColors().register(itemColor, potion::get);
        }

        registerItem(event, Reference.FluidColor.AMBER_ALE.toItemColor(), GrowthcraftCellarFluids.AMBER_ALE.bucket.get());
        registerItem(event, Reference.FluidColor.AMBER_LAGER.toItemColor(), GrowthcraftCellarFluids.AMBER_LAGER.bucket.get());
        registerItem(event, Reference.FluidColor.AMBER_WORT.toItemColor(), GrowthcraftCellarFluids.AMBER_WORT.bucket.get());
        registerItem(event, Reference.FluidColor.BROWN_ALE.toItemColor(), GrowthcraftCellarFluids.BROWN_ALE.bucket.get());
        registerItem(event, Reference.FluidColor.BROWN_LAGER.toItemColor(), GrowthcraftCellarFluids.BROWN_LAGER.bucket.get());
        registerItem(event, Reference.FluidColor.BROWN_WORT.toItemColor(), GrowthcraftCellarFluids.BROWN_WORT.bucket.get());
        registerItem(event, Reference.FluidColor.COPPER_ALE.toItemColor(), GrowthcraftCellarFluids.COPPER_ALE.bucket.get());
        registerItem(event, Reference.FluidColor.COPPER_LAGER.toItemColor(), GrowthcraftCellarFluids.COPPER_LAGER.bucket.get());
        registerItem(event, Reference.FluidColor.COPPER_WORT.toItemColor(), GrowthcraftCellarFluids.COPPER_WORT.bucket.get());
        registerItem(event, Reference.FluidColor.DARK_LAGER.toItemColor(), GrowthcraftCellarFluids.DARK_LAGER.bucket.get());
        registerItem(event, Reference.FluidColor.DARK_WORT.toItemColor(), GrowthcraftCellarFluids.DARK_WORT.bucket.get());
        registerItem(event, Reference.FluidColor.DEEP_AMBER_WORT.toItemColor(), GrowthcraftCellarFluids.DEEP_AMBER_WORT.bucket.get());
        registerItem(event, Reference.FluidColor.GOLDEN_WORT.toItemColor(), GrowthcraftCellarFluids.GOLDEN_WORT.bucket.get());
        registerItem(event, Reference.FluidColor.HOPPED_GOLDEN_WORT.toItemColor(), GrowthcraftCellarFluids.HOPPED_GOLDEN_WORT.bucket.get());
        registerItem(event, Reference.FluidColor.IPA_ALE.toItemColor(), GrowthcraftCellarFluids.IPA_ALE.bucket.get());
        registerItem(event, Reference.FluidColor.OLD_PORT_ALE.toItemColor(), GrowthcraftCellarFluids.OLD_PORT_ALE.bucket.get());
        registerItem(event, Reference.FluidColor.PALE_ALE.toItemColor(), GrowthcraftCellarFluids.PALE_ALE.bucket.get());
        registerItem(event, Reference.FluidColor.PALE_GOLDEN_WORT.toItemColor(), GrowthcraftCellarFluids.PALE_GOLDEN_WORT.bucket.get());
        registerItem(event, Reference.FluidColor.PALE_LAGER.toItemColor(), GrowthcraftCellarFluids.PALE_LAGER.bucket.get());
        registerItem(event, Reference.FluidColor.PILSNER_LAGER.toItemColor(), GrowthcraftCellarFluids.PILSNER_LAGER.bucket.get());
        registerItem(event, Reference.FluidColor.PURPLE_GRAPE_JUICE.toItemColor(), GrowthcraftCellarFluids.PURPLE_GRAPE_JUICE.bucket.get());
        registerItem(event, Reference.FluidColor.PURPLE_GRAPE_WINE.toItemColor(), GrowthcraftCellarFluids.PURPLE_GRAPE_WINE.bucket.get());
        registerItem(event, Reference.FluidColor.RED_GRAPE_JUICE.toItemColor(), GrowthcraftCellarFluids.RED_GRAPE_JUICE.bucket.get());
        registerItem(event, Reference.FluidColor.RED_GRAPE_WINE.toItemColor(), GrowthcraftCellarFluids.RED_GRAPE_WINE.bucket.get());
        registerItem(event, Reference.FluidColor.STOUT_ALE.toItemColor(), GrowthcraftCellarFluids.STOUT_ALE.bucket.get());
        registerItem(event, Reference.FluidColor.VIENNA_LAGER.toItemColor(), GrowthcraftCellarFluids.VIENNA_LAGER.bucket.get());
        registerItem(event, Reference.FluidColor.WHITE_GRAPE_JUICE.toItemColor(), GrowthcraftCellarFluids.WHITE_GRAPE_JUICE.bucket.get());
        registerItem(event, Reference.FluidColor.WHITE_GRAPE_WINE.toItemColor(), GrowthcraftCellarFluids.WHITE_GRAPE_WINE.bucket.get());
        registerItem(event, Reference.FluidColor.WORT.toItemColor(), GrowthcraftCellarFluids.WORT.bucket.get());
    }

    private GrowthcraftCellarItemRenderers() {
        /* Prevent automatic public constructor */
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
