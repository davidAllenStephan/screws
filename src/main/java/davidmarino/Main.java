/**
 * Authors: David Allen Stephan Marino
 * Date: 5/31/25
 */

package davidmarino;

import davidmarino.menu.MainMenu;

/** TODO
 * controls
 * leaderboard
 */

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        boolean running = true;
        while (running) {
            running = MainMenu.showMainMenu();
        }
    }
}
