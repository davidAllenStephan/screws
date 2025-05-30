/**
 * Authors: David Allen Stephan Marino
 * Date: 5/28/25
 */

package davidmarino.util;

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

    public void writeJson(String path, T data) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(path), data);
        } catch (IOException e) {
            throw new RuntimeException("Failed to write JSON to file: " + path, e);
        }
    }
}

