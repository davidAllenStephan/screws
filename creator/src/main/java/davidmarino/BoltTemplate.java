/**
 * Authors: David Allen Stephan Marino
 * Date: 5/29/25
 */

package davidmarino;

import lombok.Data;

import java.util.ArrayList;
import java.util.Random;
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

    public BoltTemplate(ArrayList<NutTemplate> nuts, int height) {
        this.nuts = nuts;
        this.maxBoltLength = height;
    }

    public BoltTemplate(int maxBoltLength, int value) {
        this.nuts = new ArrayList<>();
        this.maxBoltLength = maxBoltLength;
        for (int i = 0; i < maxBoltLength; i++) {
            this.nuts.add(new NutTemplate(value));
        }
    }

    public void fill(int height) {
        for (int i = height; i >= 0; i--) {
            this.nuts.set(i, new NutTemplate(i + 1));
        }
        Random rand = new Random();
        for (int i = height; i >= 0; i--) {
            int randomIndex = rand.nextInt(height);
            NutTemplate temp = nuts.get(randomIndex);
            this.nuts.set(randomIndex, this.nuts.get(i));
            this.nuts.set(i, temp);
        }
    }

    public void removeNull() {
        this.nuts = new ArrayList<NutTemplate>(this.nuts.stream().map(nut -> nut.getValue() != 0 ? nut : null).collect(Collectors.toList()).reversed());
    }

}
