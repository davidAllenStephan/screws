/**
 * Authors: David Allen Stephan Marino
 * Date: 5/27/25
 */

package davidmarino.gameplay.service;

import davidmarino.gameplay.model.Board;
import davidmarino.gameplay.model.Bolt;
import davidmarino.gameplay.utility.AnsiColor;

import java.util.ArrayList;

public class BoardService {

    private final BoltService boltService;

    public BoardService(BoltService boltService) {
        this.boltService = boltService;
    }

    private ArrayList<Bolt> deepCopyBolts(ArrayList<Bolt> original) {
        ArrayList<Bolt> copy = new ArrayList<>();
        for (Bolt b : original) {
            copy.add(new Bolt(b));
        }
        return copy;
    }

    public void shiftAway(Board board, int boltXIndex, int boltYIndex) {
        boltService.shiftAway(board.getBolts().get(boltXIndex), board.getBolts().get(boltYIndex));
        board.getBoltHistory().add(deepCopyBolts(board.getBolts()));
    }

    public void reverse(Board board, int steps) {
        ArrayList<ArrayList<Bolt>> history = board.getBoltHistory();
        int targetIndex = history.size() - 1 - steps;
        if (targetIndex >= 0) {
            board.setBolts(deepCopyBolts(history.get(targetIndex)));
        } else {
            System.out.println("Cannot reverse that far back.");
        }
    }

    public boolean isComplete(Board board) {
        for (Bolt bolt : board.getBolts()) {
            if (!boltService.isComplete(bolt)) {
                return false;
            }
        }
        return true;
    }

    public void print(Board board) {
        AnsiColor colorizer = new AnsiColor();
        int maxHeight = 0;
        for (Bolt bolt : board.getBolts()) {
            maxHeight = Math.max(maxHeight, boltService.getHeight(bolt));
        }

        StringBuilder sb = new StringBuilder();
        sb.append("\u001b[H"); // Move cursor to home
        sb.append("\u001b[2J"); // Clear screen
        sb.append("░██████╗░█████╗░██████╗░███████╗░██╗░░░░░░░██╗░██████╗\n");
        sb.append("██╔════╝██╔══██╗██╔══██╗██╔════╝░██║░░██╗░░██║██╔════╝\n");
        sb.append("╚█████╗░██║░░╚═╝██████╔╝█████╗░░░╚██╗████╗██╔╝╚█████╗░\n");
        sb.append("░╚═══██╗██║░░██╗██╔══██╗██╔══╝░░░░████╔═████║░░╚═══██╗\n");
        sb.append("██████╔╝╚█████╔╝██║░░██║███████╗░░╚██╔╝░╚██╔╝░██████╔╝\n");
        sb.append("╚═════╝░░╚════╝░╚═╝░░╚═╝╚══════╝░░░╚═╝░░░╚═╝░░╚═════╝░\n");
        sb.append("\n\n\n");

        for (int row = 0; row < maxHeight; row++) {
            for (Bolt bolt : board.getBolts()) {
                int nutRow = boltService.getHeight(bolt) - maxHeight + row;
                String symbol = boltService.getNutSymbol(bolt, nutRow, colorizer);
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
}

