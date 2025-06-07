/**
 * Authors: David Allen Stephan Marino
 * Date: 5/29/25
 */

package davidmarino.utility;

public class GameplayInput extends Input {

    public static int[] input() {
        String[] in = Input.get();
        if (in[0].equals("q")) return new int[] {-2}; // quit
        int[] inInt = new int[in.length];
        for (int i = 0; i < in.length; i++) {
            inInt[i] = Integer.parseInt(in[i]);
        }
        return inInt;
    }
}
