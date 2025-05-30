/**
 * Authors: David Allen Stephan Marino
 * Date: 5/29/25
 */

package davidmarino;


import com.fasterxml.jackson.annotation.JsonIgnore;
import davidmarino.util.AccessFile;
import lombok.Data;

import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Collectors;

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

    public BoardTemplate(ArrayList<BoltTemplate> bolts) {
        this.bolts = bolts;
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
        this.bolts.get(x).getNuts().get(y).setValue(value);
    }

    public void printBoard() {
            StringBuilder sb = new StringBuilder();
            sb.append("\u001b[H"); // Move cursor to home
            sb.append("\u001b[2J"); // Clear screen

            for (int row = 0; row < this.height; row++) {
                sb.append(row).append(" ");
                for (BoltTemplate bolt : this.bolts) {
                    int nutRow = row;
                    int value = bolt.getNuts().get(nutRow).getValue();
                    sb.append(" ").append(value).append(" ");
                }
                sb.append("\n");
            }

            sb.append("  ");
            for (int i = 0; i < this.bolts.size(); i++) {
                sb.append(" ").append(i).append(" ");
            }
            sb.append("\n");

            System.out.print(sb.toString());
            System.out.flush();
    }

    public void save() {
        AccessFile<BoardTemplate> a = new AccessFile<>(BoardTemplate.class);

        this.bolts = this.bolts.stream()
                .map(bolt -> {
                    ArrayList<NutTemplate> filteredNuts = bolt.getNuts().stream()
                            .filter(nut -> nut.getValue() != 0).collect(Collectors.toCollection(ArrayList::new));
                    return new BoltTemplate(filteredNuts, this.height);
                })
                .collect(Collectors.toCollection(ArrayList::new));

        a.writeJson("creator/src/main/resources/test.json", this);
    }


    public void fill() {
        Random rand = new Random();
        for (int i = 0; i < this.width - 2; i++) {
            this.bolts.get(i).fill(this.height-1);
        }
        for (int i = 0; i < this.height; i++) {
            int a = rand.nextInt(this.width-2);
            int b = 0;
            NutTemplate nuta = this.bolts.get(a).getNuts().get(i);
            this.bolts.get(a).getNuts().set(i, this.bolts.get(b).getNuts().get(i));
            this.bolts.get(b).getNuts().set(i, nuta);
        }
    }

    public void randomize(int width, int height) {
        this.width = width;
        this.height = height;
        resetBoard();
        fill();
    }


}
