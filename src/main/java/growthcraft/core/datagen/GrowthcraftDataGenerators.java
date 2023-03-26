package growthcraft.core.datagen;

import growthcraft.core.datagen.providers.*;
import growthcraft.core.shared.Reference;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.concurrent.CompletableFuture;

@Mod.EventBusSubscriber(modid = Reference.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class GrowthcraftDataGenerators {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        generator.addProvider(true, new GrowthcraftRecipeProvider(packOutput));
        generator.addProvider(true, GrowthcraftLootTableProvider.create(packOutput));
        generator.addProvider(true, new GrowthcraftBlockStateProvider(packOutput, existingFileHelper));
        generator.addProvider(true, new GrowthcraftItemModelProvider(packOutput, existingFileHelper));
        generator.addProvider(event.includeServer(), new GrowthcraftWorldGenProvider(packOutput, lookupProvider));

    }

    private GrowthcraftDataGenerators() {
        /* Prevent generation of default public constructor. */
    }
}
