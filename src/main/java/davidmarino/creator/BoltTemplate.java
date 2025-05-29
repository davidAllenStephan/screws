/**
 * Authors: David Allen Stephan Marino
 * Date: 5/29/25
 */

package davidmarino.creator;

import lombok.Data;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Data
public class BoltTemplate {
    private int maxBoltLength;
    private ArrayList<NutTemplate> nuts;

    public BoltTemplate() {}

    public BoltTemplate(int maxBoltLength) {
        this.nuts = new ArrayList<>();
        this.maxBoltLength = maxBoltLength;
        for (int i = 0; i < maxBoltLength; i++) {
            this.nuts.add(new NutTemplate());
        }
    }

    public void removeNull() {
        this.nuts = new ArrayList<NutTemplate>(this.nuts.stream().map(nut -> nut.getValue() != 0 ? nut : null).collect(Collectors.toList()).reversed());
    }

}
