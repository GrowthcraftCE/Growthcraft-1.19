package growthcraft.cellar.init;

import growthcraft.cellar.lib.fluid.GrowthcraftCellarFluidRegistryContainer;
import growthcraft.cellar.shared.Reference;
import growthcraft.lib.client.ClientFluidTypeExtensions;
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
            ForgeRegistries.Keys.FLUID_TYPES, Reference.MODID
    );

    public static final GrowthcraftCellarFluidRegistryContainer AMBER_ALE = new GrowthcraftCellarFluidRegistryContainer(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.AMBER_ALE).get(FluidUtils.STILL),
            FluidType.Properties.create().canSwim(true).canDrown(true).canPushEntity(true).supportsBoating(true),
            () -> GrowthcraftCellarFluidRegistryContainer.createExtension(
                    new ClientFluidTypeExtensions(
                            Reference.MODID,
                            FluidUtils.getFluidNames(Reference.UnlocalizedName.AMBER_ALE).get(FluidUtils.STILL)
                    )
                    .tint(Reference.FluidColor.AMBER_ALE.toIntValue())
                    .fogColor(
                        Reference.FluidColor.AMBER_ALE.toFloatValues().get("red"),
                        Reference.FluidColor.AMBER_ALE.toFloatValues().get("green"),
                        Reference.FluidColor.AMBER_ALE.toFloatValues().get("blue")
                    )
                    .overlay(Reference.UnlocalizedName.COPPER_ALE)
            ),
            BlockBehaviour.Properties.copy(Blocks.WATER),
            new Item.Properties().stacksTo(1)
    );

    public static final GrowthcraftCellarFluidRegistryContainer AMBER_LAGER = new GrowthcraftCellarFluidRegistryContainer(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.AMBER_LAGER).get(FluidUtils.STILL),
            FluidType.Properties.create().canSwim(true).canDrown(true).canPushEntity(true).supportsBoating(true),
            () -> GrowthcraftCellarFluidRegistryContainer.createExtension(
                    new ClientFluidTypeExtensions(
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

    public static final GrowthcraftCellarFluidRegistryContainer AMBER_WORT = new GrowthcraftCellarFluidRegistryContainer(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.AMBER_WORT).get(FluidUtils.STILL),
            FluidType.Properties.create().canSwim(true).canDrown(true).canPushEntity(true).supportsBoating(true),
            () -> GrowthcraftCellarFluidRegistryContainer.createExtension(
                    new ClientFluidTypeExtensions(
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

    public static final GrowthcraftCellarFluidRegistryContainer BROWN_ALE = new GrowthcraftCellarFluidRegistryContainer(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.BROWN_ALE).get(FluidUtils.STILL),
            FluidType.Properties.create().canSwim(true).canDrown(true).canPushEntity(true).supportsBoating(true),
            () -> GrowthcraftCellarFluidRegistryContainer.createExtension(
                    new ClientFluidTypeExtensions(
                            Reference.MODID,
                            FluidUtils.getFluidNames(Reference.UnlocalizedName.BROWN_ALE).get(FluidUtils.STILL)
                    ).tint(Reference.FluidColor.BROWN_ALE.toIntValue())
                    .fogColor(
                            Reference.FluidColor.BROWN_ALE.toFloatValues().get("red"),
                            Reference.FluidColor.BROWN_ALE.toFloatValues().get("green"),
                            Reference.FluidColor.BROWN_ALE.toFloatValues().get("blue")
                    )
                    .overlay(Reference.UnlocalizedName.BROWN_ALE)
            ),
            BlockBehaviour.Properties.copy(Blocks.WATER),
            new Item.Properties().stacksTo(1)
    );

    public static final GrowthcraftCellarFluidRegistryContainer BROWN_LAGER = new GrowthcraftCellarFluidRegistryContainer(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.BROWN_LAGER).get(FluidUtils.STILL),
            FluidType.Properties.create().canSwim(true).canDrown(true).canPushEntity(true).supportsBoating(true),
            () -> GrowthcraftCellarFluidRegistryContainer.createExtension(
                    new ClientFluidTypeExtensions(
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

    public static final GrowthcraftCellarFluidRegistryContainer BROWN_WORT = new GrowthcraftCellarFluidRegistryContainer(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.BROWN_WORT).get(FluidUtils.STILL),
            FluidType.Properties.create().canSwim(true).canDrown(true).canPushEntity(true).supportsBoating(true),
            () -> GrowthcraftCellarFluidRegistryContainer.createExtension(
                    new ClientFluidTypeExtensions(
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

    public static final GrowthcraftCellarFluidRegistryContainer COPPER_ALE = new GrowthcraftCellarFluidRegistryContainer(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.COPPER_ALE).get(FluidUtils.STILL),
            FluidType.Properties.create().canSwim(true).canDrown(true).canPushEntity(true).supportsBoating(true)
                    .canConvertToSource(false).density(4300).viscosity(4600).temperature(285),
            () -> GrowthcraftCellarFluidRegistryContainer.createExtension(
                    new ClientFluidTypeExtensions(
                            Reference.MODID,
                            FluidUtils.getFluidNames(Reference.UnlocalizedName.COPPER_ALE).get(FluidUtils.STILL))
                            .tint(Reference.FluidColor.COPPER_ALE.toIntValue())
                            .fogColor(
                                    Reference.FluidColor.COPPER_ALE.toFloatValues().get("red"),
                                    Reference.FluidColor.COPPER_ALE.toFloatValues().get("green"),
                                    Reference.FluidColor.COPPER_ALE.toFloatValues().get("blue")
                            ).overlay(Reference.UnlocalizedName.COPPER_ALE)
            ),
            BlockBehaviour.Properties.copy(Blocks.WATER),
            new Item.Properties().stacksTo(1)
    );

    public static final GrowthcraftCellarFluidRegistryContainer COPPER_LAGER = new GrowthcraftCellarFluidRegistryContainer(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.COPPER_LAGER).get(FluidUtils.STILL),
            FluidType.Properties.create().canSwim(true).canDrown(true).canPushEntity(true).supportsBoating(true),
            () -> GrowthcraftCellarFluidRegistryContainer.createExtension(
                    new ClientFluidTypeExtensions(
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

    public static final GrowthcraftCellarFluidRegistryContainer COPPER_WORT = new GrowthcraftCellarFluidRegistryContainer(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.COPPER_WORT).get(FluidUtils.STILL),
            FluidType.Properties.create().canSwim(true).canDrown(true).canPushEntity(true).supportsBoating(true),
            () -> GrowthcraftCellarFluidRegistryContainer.createExtension(
                    new ClientFluidTypeExtensions(
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

    public static final GrowthcraftCellarFluidRegistryContainer DARK_LAGER = new GrowthcraftCellarFluidRegistryContainer(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.DARK_LAGER).get(FluidUtils.STILL),
            FluidType.Properties.create().canSwim(true).canDrown(true).canPushEntity(true).supportsBoating(true),
            () -> GrowthcraftCellarFluidRegistryContainer.createExtension(
                    new ClientFluidTypeExtensions(
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

    public static final GrowthcraftCellarFluidRegistryContainer DARK_WORT = new GrowthcraftCellarFluidRegistryContainer(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.DARK_WORT).get(FluidUtils.STILL),
            FluidType.Properties.create().canSwim(true).canDrown(true).canPushEntity(true).supportsBoating(true),
            () -> GrowthcraftCellarFluidRegistryContainer.createExtension(
                    new ClientFluidTypeExtensions(
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

    public static final GrowthcraftCellarFluidRegistryContainer DEEP_AMBER_WORT = new GrowthcraftCellarFluidRegistryContainer(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.DEEP_AMBER_WORT).get(FluidUtils.STILL),
            FluidType.Properties.create().canSwim(true).canDrown(true).canPushEntity(true).supportsBoating(true),
            () -> GrowthcraftCellarFluidRegistryContainer.createExtension(
                    new ClientFluidTypeExtensions(
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

    public static final GrowthcraftCellarFluidRegistryContainer GOLDEN_WORT = new GrowthcraftCellarFluidRegistryContainer(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.GOLDEN_WORT).get(FluidUtils.STILL),
            FluidType.Properties.create().canSwim(true).canDrown(true).canPushEntity(true).supportsBoating(true),
            () -> GrowthcraftCellarFluidRegistryContainer.createExtension(
                    new ClientFluidTypeExtensions(
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

    public static final GrowthcraftCellarFluidRegistryContainer HOPPED_GOLDEN_WORT = new GrowthcraftCellarFluidRegistryContainer(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.HOPPED_GOLDEN_WORT).get(FluidUtils.STILL),
            FluidType.Properties.create().canSwim(true).canDrown(true).canPushEntity(true).supportsBoating(true),
            () -> GrowthcraftCellarFluidRegistryContainer.createExtension(
                    new ClientFluidTypeExtensions(
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

    public static final GrowthcraftCellarFluidRegistryContainer IPA_ALE = new GrowthcraftCellarFluidRegistryContainer(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.IPA_ALE).get(FluidUtils.STILL),
            FluidType.Properties.create().canSwim(true).canDrown(true).canPushEntity(true).supportsBoating(true),
            () -> GrowthcraftCellarFluidRegistryContainer.createExtension(
                    new ClientFluidTypeExtensions(
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

    public static final GrowthcraftCellarFluidRegistryContainer OLD_PORT_ALE = new GrowthcraftCellarFluidRegistryContainer(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.OLD_PORT_ALE).get(FluidUtils.STILL),
            FluidType.Properties.create().canSwim(true).canDrown(true).canPushEntity(true).supportsBoating(true),
            () -> GrowthcraftCellarFluidRegistryContainer.createExtension(
                    new ClientFluidTypeExtensions(
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

    public static final GrowthcraftCellarFluidRegistryContainer PALE_ALE = new GrowthcraftCellarFluidRegistryContainer(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.PALE_ALE).get(FluidUtils.STILL),
            FluidType.Properties.create().canSwim(true).canDrown(true).canPushEntity(true).supportsBoating(true),
            () -> GrowthcraftCellarFluidRegistryContainer.createExtension(
                    new ClientFluidTypeExtensions(
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

    public static final GrowthcraftCellarFluidRegistryContainer PALE_GOLDEN_WORT = new GrowthcraftCellarFluidRegistryContainer(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.PALE_GOLDEN_WORT).get(FluidUtils.STILL),
            FluidType.Properties.create().canSwim(true).canDrown(true).canPushEntity(true).supportsBoating(true),
            () -> GrowthcraftCellarFluidRegistryContainer.createExtension(
                    new ClientFluidTypeExtensions(
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

    public static final GrowthcraftCellarFluidRegistryContainer PALE_LAGER = new GrowthcraftCellarFluidRegistryContainer(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.PALE_LAGER).get(FluidUtils.STILL),
            FluidType.Properties.create().canSwim(true).canDrown(true).canPushEntity(true).supportsBoating(true),
            () -> GrowthcraftCellarFluidRegistryContainer.createExtension(
                    new ClientFluidTypeExtensions(
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

    public static final GrowthcraftCellarFluidRegistryContainer PILSNER_LAGER = new GrowthcraftCellarFluidRegistryContainer(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.PILSNER_LAGER).get(FluidUtils.STILL),
            FluidType.Properties.create().canSwim(true).canDrown(true).canPushEntity(true).supportsBoating(true),
            () -> GrowthcraftCellarFluidRegistryContainer.createExtension(
                    new ClientFluidTypeExtensions(
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

    public static final GrowthcraftCellarFluidRegistryContainer PURPLE_GRAPE_JUICE = new GrowthcraftCellarFluidRegistryContainer(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.PURPLE_GRAPE_JUICE).get(FluidUtils.STILL),
            FluidType.Properties.create().canSwim(true).canDrown(true).canPushEntity(true).supportsBoating(true),
            () -> GrowthcraftCellarFluidRegistryContainer.createExtension(
                    new ClientFluidTypeExtensions(
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

    public static final GrowthcraftCellarFluidRegistryContainer PURPLE_GRAPE_WINE = new GrowthcraftCellarFluidRegistryContainer(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.PURPLE_GRAPE_WINE).get(FluidUtils.STILL),
            FluidType.Properties.create().canSwim(true).canDrown(true).canPushEntity(true).supportsBoating(true),
            () -> GrowthcraftCellarFluidRegistryContainer.createExtension(
                    new ClientFluidTypeExtensions(
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

    public static final GrowthcraftCellarFluidRegistryContainer RED_GRAPE_JUICE = new GrowthcraftCellarFluidRegistryContainer(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.RED_GRAPE_JUICE).get(FluidUtils.STILL),
            FluidType.Properties.create().canSwim(true).canDrown(true).canPushEntity(true).supportsBoating(true),
            () -> GrowthcraftCellarFluidRegistryContainer.createExtension(
                    new ClientFluidTypeExtensions(
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

    public static final GrowthcraftCellarFluidRegistryContainer RED_GRAPE_WINE = new GrowthcraftCellarFluidRegistryContainer(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.RED_GRAPE_WINE).get(FluidUtils.STILL),
            FluidType.Properties.create().canSwim(true).canDrown(true).canPushEntity(true).supportsBoating(true),
            () -> GrowthcraftCellarFluidRegistryContainer.createExtension(
                    new ClientFluidTypeExtensions(
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

    public static final GrowthcraftCellarFluidRegistryContainer STOUT_ALE = new GrowthcraftCellarFluidRegistryContainer(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.STOUT_ALE).get(FluidUtils.STILL),
            FluidType.Properties.create().canSwim(true).canDrown(true).canPushEntity(true).supportsBoating(true),
            () -> GrowthcraftCellarFluidRegistryContainer.createExtension(
                    new ClientFluidTypeExtensions(
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

    public static final GrowthcraftCellarFluidRegistryContainer VIENNA_LAGER = new GrowthcraftCellarFluidRegistryContainer(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.VIENNA_LAGER).get(FluidUtils.STILL),
            FluidType.Properties.create().canSwim(true).canDrown(true).canPushEntity(true).supportsBoating(true),
            () -> GrowthcraftCellarFluidRegistryContainer.createExtension(
                    new ClientFluidTypeExtensions(
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

    public static final GrowthcraftCellarFluidRegistryContainer WHITE_GRAPE_JUICE = new GrowthcraftCellarFluidRegistryContainer(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.WHITE_GRAPE_JUICE).get(FluidUtils.STILL),
            FluidType.Properties.create().canSwim(true).canDrown(true).canPushEntity(true).supportsBoating(true),
            () -> GrowthcraftCellarFluidRegistryContainer.createExtension(
                    new ClientFluidTypeExtensions(
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

    public static final GrowthcraftCellarFluidRegistryContainer WHITE_GRAPE_WINE = new GrowthcraftCellarFluidRegistryContainer(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.WHITE_GRAPE_WINE).get(FluidUtils.STILL),
            FluidType.Properties.create().canSwim(true).canDrown(true).canPushEntity(true).supportsBoating(true),
            () -> GrowthcraftCellarFluidRegistryContainer.createExtension(
                    new ClientFluidTypeExtensions(
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

    public static final GrowthcraftCellarFluidRegistryContainer WORT = new GrowthcraftCellarFluidRegistryContainer(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.WORT).get(FluidUtils.STILL),
            FluidType.Properties.create().canSwim(true).canDrown(true).canPushEntity(true).supportsBoating(true),
            () -> GrowthcraftCellarFluidRegistryContainer.createExtension(
                    new ClientFluidTypeExtensions(
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
