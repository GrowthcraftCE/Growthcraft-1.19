package growthcraft.bamboo.init;

import growthcraft.bamboo.block.entity.BambooBeeBoxBlockEntity;
import growthcraft.bamboo.block.entity.BambooRopeBlockEntity;
import growthcraft.bamboo.shared.Reference;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class GrowthcraftBambooBlockEntities {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(
            ForgeRegistries.BLOCK_ENTITY_TYPES, Reference.MODID
    );

    public static final RegistryObject<BlockEntityType<BambooRopeBlockEntity>> BAMBOO_FENCE_ROPE_BLOCK_ENTITY = BLOCK_ENTITIES.register(
            Reference.UnlocalizedName.BAMBOO_PLANK_FENCE_ROPE_LINEN,
            () -> BlockEntityType.Builder.of(BambooRopeBlockEntity::new,
                    GrowthcraftBambooBlocks.BAMBOO_PLANK_FENCE_ROPE_LINEN.get()
            ).build(null)
    );

    public static final RegistryObject<BlockEntityType<BambooBeeBoxBlockEntity>> BAMBOO_BEE_BOX_BLOCK_ENTITY = BLOCK_ENTITIES.register(
            Reference.UnlocalizedName.BAMBOO_PLANK_BEE_BOX,
            () -> BlockEntityType.Builder.of(BambooBeeBoxBlockEntity::new,
                    GrowthcraftBambooBlocks.BAMBOO_PLANK_BEE_BOX.get()
            ).build(null)
    );

    private GrowthcraftBambooBlockEntities() {
        /* Disable automatic default public constructor */
    }
}
