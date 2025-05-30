package davidmarinogameplay;

import davidmarinogameplay.model.Board;
import davidmarinogameplay.service.BoardService;
import davidmarinogameplay.service.BoltService;
import davidmarinogameplay.utility.AccessFile;
import davidmarinogameplay.utility.GameplayInput;
import davidmarinogameplay.utility.StopWatch;

public class Main {
    public static void main(String[] args) {
        AccessFile<Board> accessJson= new AccessFile<>(Board.class);
        Board board = accessJson.readJson("creator/src/main/resources/test.json");
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