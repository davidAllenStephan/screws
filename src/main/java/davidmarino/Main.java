package davidmarino;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
        int[][] nboard = {{2,6},{0,6},{0,1}, {}, {}};
        Board board = new Board(nboard,4);
        board.print();
        while (true) {
            int[] control = Input.input();
            if (control[0] == -1) {
                break;
            }
            board.getBolts().get(control[0]).shiftAway(board.getBolts().get(control[1]));
            board.print();
        }
    }
}