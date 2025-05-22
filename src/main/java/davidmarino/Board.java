/**
 * Authors: David Allen Stephan Marino
 * Date: 5/18/25
 */

package davidmarino;

import lombok.Data;

import java.util.ArrayList;

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

    public void print() {
        AnsiColor colorizer = new AnsiColor();
        int maxHeight = 0;
        for (Bolt bolt : bolts) {
            maxHeight = Math.max(maxHeight, bolt.getHeight());
        }
        for (int row = 0; row < maxHeight; row++) {
            for (Bolt bolt : bolts) {
                int nutRow = bolt.getHeight() - maxHeight + row;
                System.out.print(bolt.getNutSymbol(nutRow, colorizer));
            }
            System.out.println();
        }
        for (int i = 0; i < bolts.size(); i++) {
            System.out.printf(" %d ", i);
        }
        System.out.println();
    }

}
