package growthcraft.apples.init;

import growthcraft.apples.block.entity.AppleBeeBoxBlockEntity;
import growthcraft.apples.shared.Reference;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class GrowthcraftApplesBlockEntities {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(
            ForgeRegistries.BLOCK_ENTITIES, Reference.MODID
    );

    public static final RegistryObject<BlockEntityType<AppleBeeBoxBlockEntity>> BEE_BOX_BLOCK_ENTITY = BLOCK_ENTITIES.register(
            Reference.UnlocalizedName.BEE_BOX,
            () -> BlockEntityType.Builder.of(AppleBeeBoxBlockEntity::new,
                    GrowthcraftApplesBlocks.BEE_BOX_APPLE.get()
            ).build(null)
    );

    private GrowthcraftApplesBlockEntities() {
        /* Disable automatic default public constructor */
    }
}
