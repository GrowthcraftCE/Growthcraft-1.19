package growthcraft.rice.shared;

import growthcraft.lib.utils.ColorUtils;
import org.codehaus.plexus.util.StringUtils;

public class Reference {
    public static final String NAME_SHORT = "rice";

    public static final String MODID = growthcraft.core.shared.Reference.MODID + "_" + NAME_SHORT;
    public static final String NAME = String.format("%s %s",
            growthcraft.core.shared.Reference.NAME,
            StringUtils.capitalise(NAME_SHORT));

    public static final String VERSION = growthcraft.core.shared.Reference.VERSION;

    public static class UnlocalizedName {
        public static final String CULTIVATED_FARMLAND = "cultivated_farmland";
        public static final String CULTIVATOR = "cultivator";
        public static final String KNIFE = "knife";
        public static final String RICE = "rice";
        public static final String RICE_COOKED = "rice_cooked";
        public static final String RICE_CROP = "rice_crop";
        public static final String RICE_STALK = "rice_stalk";
        public static final String SUSHI_ROLL = "sushi_roll";
        // Fluids
        public static final String RICE_WATER = "rice_water";
        public static final String RICE_WINE = "rice_wine";
        public static final String SAKE = "sake";

        private UnlocalizedName() {
            /* Disable Automatic Creation of Public Constructor */
        }
    }

    public static class FluidColor {
        public static final ColorUtils.GrowthcraftColor RICE_WATER_FLUID_COLOR = new ColorUtils.GrowthcraftColor(0xFFF6F8ED);
        public static final ColorUtils.GrowthcraftColor RICE_WINE_FLUID_COLOR = new ColorUtils.GrowthcraftColor(0xFFD9DADB);
        public static final ColorUtils.GrowthcraftColor SAKE_FLUID_COLOR = new ColorUtils.GrowthcraftColor(0xFFEAECEC);

        private FluidColor() {
            /* Disable Automatic Creation of Public Constructor */
        }
    }

    private Reference() {
        /* Disable Automatic Creation of Public Constructor */
    }
}
