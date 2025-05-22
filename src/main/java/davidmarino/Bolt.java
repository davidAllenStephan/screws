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
        this.nuts = new ArrayList<Nut>();
        for (int i = 0; i < length; i++) {
            this.nuts.add(null);
        }
        for (int i = 0; i < nnuts.length; i++) {
            this.nuts.set(i + nnuts.length, new Nut(nnuts[i]));
        }

        this.length = length;
        this.spaceIndex = findSpaceIndex();
    }

    private int findSpaceIndex() { // finds open space index
        int i = this.nuts.size() - 1;
        while (i >= 0) {
            if (this.nuts.get(i) == null) {
                return i;
            }
            i--;
        }
        return -1;
    }

    private int findTopNutIndex() { // finds index of top nut
        for (int i = 0; i < this.nuts.size(); i++) {
            if (this.nuts.get(i) != null) {
                return i;
            }
        }
        return -1;
    }

    private boolean isFull() {
        if (this.nuts.isEmpty()) return false;
        if (this.spaceIndex == 0) return false;
        return this.nuts.get(this.spaceIndex-1) != null;
    }

    private boolean isEmpty() {
        if (this.nuts.isEmpty()) return true;
        for (Nut nut: this.nuts) {
            if (nut != null) {
                return false;
            }
        }
        System.out.println("this.nuts: " + this.nuts);
        return true;
    }

    public Bolt addNutToTop(ArrayList<Nut> nuts) {
        for (Nut nut: nuts) {
            int i = this.findSpaceIndex();
            this.nuts.set(i, nut);
        }
        return this;
    }

    public Bolt add(ArrayList<Nut> nuts) {
        System.out.println("nuts: " + nuts);
        if (this.nuts.size()-1 < this.length) {
            for (Nut nut : nuts) {
                if (this.isEmpty() && this.nuts.size() <= 1) { // Empty and size is 0 or 1
                    System.out.println("enter 1");
                    this.nuts.addFirst(nut);
                    this.spaceIndex = 0;
                } else if (this.isEmpty() && this.nuts.size() > 1) { // Empty and size is greater than 0 or 1
                    System.out.println("enter 2");
                    this.nuts.addLast(nut);
                    this.spaceIndex = this.nuts.size() - 2;
                } else { // Not empty but this.nuts size is less than the space available
                    int j = this.nuts.size() - 1;
                    while (j >= 0) {
                        if (this.nuts.get(j) == null) { // There is a null spot available
                            this.nuts.set(j, nut);
                            this.spaceIndex = j;
                        }
                        j--;
                    }
                    if (j == -1) {
                        this.nuts.addFirst(nut);
                        this.spaceIndex = 0;
                    }
                }
            }
        } else {
            System.out.println("enter 5");
            if (isFull()) throw new BoltException("Bolt is full cannot add");
            for (Nut nut: nuts) {
                if (this.nuts.get(this.spaceIndex).getColor() != nut.getColor()) throw new BoltException("Nut color does not match");
                this.nuts.set(this.spaceIndex-1, nut);
                this.spaceIndex--;
            }
        }
        return this;
    }

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


//        this.spaceIndex++;
//        if (this.spaceIndex == this.nuts.size()) this.spaceIndex--;
//        if (spaceIndex == this.nuts.size()-1) return nnuts;
//        System.out.println("spaceIndex: " + spaceIndex);
//        while (this.nuts.get(this.spaceIndex).getColor() == nnuts.getLast().getColor()) {
//            nnuts.add(this.nuts.get(this.spaceIndex));
//            this.nuts.set(this.spaceIndex, null);
//            this.spaceIndex++;
//            if (this.spaceIndex == this.nuts.size()) this.spaceIndex = 0;
//        }
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
