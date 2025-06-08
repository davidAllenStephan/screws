/**
 * Authors: David Allen Stephan Marino
 * Date: 6/7/25
 */

package davidmarino.menu;

import static davidmarino.menu.Utility.*;

public class CreationMenu {
    public static void show() {
        clearScreen();
        printHeader("CREATION MENU");
        System.out.println("1. 🧱 New Level");
        System.out.println("2. ✏️ Edit Level");
        System.out.println("3. 🔙 Back");

        System.out.print("\nChoose an option: ");
        String input = scanner.nextLine();

        switch (input) {
            case "1" -> StartInitiator.startCreation();
            case "2" -> pauseForEnter("✏️ Edit Level selected.");
            case "3" -> {
                return;
            }
            default -> pauseForEnter("❗ Invalid input.");
        }
    }
}
