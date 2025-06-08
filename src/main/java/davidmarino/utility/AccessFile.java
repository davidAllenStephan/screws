/**
 * Authors: David Allen Stephan Marino
 * Date: 5/28/25
 */

package davidmarino.utility;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class AccessFile<T> {

    private final Class<T> type;

    public AccessFile(Class<T> type) {
        this.type = type;
    }

    public T readJson(File file) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(file, type);
        } catch (IOException e) {
            throw new RuntimeException("Failed to read JSON from file: " + file.getName(), e);
        }
    }

    public void writeJson(File file, T data) {
        ObjectMapper mapper = new ObjectMapper();
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(file, data);
        } catch (IOException e) {
            throw new RuntimeException("Failed to write JSON to file: " + file.getName(), e);
        }
    }
}

