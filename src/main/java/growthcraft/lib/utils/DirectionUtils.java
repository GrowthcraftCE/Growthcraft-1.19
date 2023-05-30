package growthcraft.lib.utils;

import net.minecraft.core.Direction;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DirectionUtils {

    public static List<Direction> SIDES = Stream.of(
            Direction.NORTH,
            Direction.EAST,
            Direction.WEST,
            Direction.SOUTH
    ).collect(Collectors.toList());

    public static boolean isSide(Direction direction) {
        return SIDES.contains(direction);
    }

    public static boolean isTop(Direction direction) {
        return direction == Direction.UP;
    }

    public static boolean isBottom(Direction direction) {
        return direction == Direction.DOWN;
    }
}
