package growthcraft.cellar.init;

import growthcraft.cellar.block.entity.CultureJarBlockEntity;
import growthcraft.cellar.shared.Reference;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class GrowthcraftCellarBlockEntities {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(
            ForgeRegistries.BLOCK_ENTITY_TYPES, Reference.MODID
    );

    public static final RegistryObject<BlockEntityType<CultureJarBlockEntity>> CULTURE_JAR_BLOCK_ENTITY = BLOCK_ENTITIES.register(
            Reference.UnlocalizedName.CULTURE_JAR,
            () -> BlockEntityType.Builder.of(
                    CultureJarBlockEntity::new,
                    GrowthcraftCellarBlocks.CULTURE_JAR.get()
            ).build(null)
    );

    public GrowthcraftCellarBlockEntities() {
        /* Disable automatic default public constructor */
    }

}
