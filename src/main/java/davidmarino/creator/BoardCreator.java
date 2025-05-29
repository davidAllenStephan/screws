/**
 * Authors: David Allen Stephan Marino
 * Date: 5/29/25
 */

package davidmarino.creator;


import lombok.Data;

import java.util.ArrayList;

@Data
public class BoardCreator {
    private ArrayList<ArrayList<Node>> board;
    private int width;
    private int height;

    public BoardCreator(int width, int height) {
        board = new ArrayList<>();
        this.width = width;
        this.height = height;
        resetBoard();
    }

    private void resetBoard() {
        this.board = new ArrayList<>();
        for (int i = 0; i < height; i++) {
            ArrayList<Node> nodes = new ArrayList<>();
            for (int j = 0; j < width; j++) {
                nodes.add(new Node());
            }
            board.add(nodes);
        }
    }

    public void updateWidth(int width) {
        this.width = width;
        resetBoard();
    }

    public void updateHeight(int height) {
        this.height = height;
        resetBoard();
    }

    public void updateNode(int x, int y, int value) {
        this.board.get(x).get(y).setValue(value);
    }

    public void printBoard() {
        for (int i = 0; i < board.size(); i++) {
            for (int j = 0; j < board.get(i).size(); j++) {
                System.out.print(" " + board.get(i).get(j).getValue() + " ");
            }
            System.out.println();
        }
    }


}
