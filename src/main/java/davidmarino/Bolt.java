/**
 * Authors: David Allen Stephan Marino
 * Date: 5/18/25
 */

package davidmarino;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Data
public class Bolt {
    private ArrayList<Nut> nuts;
    private int maxBoltLength;
    private int maxNuts;

    @JsonCreator
    public Bolt(@JsonProperty("nuts") ArrayList<Nut> nuts, @JsonProperty("maxBoltLength") int maxBoltLength) {
        this.nuts = nuts;
        this.maxNuts = nuts.size();
        for (int i = nuts.size(); i < maxBoltLength; i++) {
            this.nuts.addFirst(null);
        }
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
        // Is complete if either the bolt is empty or is full
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
        return (int) this.nuts.stream()
                .filter(Objects::isNull)
                .count() - 1;
    }

    private int findTopNutIndex() { // Find top nut index
        int topNutIndex = (this.nuts.size()) - (int) this.nuts.stream()
                .filter(Objects::nonNull)
                .count();
        return (topNutIndex == this.nuts.size()) ? -1 : topNutIndex;
    }

    private boolean isFull() {
        return ((int)this.nuts.stream().filter(Objects::nonNull).count() == this.maxBoltLength);
    }

    private boolean isEmpty() {
        return (this.nuts.stream().filter(Objects::isNull).count() == this.maxBoltLength);
    }

    public void addNutToTop(ArrayList<Nut> nuts) { // Add nut to top of bolt
        nuts.forEach(nut -> this.nuts.set(this.findSpaceIndex(), nut));
    }

    public ArrayList<Nut> remove() {
        if (isEmpty()) return new ArrayList<>();
        Nut topNut = this.nuts.get(findTopNutIndex());
        return this.nuts.stream().filter(Objects::nonNull).map(nut -> {
            if (nut.equals(topNut)) {
                this.nuts.set(findTopNutIndex(), null);
                return nut;
            }
            return null;
        }).collect(Collectors.toCollection(ArrayList::new));
    }

    public boolean isSameColor(Bolt nbolt) {
        int nTopIndex = nbolt.findTopNutIndex();
        int topIndex = this.findTopNutIndex();
        if (nTopIndex == -1) {
            return true;
        } else if (topIndex == -1) {
            return false;
        }
        int nBoltColor = nbolt.nuts.get(nTopIndex).getValue();
        int boltColor = this.nuts.get(topIndex).getValue();
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
