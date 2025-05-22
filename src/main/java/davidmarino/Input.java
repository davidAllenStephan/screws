/**
 * Authors: David Allen Stephan Marino
 * Date: 5/18/25
 */

package davidmarino;

import java.util.Scanner;

public class Input {
    public static int[] input() {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        String[] tokens = line.split(",");
        int[] result = new int[2];
        result[0] = Integer.parseInt(tokens[0]);
        result[1] = Integer.parseInt(tokens[1]);
        return result;
    }
}
