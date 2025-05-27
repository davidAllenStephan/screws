package davidmarino;


import davidmarino.model.Board;
import davidmarino.service.BoardService;
import davidmarino.service.BoltService;
import davidmarino.utility.Input;
import davidmarino.utility.ReadJson;

/**
 * TODO:
 * Add a leaderboard
 * Make customizable levels
 * Save levels
 * Load levels
 * User keymap
 */

public class Main {
    public static void main(String[] args) {
        ReadJson readJson = new ReadJson();
        Board board = readJson.readJson();
        BoardService boardService = new BoardService(new BoltService());
        boardService.print(board);
        long startTime = System.nanoTime();
        while (true) {
            if (boardService.isComplete(board)) {
                long endTime = System.nanoTime();
                long elapsedTime = endTime - startTime;
                double seconds = (double) elapsedTime / 1_000_000_000.0;
                System.out.println("Completed!");
                System.out.println(seconds);
            }
            int[] control = Input.input();
            if (control[0] == -1) {
                boardService.reverse(board, 1);
            } else {
                boardService.shiftAway(board, control[0], control[1]);
            }
            boardService.print(board);
        }
    }
}