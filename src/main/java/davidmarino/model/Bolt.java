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
public class Bolt {
    private ArrayList<Nut> nuts;
    private int maxBoltLength;
    private boolean empty;

    @JsonCreator
    public Bolt(@JsonProperty("nuts") ArrayList<Nut> nuts, @JsonProperty("maxBoltLength") int maxBoltLength, @JsonProperty("empty") boolean empty) {
        this.nuts = nuts;
        this.empty = empty;
        for (int i = nuts.size(); i < maxBoltLength; i++) {
            nuts.addFirst(null);
        }
        this.maxBoltLength = maxBoltLength;
    }

    public Bolt(Bolt bolt) {
        this.nuts = new ArrayList<>();
        for (Nut nut : bolt.nuts) {
            this.nuts.add(nut == null ? null : new Nut(nut));
        }
        this.maxBoltLength = bolt.maxBoltLength;
    }

    public Bolt(int maxBoltLength) {
        this.nuts = new ArrayList<>();
        this.maxBoltLength = maxBoltLength;
        for (int i = 0; i < maxBoltLength; i++) {
            this.nuts.add(new Nut());
        }
    }

    public Bolt(ArrayList<Nut> nuts, int height) {
        this.nuts = nuts;
        this.maxBoltLength = height;
    }

    public Bolt copy() {
        ArrayList<Nut> copiedNuts = new ArrayList<>();
        for (Nut nut : this.nuts) {
            if (nut != null) {
                copiedNuts.add(nut.copy());
            }
        }
        return new Bolt(copiedNuts, this.maxBoltLength, this.empty);
    }

}
