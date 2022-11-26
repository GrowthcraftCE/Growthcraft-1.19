package growthcraft.apiary.init;

import growthcraft.apiary.screen.BeeBoxMenu;
import growthcraft.apiary.screen.BeeBoxScreen;
import growthcraft.apiary.shared.Reference;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class GrowthcraftApiaryMenus {

    public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(
            ForgeRegistries.MENU_TYPES, Reference.MODID
    );

    public static final RegistryObject<MenuType<BeeBoxMenu>> BEE_BOX_MENU =
            registerMenuType(Reference.UnlocalizedName.BEE_BOX, BeeBoxMenu::new);

    private static <T extends AbstractContainerMenu> RegistryObject<MenuType<T>> registerMenuType(
            String name, IContainerFactory<T> factory) {
        return MENUS.register(name, () -> IForgeMenuType.create(factory));
    }

    public static void registerMenus() {
        MenuScreens.register(BEE_BOX_MENU.get(), BeeBoxScreen::new);
    }

}
