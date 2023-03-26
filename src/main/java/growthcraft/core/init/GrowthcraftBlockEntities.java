package growthcraft.core.init;

import growthcraft.core.block.entity.RopeBlockEntity;
import growthcraft.core.shared.Reference;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class GrowthcraftBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(
            ForgeRegistries.BLOCK_ENTITY_TYPES, Reference.MODID
    );

    public static final RegistryObject<BlockEntityType<RopeBlockEntity>> ROPE_BLOCK_ENTITY = BLOCK_ENTITIES.register(
            Reference.UnlocalizedName.ROPE,
            () -> BlockEntityType.Builder.of(RopeBlockEntity::new,
                    GrowthcraftBlocks.ROPE_LINEN.get(),
                    GrowthcraftBlocks.ROPE_LINEN_ACACIA_FENCE.get(),
                    GrowthcraftBlocks.ROPE_LINEN_BIRCH_FENCE.get(),
                    GrowthcraftBlocks.ROPE_LINEN_CRIMSON_FENCE.get(),
                    GrowthcraftBlocks.ROPE_LINEN_DARK_OAK_FENCE.get(),
                    GrowthcraftBlocks.ROPE_LINEN_JUNGLE_FENCE.get(),
                    GrowthcraftBlocks.ROPE_LINEN_NETHER_BRICK_FENCE.get(),
                    GrowthcraftBlocks.ROPE_LINEN_OAK_FENCE.get(),
                    GrowthcraftBlocks.ROPE_LINEN_SPRUCE_FENCE.get(),
                    GrowthcraftBlocks.ROPE_LINEN_WARPED_FENCE.get()
            ).build(null)
    );

    private GrowthcraftBlockEntities() {
        /* Disable automatic default public constructor */
    }
}
