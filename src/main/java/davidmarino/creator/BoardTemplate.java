/**
 * Authors: David Allen Stephan Marino
 * Date: 5/29/25
 */

package davidmarino.creator;


import com.fasterxml.jackson.annotation.JsonIgnore;
import davidmarino.AccessFile;
import lombok.Data;

import java.util.ArrayList;

@Data
public class BoardTemplate {
    private ArrayList<BoltTemplate> bolts;
    @JsonIgnore
    private int width;
    @JsonIgnore
    private int height;

    public BoardTemplate(int width, int height) {
        bolts = new ArrayList<>();
        this.width = width;
        this.height = height;
        resetBoard();
    }

    private void resetBoard() {
        this.bolts = new ArrayList<>();
        for (int i = 0; i < width; i++) {
            BoltTemplate bolt = new BoltTemplate(this.height);
            bolts.add(bolt);
        }
    }

    public void updateWidth(int width) {
        this.width = width;
        resetBoard();
    }

    public void updateHeight(int height) {
        this.height = height;
        resetBoard();
    }

    public void updateBoardTemplate(int x, int y, int value) {
        this.bolts.get((this.width - x) - 1).getNuts().get(y).setValue(value);
    }

    public void printBoard() {
        System.out.print("   ");
        for (int i = 0; i < this.height; i++) {
            System.out.print(" " + i + " ");
        }
        System.out.println();
        for (int i = 0; i < this.width; i++) {
            System.out.print(" " + ((this.width - i) - 1) + " ");
            for (int j = 0; j < this.height; j++) {
                int value = this.bolts.get(i).getNuts().get(j).getValue();
                if (value != 0) {
                    System.out.print(" " + bolts.get(i).getNuts().get(j).getValue() + " ");
                } else {
                    System.out.print(" X ");
                }
            }
            System.out.println();
        }
    }

    public void save() {
        AccessFile<BoardTemplate> a = new AccessFile<>(BoardTemplate.class);
        this.bolts.forEach(BoltTemplate::removeNull);
        a.writeJson("src/main/resources/test.json", this);
    }


}
