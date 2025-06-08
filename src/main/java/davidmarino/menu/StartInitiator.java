/**
 * Authors: David Allen Stephan Marino
 * Date: 6/7/25
 */

package davidmarino.menu;

import davidmarino.initiator.CreationStart;
import davidmarino.initiator.GameplayStart;

import java.io.File;

import static davidmarino.menu.Utility.*;

public class StartInitiator {

    public static void startGame(File selectedGame) {
        clearScreen();
        printHeader("STARTING GAME");
        GameplayStart.startGame(selectedGame);
        pauseForEnter("Game started.");
    }

    public static void startCreation() {
        clearScreen();
        printHeader("LEVEL CREATION");
        CreationStart.start();
        pauseForEnter("Returning to Creation Menu.");
    }

}
