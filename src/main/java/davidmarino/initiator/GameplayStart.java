package davidmarino.initiator;

import davidmarino.model.Board;
import davidmarino.service.BoardService;
import davidmarino.utility.AccessFile;
import davidmarino.utility.GameplayInput;
import davidmarino.utility.StopWatch;
import davidmarino.model.Leaderboard;
import davidmarino.model.Record;

import java.io.File;

public class GameplayStart {
    public static void startGame(File selectedFile) {
        AccessFile<Board> accessJson = new AccessFile<>(Board.class);
        Board board = accessJson.readJson(selectedFile.getPath());
        BoardService boardService = new BoardService(board);
        boardService.print();
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        while (true) {
            if (boardService.isComplete()) {
                stopWatch.stop();
                Leaderboard leaderboard = new Leaderboard(board.getGame_id());
                leaderboard.addRecord(new Record(stopWatch.getStartTime(), stopWatch.getStopTime()));
                leaderboard.save("src/main/resources/leaderboards/leaderboard.json");
                System.out.println("Completed!");
                System.out.println(stopWatch.seconds());
                break;
            }
            int[] control = GameplayInput.input();
            if (control[0] == -1) {
                boardService.reverse(1);
            } else {
                boardService.shiftAway(control[0], control[1]);
            }
            boardService.print();
        }
        MenuStart.showGameplayMenu();
    }
}