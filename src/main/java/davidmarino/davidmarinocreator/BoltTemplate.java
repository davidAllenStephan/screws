/**
 * Authors: David Allen Stephan Marino
 * Date: 5/29/25
 */

package davidmarino.davidmarinocreator;

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

    public int peekTopColor() {
        for (int i = 0; i < nuts.size(); i++) {
            int color = nuts.get(i).getValue();
            if (color != 0) {
                return color;
            }
        }
        return 0;
    }

   public boolean isEmpty() {
        for (int i = 0; i < nuts.size(); i++) {
            if (nuts.get(i).getValue() != 0) {
                return false;
            }
        }
        return true;
   }

    public void fill(int height, int color) {
        for (int i = height-1; i >= 0; i--) {
            this.nuts.set(i, new NutTemplate(color));
        }
    }

    public void removeNull() {
        this.nuts = new ArrayList<NutTemplate>(this.nuts.stream().map(nut -> nut.getValue() != 0 ? nut : null).collect(Collectors.toList()).reversed());
    }

}
