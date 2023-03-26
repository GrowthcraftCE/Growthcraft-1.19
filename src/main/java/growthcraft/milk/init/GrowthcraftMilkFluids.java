package growthcraft.milk.init;

import growthcraft.lib.utils.FluidUtils;
import growthcraft.milk.lib.turtywurty.registry.FluidRegistryContainer;
import growthcraft.milk.shared.Reference;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static growthcraft.core.shared.Reference.CREATIVE_TAB;

public class GrowthcraftMilkFluids {
    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(
            ForgeRegistries.FLUIDS, Reference.MODID
    );

    public static final DeferredRegister<FluidType> FLUID_TYPES = DeferredRegister.create(
            ForgeRegistries.Keys.FLUID_TYPES, Reference.MODID
    );

    public static final FluidRegistryContainer BUTTER_MILK = new FluidRegistryContainer(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.BUTTER_MILK).get(FluidUtils.STILL),
            FluidType.Properties.create().canSwim(true).canDrown(true).canPushEntity(true).supportsBoating(true),
            () -> FluidRegistryContainer.createExtension(
                    new FluidRegistryContainer.ClientExtensions(
                            Reference.MODID,
                            FluidUtils.getFluidNames(Reference.UnlocalizedName.BUTTER_MILK).get(FluidUtils.STILL)
                    ).tint(
                                    Reference.FluidColor.BUTTER_MILK.toIntValue()
                            )
                            .fogColor(
                                    Reference.FluidColor.BUTTER_MILK.toFloatValues().get("red"),
                                    Reference.FluidColor.BUTTER_MILK.toFloatValues().get("green"),
                                    Reference.FluidColor.BUTTER_MILK.toFloatValues().get("blue")
                            )
            ),
            BlockBehaviour.Properties.copy(Blocks.WATER),
            new Item.Properties().tab(CREATIVE_TAB).stacksTo(1)
    );

    public static final FluidRegistryContainer CHEESE_BASE = new FluidRegistryContainer(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.CHEESE_BASE).get(FluidUtils.STILL),
            FluidType.Properties.create().canSwim(true).canDrown(true).canPushEntity(true).supportsBoating(true),
            () -> FluidRegistryContainer.createExtension(
                    new FluidRegistryContainer.ClientExtensions(
                            Reference.MODID,
                            FluidUtils.getFluidNames(Reference.UnlocalizedName.CHEESE_BASE).get(FluidUtils.STILL)
                    ).tint(Reference.FluidColor.CHEESE_BASE.toIntValue())
                            .fogColor(
                                    Reference.FluidColor.CHEESE_BASE.toFloatValues().get("red"),
                                    Reference.FluidColor.CHEESE_BASE.toFloatValues().get("green"),
                                    Reference.FluidColor.CHEESE_BASE.toFloatValues().get("blue")
                            )
            ),
            BlockBehaviour.Properties.copy(Blocks.WATER),
            new Item.Properties().tab(CREATIVE_TAB).stacksTo(1)
    );

    public static final FluidRegistryContainer CONDENSED_MILK = new FluidRegistryContainer(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.CONDENSED_MILK).get(FluidUtils.STILL),
            FluidType.Properties.create().canSwim(true).canDrown(true).canPushEntity(true).supportsBoating(true),
            () -> FluidRegistryContainer.createExtension(
                    new FluidRegistryContainer.ClientExtensions(
                            Reference.MODID,
                            FluidUtils.getFluidNames(Reference.UnlocalizedName.CONDENSED_MILK).get(FluidUtils.STILL)
                    ).tint(Reference.FluidColor.CONDENSED_MILK.toIntValue())
                            .fogColor(
                                    Reference.FluidColor.CONDENSED_MILK.toFloatValues().get("red"),
                                    Reference.FluidColor.CONDENSED_MILK.toFloatValues().get("green"),
                                    Reference.FluidColor.CONDENSED_MILK.toFloatValues().get("blue")
                            )
            ),
            BlockBehaviour.Properties.copy(Blocks.WATER),
            new Item.Properties().tab(CREATIVE_TAB).stacksTo(1)
    );

    public static final FluidRegistryContainer CREAM = new FluidRegistryContainer(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.CREAM).get(FluidUtils.STILL),
            FluidType.Properties.create().canSwim(true).canDrown(true).canPushEntity(true).supportsBoating(true),
            () -> FluidRegistryContainer.createExtension(
                    new FluidRegistryContainer.ClientExtensions(
                            Reference.MODID,
                            FluidUtils.getFluidNames(Reference.UnlocalizedName.CREAM).get(FluidUtils.STILL)
                    ).tint(Reference.FluidColor.CREAM.toIntValue())
                            .fogColor(
                                    Reference.FluidColor.CREAM.toFloatValues().get("red"),
                                    Reference.FluidColor.CREAM.toFloatValues().get("green"),
                                    Reference.FluidColor.CREAM.toFloatValues().get("blue")
                            )
            ),
            BlockBehaviour.Properties.copy(Blocks.WATER),
            new Item.Properties().tab(CREATIVE_TAB).stacksTo(1)
    );

    public static final FluidRegistryContainer CULTURED_MILK = new FluidRegistryContainer(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.CULTURED_MILK).get(FluidUtils.STILL),
            FluidType.Properties.create().canSwim(true).canDrown(true).canPushEntity(true).supportsBoating(true),
            () -> FluidRegistryContainer.createExtension(
                    new FluidRegistryContainer.ClientExtensions(
                            Reference.MODID,
                            FluidUtils.getFluidNames(Reference.UnlocalizedName.CULTURED_MILK).get(FluidUtils.STILL)
                    ).tint(Reference.FluidColor.CULTURED_MILK.toIntValue())
                            .fogColor(
                                    Reference.FluidColor.CULTURED_MILK.toFloatValues().get("red"),
                                    Reference.FluidColor.CULTURED_MILK.toFloatValues().get("green"),
                                    Reference.FluidColor.CULTURED_MILK.toFloatValues().get("blue")
                            )
            ),
            BlockBehaviour.Properties.copy(Blocks.WATER),
            new Item.Properties().tab(CREATIVE_TAB).stacksTo(1)
    );

    public static final FluidRegistryContainer KUMIS = new FluidRegistryContainer(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.KUMIS).get(FluidUtils.STILL),
            FluidType.Properties.create().canSwim(true).canDrown(true).canPushEntity(true).supportsBoating(true),
            () -> FluidRegistryContainer.createExtension(
                    new FluidRegistryContainer.ClientExtensions(
                            Reference.MODID,
                            FluidUtils.getFluidNames(Reference.UnlocalizedName.KUMIS).get(FluidUtils.STILL)
                    ).tint(Reference.FluidColor.KUMIS.toIntValue())
                            .fogColor(
                                    Reference.FluidColor.KUMIS.toFloatValues().get("red"),
                                    Reference.FluidColor.KUMIS.toFloatValues().get("green"),
                                    Reference.FluidColor.KUMIS.toFloatValues().get("blue")
                            )
            ),
            BlockBehaviour.Properties.copy(Blocks.WATER),
            new Item.Properties().tab(CREATIVE_TAB).stacksTo(1)
    );

    public static final FluidRegistryContainer MILK = new FluidRegistryContainer(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.MILK).get(FluidUtils.STILL),
            FluidType.Properties.create().canSwim(true).canDrown(true).canPushEntity(true).supportsBoating(true),
            () -> FluidRegistryContainer.createExtension(
                    new FluidRegistryContainer.ClientExtensions(
                            Reference.MODID,
                            FluidUtils.getFluidNames(Reference.UnlocalizedName.MILK).get(FluidUtils.STILL)
                    ).tint(Reference.FluidColor.MILK.toIntValue())
                            .fogColor(
                                    Reference.FluidColor.MILK.toFloatValues().get("red"),
                                    Reference.FluidColor.MILK.toFloatValues().get("green"),
                                    Reference.FluidColor.MILK.toFloatValues().get("blue")
                            )
            ),
            BlockBehaviour.Properties.copy(Blocks.WATER),
            new Item.Properties().tab(CREATIVE_TAB).stacksTo(1)
    );

    public static final FluidRegistryContainer RENNET = new FluidRegistryContainer(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.RENNET).get(FluidUtils.STILL),
            FluidType.Properties.create().canSwim(true).canDrown(true).canPushEntity(true).supportsBoating(true),
            () -> FluidRegistryContainer.createExtension(
                    new FluidRegistryContainer.ClientExtensions(
                            Reference.MODID,
                            FluidUtils.getFluidNames(Reference.UnlocalizedName.RENNET).get(FluidUtils.STILL)
                    ).tint(
                            Reference.FluidColor.RENNET.toIntValue()
                    ).fogColor(
                            Reference.FluidColor.RENNET.toFloatValues().get("red"),
                            Reference.FluidColor.RENNET.toFloatValues().get("green"),
                            Reference.FluidColor.RENNET.toFloatValues().get("blue")
                    )
            ),
            BlockBehaviour.Properties.copy(Blocks.WATER),
            new Item.Properties().tab(CREATIVE_TAB).stacksTo(1)
    );

    public static final FluidRegistryContainer SKIM_MILK = new FluidRegistryContainer(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.SKIM_MILK).get(FluidUtils.STILL),
            FluidType.Properties.create().canSwim(true).canDrown(true).canPushEntity(true).supportsBoating(true),
            () -> FluidRegistryContainer.createExtension(
                    new FluidRegistryContainer.ClientExtensions(
                            Reference.MODID,
                            FluidUtils.getFluidNames(Reference.UnlocalizedName.SKIM_MILK).get(FluidUtils.STILL)
                    ).tint(Reference.FluidColor.SKIM_MILK.toIntValue())
                            .fogColor(
                                    Reference.FluidColor.SKIM_MILK.toFloatValues().get("red"),
                                    Reference.FluidColor.SKIM_MILK.toFloatValues().get("green"),
                                    Reference.FluidColor.SKIM_MILK.toFloatValues().get("blue")
                            )
            ),
            BlockBehaviour.Properties.copy(Blocks.WATER),
            new Item.Properties().tab(CREATIVE_TAB).stacksTo(1)
    );

    public static final FluidRegistryContainer WHEY = new FluidRegistryContainer(
            FluidUtils.getFluidNames(Reference.UnlocalizedName.WHEY).get(FluidUtils.STILL),
            FluidType.Properties.create().canSwim(true).canDrown(true).canPushEntity(true).supportsBoating(true),
            () -> FluidRegistryContainer.createExtension(
                    new FluidRegistryContainer.ClientExtensions(
                            Reference.MODID,
                            FluidUtils.getFluidNames(Reference.UnlocalizedName.WHEY).get(FluidUtils.STILL)
                    ).tint(Reference.FluidColor.WHEY.toIntValue())
                            .fogColor(
                                    Reference.FluidColor.WHEY.toFloatValues().get("red"),
                                    Reference.FluidColor.WHEY.toFloatValues().get("green"),
                                    Reference.FluidColor.WHEY.toFloatValues().get("blue")
                            )
            ),
            BlockBehaviour.Properties.copy(Blocks.WATER),
            new Item.Properties().tab(CREATIVE_TAB).stacksTo(1)
    );

    private GrowthcraftMilkFluids() {
        /* Prevent default public constructor */
    }
}
