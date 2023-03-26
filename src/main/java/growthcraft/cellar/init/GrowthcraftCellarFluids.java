package growthcraft.cellar.init;

import growthcraft.cellar.lib.turtywurty.registry.FluidRegistryContainer;
import growthcraft.cellar.shared.Reference;
import growthcraft.lib.utils.FluidUtils;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static growthcraft.core.shared.Reference.CREATIVE_TAB;

public class GrowthcraftCellarFluids {
    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(
            ForgeRegistries.FLUIDS, Reference.MODID
    );

    public static final DeferredRegister<FluidType> FLUID_TYPES = DeferredRegister.create(
            ForgeRegistries.Keys.FLUID_TYPES, growthcraft.apples.shared.Reference.MODID
    );

    public static final FluidRegistryContainer AMBER_ALE = new FluidRegistryContainer(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.AMBER_ALE).get(FluidUtils.STILL),
            FluidType.Properties.create().canSwim(true).canDrown(true).canPushEntity(true).supportsBoating(true),
            () -> FluidRegistryContainer.createExtension(
                    new FluidRegistryContainer.ClientExtensions(
                            Reference.MODID,
                            FluidUtils.getFluidNames(Reference.UnlocalizedName.AMBER_ALE).get(FluidUtils.STILL)
                    ).tint(Reference.FluidColor.AMBER_ALE.toIntValue())
                            .fogColor(
                                    Reference.FluidColor.AMBER_ALE.getColor().getRed(),
                                    Reference.FluidColor.AMBER_ALE.getColor().getGreen(),
                                    Reference.FluidColor.AMBER_ALE.getColor().getBlue()
                            )
            ),
            BlockBehaviour.Properties.copy(Blocks.WATER),
            new Item.Properties().tab(CREATIVE_TAB).stacksTo(1)
    );

    public static final FluidRegistryContainer AMBER_LAGER = new FluidRegistryContainer(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.AMBER_LAGER).get(FluidUtils.STILL),
            FluidType.Properties.create().canSwim(true).canDrown(true).canPushEntity(true).supportsBoating(true),
            () -> FluidRegistryContainer.createExtension(
                    new FluidRegistryContainer.ClientExtensions(
                            Reference.MODID,
                            FluidUtils.getFluidNames(Reference.UnlocalizedName.AMBER_LAGER).get(FluidUtils.STILL)
                    ).tint(Reference.FluidColor.AMBER_LAGER.toIntValue())
                            .fogColor(
                                    Reference.FluidColor.AMBER_LAGER.getColor().getRed(),
                                    Reference.FluidColor.AMBER_LAGER.getColor().getGreen(),
                                    Reference.FluidColor.AMBER_LAGER.getColor().getBlue()
                            )
            ),
            BlockBehaviour.Properties.copy(Blocks.WATER),
            new Item.Properties().tab(CREATIVE_TAB).stacksTo(1)
    );

    public static final FluidRegistryContainer AMBER_WORT = new FluidRegistryContainer(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.AMBER_WORT).get(FluidUtils.STILL),
            FluidType.Properties.create().canSwim(true).canDrown(true).canPushEntity(true).supportsBoating(true),
            () -> FluidRegistryContainer.createExtension(
                    new FluidRegistryContainer.ClientExtensions(
                            Reference.MODID,
                            FluidUtils.getFluidNames(Reference.UnlocalizedName.AMBER_WORT).get(FluidUtils.STILL)
                    ).tint(Reference.FluidColor.AMBER_WORT.toIntValue())
                            .fogColor(
                                    Reference.FluidColor.AMBER_WORT.getColor().getRed(),
                                    Reference.FluidColor.AMBER_WORT.getColor().getGreen(),
                                    Reference.FluidColor.AMBER_WORT.getColor().getBlue()
                            )
            ),
            BlockBehaviour.Properties.copy(Blocks.WATER),
            new Item.Properties().tab(CREATIVE_TAB).stacksTo(1)
    );

    public static final FluidRegistryContainer BROWN_ALE = new FluidRegistryContainer(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.BROWN_ALE).get(FluidUtils.STILL),
            FluidType.Properties.create().canSwim(true).canDrown(true).canPushEntity(true).supportsBoating(true),
            () -> FluidRegistryContainer.createExtension(
                    new FluidRegistryContainer.ClientExtensions(
                            Reference.MODID,
                            FluidUtils.getFluidNames(Reference.UnlocalizedName.BROWN_ALE).get(FluidUtils.STILL)
                    ).tint(Reference.FluidColor.BROWN_ALE.toIntValue())
                            .fogColor(
                                    Reference.FluidColor.BROWN_ALE.getColor().getRed(),
                                    Reference.FluidColor.BROWN_ALE.getColor().getGreen(),
                                    Reference.FluidColor.BROWN_ALE.getColor().getBlue()
                            )
            ),
            BlockBehaviour.Properties.copy(Blocks.WATER),
            new Item.Properties().tab(CREATIVE_TAB).stacksTo(1)
    );

    public static final FluidRegistryContainer BROWN_LAGER = new FluidRegistryContainer(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.BROWN_LAGER).get(FluidUtils.STILL),
            FluidType.Properties.create().canSwim(true).canDrown(true).canPushEntity(true).supportsBoating(true),
            () -> FluidRegistryContainer.createExtension(
                    new FluidRegistryContainer.ClientExtensions(
                            Reference.MODID,
                            FluidUtils.getFluidNames(Reference.UnlocalizedName.BROWN_LAGER).get(FluidUtils.STILL)
                    ).tint(Reference.FluidColor.BROWN_LAGER.toIntValue())
                            .fogColor(
                                    Reference.FluidColor.BROWN_LAGER.getColor().getRed(),
                                    Reference.FluidColor.BROWN_LAGER.getColor().getGreen(),
                                    Reference.FluidColor.BROWN_LAGER.getColor().getBlue()
                            )
            ),
            BlockBehaviour.Properties.copy(Blocks.WATER),
            new Item.Properties().tab(CREATIVE_TAB).stacksTo(1)
    );

    public static final FluidRegistryContainer BROWN_WORT = new FluidRegistryContainer(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.BROWN_WORT).get(FluidUtils.STILL),
            FluidType.Properties.create().canSwim(true).canDrown(true).canPushEntity(true).supportsBoating(true),
            () -> FluidRegistryContainer.createExtension(
                    new FluidRegistryContainer.ClientExtensions(
                            Reference.MODID,
                            FluidUtils.getFluidNames(Reference.UnlocalizedName.BROWN_WORT).get(FluidUtils.STILL)
                    ).tint(Reference.FluidColor.BROWN_WORT.toIntValue())
                            .fogColor(
                                    Reference.FluidColor.BROWN_WORT.getColor().getRed(),
                                    Reference.FluidColor.BROWN_WORT.getColor().getGreen(),
                                    Reference.FluidColor.BROWN_WORT.getColor().getBlue()
                            )
            ),
            BlockBehaviour.Properties.copy(Blocks.WATER),
            new Item.Properties().tab(CREATIVE_TAB).stacksTo(1)
    );

    public static final FluidRegistryContainer COPPER_ALE = new FluidRegistryContainer(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.COPPER_ALE).get(FluidUtils.STILL),
            FluidType.Properties.create().canSwim(true).canDrown(true).canPushEntity(true).supportsBoating(true),
            () -> FluidRegistryContainer.createExtension(
                    new FluidRegistryContainer.ClientExtensions(
                            Reference.MODID,
                            FluidUtils.getFluidNames(Reference.UnlocalizedName.COPPER_ALE).get(FluidUtils.STILL)
                    ).tint(Reference.FluidColor.COPPER_ALE.toIntValue())
                            .fogColor(
                                    Reference.FluidColor.COPPER_ALE.getColor().getRed(),
                                    Reference.FluidColor.COPPER_ALE.getColor().getGreen(),
                                    Reference.FluidColor.COPPER_ALE.getColor().getBlue()
                            )
            ),
            BlockBehaviour.Properties.copy(Blocks.WATER),
            new Item.Properties().tab(CREATIVE_TAB).stacksTo(1)
    );

    public static final FluidRegistryContainer COPPER_LAGER = new FluidRegistryContainer(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.COPPER_LAGER).get(FluidUtils.STILL),
            FluidType.Properties.create().canSwim(true).canDrown(true).canPushEntity(true).supportsBoating(true),
            () -> FluidRegistryContainer.createExtension(
                    new FluidRegistryContainer.ClientExtensions(
                            Reference.MODID,
                            FluidUtils.getFluidNames(Reference.UnlocalizedName.COPPER_ALE).get(FluidUtils.STILL)
                    ).tint(Reference.FluidColor.COPPER_LAGER.toIntValue())
                            .fogColor(
                                    Reference.FluidColor.COPPER_LAGER.getColor().getRed(),
                                    Reference.FluidColor.COPPER_LAGER.getColor().getGreen(),
                                    Reference.FluidColor.COPPER_LAGER.getColor().getBlue()
                            )
            ),
            BlockBehaviour.Properties.copy(Blocks.WATER),
            new Item.Properties().tab(CREATIVE_TAB).stacksTo(1)
    );

    public static final FluidRegistryContainer COPPER_WORT = new FluidRegistryContainer(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.COPPER_WORT).get(FluidUtils.STILL),
            FluidType.Properties.create().canSwim(true).canDrown(true).canPushEntity(true).supportsBoating(true),
            () -> FluidRegistryContainer.createExtension(
                    new FluidRegistryContainer.ClientExtensions(
                            Reference.MODID,
                            FluidUtils.getFluidNames(Reference.UnlocalizedName.COPPER_WORT).get(FluidUtils.STILL)
                    ).tint(Reference.FluidColor.COPPER_WORT.toIntValue())
                            .fogColor(
                                    Reference.FluidColor.COPPER_WORT.getColor().getRed(),
                                    Reference.FluidColor.COPPER_WORT.getColor().getGreen(),
                                    Reference.FluidColor.COPPER_WORT.getColor().getBlue()
                            )
            ),
            BlockBehaviour.Properties.copy(Blocks.WATER),
            new Item.Properties().tab(CREATIVE_TAB).stacksTo(1)
    );

    public static final FluidRegistryContainer DARK_LAGER = new FluidRegistryContainer(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.DARK_LAGER).get(FluidUtils.STILL),
            FluidType.Properties.create().canSwim(true).canDrown(true).canPushEntity(true).supportsBoating(true),
            () -> FluidRegistryContainer.createExtension(
                    new FluidRegistryContainer.ClientExtensions(
                            Reference.MODID,
                            FluidUtils.getFluidNames(Reference.UnlocalizedName.DARK_LAGER).get(FluidUtils.STILL)
                    ).tint(Reference.FluidColor.DARK_LAGER.toIntValue())
                            .fogColor(
                                    Reference.FluidColor.DARK_LAGER.getColor().getRed(),
                                    Reference.FluidColor.DARK_LAGER.getColor().getGreen(),
                                    Reference.FluidColor.DARK_LAGER.getColor().getBlue()
                            )
            ),
            BlockBehaviour.Properties.copy(Blocks.WATER),
            new Item.Properties().tab(CREATIVE_TAB).stacksTo(1)
    );

    public static final FluidRegistryContainer DARK_WORT = new FluidRegistryContainer(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.DARK_WORT).get(FluidUtils.STILL),
            FluidType.Properties.create().canSwim(true).canDrown(true).canPushEntity(true).supportsBoating(true),
            () -> FluidRegistryContainer.createExtension(
                    new FluidRegistryContainer.ClientExtensions(
                            Reference.MODID,
                            FluidUtils.getFluidNames(Reference.UnlocalizedName.DARK_WORT).get(FluidUtils.STILL)
                    ).tint(Reference.FluidColor.DARK_WORT.toIntValue())
                            .fogColor(
                                    Reference.FluidColor.DARK_WORT.getColor().getRed(),
                                    Reference.FluidColor.DARK_WORT.getColor().getGreen(),
                                    Reference.FluidColor.DARK_WORT.getColor().getBlue()
                            )
            ),
            BlockBehaviour.Properties.copy(Blocks.WATER),
            new Item.Properties().tab(CREATIVE_TAB).stacksTo(1)
    );

    public static final FluidRegistryContainer DEEP_AMBER_WORT = new FluidRegistryContainer(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.DEEP_AMBER_WORT).get(FluidUtils.STILL),
            FluidType.Properties.create().canSwim(true).canDrown(true).canPushEntity(true).supportsBoating(true),
            () -> FluidRegistryContainer.createExtension(
                    new FluidRegistryContainer.ClientExtensions(
                            Reference.MODID,
                            FluidUtils.getFluidNames(Reference.UnlocalizedName.DEEP_AMBER_WORT).get(FluidUtils.STILL)
                    ).tint(Reference.FluidColor.DEEP_AMBER_WORT.toIntValue())
                            .fogColor(
                                    Reference.FluidColor.DEEP_AMBER_WORT.getColor().getRed(),
                                    Reference.FluidColor.DEEP_AMBER_WORT.getColor().getGreen(),
                                    Reference.FluidColor.DEEP_AMBER_WORT.getColor().getBlue()
                            )
            ),
            BlockBehaviour.Properties.copy(Blocks.WATER),
            new Item.Properties().tab(CREATIVE_TAB).stacksTo(1)
    );

    public static final FluidRegistryContainer GOLDEN_WORT = new FluidRegistryContainer(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.GOLDEN_WORT).get(FluidUtils.STILL),
            FluidType.Properties.create().canSwim(true).canDrown(true).canPushEntity(true).supportsBoating(true),
            () -> FluidRegistryContainer.createExtension(
                    new FluidRegistryContainer.ClientExtensions(
                            Reference.MODID,
                            FluidUtils.getFluidNames(Reference.UnlocalizedName.GOLDEN_WORT).get(FluidUtils.STILL)
                    ).tint(Reference.FluidColor.GOLDEN_WORT.toIntValue())
                            .fogColor(
                                    Reference.FluidColor.GOLDEN_WORT.getColor().getRed(),
                                    Reference.FluidColor.GOLDEN_WORT.getColor().getGreen(),
                                    Reference.FluidColor.GOLDEN_WORT.getColor().getBlue()
                            )
            ),
            BlockBehaviour.Properties.copy(Blocks.WATER),
            new Item.Properties().tab(CREATIVE_TAB).stacksTo(1)
    );

    public static final FluidRegistryContainer HOPPED_GOLDEN_WORT = new FluidRegistryContainer(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.HOPPED_GOLDEN_WORT).get(FluidUtils.STILL),
            FluidType.Properties.create().canSwim(true).canDrown(true).canPushEntity(true).supportsBoating(true),
            () -> FluidRegistryContainer.createExtension(
                    new FluidRegistryContainer.ClientExtensions(
                            Reference.MODID,
                            FluidUtils.getFluidNames(Reference.UnlocalizedName.HOPPED_GOLDEN_WORT).get(FluidUtils.STILL)
                    ).tint(Reference.FluidColor.HOPPED_GOLDEN_WORT.toIntValue())
                            .fogColor(
                                    Reference.FluidColor.HOPPED_GOLDEN_WORT.getColor().getRed(),
                                    Reference.FluidColor.HOPPED_GOLDEN_WORT.getColor().getGreen(),
                                    Reference.FluidColor.HOPPED_GOLDEN_WORT.getColor().getBlue()
                            )
            ),
            BlockBehaviour.Properties.copy(Blocks.WATER),
            new Item.Properties().tab(CREATIVE_TAB).stacksTo(1)
    );

    public static final FluidRegistryContainer IPA_ALE = new FluidRegistryContainer(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.IPA_ALE).get(FluidUtils.STILL),
            FluidType.Properties.create().canSwim(true).canDrown(true).canPushEntity(true).supportsBoating(true),
            () -> FluidRegistryContainer.createExtension(
                    new FluidRegistryContainer.ClientExtensions(
                            Reference.MODID,
                            FluidUtils.getFluidNames(Reference.UnlocalizedName.IPA_ALE).get(FluidUtils.STILL)
                    ).tint(Reference.FluidColor.IPA_ALE.toIntValue())
                            .fogColor(
                                    Reference.FluidColor.IPA_ALE.getColor().getRed(),
                                    Reference.FluidColor.IPA_ALE.getColor().getGreen(),
                                    Reference.FluidColor.IPA_ALE.getColor().getBlue()
                            )
            ),
            BlockBehaviour.Properties.copy(Blocks.WATER),
            new Item.Properties().tab(CREATIVE_TAB).stacksTo(1)
    );

    public static final FluidRegistryContainer OLD_PORT_ALE = new FluidRegistryContainer(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.OLD_PORT_ALE).get(FluidUtils.STILL),
            FluidType.Properties.create().canSwim(true).canDrown(true).canPushEntity(true).supportsBoating(true),
            () -> FluidRegistryContainer.createExtension(
                    new FluidRegistryContainer.ClientExtensions(
                            Reference.MODID,
                            FluidUtils.getFluidNames(Reference.UnlocalizedName.OLD_PORT_ALE).get(FluidUtils.STILL)
                    ).tint(Reference.FluidColor.OLD_PORT_ALE.toIntValue())
                            .fogColor(
                                    Reference.FluidColor.OLD_PORT_ALE.getColor().getRed(),
                                    Reference.FluidColor.OLD_PORT_ALE.getColor().getGreen(),
                                    Reference.FluidColor.OLD_PORT_ALE.getColor().getBlue()
                            )
            ),
            BlockBehaviour.Properties.copy(Blocks.WATER),
            new Item.Properties().tab(CREATIVE_TAB).stacksTo(1)
    );

    public static final FluidRegistryContainer PALE_ALE = new FluidRegistryContainer(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.PALE_ALE).get(FluidUtils.STILL),
            FluidType.Properties.create().canSwim(true).canDrown(true).canPushEntity(true).supportsBoating(true),
            () -> FluidRegistryContainer.createExtension(
                    new FluidRegistryContainer.ClientExtensions(
                            Reference.MODID,
                            FluidUtils.getFluidNames(Reference.UnlocalizedName.PALE_ALE).get(FluidUtils.STILL)
                    ).tint(Reference.FluidColor.PALE_ALE.toIntValue())
                            .fogColor(
                                    Reference.FluidColor.PALE_ALE.getColor().getRed(),
                                    Reference.FluidColor.PALE_ALE.getColor().getGreen(),
                                    Reference.FluidColor.PALE_ALE.getColor().getBlue()
                            )
            ),
            BlockBehaviour.Properties.copy(Blocks.WATER),
            new Item.Properties().tab(CREATIVE_TAB).stacksTo(1)
    );

    public static final FluidRegistryContainer PALE_GOLDEN_WORT = new FluidRegistryContainer(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.PALE_GOLDEN_WORT).get(FluidUtils.STILL),
            FluidType.Properties.create().canSwim(true).canDrown(true).canPushEntity(true).supportsBoating(true),
            () -> FluidRegistryContainer.createExtension(
                    new FluidRegistryContainer.ClientExtensions(
                            Reference.MODID,
                            FluidUtils.getFluidNames(Reference.UnlocalizedName.PALE_GOLDEN_WORT).get(FluidUtils.STILL)
                    ).tint(Reference.FluidColor.PALE_GOLDEN_WORT.toIntValue())
                            .fogColor(
                                    Reference.FluidColor.PALE_GOLDEN_WORT.getColor().getRed(),
                                    Reference.FluidColor.PALE_GOLDEN_WORT.getColor().getGreen(),
                                    Reference.FluidColor.PALE_GOLDEN_WORT.getColor().getBlue()
                            )
            ),
            BlockBehaviour.Properties.copy(Blocks.WATER),
            new Item.Properties().tab(CREATIVE_TAB).stacksTo(1)
    );

    public static final FluidRegistryContainer PALE_LAGER = new FluidRegistryContainer(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.PALE_LAGER).get(FluidUtils.STILL),
            FluidType.Properties.create().canSwim(true).canDrown(true).canPushEntity(true).supportsBoating(true),
            () -> FluidRegistryContainer.createExtension(
                    new FluidRegistryContainer.ClientExtensions(
                            Reference.MODID,
                            FluidUtils.getFluidNames(Reference.UnlocalizedName.PALE_LAGER).get(FluidUtils.STILL)
                    ).tint(Reference.FluidColor.PALE_LAGER.toIntValue())
                            .fogColor(
                                    Reference.FluidColor.PALE_LAGER.getColor().getRed(),
                                    Reference.FluidColor.PALE_LAGER.getColor().getGreen(),
                                    Reference.FluidColor.PALE_LAGER.getColor().getBlue()
                            )
            ),
            BlockBehaviour.Properties.copy(Blocks.WATER),
            new Item.Properties().tab(CREATIVE_TAB).stacksTo(1)
    );

    public static final FluidRegistryContainer PILSNER_LAGER = new FluidRegistryContainer(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.PILSNER_LAGER).get(FluidUtils.STILL),
            FluidType.Properties.create().canSwim(true).canDrown(true).canPushEntity(true).supportsBoating(true),
            () -> FluidRegistryContainer.createExtension(
                    new FluidRegistryContainer.ClientExtensions(
                            Reference.MODID,
                            FluidUtils.getFluidNames(Reference.UnlocalizedName.PILSNER_LAGER).get(FluidUtils.STILL)
                    ).tint(Reference.FluidColor.PILSNER_LAGER.toIntValue())
                            .fogColor(
                                    Reference.FluidColor.PILSNER_LAGER.getColor().getRed(),
                                    Reference.FluidColor.PILSNER_LAGER.getColor().getGreen(),
                                    Reference.FluidColor.PILSNER_LAGER.getColor().getBlue()
                            )
            ),
            BlockBehaviour.Properties.copy(Blocks.WATER),
            new Item.Properties().tab(CREATIVE_TAB).stacksTo(1)
    );

    public static final FluidRegistryContainer PURPLE_GRAPE_JUICE = new FluidRegistryContainer(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.PURPLE_GRAPE_JUICE).get(FluidUtils.STILL),
            FluidType.Properties.create().canSwim(true).canDrown(true).canPushEntity(true).supportsBoating(true),
            () -> FluidRegistryContainer.createExtension(
                    new FluidRegistryContainer.ClientExtensions(
                            Reference.MODID,
                            FluidUtils.getFluidNames(Reference.UnlocalizedName.PURPLE_GRAPE_JUICE).get(FluidUtils.STILL)
                    ).tint(Reference.FluidColor.PURPLE_GRAPE_JUICE.toIntValue())
                            .fogColor(
                                    Reference.FluidColor.PURPLE_GRAPE_JUICE.getColor().getRed(),
                                    Reference.FluidColor.PURPLE_GRAPE_JUICE.getColor().getGreen(),
                                    Reference.FluidColor.PURPLE_GRAPE_JUICE.getColor().getBlue()
                            )
            ),
            BlockBehaviour.Properties.copy(Blocks.WATER),
            new Item.Properties().tab(CREATIVE_TAB).stacksTo(1)
    );

    public static final FluidRegistryContainer PURPLE_GRAPE_WINE = new FluidRegistryContainer(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.PURPLE_GRAPE_WINE).get(FluidUtils.STILL),
            FluidType.Properties.create().canSwim(true).canDrown(true).canPushEntity(true).supportsBoating(true),
            () -> FluidRegistryContainer.createExtension(
                    new FluidRegistryContainer.ClientExtensions(
                            Reference.MODID,
                            FluidUtils.getFluidNames(Reference.UnlocalizedName.PURPLE_GRAPE_WINE).get(FluidUtils.STILL)
                    ).tint(Reference.FluidColor.PURPLE_GRAPE_WINE.toIntValue())
                            .fogColor(
                                    Reference.FluidColor.PURPLE_GRAPE_WINE.getColor().getRed(),
                                    Reference.FluidColor.PURPLE_GRAPE_WINE.getColor().getGreen(),
                                    Reference.FluidColor.PURPLE_GRAPE_WINE.getColor().getBlue()
                            )
            ),
            BlockBehaviour.Properties.copy(Blocks.WATER),
            new Item.Properties().tab(CREATIVE_TAB).stacksTo(1)
    );

    public static final FluidRegistryContainer RED_GRAPE_JUICE = new FluidRegistryContainer(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.RED_GRAPE_JUICE).get(FluidUtils.STILL),
            FluidType.Properties.create().canSwim(true).canDrown(true).canPushEntity(true).supportsBoating(true),
            () -> FluidRegistryContainer.createExtension(
                    new FluidRegistryContainer.ClientExtensions(
                            Reference.MODID,
                            FluidUtils.getFluidNames(Reference.UnlocalizedName.RED_GRAPE_JUICE).get(FluidUtils.STILL)
                    ).tint(Reference.FluidColor.RED_GRAPE_JUICE.toIntValue())
                            .fogColor(
                                    Reference.FluidColor.RED_GRAPE_JUICE.getColor().getRed(),
                                    Reference.FluidColor.RED_GRAPE_JUICE.getColor().getGreen(),
                                    Reference.FluidColor.RED_GRAPE_JUICE.getColor().getBlue()
                            )
            ),
            BlockBehaviour.Properties.copy(Blocks.WATER),
            new Item.Properties().tab(CREATIVE_TAB).stacksTo(1)
    );

    public static final FluidRegistryContainer RED_GRAPE_WINE = new FluidRegistryContainer(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.RED_GRAPE_WINE).get(FluidUtils.STILL),
            FluidType.Properties.create().canSwim(true).canDrown(true).canPushEntity(true).supportsBoating(true),
            () -> FluidRegistryContainer.createExtension(
                    new FluidRegistryContainer.ClientExtensions(
                            Reference.MODID,
                            FluidUtils.getFluidNames(Reference.UnlocalizedName.RED_GRAPE_WINE).get(FluidUtils.STILL)
                    ).tint(Reference.FluidColor.RED_GRAPE_WINE.toIntValue())
                            .fogColor(
                                    Reference.FluidColor.RED_GRAPE_WINE.getColor().getRed(),
                                    Reference.FluidColor.RED_GRAPE_WINE.getColor().getGreen(),
                                    Reference.FluidColor.RED_GRAPE_WINE.getColor().getBlue()
                            )
            ),
            BlockBehaviour.Properties.copy(Blocks.WATER),
            new Item.Properties().tab(CREATIVE_TAB).stacksTo(1)
    );

    public static final FluidRegistryContainer STOUT_ALE = new FluidRegistryContainer(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.STOUT_ALE).get(FluidUtils.STILL),
            FluidType.Properties.create().canSwim(true).canDrown(true).canPushEntity(true).supportsBoating(true),
            () -> FluidRegistryContainer.createExtension(
                    new FluidRegistryContainer.ClientExtensions(
                            Reference.MODID,
                            FluidUtils.getFluidNames(Reference.UnlocalizedName.STOUT_ALE).get(FluidUtils.STILL)
                    ).tint(Reference.FluidColor.STOUT_ALE.toIntValue())
                            .fogColor(
                                    Reference.FluidColor.STOUT_ALE.getColor().getRed(),
                                    Reference.FluidColor.STOUT_ALE.getColor().getGreen(),
                                    Reference.FluidColor.STOUT_ALE.getColor().getBlue()
                            )
            ),
            BlockBehaviour.Properties.copy(Blocks.WATER),
            new Item.Properties().tab(CREATIVE_TAB).stacksTo(1)
    );

    public static final FluidRegistryContainer VIENNA_LAGER = new FluidRegistryContainer(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.VIENNA_LAGER).get(FluidUtils.STILL),
            FluidType.Properties.create().canSwim(true).canDrown(true).canPushEntity(true).supportsBoating(true),
            () -> FluidRegistryContainer.createExtension(
                    new FluidRegistryContainer.ClientExtensions(
                            Reference.MODID,
                            FluidUtils.getFluidNames(Reference.UnlocalizedName.VIENNA_LAGER).get(FluidUtils.STILL)
                    ).tint(Reference.FluidColor.VIENNA_LAGER.toIntValue())
                            .fogColor(
                                    Reference.FluidColor.VIENNA_LAGER.getColor().getRed(),
                                    Reference.FluidColor.VIENNA_LAGER.getColor().getGreen(),
                                    Reference.FluidColor.VIENNA_LAGER.getColor().getBlue()
                            )
            ),
            BlockBehaviour.Properties.copy(Blocks.WATER),
            new Item.Properties().tab(CREATIVE_TAB).stacksTo(1)
    );

    public static final FluidRegistryContainer WHITE_GRAPE_JUICE = new FluidRegistryContainer(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.WHITE_GRAPE_JUICE).get(FluidUtils.STILL),
            FluidType.Properties.create().canSwim(true).canDrown(true).canPushEntity(true).supportsBoating(true),
            () -> FluidRegistryContainer.createExtension(
                    new FluidRegistryContainer.ClientExtensions(
                            Reference.MODID,
                            FluidUtils.getFluidNames(Reference.UnlocalizedName.WHITE_GRAPE_JUICE).get(FluidUtils.STILL)
                    ).tint(Reference.FluidColor.WHITE_GRAPE_JUICE.toIntValue())
                            .fogColor(
                                    Reference.FluidColor.WHITE_GRAPE_JUICE.getColor().getRed(),
                                    Reference.FluidColor.WHITE_GRAPE_JUICE.getColor().getGreen(),
                                    Reference.FluidColor.WHITE_GRAPE_JUICE.getColor().getBlue()
                            )
            ),
            BlockBehaviour.Properties.copy(Blocks.WATER),
            new Item.Properties().tab(CREATIVE_TAB).stacksTo(1)
    );

    public static final FluidRegistryContainer WHITE_GRAPE_WINE = new FluidRegistryContainer(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.WHITE_GRAPE_WINE).get(FluidUtils.STILL),
            FluidType.Properties.create().canSwim(true).canDrown(true).canPushEntity(true).supportsBoating(true),
            () -> FluidRegistryContainer.createExtension(
                    new FluidRegistryContainer.ClientExtensions(
                            Reference.MODID,
                            FluidUtils.getFluidNames(Reference.UnlocalizedName.WHITE_GRAPE_WINE).get(FluidUtils.STILL)
                    ).tint(Reference.FluidColor.WHITE_GRAPE_WINE.toIntValue())
                            .fogColor(
                                    Reference.FluidColor.WHITE_GRAPE_WINE.getColor().getRed(),
                                    Reference.FluidColor.WHITE_GRAPE_WINE.getColor().getGreen(),
                                    Reference.FluidColor.WHITE_GRAPE_WINE.getColor().getBlue()
                            )
            ),
            BlockBehaviour.Properties.copy(Blocks.WATER),
            new Item.Properties().tab(CREATIVE_TAB).stacksTo(1)
    );

    public static final FluidRegistryContainer WORT = new FluidRegistryContainer(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.WORT).get(FluidUtils.STILL),
            FluidType.Properties.create().canSwim(true).canDrown(true).canPushEntity(true).supportsBoating(true),
            () -> FluidRegistryContainer.createExtension(
                    new FluidRegistryContainer.ClientExtensions(
                            Reference.MODID,
                            FluidUtils.getFluidNames(Reference.UnlocalizedName.WORT).get(FluidUtils.STILL)
                    ).tint(Reference.FluidColor.WORT.toIntValue())
                            .fogColor(
                                    Reference.FluidColor.WORT.getColor().getRed(),
                                    Reference.FluidColor.WORT.getColor().getGreen(),
                                    Reference.FluidColor.WORT.getColor().getBlue()
                            )
            ),
            BlockBehaviour.Properties.copy(Blocks.WATER),
            new Item.Properties().tab(CREATIVE_TAB).stacksTo(1)
    );

    private GrowthcraftCellarFluids() {
        /* Prevent default public constructor */
    }
}
