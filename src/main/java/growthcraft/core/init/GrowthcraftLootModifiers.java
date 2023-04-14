package growthcraft.core.init;

import com.mojang.serialization.Codec;
import growthcraft.core.loot.GlobalLootModifier;
import growthcraft.core.shared.Reference;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class GrowthcraftLootModifiers {
    public static final DeferredRegister<Codec<? extends IGlobalLootModifier>> LOOT_MODIFIER_SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, Reference.MODID);

    public static final RegistryObject<Codec<? extends IGlobalLootModifier>> GLOBAL_BLOCK_LOOT_MODIFIER =
            LOOT_MODIFIER_SERIALIZERS.register("global_block_loot_modifier", GlobalLootModifier.LOOT_MODIFIER);

    public static void register(IEventBus bus) {
        LOOT_MODIFIER_SERIALIZERS.register(bus);
    }
}
