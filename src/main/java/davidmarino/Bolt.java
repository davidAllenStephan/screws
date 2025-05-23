/**
 * Authors: David Allen Stephan Marino
 * Date: 5/18/25
 */

package davidmarino;

import lombok.Data;

import java.util.ArrayList;

@Data
public class Bolt {
    private ArrayList<Nut> nuts;
    private int spaceIndex;
    private int length;

    public Bolt(int[] nnuts, int length) {
        this.nuts = new ArrayList<Nut>(); // Create an empty ArrayList
        for (int i = 0; i < length; i++) { // Add null for the length of the bolt
            this.nuts.add(null);
        }
        for (int i = 0; i < nnuts.length; i++) { // Add the initial nuts to the ArrayList
            this.nuts.set(i + nnuts.length, new Nut(nnuts[i]));
        }

        this.length = length; // Set the length of the bolt
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
        return findTopNutIndex() == this.length; // Top nut is same as bolt length
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
        if (isEmpty()) throw new BoltException("Bolt is empty cannot remove");
        ArrayList<Nut> nnuts = new ArrayList<>();
        int topIndex = this.findTopNutIndex();
        nnuts.add(this.nuts.get(topIndex));
        int i = topIndex+1;
        while (i < this.nuts.size()) {
            if (this.nuts.get(i) != null) {
                if (nnuts.getFirst().getColor() == this.nuts.get(i).getColor()) {
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

    public boolean isSameColor(Bolt nbolt) {
        int nTopIndex = nbolt.findTopNutIndex();
        int topIndex = this.findTopNutIndex();
        System.out.println("nTopIndex: " + nTopIndex);
        System.out.println("topIndex: " + topIndex);
        if (nTopIndex == -1) {
            return true;
        } else if (topIndex == -1) {
            return false;
        }
        int nBoltColor = nbolt.nuts.get(nTopIndex).getColor();
        int boltColor = this.nuts.get(topIndex).getColor();
        System.out.println("nBoltColor: " + nBoltColor);
        System.out.println("boltColor: " + boltColor);
        return nBoltColor == boltColor;
        // This is returning true should be false
    }

    public Bolt shiftAway(Bolt nbolt) {
        if (nbolt == null) throw new BoltException("Bolt null cannot shift");
        if (nbolt.isFull()) throw new BoltException("NBolt is full cannot shift");
        if (!this.isSameColor(nbolt)) throw new BoltException("Not the same color!");
        ArrayList<Nut> nnuts = this.remove();
        nbolt.addNutToTop(nnuts);
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
