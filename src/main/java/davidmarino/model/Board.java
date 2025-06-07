/**
 * Authors: David Allen Stephan Marino
 * Date: 5/18/25
 */

package davidmarino.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;

@Data
public class Board {
    private String game_id;
    private ArrayList<Bolt> bolts;
    @JsonIgnore
    private ArrayList<ArrayList<Bolt>> boltHistory;
    @JsonIgnore
    private int width;
    @JsonIgnore
    private int height;
    @JsonIgnore
    private Leaderboard leaderboard;

    @JsonCreator
    public Board(@JsonProperty("game_id") String game_id, @JsonProperty("bolts") ArrayList<Bolt> bolts) {
        this.game_id = game_id;
        this.bolts = bolts;
        this.boltHistory = new ArrayList<>();
    }

    public Board(int width, int height) {
        this.width = width;
        this.height = height;
        bolts = new ArrayList<>();
    }

}
