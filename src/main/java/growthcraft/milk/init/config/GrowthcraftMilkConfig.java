package growthcraft.milk.init.config;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.loading.FMLPaths;

import java.io.File;

public class GrowthcraftMilkConfig {

    public static final ForgeConfigSpec.Builder SERVER_BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SERVER;

    public static final String SERVER_CONFIG = "growthcraft-milk-server.toml";

    private static final String CATEGORY_CHURN = "churn";
    private static final String CATEGORY_MIXING_VAT = "mixing_vat";
    private static final String CATEGORY_PANCHEON = "pancheon";
    private static final String CATEGORY_LOOT_CHANCES = "loot_modifiers";

    private static ForgeConfigSpec.BooleanValue churnGuiEnabled;
    private static ForgeConfigSpec.BooleanValue mixingVatGuiEnabled;
    private static ForgeConfigSpec.BooleanValue mixingVatConsumeActivationItem;
    private static ForgeConfigSpec.BooleanValue pancheonGuiEnabled;
    private static ForgeConfigSpec.BooleanValue stomachLootEnabled;
    private static ForgeConfigSpec.IntValue stomachLootChance;

    static {
        initServerConfig(SERVER_BUILDER);
        SERVER = SERVER_BUILDER.build();
    }

    private GrowthcraftMilkConfig() {
        /* Prevent generation of public constructor */
    }

    public static void loadConfig() {
        loadConfig(SERVER, FMLPaths.CONFIGDIR.get().resolve(SERVER_CONFIG).toString());
    }

    public static void loadConfig(ForgeConfigSpec configSpec, String path) {
        final CommentedFileConfig fileConfig = CommentedFileConfig.builder(
                new File(path)).sync().autosave().writingMode(WritingMode.REPLACE).build();

        fileConfig.load();
        configSpec.setConfig(fileConfig);
    }

    public static void initServerConfig(ForgeConfigSpec.Builder specBuilder) {
        // Init Server Side Configuration
        churnGuiEnabled = specBuilder
                .comment("Set to true to allow users to access the Churn GUI.")
                .define(String.format("%s.%s", CATEGORY_CHURN, "guiEnabled"), true);
        mixingVatGuiEnabled = specBuilder
                .comment("Set to true to allow users to access the Mixing Vat GUI.")
                .define(String.format("%s.%s", CATEGORY_MIXING_VAT, "guiEnabled"), true);
        mixingVatConsumeActivationItem = specBuilder
                .comment("Set to true to allow users to access the Mixing Vat GUI.")
                .define(String.format("%s.%s", CATEGORY_MIXING_VAT, "consumeMixingVatActivator"), false);
        pancheonGuiEnabled = specBuilder
                .comment("Set to true to allow users to access the Pancheon GUI.")
                .define(String.format("%s.%s", CATEGORY_PANCHEON, "guiEnabled"), true);
        stomachLootEnabled = specBuilder
                .comment("Set to true to enable looting of stomach from cows.")
                .define(String.format("%s.%s", CATEGORY_LOOT_CHANCES, "stomachLootEnabled"), true);

        stomachLootChance = specBuilder
                .comment("Chance to loot a stomach from a cow. stomachLootEnabled must be set to true.")
                .defineInRange(String.format("%s.%s", CATEGORY_LOOT_CHANCES, "stomachLootChance"), 5, 0, 100);
    }

    public static boolean isChurnGuiEnabled() {
        return churnGuiEnabled.get();
    }

    public static boolean isPancheonGuiEnabled() {
        return pancheonGuiEnabled.get();
    }

    public static boolean isMixingVatGuiEnabled() {
        return mixingVatGuiEnabled.get();
    }

    public static boolean isConsumeMixingVatActivator() {
        return mixingVatConsumeActivationItem.get();
    }

    public static boolean isStomachLootingEnabled() { return stomachLootEnabled.get(); }

    public static int getStomachLootChance() { return stomachLootChance.get();}
}
