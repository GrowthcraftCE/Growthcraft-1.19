package growthcraft.core.init;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import static growthcraft.core.shared.Reference.MODID;

public class GrowthcraftCreativeModeTabs {

    public static CreativeModeTab GROWTHCRAFT_CREATIVE_TAB = new CreativeModeTab(MODID) {
        @Override
        public @NotNull ItemStack makeIcon() {
            return GrowthcraftItems.CROWBAR_ORANGE.get().getDefaultInstance();
        }
    };

    private GrowthcraftCreativeModeTabs() {
        /* Prevent generation of default public constructor. */
    }
}
