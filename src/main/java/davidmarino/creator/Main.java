/**
 * Authors: David Allen Stephan Marino
 * Date: 5/29/25
 */

package davidmarino.creator;

public class Main {
    public static void main(String[] args) {
        BoardTemplate boardCreator = new BoardTemplate(5, 5);
        boardCreator.printBoard();
        while (true) {
            int[] input = CreatorInput.input();
            if (input[0] == -2) {
                break;
            } else if (input[0] == -3) {
                boardCreator.updateWidth(input[1]);
            } else if (input[0] == -4) {
                boardCreator.updateHeight(input[1]);
            } else if (input[0] == -5) {
                boardCreator.save();
                break;
            } else {
                boardCreator.updateBoardTemplate(input[0], input[1], input[2]);
            }
            boardCreator.printBoard();

        }
    }
}
