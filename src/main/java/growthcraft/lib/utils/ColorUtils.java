package growthcraft.lib.utils;

import net.minecraft.client.color.item.ItemColor;

import java.awt.*;

public class ColorUtils {
    public static class GrowthcraftColor {

        private final Color color;
        private final int colorIntValue;

        public GrowthcraftColor(int colorIntValue) {
            color = new Color(colorIntValue);
            this.colorIntValue = colorIntValue;
        }

        public int toIntValue() {
            return colorIntValue;
        }

        public Color getColor() {
            return color;
        }

        public ItemColor toItemColor() {
            return (itemStack, layer) -> layer == 0 ? colorIntValue : 0xFFFFFFFF;
        }

    }
}
