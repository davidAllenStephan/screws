/**
 * Authors: David Allen Stephan Marino
 * Date: 5/29/25
 */

package davidmarino.initiator;


import davidmarino.model.Board;
import davidmarino.service.BoardService;
import davidmarino.utility.CreatorInput;
import davidmarino.utility.Input;

import java.io.File;

public class CreationStart {
    public static void start() {
        Board boardCreator = new Board(5, 5);
        BoardService boardService = new BoardService(boardCreator);
        boardService.resetBoard();
        boardService.printBoard();
        while (true) {
            int[] input = CreatorInput.input();
            switch (input[0]) {
                case -2 -> {
                    break;
                }
                case -3 -> {
                    boardService.updateWidth(input[1]);
                }
                case -4 -> {
                    boardService.updateHeight(input[1]);
                }
                case -5 -> {
                    String[] s = Input.get();
                    StringBuilder builder = new StringBuilder();
                    builder.append("src/main/resources/games/");
                    builder.append(String.join("", s));
                    builder.append(".json");
                    boardService.save(new File(builder.toString()));
                    return;
                }
                case -6 -> {
                    boardService.randomize(input[1], input[2]);
                }
                default -> {
                    boardService.updateBoardTemplate(input[0], input[1], input[2]);
                }
            }
            boardService.printBoard();

        }
    }
}
