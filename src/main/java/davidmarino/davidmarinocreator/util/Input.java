/**
 * Authors: David Allen Stephan Marino
 * Date: 5/18/25
 */

package davidmarino.davidmarinocreator.util;

import java.util.Scanner;

public class Input {
    public static String[] get() {
        Scanner s = new Scanner(System.in);
        String input = s.nextLine();
        return input.split("");
    }
}

