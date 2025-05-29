/**
 * Authors: David Allen Stephan Marino
 * Date: 5/29/25
 */

package davidmarino.creator;

import lombok.Data;

@Data
public class NutTemplate {
    private int value;
    private String color;
    private String type;

    public NutTemplate() {}

    public NutTemplate(int value, String color, String type) {
        this.value = value;
        this.color = color;
        this.type = type;
    }

}
