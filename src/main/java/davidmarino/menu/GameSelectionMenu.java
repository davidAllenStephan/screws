/**
 * Authors: David Allen Stephan Marino
 * Date: 6/7/25
 */

package davidmarino.menu;

import java.io.File;
import java.util.Scanner;

import static davidmarino.menu.Utility.clearScreen;

public class GameSelectionMenu {
    public static void show() {
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
            System.out.print("\nEnter the number of the game to start (add - in front to view leaderboard): ");
            try {
                choice = Integer.parseInt(scanner.nextLine());
                if (choice < 0) {

                }
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
        GameMenu.show(selectedGame);
    }
}
