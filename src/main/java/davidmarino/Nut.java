/**
 * Authors: David Allen Stephan Marino
 * Date: 5/18/25
 */

package davidmarino;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Nut {
    private int value = -1;
    private String color;
    private String type;

    @JsonCreator
    public Nut (@JsonProperty("value") int value, @JsonProperty("color") String color, @JsonProperty("type") String type) {
        this.value = value;
        this.color = color;
        this.type = type;
    }

    public Nut(int value) {
        this.value = value;
    }

    public String render(AnsiColor colorizer) {
        return colorizer.colorize(this.value, "\u2580");
    }

    public boolean equals(Nut o) {
        return this.value == o.getValue();
    }
}
