/**
 * Authors: David Allen Stephan Marino
 * Date: 5/18/25
 */

package davidmarino;

import java.util.HashMap;
import java.util.Map;

public class AnsiColor {
    private static final String[] COLORS = {
            "\u001B[31m", // Red
            "\u001B[32m", // Green
            "\u001B[33m", // Yellow
            "\u001B[34m", // Blue
            "\u001B[35m", // Magenta
            "\u001B[36m", // Cyan
    };
    private static final String RESET = "\u001B[0m";

    private Map<Integer, String> colorMap = new HashMap<>();
    private int nextColorIndex = 0;

    public String getColorForSize(int size) {
        return colorMap.computeIfAbsent(size, s -> {
            String color = COLORS[nextColorIndex % COLORS.length];
            nextColorIndex++;
            return color;
        });
    }

    public String colorize(int size, String symbol) {
        return getColorForSize(size) + symbol + RESET;
    }
}
