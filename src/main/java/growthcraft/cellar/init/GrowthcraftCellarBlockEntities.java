package growthcraft.cellar.init;

import growthcraft.cellar.block.entity.*;
import growthcraft.cellar.shared.Reference;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class GrowthcraftCellarBlockEntities {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(
            ForgeRegistries.BLOCK_ENTITY_TYPES, Reference.MODID
    );

    public static final RegistryObject<BlockEntityType<BrewKettleBlockEntity>> BREW_KETTLE_BLOCK_ENTITY = BLOCK_ENTITIES.register(
            Reference.UnlocalizedName.BREW_KETTLE,
            () -> BlockEntityType.Builder.of(
                    BrewKettleBlockEntity::new,
                    GrowthcraftCellarBlocks.BREW_KETTLE.get()
            ).build(null)
    );

    public static final RegistryObject<BlockEntityType<CultureJarBlockEntity>> CULTURE_JAR_BLOCK_ENTITY = BLOCK_ENTITIES.register(
            Reference.UnlocalizedName.CULTURE_JAR,
            () -> BlockEntityType.Builder.of(
                    CultureJarBlockEntity::new,
                    GrowthcraftCellarBlocks.CULTURE_JAR.get()
            ).build(null)
    );

    public static final RegistryObject<BlockEntityType<FermentationBarrelBlockEntity>> FERMENTATION_BARREL_BLOCK_ENTITY = BLOCK_ENTITIES.register(
            Reference.UnlocalizedName.FERMENT_BARREL,
            () -> BlockEntityType.Builder.of(
                    FermentationBarrelBlockEntity::new,
                    GrowthcraftCellarBlocks.FERMENTATION_BARREL_OAK.get()
            ).build(null)
    );

    public static final RegistryObject<BlockEntityType<FruitPressBlockEntity>> FRUIT_PRESS_BLOCK_ENTITY = BLOCK_ENTITIES.register(
            Reference.UnlocalizedName.FRUIT_PRESS,
            () -> BlockEntityType.Builder.of(
                    FruitPressBlockEntity::new,
                    GrowthcraftCellarBlocks.FRUIT_PRESS.get()
            ).build(null)
    );

    public static final RegistryObject<BlockEntityType<RoasterBlockEntity>> ROASTER_BLOCK_ENTITY = BLOCK_ENTITIES.register(
            Reference.UnlocalizedName.ROASTER,
            () -> BlockEntityType.Builder.of(
                    RoasterBlockEntity::new,
                    GrowthcraftCellarBlocks.ROASTER.get()
            ).build(null)
    );

    public GrowthcraftCellarBlockEntities() {
        /* Disable automatic default public constructor */
    }

}
