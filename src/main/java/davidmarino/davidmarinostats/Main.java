/**
 * Authors: David Allen Stephan Marino
 * Date: 6/1/25
 */

package davidmarino.davidmarinostats;

public class Main {
    public static void main(String[] args) {
        Leaderboard leaderboard = Leaderboard.read("src/main/resources/leaderboards/leaderboard.json");
        for (int i = 0; i < 2; i++) {
            leaderboard.addRecord(new Record(0, i));
        }
        for (int i = 0; i < 2; i++) {
            leaderboard.addRecord(new Record(0, 1-i));
        }
        leaderboard.sort();
        leaderboard.save("src/main/resources/leaderboards/leaderboard.json");
    }
}
