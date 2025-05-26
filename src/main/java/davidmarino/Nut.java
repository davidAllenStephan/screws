/**
 * Authors: David Allen Stephan Marino
 * Date: 5/18/25
 */

package davidmarino;

import lombok.Data;

@Data
public class Nut {
    private int color = -1;

    public Nut(int color) {
        this.color = color;
    }

    public String render(AnsiColor colorizer) {
        return colorizer.colorize(this.color, "\u2580");
    }

    public boolean equals(Nut o) {
        return this.color == o.getColor();
    }
}
