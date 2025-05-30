/**
 * Authors: David Allen Stephan Marino
 * Date: 5/29/25
 */

package davidmarino;

public class Main {
    public static void main(String[] args) {
        BoardTemplate boardCreator = new BoardTemplate(5, 5);
        boardCreator.printBoard();
        while (true) {
            int[] input = CreatorInput.input();
            switch (input[0]) {
                case -2 -> {
                    break;
                }
                case -3 -> {
                    boardCreator.updateWidth(input[1]);
                }
                case -4 -> {
                    boardCreator.updateHeight(input[1]);
                }
                case -5 -> {
                    boardCreator.save();
                    return;
                }
                case -6 -> {
                    boardCreator.randomize(input[1], input[2]);
                }
                default -> {
                    boardCreator.updateBoardTemplate(input[0], input[1], input[2]);
                }
            }
            boardCreator.printBoard();

        }
    }
}
