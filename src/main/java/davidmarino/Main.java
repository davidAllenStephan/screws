package davidmarino;


/**
 * TODO:
 * Add a leaderboard
 * Make customizable levels
 * Save levels
 * Load levels
 * Test different bolt size
 * User keymap
 */

public class Main {
    public static void main(String[] args) {
        ReadJson readJson = new ReadJson();
        readJson.readJson();
        int nBolts = 3;
        int nDistinctNut = 1;
        int maxBoltLength = 3;
        int[] boltHeightMap = new int[nBolts];
        for (int i = 0; i < nBolts; i++) {
            boltHeightMap[i] = i;
        }
        Board board = new Board(nDistinctNut,maxBoltLength, nBolts);
        board.print();
        long startTime = System.nanoTime();
        while (true) {
            if (board.isComplete()) {
                long endTime = System.nanoTime();
                long elapsedTime = endTime - startTime;
                double seconds = (double) elapsedTime / 1_000_000_000.0;
                System.out.println("Completed!");
                System.out.println(seconds);
            }
            int[] control = Input.input();
            if (control[0] == -1) {
                break;
            }
            board.getBolts().get(control[0]).shiftAway(board.getBolts().get(control[1]));
            board.print();
        }
    }
}