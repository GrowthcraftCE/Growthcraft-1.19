package growthcraft.apiary.init;

import growthcraft.apiary.block.entity.BeeBoxBlockEntity;
import growthcraft.apiary.shared.Reference;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class GrowthcraftApiaryBlockEntities {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(
            ForgeRegistries.BLOCK_ENTITIES, Reference.MODID
    );

    public static final RegistryObject<BlockEntityType<BeeBoxBlockEntity>> BEE_BOX_BLOCK_ENTITY = BLOCK_ENTITIES.register(
            Reference.UnlocalizedName.BEE_BOX,
            () -> BlockEntityType.Builder.of(BeeBoxBlockEntity::new,
                    GrowthcraftApiaryBlocks.BEE_BOX_ACACIA.get(),
                    GrowthcraftApiaryBlocks.BEE_BOX_BIRCH.get(),
                    GrowthcraftApiaryBlocks.BEE_BOX_CRIMSON.get(),
                    GrowthcraftApiaryBlocks.BEE_BOX_DARK_OAK.get(),
                    GrowthcraftApiaryBlocks.BEE_BOX_JUNGLE.get(),
                    GrowthcraftApiaryBlocks.BEE_BOX_OAK.get(),
                    GrowthcraftApiaryBlocks.BEE_BOX_SPRUCE.get(),
                    GrowthcraftApiaryBlocks.BEE_BOX_WARPED.get()
                    ).build(null)
    );

    private GrowthcraftApiaryBlockEntities() {
        /* Disable automatic default public constructor */
    }
}
