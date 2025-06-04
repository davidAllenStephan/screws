/**
 * Authors: David Allen Stephan Marino
 * Date: 6/4/25
 */

package davidmarino.davidmarinouser;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;

@Data
public class UserCatalog {
    private ArrayList<User> users;
    public UserCatalog() {
        users = new ArrayList<>();
    }

    @JsonCreator
    public UserCatalog(@JsonProperty("users") ArrayList<User> users) {
        this.users = users;
    }

}
