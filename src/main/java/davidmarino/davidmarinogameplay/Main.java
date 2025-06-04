package davidmarino.davidmarinogameplay;

import davidmarino.davidmarinogameplay.model.Board;
import davidmarino.davidmarinogameplay.service.BoardService;
import davidmarino.davidmarinogameplay.service.BoltService;
import davidmarino.davidmarinogameplay.utility.AccessFile;
import davidmarino.davidmarinogameplay.utility.GameplayInput;
import davidmarino.davidmarinogameplay.utility.StopWatch;
import davidmarino.davidmarinomenu.Menu;
import davidmarino.davidmarinostats.Leaderboard;
import davidmarino.davidmarinostats.Record;

import java.io.File;

public class Main {
    public static void startGame(File selectedFile) {
        AccessFile<Board> accessJson = new AccessFile<>(Board.class);
        Board board = accessJson.readJson(selectedFile.getPath());
        BoardService boardService = new BoardService(new BoltService());
        boardService.print(board);
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        while (true) {
            if (boardService.isComplete(board)) {
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
                boardService.reverse(board, 1);
            } else {
                boardService.shiftAway(board, control[0], control[1]);
            }
            boardService.print(board);
        }
        Menu.showGameplayMenu();
    }
}