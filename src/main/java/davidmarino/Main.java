package davidmarino;


import davidmarino.model.Board;
import davidmarino.service.BoardService;
import davidmarino.service.BoltService;
import davidmarino.utility.Input;
import davidmarino.utility.ReadJson;
import davidmarino.utility.StopWatch;

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
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        while (true) {
            if (boardService.isComplete(board)) {
                stopWatch.stop();
                System.out.println("Completed!");
                System.out.println(stopWatch.seconds());
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