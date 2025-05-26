/**
 * Authors: David Allen Stephan Marino
 * Date: 5/18/25
 */

package davidmarino;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;

@Data
public class Bolt {
    private ArrayList<Nut> nuts;
    private int maxBoltLength;

    @JsonCreator
    public Bolt(@JsonProperty("nuts") ArrayList<Nut> nuts, @JsonProperty("maxBoltLength") int maxBoltLength) {
        this.nuts = nuts;
        this.maxBoltLength = maxBoltLength;
    }

    public Bolt(int[] primitiveNuts, int maxBoltLength) {
        this.nuts = new ArrayList<>(); // init
        this.maxBoltLength = maxBoltLength;

        for (int i = 0; i < maxBoltLength; i++) { // fill null, 0 to the maximum length of the bolt
            this.nuts.add(null);
        }
        if (primitiveNuts.length != 0) { // number of nuts is not 0
            int i = maxBoltLength - 1; // iterate from maximum size of the bolt to 0, moving upward from bottom visually
            while (i >= 0) {
                this.nuts.set(i, new Nut(primitiveNuts[i])); // Add nut to bolt, visually stacking the nuts bottom to top
                i--;
            }
        }
    }

    public boolean isComplete() {
        int count = 0;
        int topNutIndex = findTopNutIndex();
        if (topNutIndex == -1) {
            return true;
        }
        for (int i = topNutIndex; i < nuts.size(); i++) {
            if (nuts.get(topNutIndex).equals(nuts.get(i))) {
                count++;
            }
        }
        return count == nuts.size();
    }

    private int findSpaceIndex() { // Find open space index
        int i = this.nuts.size() - 1;
        while (i >= 0) { // Iterates bottom to top returns first null index
            if (this.nuts.get(i) == null) {
                return i;
            }
            i--;
        }
        return -1;
    }

    private int findTopNutIndex() { // Find top nut index
        int i = 0;
        while (i < this.nuts.size()) {
            if (this.nuts.get(i) != null) {
                return i;
            }
            i++;
        }
        return -1;
    }

    private boolean isFull() {
        return findTopNutIndex() == maxBoltLength; // Top nut is same as bolt length
    }

    private boolean isEmpty() {
        return findTopNutIndex() == -1; // No top nut is found
    }

    public Bolt addNutToTop(ArrayList<Nut> nuts) { // Add nut to top of bolt
        for (Nut nut: nuts) { // For every nut in nuts
            int i = this.findSpaceIndex(); // Find the first open space
            this.nuts.set(i, nut); // Set open space to new nut
        }
        return this;
    }

    public ArrayList<Nut> remove() {
        ArrayList<Nut> nnuts = new ArrayList<>();
        if (!isEmpty()) {
            int topIndex = this.findTopNutIndex();
            nnuts.add(this.nuts.get(topIndex));
            int i = topIndex+1;
            while (i < this.nuts.size()) {
                if (this.nuts.get(i) != null) {
                    if (nnuts.getFirst().getColor().equals(this.nuts.get(i).getColor())) {
                        nnuts.addFirst(this.nuts.get(i));
                        this.nuts.set(i, null);
                    } else {
                        break;
                    }
                }
                i++;
            }
            this.nuts.set(topIndex, null);
            return nnuts;
        }
        return nnuts;
    }

    public boolean isSameColor(Bolt nbolt) {
        int nTopIndex = nbolt.findTopNutIndex();
        int topIndex = this.findTopNutIndex();
        if (nTopIndex == -1) {
            return true;
        } else if (topIndex == -1) {
            return false;
        }
        int nBoltColor = Integer.parseInt(nbolt.nuts.get(nTopIndex).getColor());
        int boltColor = Integer.parseInt(this.nuts.get(topIndex).getColor());
        return nBoltColor == boltColor;
        // This is returning true should be false
    }

    public Bolt shiftAway(Bolt nbolt) {
        if (this.isSameColor(nbolt) && !nbolt.isFull()) {
            ArrayList<Nut> nnuts = this.remove();
            nbolt.addNutToTop(nnuts);
            return nbolt;
        }
        return nbolt;
    }

    public int getHeight() {
        return nuts.size();
    }

    public String getNutSymbol(int i, AnsiColor colorizer) {
        if (i < 0 || i >= nuts.size()) return "   ";
        if (nuts.get(i) == null) return "   ";
        return " " + nuts.get(i).render(colorizer) + " ";
    }

}
