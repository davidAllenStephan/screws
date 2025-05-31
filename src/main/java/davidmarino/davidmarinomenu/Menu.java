/**
 * Authors: David Allen Stephan Marino
 * Date: 5/31/25
 */

package davidmarino.davidmarinomenu;
import davidmarino.davidmarinogameplay.Main;
import lombok.Data;

import java.util.Scanner;

@Data
public class Menu {
    private static final Scanner scanner = new Scanner(System.in);
    private static boolean running = true;

    // Clears the screen using ANSI escape codes
    private static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    // Displays the main menu
    public static boolean showMainMenu() {
        clearScreen();
        System.out.println("=== MAIN MENU ===");
        System.out.println("1. Gameplay");
        System.out.println("2. Creation");
        System.out.println("3. Settings");
        System.out.println("4. Exit");
        System.out.print("\nChoose an option: ");

        String input = scanner.nextLine();

        switch (input) {
            case "1":
                showGameplayMenu();
                break;
            case "2":
                showCreationMenu();
                break;
            case "3":
                showSettingsMenu();
                break;
            case "4":
                return false;
            default:
                System.out.println("Invalid input. Press Enter to continue...");
                scanner.nextLine();
                return true;
        }
        return true;
    }

    private static void showGameplayMenu() {
        clearScreen();
        System.out.println("=== GAMEPLAY MENU ===");
        System.out.println("1. Start Game");
        System.out.println("2. Load Game");
        System.out.println("3. Back");
        System.out.print("\nChoose an option: ");
        String input = scanner.nextLine();
        if ("3".equals(input)) return;
        if ("1".equals(input)) startGame();
        System.out.println("Option " + input + " selected. Press Enter to return...");
        scanner.nextLine();
    }

    private static void startGame() {
        System.out.println("=== START GAME ===");
        Main.startGame();
    }

    private static void showCreationMenu() {
        clearScreen();
        System.out.println("=== CREATION MENU ===");
        System.out.println("1. New Level");
        System.out.println("2. Edit Level");
        System.out.println("3. Back");
        System.out.print("\nChoose an option: ");
        String input = scanner.nextLine();
        if ("3".equals(input)) return;
        if ("1".equals(input)) startCreation();
        System.out.println("Option " + input + " selected. Press Enter to return...");
        scanner.nextLine();
    }

    private static void startCreation() {
        System.out.println("=== CREATION MENU ===");
        davidmarino.davidmarinocreator.Main.start();
    }

    private static void showSettingsMenu() {
        clearScreen();
        System.out.println("=== SETTINGS MENU ===");
        System.out.println("1. Audio");
        System.out.println("2. Display");
        System.out.println("3. Back");
        System.out.print("\nChoose an option: ");
        String input = scanner.nextLine();
        if ("3".equals(input)) return;
        System.out.println("Option " + input + " selected. Press Enter to return...");
        scanner.nextLine();
    }

}
