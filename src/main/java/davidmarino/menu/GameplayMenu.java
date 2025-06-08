/**
 * Authors: David Allen Stephan Marino
 * Date: 6/7/25
 */

package davidmarino.menu;

import static davidmarino.menu.Utility.*;

public class GameplayMenu {
    public static void show() {
        clearScreen();
        printHeader("GAMEPLAY MENU");
        System.out.println("1. 🎮 Select Game");
        System.out.println("2. 🔙 Back");

        System.out.print("\nChoose an option: ");
        String input = scanner.nextLine();

        switch (input) {
            case "1" -> GameSelectionMenu.show();
            case "2" -> {
                return;
            }
            default -> pauseForEnter("❗ Invalid input.");
        }
    }
}
