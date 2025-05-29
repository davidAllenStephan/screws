/**
 * Authors: David Allen Stephan Marino
 * Date: 5/29/25
 */

package davidmarino.creator;

import davidmarino.Input;

public class CreatorInput extends Input {
    public static int[] input() {
        String[] in = Input.get();
        switch (in[0]) {
            case "q" -> {
                return new int[]{-2}; // quit
            }
            case "w" -> {
                return new int[]{-3, Integer.parseInt(in[1])};
            }
            case "h" -> {
                return new int[]{-4, Integer.parseInt(in[1])};
            }
            case "s" -> {
                return new int[]{-5};
            }
        }
        int[] inInt = new int[in.length];
        for (int i = 0; i < in.length; i++) {
            inInt[i] = Integer.parseInt(in[i]);
        }
        return inInt;
    }
}
