/**
 * Authors: David Allen Stephan Marino
 * Date: 5/29/25
 */

package davidmarino.davidmarinocreator;

import lombok.Data;

@Data
public class NutTemplate {
    private int value;
    private String color;
    private String type;

    public NutTemplate() {
        this.value = 0;
    }

    public NutTemplate(int value) {
        this.value = value;
    }

}
