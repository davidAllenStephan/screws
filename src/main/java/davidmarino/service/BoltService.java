/**
 * Authors: David Allen Stephan Marino
 * Date: 5/27/25
 */

package davidmarino.service;

import davidmarino.model.Bolt;
import davidmarino.model.Nut;

import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.Collectors;

public class BoltService {

    public ArrayList<Nut> nuts;

    private static int findTopNutIndex(Bolt bolt) {
        int topNutIndex = bolt.getNuts().size() - (int) bolt.getNuts().stream()
                .filter(Objects::nonNull).count();
        return (topNutIndex == bolt.getNuts().size()) ? -1 : topNutIndex;
    }

    private static int findSpaceIndex(Bolt bolt) {
        return (int) bolt.getNuts().stream().filter(Objects::isNull).count() - 1;
    }

    private static boolean isEmpty(Bolt bolt) {
        return bolt.getNuts().stream().noneMatch(Objects::nonNull);
    }

    private static boolean isFull(Bolt bolt) {
        return bolt.getNuts().stream().filter(Objects::nonNull).count() == bolt.getMaxBoltLength();
    }

    public static boolean isComplete(Bolt bolt) {
        int count = 0;
        int topNutIndex = findTopNutIndex(bolt);
        if (topNutIndex == -1) return true;
        Nut reference = bolt.getNuts().get(topNutIndex);
        for (int i = topNutIndex; i < bolt.getNuts().size(); i++) {
            if (reference.equals(bolt.getNuts().get(i))) count++;
        }
        return count == bolt.getNuts().size();
    }

    public static ArrayList<Nut> remove(Bolt bolt) {
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

    public static void addNutToTop(Bolt bolt, ArrayList<Nut> nuts) {
        for (Nut nut : nuts) {
            int index = findSpaceIndex(bolt);
            if (index >= 0 && index < bolt.getNuts().size()) {
                bolt.getNuts().set(index, nut);
            }
        }
    }

    public static boolean isSameColor(Bolt a, Bolt b) {
        int topIndex1 = findTopNutIndex(a);
        int topIndex2 = findTopNutIndex(b);
        if (topIndex2 == -1) return true;
        if (topIndex1 == -1) return false;
        return a.getNuts().get(topIndex1).getValue() == b.getNuts().get(topIndex2).getValue();
    }

    public static void shiftAway(Bolt from, Bolt to) {
        if (isSameColor(from, to) && !isFull(to)) {
            ArrayList<Nut> removed = remove(from);
            addNutToTop(to, removed);
        }
    }

    public static int getHeight(Bolt bolt) {
        return bolt.getNuts().size();
    }

    public static String getNutSymbol(Bolt bolt, int index) {
        if (index < 0 || index >= bolt.getNuts().size()) return "   ";
        Nut nut = bolt.getNuts().get(index);
        return (nut == null) ? "   " : " " + nut.render() + " ";
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
            this.nuts.set(i, new Nut(color));
        }
    }

    public void removeNull() {
        this.nuts = new ArrayList<Nut>(this.nuts.stream().map(nut -> nut.getValue() != 0 ? nut : null).collect(Collectors.toList()).reversed());
    }
}