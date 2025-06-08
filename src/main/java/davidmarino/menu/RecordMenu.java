/**
 * Authors: David Allen Stephan Marino
 * Date: 6/8/25
 */

package davidmarino.menu;

import static davidmarino.menu.Utility.*;

public class RecordMenu {
    public static String show(long duration) {
        clearScreen();
        printHeader("RECORD MENU");
        System.out.println("Congratulations you've completed the puzzle!");
        System.out.println("You took " + duration + " milliseconds.");
        System.out.println("Please enter a name for this record.");

        System.out.print("\nName: ");
        String input = scanner.nextLine();
        return input;
    }
}
