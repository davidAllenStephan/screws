/**
 * Authors: David Allen Stephan Marino
 * Date: 6/7/25
 */

package davidmarino.menu;

import davidmarino.model.Board;
import davidmarino.model.Leaderboard;
import davidmarino.model.Record;
import davidmarino.utility.AccessFile;

import java.io.File;

import static davidmarino.menu.Utility.*;
import static davidmarino.menu.Utility.pauseForEnter;

public class LeaderboardMenu {
    public static void show(File file) {
        clearScreen();
        printHeader("LEADERBOARD");
        AccessFile<Board> b = new AccessFile<>(Board.class);
        Board bb = b.readJson(file);
        Leaderboard leaderboard = bb.getLeaderboard();
        String format = "| %-36s | %-8s | %-11s | %-13s | %-13s |\n";
        String divider = "+--------------------------------------+----------+-------------+---------------+---------------+";
        System.out.println(divider);
        System.out.printf(format, "Record ID", "Duration", "User ID", "Start Time", "End Time");
        System.out.println(divider);

        for (Record r : leaderboard.getRecords()) {
            System.out.printf(
                    format,
                    r.getRecordId(),
                    r.getDuration(),
                    r.getUserId() == null ? "null" : r.getUserId(),
                    r.getStartTime(),
                    r.getEndTime()
            );
        }
        System.out.println(divider);
        pauseForEnter("Press enter to return.");
        GameMenu.show(file);
    }
}
