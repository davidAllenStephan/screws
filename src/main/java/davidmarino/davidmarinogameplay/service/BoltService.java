/**
 * Authors: David Allen Stephan Marino
 * Date: 5/27/25
 */

package davidmarino.davidmarinogameplay.service;

import davidmarino.davidmarinogameplay.model.Bolt;
import davidmarino.davidmarinogameplay.model.Nut;
import davidmarino.davidmarinogameplay.utility.AnsiColor;

import java.util.ArrayList;
import java.util.Objects;

public class BoltService {

    private int findTopNutIndex(Bolt bolt) {
        int topNutIndex = bolt.getNuts().size() - (int) bolt.getNuts().stream()
                .filter(Objects::nonNull).count();
        return (topNutIndex == bolt.getNuts().size()) ? -1 : topNutIndex;
    }

    private int findSpaceIndex(Bolt bolt) {
        return (int) bolt.getNuts().stream().filter(Objects::isNull).count() - 1;
    }

    private boolean isEmpty(Bolt bolt) {
        return bolt.getNuts().stream().noneMatch(Objects::nonNull);
    }

    private boolean isFull(Bolt bolt) {
        return bolt.getNuts().stream().filter(Objects::nonNull).count() == bolt.getMaxBoltLength();
    }

    public boolean isComplete(Bolt bolt) {
        int count = 0;
        int topNutIndex = findTopNutIndex(bolt);
        if (topNutIndex == -1) return true;
        Nut reference = bolt.getNuts().get(topNutIndex);
        for (int i = topNutIndex; i < bolt.getNuts().size(); i++) {
            if (reference.equals(bolt.getNuts().get(i))) count++;
        }
        return count == bolt.getNuts().size() - topNutIndex;
    }

    public ArrayList<Nut> remove(Bolt bolt) {
        if (isEmpty(bolt)) return new ArrayList<>();
        int topNutIndex = findTopNutIndex(bolt);
        Nut topNut = bolt.getNuts().get(topNutIndex);
        ArrayList<Nut> removed = new ArrayList<>();
        for (int i = topNutIndex; i < bolt.getNuts().size(); i++) {
            if (topNut.equals(bolt.getNuts().get(i))) {
                removed.add(bolt.getNuts().get(i));
                bolt.getNuts().set(i, null);
            } else {
                break;
            }
        }
        return removed;
    }

    public void addNutToTop(Bolt bolt, ArrayList<Nut> nuts) {
        for (Nut nut : nuts) {
            int index = findSpaceIndex(bolt);
            if (index >= 0 && index < bolt.getNuts().size()) {
                bolt.getNuts().set(index, nut);
            }
        }
    }

    public boolean isSameColor(Bolt a, Bolt b) {
        int topIndex1 = findTopNutIndex(a);
        int topIndex2 = findTopNutIndex(b);
        if (topIndex2 == -1) return true;
        if (topIndex1 == -1) return false;
        return a.getNuts().get(topIndex1).getValue() == b.getNuts().get(topIndex2).getValue();
    }

    public Bolt shiftAway(Bolt from, Bolt to) {
        if (isSameColor(from, to) && !isFull(to)) {
            ArrayList<Nut> removed = remove(from);
            addNutToTop(to, removed);
        }
        return to;
    }

    public int getHeight(Bolt bolt) {
        return bolt.getNuts().size();
    }

    public String getNutSymbol(Bolt bolt, int index, AnsiColor colorizer) {
        if (index < 0 || index >= bolt.getNuts().size()) return "   ";
        Nut nut = bolt.getNuts().get(index);
        return (nut == null) ? "   " : " " + nut.render(colorizer) + " ";
    }
}