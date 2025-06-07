/**
 * Authors: David Allen Stephan Marino
 * Date: 6/6/25
 */

package davidmarino.utility;

import static davidmarino.utility.AnsiColor.COLORS;
import static davidmarino.utility.AnsiColor.RESET;

public class Display {
    public static String colorize(int nutValue, String symbol) {
        return COLORS[nutValue] + symbol + RESET;
    }
    public static StringBuilder gameplayTitle(StringBuilder sb) {
        sb.append("\u001b[H"); // Move cursor to home
        sb.append("\u001b[2J"); // Clear screen
        sb.append("░██████╗░█████╗░██████╗░███████╗░██╗░░░░░░░██╗░██████╗\n");
        sb.append("██╔════╝██╔══██╗██╔══██╗██╔════╝░██║░░██╗░░██║██╔════╝\n");
        sb.append("╚█████╗░██║░░╚═╝██████╔╝█████╗░░░╚██╗████╗██╔╝╚█████╗░\n");
        sb.append("░╚═══██╗██║░░██╗██╔══██╗██╔══╝░░░░████╔═████║░░╚═══██╗\n");
        sb.append("██████╔╝╚█████╔╝██║░░██║███████╗░░╚██╔╝░╚██╔╝░██████╔╝\n");
        sb.append("╚═════╝░░╚════╝░╚═╝░░╚═╝╚══════╝░░░╚═╝░░░╚═╝░░╚═════╝░\n");
        sb.append("\n\n\n");
        return sb;
    }
}
