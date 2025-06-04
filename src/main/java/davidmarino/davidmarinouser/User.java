/**
 * Authors: David Allen Stephan Marino
 * Date: 6/4/25
 */

package davidmarino.davidmarinouser;

import lombok.Data;

import java.util.UUID;

/** TODO
 * Check if user already exists
 * if not then create a new one
 * attach to the board as a creator_id
 * attach to the record when the user completes a board
 * Add a login page to the menu
 */
@Data
public class User {
    private String user_id;
    public User(String user_id) {
        this.user_id = user_id;
    }
    public User() {
        this.user_id = UUID.randomUUID().toString();
    }
}
