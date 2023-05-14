package growthcraft.milk.init;

import growthcraft.milk.block.entity.ChurnBlockEntity;
import growthcraft.milk.block.entity.PancheonBlockEntity;
import growthcraft.milk.shared.Reference;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class GrowthcraftMilkBlockEntities {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(
            ForgeRegistries.BLOCK_ENTITY_TYPES, Reference.MODID
    );

    public static final RegistryObject<BlockEntityType<ChurnBlockEntity>> CHURN_BLOCK_ENTITY = BLOCK_ENTITIES.register(
            Reference.UnlocalizedName.CHURN,
            () -> BlockEntityType.Builder.of(
                    ChurnBlockEntity::new,
                    GrowthcraftMilkBlocks.CHURN.get()
            ).build(null)
    );

    public static final RegistryObject<BlockEntityType<PancheonBlockEntity>> PANCHEON_BLOCK_ENTITY = BLOCK_ENTITIES.register(
            Reference.UnlocalizedName.PANCHEON,
            () -> BlockEntityType.Builder.of(
                    PancheonBlockEntity::new,
                    GrowthcraftMilkBlocks.PANCHEON.get()
            ).build(null)
    );

    public GrowthcraftMilkBlockEntities() {
        /* Disable automatic default public constructor */
    }

}
