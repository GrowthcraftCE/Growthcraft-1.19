package growthcraft.cellar.init;

import growthcraft.cellar.screen.CultureJarMenu;
import growthcraft.cellar.screen.FermentationBarrelMenu;
import growthcraft.cellar.shared.Reference;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class GrowthcraftCellarMenus {

    public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(
            ForgeRegistries.MENU_TYPES, Reference.MODID
    );

    public static final RegistryObject<MenuType<CultureJarMenu>> CULTURE_JAR_MENU = registerMenuType(
            Reference.UnlocalizedName.CULTURE_JAR,
            CultureJarMenu::new
    );

    public static final RegistryObject<MenuType<FermentationBarrelMenu>> FERMENTATION_BARREL_MENU = registerMenuType(
            Reference.UnlocalizedName.FERMENT_BARREL,
            FermentationBarrelMenu::new
    );

    private static <T extends AbstractContainerMenu> RegistryObject<MenuType<T>> registerMenuType(
            String name, IContainerFactory<T> factory) {
        return MENUS.register(name, () -> IForgeMenuType.create(factory));
    }

}
