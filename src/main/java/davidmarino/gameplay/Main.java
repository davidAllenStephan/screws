package davidmarino.gameplay;

import davidmarino.AccessFile;
import davidmarino.gameplay.model.Board;
import davidmarino.gameplay.service.BoardService;
import davidmarino.gameplay.service.BoltService;
import davidmarino.gameplay.utility.GameplayInput;
import davidmarino.gameplay.utility.StopWatch;

public class Main {
    public static void main(String[] args) {
        AccessFile<Board> accessJson= new AccessFile<>(Board.class);
        Board board = accessJson.readJson("src/main/resources/test.json");
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
            int[] control = GameplayInput.input();
            if (control[0] == -1) {
                boardService.reverse(board, 1);
            } else {
                boardService.shiftAway(board, control[0], control[1]);
            }
            boardService.print(board);
        }
    }
}