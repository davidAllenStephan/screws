/**
 * Authors: David Allen Stephan Marino
 * Date: 6/7/25
 */

package davidmarino.menu;

import java.io.File;

import static davidmarino.menu.Utility.*;

public class GameMenu {
    public static void show(File file) {
        clearScreen();
        printHeader("GAME MENU");
        System.out.println("1. Start Game");
        System.out.println("2. View Leaderboard");
        System.out.println("3. Back");
        String input = scanner.nextLine();
        switch (input) {
            case "1" -> StartInitiator.startGame(file);
            case "2" -> LeaderboardMenu.show(file);
            case "3" -> {
                return;
            }
            default -> pauseForEnter("! Invalid input.");
        }
    }
}
