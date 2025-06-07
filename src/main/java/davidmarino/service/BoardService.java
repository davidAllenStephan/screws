/**
 * Authors: David Allen Stephan Marino
 * Date: 5/27/25
 */

package davidmarino.service;

import davidmarino.model.Board;
import davidmarino.model.Bolt;
import davidmarino.model.Nut;
import davidmarino.utility.AccessFile;
import davidmarino.utility.Display;

import java.util.*;
import java.util.stream.Collectors;

public class BoardService {

    public Board board;

    public BoardService(Board board) {
        this.board = board;
    }

    private ArrayList<Bolt> deepCopyBolts(ArrayList<Bolt> original) {
        ArrayList<Bolt> copy = new ArrayList<>();
        for (Bolt b : original) {
            copy.add(new Bolt(b));
        }
        return copy;
    }

    public void shiftAway(int boltXIndex, int boltYIndex) {
        BoltService.shiftAway(board.getBolts().get(boltXIndex), board.getBolts().get(boltYIndex));
        board.getBoltHistory().add(deepCopyBolts(board.getBolts()));
    }

    public void reverse(int steps) {
        ArrayList<ArrayList<Bolt>> history = board.getBoltHistory();
        int targetIndex = history.size() - 1 - steps;
        if (targetIndex >= 0) {
            board.setBolts(deepCopyBolts(history.get(targetIndex)));
        } else {
            System.out.println("Cannot reverse that far back.");
        }
    }

    public boolean isComplete() {
        for (Bolt bolt : board.getBolts()) {
            if (!BoltService.isComplete(bolt)) {
                return false;
            }
        }
        return true;
    }

    public void print() {
        int maxHeight = 0;
        for (Bolt bolt : board.getBolts()) {
            maxHeight = Math.max(maxHeight, BoltService.getHeight(bolt));
        }

        StringBuilder sb = new StringBuilder();
        Display.gameplayTitle(sb);

        for (int row = 0; row < maxHeight; row++) {
            for (Bolt bolt : board.getBolts()) {
                int nutRow = BoltService.getHeight(bolt) - maxHeight + row;
                String symbol = BoltService.getNutSymbol(bolt, nutRow);
                sb.append(String.format("%-1s", symbol));
            }
            sb.append("\n");
        }

        for (int i = 0; i < board.getBolts().size(); i++) {
            sb.append(String.format(" %-2s", i));
        }
        sb.append("\n");

        System.out.print(sb.toString());
        System.out.flush();
    }

    public void resetBoard() {
        board.setBolts(new ArrayList<>());
        for (int i = 0; i < board.getWidth(); i++) {
            Bolt bolt = new Bolt(board.getHeight());
            board.getBolts().add(bolt);
        }
    }
    public void updateWidth(int width) {
        board.setWidth(width);
        resetBoard();
    }

    public void updateHeight(int height) {
        board.setHeight(height);
        resetBoard();
    }
    public void updateBoardTemplate(int x, int y, int value) {
        board.getBolts().get(x).getNuts().get(y).setValue(value);
    }
    public void printBoard() {
        StringBuilder sb = new StringBuilder();
        sb.append("\u001b[H"); // Move cursor to home
        sb.append("\u001b[2J"); // Clear screen

        for (int row = 0; row < board.getHeight(); row++) {
            sb.append(row).append(" ");
            for (Bolt bolt : board.getBolts()) {
                int nutRow = row;
                int value = bolt.getNuts().get(nutRow).getValue();
                sb.append(" ").append(value).append(" ");
            }
            sb.append("\n");
        }

        sb.append("  ");
        for (int i = 0; i < board.getBolts().size(); i++) {
            sb.append(" ").append(i).append(" ");
        }
        sb.append("\n");

        System.out.print(sb.toString());
        System.out.flush();
    }
    public void save(String fileName) {
        AccessFile<Board> a = new AccessFile<>(Board.class);
        board.setGame_id(UUID.randomUUID().toString());
        board.setBolts(board.getBolts().stream()
                .map(bolt -> {
                    ArrayList<Nut> filteredNuts = bolt.getNuts().stream()
                            .filter(nut -> nut.getValue() != 0).collect(Collectors.toCollection(ArrayList::new));
                    return new Bolt(filteredNuts, board.getHeight());
                })
                .collect(Collectors.toCollection(ArrayList::new)));

        a.writeJson("src/main/resources/games/" + fileName + ".json", board);
    }

    public void fill() {
        ArrayList<ArrayList<Integer>> foo = new ArrayList<>();
        for (int j = 0; j < board.getHeight(); j++) {
            for (int i = 0; i < board.getWidth() - 2; i++) {
                ArrayList<Integer> bar = new ArrayList<>();
                bar.add(i + 1);
                foo.add(bar);
            }
        }
        Random random = new Random();
        int width = board.getWidth();
        int fullStackLength = width - 2;
        Set<Integer> visited = new HashSet<>();
        while (foo.get(board.getWidth()-3).size() != board.getHeight()) {
            for (int i = 0; i < fullStackLength; i++) {
                int ran = random.nextInt(width-2, foo.size());
                boolean valid = !visited.contains(ran) && foo.get(ran).getFirst() != 0;
                while (!valid) {
                    ran = random.nextInt(width-2, foo.size());
                    valid = !visited.contains(ran) && foo.get(ran).getFirst() != 0;
                }
                visited.add(ran);
                foo.get(i).add(foo.get(ran).getFirst());
            }
        }

        for (int i = 0; i < width - 2; i++) {
            for (int j = 0; j < board.getHeight(); j++) {
                board.getBolts().get(i).getNuts().set(j, new Nut(foo.get(i).get(j)));
            }
        }
    }
    public void randomize(int width, int height) {
        board.setWidth(width);
        board.setHeight(height);
        resetBoard();
        fill();
    }
}

