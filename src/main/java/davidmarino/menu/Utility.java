/**
 * Authors: David Allen Stephan Marino
 * Date: 6/7/25
 */

package davidmarino.menu;

import java.util.Scanner;

public class Utility {

    public static final Scanner scanner = new Scanner(System.in);

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void pauseForEnter(String message) {
        System.out.print("\n" + message + " Press Enter to continue...");
        scanner.nextLine();
    }

    public static void printHeader(String title) {
        System.out.println("=".repeat(30));
        System.out.printf("=== %-24s ===%n", title);
        System.out.println("=".repeat(30));
    }
}
