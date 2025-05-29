/**
 * Authors: David Allen Stephan Marino
 * Date: 5/18/25
 */

package davidmarino.gameplay.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;

@Data
public class Bolt {
    private ArrayList<Nut> nuts;
    private int maxBoltLength;
    private int maxNuts;

    @JsonCreator
    public Bolt(@JsonProperty("nuts") ArrayList<Nut> nuts, @JsonProperty("maxBoltLength") int maxBoltLength) {
        this.nuts = nuts;
        for (int i = nuts.size(); i < maxBoltLength; i++) {
            nuts.addFirst(null);
        }
        this.maxNuts = nuts.size();
        this.maxBoltLength = maxBoltLength;
    }

    public Bolt(Bolt bolt) {
        this.nuts = new ArrayList<>();
        for (Nut nut : bolt.nuts) {
            this.nuts.add(nut == null ? null : new Nut(nut));
        }
        this.maxBoltLength = bolt.maxBoltLength;
        this.maxNuts = bolt.maxNuts;
    }
}
