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
        for (int i = 0; i < this.nuts.size(); i++) { // Iterates top to bottom returns first not null index
            if (this.nuts.get(i) != null) {
                return i;
            }
        }
        return -1;
    }

    private boolean isFull() {
        return findTopNutIndex() == this.length - 1; // Top nut is same as bolt length
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

//    public Bolt add(ArrayList<Nut> nuts) {
//        if (this.nuts.size()-1 < this.length) {
//            for (Nut nut : nuts) {
//                if (this.isEmpty() && this.nuts.size() <= 1) { // Empty and size is 0 or 1
//                    this.nuts.addFirst(nut);
//                    this.spaceIndex = 0;
//                } else if (this.isEmpty() && this.nuts.size() > 1) { // Empty and size is greater than 0 or 1
//                    this.nuts.addLast(nut);
//                    this.spaceIndex = this.nuts.size() - 2;
//                } else { // Not empty but this.nuts size is less than the space available
//                    int j = this.nuts.size() - 1;
//                    while (j >= 0) {
//                        if (this.nuts.get(j) == null) { // There is a null spot available
//                            this.nuts.set(j, nut);
//                            this.spaceIndex = j;
//                        }
//                        j--;
//                    }
//                    if (j == -1) {
//                        this.nuts.addFirst(nut);
//                        this.spaceIndex = 0;
//                    }
//                }
//            }
//        } else {
//            if (isFull()) throw new BoltException("Bolt is full cannot add");
//            for (Nut nut: nuts) {
//                if (this.nuts.get(this.spaceIndex).getColor() != nut.getColor()) throw new BoltException("Nut color does not match");
//                this.nuts.set(this.spaceIndex-1, nut);
//                this.spaceIndex--;
//            }
//        }
//        return this;
//    }

    public ArrayList<Nut> remove() {
        if (isEmpty()) throw new BoltException("Bolt is empty cannot remove");
        ArrayList<Nut> nnuts = new ArrayList<>();
        int topIndex = this.findTopNutIndex();
        nnuts.add(this.nuts.get(topIndex));
        for (int i = topIndex; i < this.nuts.size() - 1; i++) {
            if (this.nuts.get(i).getColor() == this.nuts.get(i+1).getColor()) {
                nnuts.add(this.nuts.get(i+1));
                this.nuts.set(i+1, null);
            }
        }
        this.nuts.set(topIndex, null);
        return nnuts;
    }

    public Bolt shiftAway(Bolt nbolt) {
        if (nbolt == null) throw new BoltException("Bolt null cannot shift");
        if (nbolt.isFull()) throw new BoltException("NBolt is full cannot shift");
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
