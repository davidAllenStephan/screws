/**
 * Authors: David Allen Stephan Marino
 * Date: 5/18/25
 */

package davidmarino.gameplay.utility;

public class AnsiColor {
    private static final String[] COLORS = {
            "\u001B[31m",     // Red
            "\u001B[32m",     // Green
            "\u001B[33m",     // Yellow
            "\u001B[34m",     // Blue
            "\u001B[35m",     // Magenta
            "\u001B[36m",     // Cyan
            "\u001B[2;31;40m",   // Dim Red
            "\u001B[2;32;40m",   // Dim Green
            "\u001B[2;33;40m",   // Dim Yellow
            "\u001B[2;34;40m",   // Dim Blue
            "\u001B[2;35;40m",   // Dim Magenta
            "\u001B[2;36;40m",   // Dim Cyan
            "\u001B[1;31;47m",   // Bright Red
            "\u001B[1;32;47m",   // Bright Green
            "\u001B[1;33;47m",   // Bright Yellow
            "\u001B[1;34;47m",   // Bright Blue
            "\u001B[1;35;47m",   // Bright Magenta
            "\u001B[1;36;47m",   // Bright Cyan
    };

    private static final String RESET = "\u001B[0m";

    public String colorize(int nutValue, String symbol) {
        return COLORS[nutValue] + symbol + RESET;
    }

}
