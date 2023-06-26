package growthcraft.lib.utils;

import com.mojang.math.Vector3f;
import net.minecraft.client.color.item.ItemColor;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

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

        public Map<String, Float> toFloatValues() {
            Map<String, Float> floatMap = new HashMap<String, Float>();
            floatMap.put("red", this.color.getRed() / 255.0F);
            floatMap.put("green", this.color.getGreen() / 255.0F);
            floatMap.put("blue", this.color.getBlue() / 255.0F);
            floatMap.put("alpha", this.color.getAlpha() / 255.0F);
            return floatMap;
        }

        public Vector3f toVectorColor() {
            return new Vector3f(color.getRed(), color.getGreen(), color.getBlue());
        }

        public Color getColor() {
            return color;
        }

        public ItemColor toItemColor() {
            return (itemStack, layer) -> layer == 0 ? colorIntValue : 0xFFFFFFFF;
        }

    }
}
