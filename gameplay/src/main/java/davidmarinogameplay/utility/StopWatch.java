/**
 * Authors: David Allen Stephan Marino
 * Date: 5/27/25
 */

package davidmarinogameplay.utility;

public class StopWatch {
    private long startTime;
    private long stopTime;
    public StopWatch() {};
    public void start() {
        startTime = System.currentTimeMillis();
    }
    public void stop() {
        stopTime = System.currentTimeMillis();
    }
    public double seconds() {
        return (stopTime - startTime) / 1000.00;
    }
}
