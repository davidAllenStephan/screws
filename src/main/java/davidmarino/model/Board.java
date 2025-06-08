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
    // Board is parent to bolts
    // but leaderboard is just association this is a problem
    private String gameId;
    private ArrayList<Bolt> bolts;
    private Leaderboard leaderboard;

    @JsonIgnore
    private ArrayList<ArrayList<Bolt>> boltHistory;
    @JsonIgnore
    private int width;
    @JsonIgnore
    private int height;

    @JsonCreator
    public Board(@JsonProperty("game_id") String gameId, @JsonProperty("bolts") ArrayList<Bolt> bolts, @JsonProperty("leaderboard") Leaderboard leaderboard) {
        this.gameId = gameId;
        this.bolts = bolts;
        this.boltHistory = new ArrayList<>();
        this.leaderboard = leaderboard;
    }

    public Board(int width, int height) {
        this.width = width;
        this.height = height;
        this.bolts = new ArrayList<>();
        this.leaderboard = new Leaderboard();
    }

    public Board copy() {
        ArrayList<Bolt> copiedBolts = new ArrayList<>();
        for (Bolt bolt : this.bolts) {
            copiedBolts.add(bolt.copy());
        }
        return new Board(this.gameId, copiedBolts, this.leaderboard);
    }

}
