/**
 * Authors: David Allen Stephan Marino
 * Date: 5/18/25
 */

package davidmarino;

import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

@Data
public class Board {
    private ArrayList<Bolt> bolts;
    // Constructor, "board" refers to the grouping of bolts, board -> bolt -> nut
    public Board(int[][] primitiveBoard, int maxBoltLength) {
        this.bolts = new ArrayList<>();
        for (int i = 0; i < primitiveBoard.length; i++) { // 0 to number of bolts
            this.bolts.add(new Bolt(primitiveBoard[i], maxBoltLength));
        }
    }

    // Shuffles array by index
    private int[][] shuffle(int[][] array) {
        Random random = new Random(); // Random
        for (int i = 0; i < array[0].length; i++) { // 0 to the length of the first value
            int randIndex = random.nextInt(array.length); // Get random nDistinctNut column
            int randIndex2 = random.nextInt(array.length); // Get random nDistinctNut column
            int temp = array[randIndex][i];
            array[randIndex][i] = array[randIndex2][i];
            array[randIndex2][i] = temp;
        }
        return array;
    }

    public Board(int nDistinctNut, int maxBoltLength) {
        this.bolts = new ArrayList<>();
        int[][] bar = new int[nDistinctNut][maxBoltLength];
        for (int i = 0; i < nDistinctNut; i++) { // Iterate through nDistinctNut nut color
            int[] nuts = new int[maxBoltLength]; // Create empty int array size length
            Arrays.fill(nuts, i); // Fill with nDistinctNut nut color
            bar[i] = nuts;
        }
        int[][] foo = shuffle(bar);
        for (int i = 0; i < 100; i++) {
            foo = shuffle(foo);
        }
        for (int i = 0; i < nDistinctNut; i++) {
            this.bolts.add(new Bolt(foo[i], maxBoltLength));
        }
        // Create empty bolts
        int[] empty = new int[0];
        this.bolts.add(new Bolt(empty, maxBoltLength));
        this.bolts.add(new Bolt(empty, maxBoltLength));
    }


    public boolean isComplete() {
        for (Bolt bolt : bolts) {
            if (!bolt.isComplete()) {
                return false;
            }
        }
        return true;
    }

    public void print() {
        AnsiColor colorizer = new AnsiColor();
        int maxHeight = 0;
        for (Bolt bolt : bolts) {
            maxHeight = Math.max(maxHeight, bolt.getHeight());
        }

        StringBuilder sb = new StringBuilder();
        sb.append("\u001b[H"); // Move cursor to home
        sb.append("\u001b[2J"); // Clear screen
        sb.append("░██████╗░█████╗░██████╗░███████╗░██╗░░░░░░░██╗░██████╗\n");
        sb.append("██╔════╝██╔══██╗██╔══██╗██╔════╝░██║░░██╗░░██║██╔════╝\n");
        sb.append("╚█████╗░██║░░╚═╝██████╔╝█████╗░░░╚██╗████╗██╔╝╚█████╗░\n");
        sb.append("░╚═══██╗██║░░██╗██╔══██╗██╔══╝░░░░████╔═████║░░╚═══██╗\n");
        sb.append("██████╔╝╚█████╔╝██║░░██║███████╗░░╚██╔╝░╚██╔╝░██████╔╝\n");
        sb.append("╚═════╝░░╚════╝░╚═╝░░╚═╝╚══════╝░░░╚═╝░░░╚═╝░░╚═════╝░\n");
        sb.append("\n\n\n");

        // Print nut stacks row by row (top to bottom)
        for (int row = 0; row < maxHeight; row++) {
            for (Bolt bolt : bolts) {
                int nutRow = bolt.getHeight() - maxHeight + row;
                String symbol = bolt.getNutSymbol(nutRow, colorizer);
                sb.append(String.format("%-1s", symbol)); // each cell is exactly 3 characters wide
            }
            sb.append("\n");
        }

        // Print aligned column indices below
        for (int i = 0; i < bolts.size(); i++) {
            sb.append(String.format(" %-2s", i)); // each index is 3 characters wide
        }
        sb.append("\n");

        System.out.print(sb.toString());
        System.out.flush();
    }

}
