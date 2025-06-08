/**
 * Authors: David Allen Stephan Marino
 * Date: 6/7/25
 */

package davidmarino.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import davidmarino.model.Leaderboard;
import davidmarino.model.Record;
import lombok.Data;

import java.io.File;
import java.io.IOException;

@Data
public class LeaderboardService {
    private Leaderboard leaderboard;

    public LeaderboardService(Leaderboard leaderboard) {
        this.leaderboard = leaderboard;
    }

    public void addRecord(davidmarino.model.Record r) {
        this.leaderboard.getRecords().add(r);
    }

    public davidmarino.model.Record getRecord(String id) {
        for (Record record : this.leaderboard.getRecords()) {
            if (record.equals(id)) return record;
        }
        return null;
    }

    public void sort() {
        this.leaderboard.getRecords().sort((p, q) -> {
            return p.getDuration() > q.getDuration() ? 1 : -1;
        });
    }

}
