package growthcraft.lib.utils;

public class RecipeUtils {
    public static enum Category {
        FLUID("fluid"), ITEM("item"), NULL("null");

        private final String value;

        Category(final String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return value;
        }

        public static Category with(String value) {
            return switch (value) {
                case "fluid" -> FLUID;
                case "item" -> ITEM;
                case "null" -> NULL;
                default -> null;
            };
        }
    }

    public static boolean isNull(Category category) {
        return category.equals(Category.NULL);
    }

    public static boolean isNotNull(Category category) {
        return !category.equals(Category.NULL);
    }
}
