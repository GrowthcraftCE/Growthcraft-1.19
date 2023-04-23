package growthcraft.bamboo.shared;

import org.codehaus.plexus.util.StringUtils;

public class Reference {
    public static final String NAME_SHORT = "bamboo";

    public static final String MODID = growthcraft.core.shared.Reference.MODID + "_" + NAME_SHORT;

    public static final String NAME = String.format("%s %s",
            growthcraft.core.shared.Reference.NAME,
            StringUtils.capitalise(NAME_SHORT));

    public static final String VERSION = growthcraft.core.shared.Reference.VERSION;

    public static class UnlocalizedName {
        public static final String BAMBOO_PLANK = "bamboo_plank";
        public static final String BAMBOO_PLANK_BUTTON = "bamboo_plank_button";
        public static final String BAMBOO_PLANK_DOOR = "bamboo_plank_door";
        public static final String BAMBOO_PLANK_FENCE = "bamboo_plank_fence";
        public static final String BAMBOO_PLANK_FENCE_ROPE_LINEN = "bamboo_plank_fence_rope_linen";

        public static final String BAMBOO_PLANK_FENCE_GATE = "bamboo_plank_fence_gate";
        public static final String BAMBOO_PLANK_PRESSURE_PLATE = "bamboo_plank_pressure_plate";
        public static final String BAMBOO_PLANK_SLAB = "bamboo_plank_slab";
        public static final String BAMBOO_PLANK_STAIRS = "bamboo_plank_stairs";
        public static final String BAMBOO_PLANK_TRAPDOOR = "bamboo_plank_trapdoor";
        public static final String BAMBOO_SEEDS = "bamboo_seeds";
        public static final String BAMBOO_TREE_FRUIT = "bamboo_tree_fruit";
        public static final String BAMBOO_TREE_LEAVES = "bamboo_tree_leaves";
        public static final String BAMBOO_TREE_SAPLING = "bamboo_tree_sapling";
        public static final String BAMBOO_WOOD = "bamboo_wood";
        public static final String BAMBOO_WOOD_STRIPPED = "bamboo_wood_stripped";
        public static final String BAMBOO_WOOD_LOG = "bamboo_wood_log";
        public static final String BAMBOO_WOOD_LOG_STRIPPED = "bamboo_wood_log_stripped";
        public static final String BAMBOO_PLANK_BEE_BOX = "bee_box_bamboo";

        private UnlocalizedName() {
            /* Disable Automatic Creation of Public Constructor */
        }
    }

    public static class FluidColor {

        private FluidColor() {
            /* Disable Automatic Creation of Public Constructor */
        }
    }

    private Reference() {
        /* Disable Automatic Creation of Public Constructor */
    }
}
