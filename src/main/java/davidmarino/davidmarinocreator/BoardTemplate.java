/**
 * Authors: David Allen Stephan Marino
 * Date: 5/29/25
 */

package davidmarino.davidmarinocreator;


import com.fasterxml.jackson.annotation.JsonIgnore;
import davidmarinocreator.util.AccessFile;
import lombok.Data;

import java.util.*;
import java.util.stream.Collectors;

@Data
public class BoardTemplate {
    private String game_id;
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

    public void save(String fileName) {
        AccessFile<BoardTemplate> a = new AccessFile<>(BoardTemplate.class);
        this.game_id = UUID.randomUUID().toString();
        this.bolts = this.bolts.stream()
                .map(bolt -> {
                    ArrayList<NutTemplate> filteredNuts = bolt.getNuts().stream()
                            .filter(nut -> nut.getValue() != 0).collect(Collectors.toCollection(ArrayList::new));
                    return new BoltTemplate(filteredNuts, this.height);
                })
                .collect(Collectors.toCollection(ArrayList::new));

        a.writeJson("src/main/resources/games/" + fileName + ".json", this);
    }

    public void fill() {
        ArrayList<ArrayList<Integer>> foo = new ArrayList<>();
        for (int j = 0; j < this.height; j++) {
            for (int i = 0; i < this.width - 2; i++) {
                ArrayList<Integer> bar = new ArrayList<>();
                bar.add(i + 1);
                foo.add(bar);
            }
        }
        Random random = new Random();
        int fullStackLength = this.width - 2;
        Set<Integer> visited = new HashSet<>();
        while (foo.get(this.width-3).size() != this.height) {
            for (int i = 0; i < fullStackLength; i++) {
                int ran = random.nextInt(this.width-2, foo.size());
                boolean valid = !visited.contains(ran) && foo.get(ran).getFirst() != 0;
                while (!valid) {
                    ran = random.nextInt(this.width-2, foo.size());
                    valid = !visited.contains(ran) && foo.get(ran).getFirst() != 0;
                }
                visited.add(ran);
                foo.get(i).add(foo.get(ran).getFirst());
            }
        }

        for (int i = 0; i < this.width - 2; i++) {
            this.bolts.get(i);
            for (int j = 0; j < this.height; j++) {
                this.bolts.get(i).getNuts().set(j, new NutTemplate(foo.get(i).get(j)));
            }
        }
    }

    public void randomize(int width, int height) {
        this.width = width;
        this.height = height;
        resetBoard();
        fill();
    }

}
