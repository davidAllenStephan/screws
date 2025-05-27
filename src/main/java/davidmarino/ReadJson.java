/**
 * Authors: David Allen Stephan Marino
 * Date: 5/26/25
 */

package davidmarino;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class ReadJson {
    public static Board readJson() {
        ObjectMapper mapper = new ObjectMapper();
        Board data;
        try {
            data = mapper.readValue(new File("src/main/resources/level.json"), Board.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return data;
    }
}
