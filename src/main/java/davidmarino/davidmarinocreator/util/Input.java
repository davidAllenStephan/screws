/**
 * Authors: David Allen Stephan Marino
 * Date: 5/18/25
 */

package davidmarinocreator.util;

import java.util.Scanner;

public class Input {
//    public static int[] input() {
//        Scanner scanner = new Scanner(System.in);
//        String line = scanner.nextLine();
//        String[] tokens = line.split("");
//
//        int[] result = new int[2];
//        if (tokens[0].equals("u")) {
//            result[0] = -1;
//            return result;
//        }
//        result[0] = Integer.parseInt(tokens[0]);
//        result[1] = Integer.parseInt(tokens[1]);
//        return result;
//    }

    public static String[] get() {
        Scanner s = new Scanner(System.in);
        String input = s.nextLine();
        return input.split("");
    }
}

