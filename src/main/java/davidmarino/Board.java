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

    public Board(int[][] board, int length) {
        if (board.length <= 0) throw new BoardException("N must be positive");
        this.bolts = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            this.bolts.add(new Bolt(board[i], length));
        }
    }

    private int[][] shuffle(int[][] array) {
        Random random = new Random();
        for (int i = 0; i < array[0].length; i++) {
            int index = random.nextInt(array.length);
            int temp = array[index][i];
            array[index][i] = array[i][0];
            array[i][0] = temp;
        }
        return array;
    }

    public Board(int dis, int length) {
        this.bolts = new ArrayList<>();
        int[][] bar = new int[dis][length];
        for (int i = 0; i < dis; i++) { // Iterate through distinct nut color
            int[] nuts = new int[length]; // Create empty int array size length
            Arrays.fill(nuts, i); // Fill with distinct nut color
            bar[i] = nuts;
        }
        int[][] foo = shuffle(bar);
        for (int i = 0; i < 100; i++) {
            foo = shuffle(foo);
        }
        for (int i = 0; i < dis; i++) {
            this.bolts.add(new Bolt(foo[i], length));
        }
        // Create empty bolts
        int[] empty = new int[0];
        this.bolts.add(new Bolt(empty, length));
        this.bolts.add(new Bolt(empty, length));
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
