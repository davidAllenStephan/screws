/**
 * Authors: David Allen Stephan Marino
 * Date: 6/7/25
 */

package davidmarino.menu;

import static davidmarino.menu.Utility.*;

public class MainMenu {
    public static boolean showMainMenu() {
        clearScreen();
        printHeader("MAIN MENU");
        System.out.println("1. ▶ Gameplay");
        System.out.println("2. 🛠 Creation");
        System.out.println("3. ❌ Exit");

        System.out.print("\nChoose an option: ");
        String input = scanner.nextLine();

        switch (input) {
            case "1" -> GameplayMenu.show();
            case "2" -> CreationMenu.show();
            case "3" -> {
                return false;
            }
            default -> pauseForEnter("❗ Invalid input.");
        }

        return true;
    }
}
