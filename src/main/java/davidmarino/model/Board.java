/**
 * Authors: David Allen Stephan Marino
 * Date: 5/18/25
 */

package davidmarino.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;

@Data
public class Board {

    private ArrayList<Bolt> bolts;
    private int nBolts;
    private int extraBolts;
    private int nDistinctNut;
    private ArrayList<ArrayList<Bolt>> boltHistory;

    @JsonCreator
    public Board(@JsonProperty("bolts") ArrayList<Bolt> bolts, @JsonProperty("nBolts") int nBolts, @JsonProperty("extraBolts") int extraBolts, @JsonProperty("nDistinctNut") int nDistinctNut) {
        this.bolts = bolts;
        this.nBolts = nBolts;
        this.extraBolts = extraBolts;
        this.nDistinctNut = nDistinctNut;
        this.boltHistory = new ArrayList<>();
    }

}
