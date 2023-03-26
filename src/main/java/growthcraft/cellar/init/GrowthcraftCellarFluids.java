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
                                    Reference.FluidColor.AMBER_ALE.toFloatValues().get("red"),
                                    Reference.FluidColor.AMBER_ALE.toFloatValues().get("green"),
                                    Reference.FluidColor.AMBER_ALE.toFloatValues().get("blue")
                            )
            ),
            BlockBehaviour.Properties.copy(Blocks.WATER),
            new Item.Properties().stacksTo(1)
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
                                    Reference.FluidColor.AMBER_LAGER.toFloatValues().get("red"),
                                    Reference.FluidColor.AMBER_LAGER.toFloatValues().get("green"),
                                    Reference.FluidColor.AMBER_LAGER.toFloatValues().get("blue")
                            )
            ),
            BlockBehaviour.Properties.copy(Blocks.WATER),
            new Item.Properties().stacksTo(1)
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
                                    Reference.FluidColor.AMBER_WORT.toFloatValues().get("red"),
                                    Reference.FluidColor.AMBER_WORT.toFloatValues().get("green"),
                                    Reference.FluidColor.AMBER_WORT.toFloatValues().get("blue")
                            )
            ),
            BlockBehaviour.Properties.copy(Blocks.WATER),
            new Item.Properties().stacksTo(1)
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
                                    Reference.FluidColor.BROWN_ALE.toFloatValues().get("red"),
                                    Reference.FluidColor.BROWN_ALE.toFloatValues().get("green"),
                                    Reference.FluidColor.BROWN_ALE.toFloatValues().get("blue")
                            )
            ),
            BlockBehaviour.Properties.copy(Blocks.WATER),
            new Item.Properties().stacksTo(1)
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
                                    Reference.FluidColor.BROWN_LAGER.toFloatValues().get("red"),
                                    Reference.FluidColor.BROWN_LAGER.toFloatValues().get("green"),
                                    Reference.FluidColor.BROWN_LAGER.toFloatValues().get("blue")
                            )
            ),
            BlockBehaviour.Properties.copy(Blocks.WATER),
            new Item.Properties().stacksTo(1)
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
                                    Reference.FluidColor.BROWN_WORT.toFloatValues().get("red"),
                                    Reference.FluidColor.BROWN_WORT.toFloatValues().get("green"),
                                    Reference.FluidColor.BROWN_WORT.toFloatValues().get("blue")
                            )
            ),
            BlockBehaviour.Properties.copy(Blocks.WATER),
            new Item.Properties().stacksTo(1)
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
                                    Reference.FluidColor.COPPER_ALE.toFloatValues().get("red"),
                                    Reference.FluidColor.COPPER_ALE.toFloatValues().get("green"),
                                    Reference.FluidColor.COPPER_ALE.toFloatValues().get("blue")
                            )
            ),
            BlockBehaviour.Properties.copy(Blocks.WATER),
            new Item.Properties().stacksTo(1)
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
                                    Reference.FluidColor.COPPER_LAGER.toFloatValues().get("red"),
                                    Reference.FluidColor.COPPER_LAGER.toFloatValues().get("green"),
                                    Reference.FluidColor.COPPER_LAGER.toFloatValues().get("blue")
                            )
            ),
            BlockBehaviour.Properties.copy(Blocks.WATER),
            new Item.Properties().stacksTo(1)
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
                                    Reference.FluidColor.COPPER_WORT.toFloatValues().get("red"),
                                    Reference.FluidColor.COPPER_WORT.toFloatValues().get("green"),
                                    Reference.FluidColor.COPPER_WORT.toFloatValues().get("blue")
                            )
            ),
            BlockBehaviour.Properties.copy(Blocks.WATER),
            new Item.Properties().stacksTo(1)
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
                                    Reference.FluidColor.DARK_LAGER.toFloatValues().get("red"),
                                    Reference.FluidColor.DARK_LAGER.toFloatValues().get("green"),
                                    Reference.FluidColor.DARK_LAGER.toFloatValues().get("blue")
                            )
            ),
            BlockBehaviour.Properties.copy(Blocks.WATER),
            new Item.Properties().stacksTo(1)
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
                                    Reference.FluidColor.DARK_WORT.toFloatValues().get("red"),
                                    Reference.FluidColor.DARK_WORT.toFloatValues().get("green"),
                                    Reference.FluidColor.DARK_WORT.toFloatValues().get("blue")
                            )
            ),
            BlockBehaviour.Properties.copy(Blocks.WATER),
            new Item.Properties().stacksTo(1)
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
                                    Reference.FluidColor.DEEP_AMBER_WORT.toFloatValues().get("red"),
                                    Reference.FluidColor.DEEP_AMBER_WORT.toFloatValues().get("green"),
                                    Reference.FluidColor.DEEP_AMBER_WORT.toFloatValues().get("blue")
                            )
            ),
            BlockBehaviour.Properties.copy(Blocks.WATER),
            new Item.Properties().stacksTo(1)
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
                                    Reference.FluidColor.GOLDEN_WORT.toFloatValues().get("red"),
                                    Reference.FluidColor.GOLDEN_WORT.toFloatValues().get("green"),
                                    Reference.FluidColor.GOLDEN_WORT.toFloatValues().get("blue")
                            )
            ),
            BlockBehaviour.Properties.copy(Blocks.WATER),
            new Item.Properties().stacksTo(1)
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
                                    Reference.FluidColor.HOPPED_GOLDEN_WORT.toFloatValues().get("red"),
                                    Reference.FluidColor.HOPPED_GOLDEN_WORT.toFloatValues().get("green"),
                                    Reference.FluidColor.HOPPED_GOLDEN_WORT.toFloatValues().get("blue")
                            )
            ),
            BlockBehaviour.Properties.copy(Blocks.WATER),
            new Item.Properties().stacksTo(1)
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
                                    Reference.FluidColor.IPA_ALE.toFloatValues().get("red"),
                                    Reference.FluidColor.IPA_ALE.toFloatValues().get("green"),
                                    Reference.FluidColor.IPA_ALE.toFloatValues().get("blue")
                            )
            ),
            BlockBehaviour.Properties.copy(Blocks.WATER),
            new Item.Properties().stacksTo(1)
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
                                    Reference.FluidColor.OLD_PORT_ALE.toFloatValues().get("red"),
                                    Reference.FluidColor.OLD_PORT_ALE.toFloatValues().get("green"),
                                    Reference.FluidColor.OLD_PORT_ALE.toFloatValues().get("blue")
                            )
            ),
            BlockBehaviour.Properties.copy(Blocks.WATER),
            new Item.Properties().stacksTo(1)
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
                                    Reference.FluidColor.PALE_ALE.toFloatValues().get("red"),
                                    Reference.FluidColor.PALE_ALE.toFloatValues().get("green"),
                                    Reference.FluidColor.PALE_ALE.toFloatValues().get("blue")
                            )
            ),
            BlockBehaviour.Properties.copy(Blocks.WATER),
            new Item.Properties().stacksTo(1)
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
                                    Reference.FluidColor.PALE_GOLDEN_WORT.toFloatValues().get("red"),
                                    Reference.FluidColor.PALE_GOLDEN_WORT.toFloatValues().get("green"),
                                    Reference.FluidColor.PALE_GOLDEN_WORT.toFloatValues().get("blue")
                            )
            ),
            BlockBehaviour.Properties.copy(Blocks.WATER),
            new Item.Properties().stacksTo(1)
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
                                    Reference.FluidColor.PALE_LAGER.toFloatValues().get("red"),
                                    Reference.FluidColor.PALE_LAGER.toFloatValues().get("green"),
                                    Reference.FluidColor.PALE_LAGER.toFloatValues().get("blue")
                            )
            ),
            BlockBehaviour.Properties.copy(Blocks.WATER),
            new Item.Properties().stacksTo(1)
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
                                    Reference.FluidColor.PILSNER_LAGER.toFloatValues().get("red"),
                                    Reference.FluidColor.PILSNER_LAGER.toFloatValues().get("green"),
                                    Reference.FluidColor.PILSNER_LAGER.toFloatValues().get("blue")
                            )
            ),
            BlockBehaviour.Properties.copy(Blocks.WATER),
            new Item.Properties().stacksTo(1)
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
                                    Reference.FluidColor.PURPLE_GRAPE_JUICE.toFloatValues().get("red"),
                                    Reference.FluidColor.PURPLE_GRAPE_JUICE.toFloatValues().get("green"),
                                    Reference.FluidColor.PURPLE_GRAPE_JUICE.toFloatValues().get("blue")
                            )
            ),
            BlockBehaviour.Properties.copy(Blocks.WATER),
            new Item.Properties().stacksTo(1)
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
                                    Reference.FluidColor.PURPLE_GRAPE_WINE.toFloatValues().get("red"),
                                    Reference.FluidColor.PURPLE_GRAPE_WINE.toFloatValues().get("green"),
                                    Reference.FluidColor.PURPLE_GRAPE_WINE.toFloatValues().get("blue")
                            )
            ),
            BlockBehaviour.Properties.copy(Blocks.WATER),
            new Item.Properties().stacksTo(1)
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
                                    Reference.FluidColor.RED_GRAPE_JUICE.toFloatValues().get("red"),
                                    Reference.FluidColor.RED_GRAPE_JUICE.toFloatValues().get("green"),
                                    Reference.FluidColor.RED_GRAPE_JUICE.toFloatValues().get("blue")
                            )
            ),
            BlockBehaviour.Properties.copy(Blocks.WATER),
            new Item.Properties().stacksTo(1)
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
                                    Reference.FluidColor.RED_GRAPE_WINE.toFloatValues().get("red"),
                                    Reference.FluidColor.RED_GRAPE_WINE.toFloatValues().get("green"),
                                    Reference.FluidColor.RED_GRAPE_WINE.toFloatValues().get("blue")
                            )
            ),
            BlockBehaviour.Properties.copy(Blocks.WATER),
            new Item.Properties().stacksTo(1)
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
                                    Reference.FluidColor.STOUT_ALE.toFloatValues().get("red"),
                                    Reference.FluidColor.STOUT_ALE.toFloatValues().get("green"),
                                    Reference.FluidColor.STOUT_ALE.toFloatValues().get("blue")
                            )
            ),
            BlockBehaviour.Properties.copy(Blocks.WATER),
            new Item.Properties().stacksTo(1)
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
                                    Reference.FluidColor.VIENNA_LAGER.toFloatValues().get("red"),
                                    Reference.FluidColor.VIENNA_LAGER.toFloatValues().get("green"),
                                    Reference.FluidColor.VIENNA_LAGER.toFloatValues().get("blue")
                            )
            ),
            BlockBehaviour.Properties.copy(Blocks.WATER),
            new Item.Properties().stacksTo(1)
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
                                    Reference.FluidColor.WHITE_GRAPE_JUICE.toFloatValues().get("red"),
                                    Reference.FluidColor.WHITE_GRAPE_JUICE.toFloatValues().get("green"),
                                    Reference.FluidColor.WHITE_GRAPE_JUICE.toFloatValues().get("blue")
                            )
            ),
            BlockBehaviour.Properties.copy(Blocks.WATER),
            new Item.Properties().stacksTo(1)
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
                                    Reference.FluidColor.WHITE_GRAPE_WINE.toFloatValues().get("red"),
                                    Reference.FluidColor.WHITE_GRAPE_WINE.toFloatValues().get("green"),
                                    Reference.FluidColor.WHITE_GRAPE_WINE.toFloatValues().get("blue")
                            )
            ),
            BlockBehaviour.Properties.copy(Blocks.WATER),
            new Item.Properties().stacksTo(1)
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
                                    Reference.FluidColor.WORT.toFloatValues().get("red"),
                                    Reference.FluidColor.WORT.toFloatValues().get("green"),
                                    Reference.FluidColor.WORT.toFloatValues().get("blue")
                            )
            ),
            BlockBehaviour.Properties.copy(Blocks.WATER),
            new Item.Properties().stacksTo(1)
    );

    private GrowthcraftCellarFluids() {
        /* Prevent default public constructor */
    }
}
