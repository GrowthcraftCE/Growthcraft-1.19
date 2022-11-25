package growthcraft.apples.shared;

import growthcraft.lib.utils.ColorUtils;
import org.codehaus.plexus.util.StringUtils;

public class Reference {
    public static final String NAME_SHORT = "apples";

    public static final String MODID = growthcraft.core.shared.Reference.MODID + "_" + NAME_SHORT;

    public static final String NAME = String.format("%s %s",
            growthcraft.core.shared.Reference.NAME,
            StringUtils.capitalise(NAME_SHORT));

    public static final String VERSION = growthcraft.core.shared.Reference.VERSION;

    public static class UnlocalizedName {

        public static final String APPLE_CIDER = "apple_cider";
        public static final String APPLE_JUICE = "apple_juice";
        public static final String APPLE_PLANK = "apple_plank";
        public static final String APPLE_PLANK_BUTTON = "apple_plank_button";
        public static final String APPLE_PLANK_DOOR = "apple_plank_door";
        public static final String APPLE_PLANK_FENCE = "apple_plank_fence";
        public static final String APPLE_PLANK_FENCE_GATE = "apple_plank_fence_gate";
        public static final String APPLE_PLANK_FENCE_ROPE_LINEN = "apple_plank_fence_rope_linen";
        public static final String APPLE_PLANK_PRESSURE_PLATE = "apple_plank_pressure_plate";
        public static final String APPLE_PLANK_SLAB = "apple_plank_slab";
        public static final String APPLE_PLANK_STAIRS = "apple_plank_stairs";
        public static final String APPLE_PLANK_TRAPDOOR = "apple_plank_trapdoor";
        public static final String APPLE_SEEDS = "apple_seeds";
        public static final String APPLE_TREE_FRUIT = "apple_tree_fruit";
        public static final String APPLE_TREE_LEAVES = "apple_tree_leaves";
        public static final String APPLE_TREE_SAPLING = "apple_tree_sapling";
        public static final String APPLE_WOOD = "apple_wood";
        public static final String APPLE_WOOD_STRIPPED = "apple_wood_stripped";
        public static final String APPLE_WOOD_LOG = "apple_wood_log";
        public static final String APPLE_WOOD_LOG_STRIPPED = "apple_wood_log_stripped";
        public static final String BEE_BOX = "bee_box";
        public static final String BEE_BOX_APPLE = "bee_box_apple";

        private UnlocalizedName() {
            /* Disable Automatic Creation of Public Constructor */
        }
    }

    public static class FluidColor {

        public static final ColorUtils.GrowthcraftColor APPLE_JUICE_FLUID_COLOR = new ColorUtils.GrowthcraftColor(0xFFFFD627);
        public static final ColorUtils.GrowthcraftColor APPLE_CIDER_FLUID_COLOR = new ColorUtils.GrowthcraftColor(0xFFDF9C40);

        private FluidColor() {
            /* Disable Automatic Creation of Public Constructor */
        }
    }

    private Reference() {
        /* Disable Automatic Creation of Public Constructor */
    }
}
