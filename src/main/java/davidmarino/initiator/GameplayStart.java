package davidmarino.initiator;

import davidmarino.menu.GameplayMenu;
import davidmarino.menu.RecordMenu;
import davidmarino.model.Board;
import davidmarino.service.BoardService;
import davidmarino.service.LeaderboardService;
import davidmarino.utility.AccessFile;
import davidmarino.utility.GameplayInput;
import davidmarino.utility.StopWatch;
import davidmarino.model.Record;

import java.io.File;

public class GameplayStart {
    public static void startGame(File selectedFile) {

        // Creating file accessor
        AccessFile<Board> accessJson = new AccessFile<>(Board.class);

        // Creating models
        Board board = accessJson.readJson(new File(selectedFile.getPath()));
        Board boardCopy = board.copy();

        // Setting up services
        BoardService boardService = new BoardService(board);
        LeaderboardService leaderboardService = new LeaderboardService(board.getLeaderboard());

        // Initial setup
        boardService.print();
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        while (true) {
            if (boardService.isComplete()) {
                stopWatch.stop();
                String name = RecordMenu.show(stopWatch.getStopTime() - stopWatch.getStartTime());
                leaderboardService.addRecord(new Record(stopWatch.getStartTime(), stopWatch.getStopTime(), name));
                boardCopy.setLeaderboard(board.getLeaderboard());
                accessJson.writeJson(selectedFile, boardCopy);
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
        GameplayMenu.show();
    }
}