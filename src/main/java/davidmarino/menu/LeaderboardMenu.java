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
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import static davidmarino.menu.Utility.*;
import static davidmarino.menu.Utility.pauseForEnter;

public class LeaderboardMenu {
    public static void show(File file) {
        clearScreen();
        printHeader("LEADERBOARD");
        AccessFile<Board> b = new AccessFile<>(Board.class);
        Board bb = b.readJson(file);
        Leaderboard leaderboard = bb.getLeaderboard();
        String format = "| %-13s | %-13s | %-13s |\n";
        String divider = "+--------------------------------------+----------+-------------+---------------+---------------+";
        System.out.println(divider);
        System.out.printf(format, "Duration", "User ID", "Datetime");
        System.out.println(divider);


        for (Record r : leaderboard.getRecords()) {
            long millis = System.currentTimeMillis(); // example millis

            System.out.printf(
                    format,
                    r.getDuration() / 1000.0,
                    r.getUserId() == null ? "null" : r.getUserId(),
                    r.getDatetime()
            );
        }
        System.out.println(divider);
        pauseForEnter("Press enter to return.");
        GameMenu.show(file);
    }
}
