/**
 * Authors: David Allen Stephan Marino
 * Date: 6/1/25
 */

package davidmarino.davidmarinostats;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

@Data
public class Leaderboard {
    private String game_id;
    private ArrayList<Record> records;

    @JsonCreator
    public Leaderboard(@JsonProperty("game_id") String game_id, @JsonProperty("records") ArrayList<Record> records) {
        this.game_id = game_id;
        this.records = records;
    }
    public Leaderboard(String game_id) {
        this.game_id = game_id;
        this.records = new ArrayList<>();
    }
    public void addRecord(Record r) {
        this.records.add(r);
    }
    public Record getRecord(String id) {
        for (Record record : records) {
            if (record.equals(id)) return record;
        }
            return null;
    }
    public void sort() {
        this.records.sort((p, q) -> {
            return p.getDuration() > q.getDuration() ? 1 : -1;
        });
    }
    public void save(String path) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(path), this);
        } catch (IOException e) {
            throw new RuntimeException("Failed to write JSON to file", e);
        }
    }
    public static Leaderboard read(String path) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(new File(path), Leaderboard.class);
        } catch (IOException e) {
            throw new RuntimeException("Failed to read JSON from file", e);
        }
    }
}
