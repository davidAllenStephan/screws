/**
 * Authors: David Allen Stephan Marino
 * Date: 5/29/25
 */

package davidmarino.davidmarinocreator;

import davidmarinocreator.util.Input;

public class CreatorInput extends Input {
    public static int[] input() {
        String[] in = Input.get();
        switch (in[0]) {
            case "q" -> { // quit
                return new int[]{-2}; // [0] command
            }
            case "w" -> { // change width
                return new int[]{-3, Integer.parseInt(in[1])}; // [0] command, [1] template width
            }
            case "h" -> { // change height
                return new int[]{-4, Integer.parseInt(in[1])}; // [0] command, [1] template height
            }
            case "s" -> { // save
                return new int[]{-5}; // [0] command
            }
            case "r" -> { // randomize
                return new int[]{-6, Integer.parseInt(in[1]), Integer.parseInt(in[2])}; // [0] command, [1] number of empty bolts
            }
        }
        int[] inInt = new int[in.length];
        for (int i = 0; i < in.length; i++) {
            inInt[i] = Integer.parseInt(in[i]);
        }
        return inInt;
    }
}
