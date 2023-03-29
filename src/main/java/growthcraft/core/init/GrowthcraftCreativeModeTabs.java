package growthcraft.core.init;

import growthcraft.core.shared.Reference;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.CreativeModeTabEvent;

public class GrowthcraftCreativeModeTabs {

    public static CreativeModeTab GROWTHCRAFT_CREATIVE_TAB;

    private GrowthcraftCreativeModeTabs() {
        /* Prevent generation of default public constructor. */
    }

    public static void registerCreativeModeTab(CreativeModeTabEvent.Register event) {
        GROWTHCRAFT_CREATIVE_TAB = event.registerCreativeModeTab(new ResourceLocation(Reference.MODID, "tab"),
                builder -> {
                    builder
                            .icon(() -> new ItemStack(GrowthcraftItems.CROWBAR_ORANGE.get()))
                            .title(Component.translatable("item_group." + Reference.MODID + ".tab"));
                }
        );
    }
}
