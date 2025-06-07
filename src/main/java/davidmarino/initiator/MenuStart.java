/**
 * Authors: David Allen Stephan Marino
 * Date: 5/31/25
 */

package davidmarino.initiator;

import lombok.Data;

import java.io.File;
import java.util.Scanner;

@Data
public class MenuStart {
    private static final Scanner scanner = new Scanner(System.in);

    // Clears the screen using ANSI escape codes
    private static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private static void pauseForEnter(String message) {
        System.out.print("\n" + message + " Press Enter to continue...");
        scanner.nextLine();
    }

    public static boolean showMainMenu() {
        clearScreen();
        printHeader("MAIN MENU");
        System.out.println("1. ▶ Gameplay");
        System.out.println("2. 🛠 Creation");
        System.out.println("3. ⚙ Settings");
        System.out.println("4. ❌ Exit");

        System.out.print("\nChoose an option: ");
        String input = scanner.nextLine();

        switch (input) {
            case "1" -> showGameplayMenu();
            case "2" -> showCreationMenu();
            case "3" -> showSettingsMenu();
            case "4" -> {
                return false;
            }
            default -> pauseForEnter("❗ Invalid input.");
        }
        return true;
    }

    public static void showGameOptions() {
        clearScreen();
        File folder = new File("src/main/resources/games");
        File[] listOfFiles = folder.listFiles();
        if (listOfFiles == null || listOfFiles.length == 0) {
            System.out.println("No games found in the directory.");
            return;
        }
        System.out.println("Select a game to start:\n");
        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                System.out.println((i + 1) + ". " + listOfFiles[i].getName());
            }
        }
        Scanner scanner = new Scanner(System.in);
        int choice = -1;
        while (true) {
            System.out.print("\nEnter the number of the game to start: ");
            try {
                choice = Integer.parseInt(scanner.nextLine());
                if (choice >= 1 && choice <= listOfFiles.length) {
                    break;
                } else {
                    System.out.println("Invalid number. Please choose between 1 and " + listOfFiles.length);
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }

        File selectedGame = listOfFiles[choice - 1];
        startGame(selectedGame);
    }

    public static void showGameplayMenu() {
        clearScreen();
        printHeader("GAMEPLAY MENU");
        System.out.println("1. 🎮 Start Game");
        System.out.println("2. 💾 Load Game");
        System.out.println("3. 🔙 Back");

        System.out.print("\nChoose an option: ");
        String input = scanner.nextLine();

        switch (input) {
            case "1" -> showGameOptions();
            case "2" -> {
                pauseForEnter("💾 Load Game selected.");
            }
            case "3" -> {
                return;
            }
            default -> pauseForEnter("❗ Invalid input.");
        }
    }

    private static void startGame(File selectedGame) {
        clearScreen();
        printHeader("STARTING GAME");
        GameplayStart.startGame(selectedGame);
        pauseForEnter("Game started.");
    }

    private static void showCreationMenu() {
        clearScreen();
        printHeader("CREATION MENU");
        System.out.println("1. 🧱 New Level");
        System.out.println("2. ✏️ Edit Level");
        System.out.println("3. 🔙 Back");

        System.out.print("\nChoose an option: ");
        String input = scanner.nextLine();

        switch (input) {
            case "1" -> startCreation();
            case "2" -> pauseForEnter("✏️ Edit Level selected.");
            case "3" -> {
                return;
            }
            default -> pauseForEnter("❗ Invalid input.");
        }
    }

    private static void startCreation() {
        clearScreen();
        printHeader("LEVEL CREATION");
        CreationStart.start();
        pauseForEnter("Returning to Creation Menu.");
    }

    private static void showSettingsMenu() {
        clearScreen();
        printHeader("SETTINGS MENU");
        System.out.println("1. 🔊 Audio");
        System.out.println("2. 💡 Display");
        System.out.println("3. 🔙 Back");

        System.out.print("\nChoose an option: ");
        String input = scanner.nextLine();

        switch (input) {
            case "1" -> pauseForEnter("🔊 Audio settings selected.");
            case "2" -> pauseForEnter("💡 Display settings selected.");
            case "3" -> {
                return;
            }
            default -> pauseForEnter("❗ Invalid input.");
        }
    }

    private static void printHeader(String title) {
        System.out.println("=".repeat(30));
        System.out.printf("=== %-24s ===%n", title);
        System.out.println("=".repeat(30));
    }

    private static void printLeaderboard() {

    }
}
