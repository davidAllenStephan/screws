/**
 * Authors: David Allen Stephan Marino
 * Date: 5/29/25
 */

package davidmarino.creator;

import davidmarino.AccessFile;
import davidmarino.gameplay.utility.GameplayInput;

public class Main {
    public static void main(String[] args) {
        BoardCreator boardCreator = new BoardCreator(5, 5);
        AccessFile<BoardCreator> a = new AccessFile<>(BoardCreator.class);
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
                a.writeJson("test.json", boardCreator);
            } else {
                boardCreator.updateNode(input[0], input[1], input[2]);
            }
            boardCreator.printBoard();

        }
    }
}
