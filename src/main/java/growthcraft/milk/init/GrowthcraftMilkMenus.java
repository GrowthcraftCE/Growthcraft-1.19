package growthcraft.milk.init;

import growthcraft.milk.screen.container.ChurnMenu;
import growthcraft.milk.screen.container.MixingVatMenu;
import growthcraft.milk.screen.container.PancheonMenu;
import growthcraft.milk.shared.Reference;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class GrowthcraftMilkMenus {

    public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(
            ForgeRegistries.MENU_TYPES, Reference.MODID
    );

    public static final RegistryObject<MenuType<ChurnMenu>> CHURN_MENU =
            registerMenuType(Reference.UnlocalizedName.CHURN, ChurnMenu::new);

    public static final RegistryObject<MenuType<MixingVatMenu>> MIXING_VAT_MENU =
            registerMenuType(Reference.UnlocalizedName.MIXING_VAT, MixingVatMenu::new);

    public static final RegistryObject<MenuType<PancheonMenu>> PANCHEON_MENU =
            registerMenuType(Reference.UnlocalizedName.PANCHEON, PancheonMenu::new);

    private static <T extends AbstractContainerMenu> RegistryObject<MenuType<T>> registerMenuType(
            String name, IContainerFactory<T> factory) {
        return MENUS.register(name, () -> IForgeMenuType.create(factory));
    }

}
