/**
 * Authors: David Allen Stephan Marino
 * Date: 5/28/25
 */

package davidmarino.davidmarinogameplay.utility;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class AccessFile<T> {

    private final Class<T> type;

    public AccessFile(Class<T> type) {
        this.type = type;
    }

    public T readJson(String path) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(new File(path), type);
        } catch (IOException e) {
            throw new RuntimeException("Failed to read JSON from file: " + path, e);
        }
    }
}

