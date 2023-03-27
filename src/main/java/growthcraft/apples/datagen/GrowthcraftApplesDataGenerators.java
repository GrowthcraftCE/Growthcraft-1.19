package growthcraft.apples.datagen;

import growthcraft.apples.shared.Reference;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.concurrent.CompletableFuture;

@Mod.EventBusSubscriber(modid = Reference.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class GrowthcraftApplesDataGenerators {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        //generator.addProvider(true, new GrowthcraftApplesRecipeProvider(packOutput));
        //generator.addProvider(true, GrowthcraftApplesLootTableProvider.create(packOutput));
        //generator.addProvider(true, new GrowthcraftApplesBlockStateProvider(packOutput, existingFileHelper));
        //generator.addProvider(true, new GrowthcraftApplesItemModelProvider(packOutput, existingFileHelper));
        //generator.addProvider(event.includeServer(), new GrowthcraftApplesWorldGenProvider(packOutput, lookupProvider));

    }

    private GrowthcraftApplesDataGenerators() {
        /* Prevent generation of default public constructor. */
    }
}
