package davidmarino;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
        Board board = new Board(6,10);
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