/**
 * Authors: David Allen Stephan Marino
 * Date: 6/4/25
 */

package davidmarino.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import davidmarino.model.User;
import davidmarino.model.UserCatalog;

import java.io.File;
import java.io.IOException;

public class UserService {
    public static User createUser(String user_id) {
        return new User(user_id);
    }
    public static User createUser() {
        return new User();
    }
    public static User findUserById(String user_id, UserCatalog userCatalog) {
        for (User user : userCatalog.getUsers()) {
            if (user.getUser_id().equals(user_id)) {
                return user;
            }
        }
        return null;
    }
    public static void addUser(User user, UserCatalog userCatalog) {
        userCatalog.getUsers().add(user);
    }
    public static void save(String path, UserCatalog userCatalog) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(path), userCatalog);
        } catch (IOException e) {
            throw new RuntimeException("Failed to write JSON to file", e);
        }
    }
    public static UserCatalog read(String path) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(new File(path), UserCatalog.class);
        } catch (IOException e) {
            throw new RuntimeException("Failed to read JSON from file", e);
        }
    }
}
