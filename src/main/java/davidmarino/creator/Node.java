/**
 * Authors: David Allen Stephan Marino
 * Date: 5/29/25
 */

package davidmarino.creator;

import lombok.Data;

@Data
public class Node {
    private int value;

    public Node(int value) {
        this.value = value;
    }

    public Node() {
        this.value = -1;
    }
}
