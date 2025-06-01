package davidmarino.davidmarinogameplay;

import davidmarino.davidmarinogameplay.model.Board;
import davidmarino.davidmarinogameplay.service.BoardService;
import davidmarino.davidmarinogameplay.service.BoltService;
import davidmarino.davidmarinogameplay.utility.AccessFile;
import davidmarino.davidmarinogameplay.utility.GameplayInput;
import davidmarino.davidmarinogameplay.utility.StopWatch;

public class Main {
    public static void startGame() {
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