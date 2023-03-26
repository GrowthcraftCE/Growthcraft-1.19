package growthcraft.cellar.shared;

import growthcraft.lib.utils.ColorUtils;
import org.codehaus.plexus.util.StringUtils;

public class Reference {
    public static final String NAME_SHORT = "cellar";

    public static final String MODID = growthcraft.core.shared.Reference.MODID + "_" + NAME_SHORT;

    public static final String NAME = String.format("%s %s",
            growthcraft.core.shared.Reference.NAME,
            StringUtils.capitalise(NAME_SHORT));

    public static final String VERSION = growthcraft.core.shared.Reference.VERSION;

    public static class UnlocalizedName {
        public static final String AMBER_ALE = "amber_ale";
        public static final String AMBER_LAGER = "amber_lager";
        public static final String AMBER_WORT = "amber_wort";
        public static final String BREW_KETTLE = "brew_kettle";
        public static final String BREW_KETTLE_LID = "brew_kettle_lid";
        public static final String BREW_KETTLE_RECIPE = "brew_kettle_recipe";
        public static final String BROWN_ALE = "brown_ale";
        public static final String BROWN_LAGER = "brown_lager";
        public static final String BROWN_WORT = "brown_wort";
        public static final String COPPER_ALE = "copper_ale";
        public static final String COPPER_LAGER = "copper_lager";
        public static final String COPPER_WORT = "copper_wort";
        public static final String CULTURE_JAR = "culture_jar";
        public static final String CULTURE_JAR_RECIPE = "culture_jar_recipe";
        public static final String DARK_LAGER = "dark_lager";
        public static final String DARK_WORT = "dark_wort";
        public static final String DEEP_AMBER_WORT = "deep_amber_wort";
        public static final String DEEP_COPPER_WORT = "deep_copper_wort";
        public static final String FERMENT_BARREL = "barrel_ferment";
        public static final String FERMENT_BARREL_OAK = "barrel_ferment_oak";
        public static final String FERMENT_BARREL_RECIPE = "ferment_barrel_recipe";
        public static final String FRUIT_PRESS = "fruit_press";
        public static final String FRUIT_PRESS_PISTON = "fruit_press_piston";
        public static final String FRUIT_PRESS_RECIPE = "fruit_press_recipe";
        public static final String GOLDEN_WORT = "golden_wort";
        public static final String GRAIN = "grain";
        public static final String GRAIN_AMBER = "grain_amber";
        public static final String GRAIN_BROWN = "grain_brown";
        public static final String GRAIN_COPPER = "grain_copper";
        public static final String GRAIN_DARK = "grain_dark";
        public static final String GRAIN_DEEP_AMBER = "grain_deep_amber";
        public static final String GRAIN_DEEP_COPPER = "grain_deep_copper";
        public static final String GRAIN_GOLDEN = "grain_golden";
        public static final String GRAIN_PALE_GOLDEN = "grain_pale_golden";
        public static final String GRAPE_PURPLE = "grape_purple";
        public static final String GRAPE_RED = "grape_red";
        public static final String GRAPE_SEEDS_PURPLE = "grape_seeds_purple";
        public static final String GRAPE_SEEDS_RED = "grape_seeds_red";
        public static final String GRAPE_SEEDS_WHITE = "grape_seeds_white";
        public static final String GRAPE_VINE = "grape_vine";
        public static final String GRAPE_VINE_CROP = "grape_vine_crop";
        public static final String GRAPE_VINE_LEAVES = "grape_vine_leaves";
        public static final String GRAPE_WHITE = "grape_white";
        public static final String HOPPED_GOLDEN_WORT = "hopped_golden_wort";
        public static final String HOPS = "hops";
        public static final String HOPS_SEEDS = "hops_seeds";
        public static final String HOPS_VINE = "hops_vine";
        public static final String IPA_ALE = "ipa_ale";
        public static final String LOOT_SERIALIZER_BLOCK = "global_block_loot_modifier";
        public static final String OLD_PORT_ALE = "old_port_ale";
        public static final String PALE_ALE = "pale_ale";
        public static final String PALE_GOLDEN_WORT = "pale_golden_wort";
        public static final String PALE_LAGER = "pale_lager";
        public static final String PILSNER_LAGER = "pilsner_lager";
        public static final String POTION_ALE = "potion_ale";
        public static final String POTION_LAGER = "potion_lager";
        public static final String POTION_WINE = "potion_wine";
        public static final String PURPLE_GRAPE_JUICE = "purple_grape_juice";
        public static final String PURPLE_GRAPE_VINE = "purple_grape_vine";
        public static final String PURPLE_GRAPE_VINE_FRUIT = "purple_grape_vine_crop";
        public static final String PURPLE_GRAPE_VINE_LEAVES = "purple_grape_vine_leaves";
        public static final String PURPLE_GRAPE_WINE = "purple_grape_wine";
        public static final String RED_GRAPE_JUICE = "red_grape_juice";
        public static final String RED_GRAPE_VINE = "red_grape_vine";
        public static final String RED_GRAPE_VINE_FRUIT = "red_grape_vine_crop";
        public static final String RED_GRAPE_VINE_LEAVES = "red_grape_vine_leaves";
        public static final String RED_GRAPE_WINE = "red_grape_wine";
        public static final String ROASTER = "roaster";
        public static final String ROASTER_RECIPE = "roaster_recipe";
        public static final String STOUT_ALE = "stout_ale";
        public static final String VIENNA_LAGER = "vienna_lager";
        public static final String WHITE_GRAPE_JUICE = "white_grape_juice";
        public static final String WHITE_GRAPE_VINE = "white_grape_vine";
        public static final String WHITE_GRAPE_VINE_FRUIT = "white_grape_vine_crop";
        public static final String WHITE_GRAPE_VINE_LEAVES = "white_grape_vine_leaves";
        public static final String WHITE_GRAPE_WINE = "white_grape_wine";
        public static final String WORT = "wort";
        public static final String YEAST_BAYANUS = "yeast_bayanus";
        public static final String YEAST_BAYANUS_ETHEREAL = "yeast_bayanus_ethereal";
        public static final String YEAST_BREWERS = "yeast_brewers";
        public static final String YEAST_BREWERS_ETHEREAL = "yeast_brewers_ethereal";
        public static final String YEAST_ETHEREAL = "yeast_ethereal";
        public static final String YEAST_LAGER = "yeast_lager";
        public static final String YEAST_LAGER_ETHEREAL = "yeast_lager_ethereal";

        private UnlocalizedName() {
            /* Disable Automatic Creation of Public Constructor */
        }
    }

    public static class FluidColor {
        public static final ColorUtils.GrowthcraftColor AMBER_ALE = new ColorUtils.GrowthcraftColor(0xFFBC6633);
        public static final ColorUtils.GrowthcraftColor AMBER_LAGER = new ColorUtils.GrowthcraftColor(0xFFAB5E2F);
        public static final ColorUtils.GrowthcraftColor AMBER_WORT = new ColorUtils.GrowthcraftColor(0xFFE5C7A2);
        public static final ColorUtils.GrowthcraftColor BROWN_ALE = new ColorUtils.GrowthcraftColor(0xFF5C311A);
        public static final ColorUtils.GrowthcraftColor BROWN_LAGER = new ColorUtils.GrowthcraftColor(0xFF401F1C);
        public static final ColorUtils.GrowthcraftColor BROWN_WORT = new ColorUtils.GrowthcraftColor(0xFF594732);
        public static final ColorUtils.GrowthcraftColor COPPER_ALE = new ColorUtils.GrowthcraftColor(0xFF8E4B31);
        public static final ColorUtils.GrowthcraftColor COPPER_LAGER = new ColorUtils.GrowthcraftColor(0xFF6F3D1F);
        public static final ColorUtils.GrowthcraftColor COPPER_WORT = new ColorUtils.GrowthcraftColor(0xFF936B53);
        public static final ColorUtils.GrowthcraftColor DARK_LAGER = new ColorUtils.GrowthcraftColor(0xFF211515);
        public static final ColorUtils.GrowthcraftColor DARK_WORT = new ColorUtils.GrowthcraftColor(0xFF452A19);
        public static final ColorUtils.GrowthcraftColor DEEP_AMBER_WORT = new ColorUtils.GrowthcraftColor(0xFFCFA26F);
        public static final ColorUtils.GrowthcraftColor DEEP_COPPER_WORT = new ColorUtils.GrowthcraftColor(0xFF805C2F);
        public static final ColorUtils.GrowthcraftColor GOLDEN_WORT = new ColorUtils.GrowthcraftColor(0xFFF6D02E);
        public static final ColorUtils.GrowthcraftColor HOPPED_GOLDEN_WORT = new ColorUtils.GrowthcraftColor(0xFFF6FE2E);
        public static final ColorUtils.GrowthcraftColor IPA_ALE = new ColorUtils.GrowthcraftColor(0xFFD2BD2C);
        public static final ColorUtils.GrowthcraftColor OLD_PORT_ALE = new ColorUtils.GrowthcraftColor(0xFF8E3616);
        public static final ColorUtils.GrowthcraftColor PALE_ALE = new ColorUtils.GrowthcraftColor(0xFFFBF855);
        public static final ColorUtils.GrowthcraftColor PALE_GOLDEN_WORT = new ColorUtils.GrowthcraftColor(0xFFFBF855);
        public static final ColorUtils.GrowthcraftColor PALE_LAGER = new ColorUtils.GrowthcraftColor(0xFFF3F33F);
        public static final ColorUtils.GrowthcraftColor PILSNER_LAGER = new ColorUtils.GrowthcraftColor(0xFFF6D02E);
        public static final ColorUtils.GrowthcraftColor PURPLE_GRAPE_JUICE = new ColorUtils.GrowthcraftColor(0xFF682961);
        public static final ColorUtils.GrowthcraftColor PURPLE_GRAPE_WINE = new ColorUtils.GrowthcraftColor(0xFF562251);
        public static final ColorUtils.GrowthcraftColor RED_GRAPE_JUICE = new ColorUtils.GrowthcraftColor(0xFFA63F4A);
        public static final ColorUtils.GrowthcraftColor RED_GRAPE_WINE = new ColorUtils.GrowthcraftColor(0xFF8A343D);
        public static final ColorUtils.GrowthcraftColor STOUT_ALE = new ColorUtils.GrowthcraftColor(0xFF0E0A07);
        public static final ColorUtils.GrowthcraftColor VIENNA_LAGER = new ColorUtils.GrowthcraftColor(0xFF904730);
        public static final ColorUtils.GrowthcraftColor WHITE_GRAPE_JUICE = new ColorUtils.GrowthcraftColor(0xFFB4C91C);
        public static final ColorUtils.GrowthcraftColor WHITE_GRAPE_WINE = new ColorUtils.GrowthcraftColor(0xFF96A817);
        public static final ColorUtils.GrowthcraftColor WORT = new ColorUtils.GrowthcraftColor(0xFFD0AF4E);

        private FluidColor() {
            /* Disable Automatic Creation of Public Constructor */
        }
    }

    public static class GrainColor {
        public static final ColorUtils.GrowthcraftColor GRAIN = new ColorUtils.GrowthcraftColor(0xFF595903);
        public static final ColorUtils.GrowthcraftColor GRAIN_AMBER = new ColorUtils.GrowthcraftColor(0xFFBBA04E);
        public static final ColorUtils.GrowthcraftColor GRAIN_BROWN = new ColorUtils.GrowthcraftColor(0xFF3F2F15);
        public static final ColorUtils.GrowthcraftColor GRAIN_COPPER = new ColorUtils.GrowthcraftColor(0xFF7c6430);
        public static final ColorUtils.GrowthcraftColor GRAIN_DARK = new ColorUtils.GrowthcraftColor(0xFF241802);
        public static final ColorUtils.GrowthcraftColor GRAIN_DEEP_AMBER = new ColorUtils.GrowthcraftColor(0xFF9b813e);
        public static final ColorUtils.GrowthcraftColor GRAIN_DEEP_COPPER = new ColorUtils.GrowthcraftColor(0xFF5d4922);
        public static final ColorUtils.GrowthcraftColor GRAIN_GOLDEN = new ColorUtils.GrowthcraftColor(0xFFdbc05d);
        public static final ColorUtils.GrowthcraftColor GRAIN_PALE_GOLDEN = new ColorUtils.GrowthcraftColor(0xFFfae16e);

        private GrainColor() {
            /* Disable Automatic Creation of Public Constructor */
        }
    }

    private Reference() {
        /* Disable Automatic Creation of Public Constructor */
    }
}
