/**
 * Authors: David Allen Stephan Marino
 * Date: 5/18/25
 */

package davidmarino.davidmarinogameplay.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;

@Data
public class Board {
    private String game_id;
    private ArrayList<Bolt> bolts;
    private ArrayList<ArrayList<Bolt>> boltHistory;

    @JsonCreator
    public Board(@JsonProperty("game_id") String game_id, @JsonProperty("bolts") ArrayList<Bolt> bolts) {
        this.game_id = game_id;
        this.bolts = bolts;
        this.boltHistory = new ArrayList<>();
    }

}
