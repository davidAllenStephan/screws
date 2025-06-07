/**
 * Authors: David Allen Stephan Marino
 * Date: 6/4/25
 */

package davidmarino.initiator;

import davidmarino.model.User;
import davidmarino.model.UserCatalog;
import davidmarino.service.UserService;

public class UserStart {
    public static void main(String[] args) {
        UserCatalog userCatalog = new UserCatalog();
        User user = UserService.createUser();
        UserService.addUser(user, userCatalog);
        UserService.save("src/main/resources/users/users.json", userCatalog);
    }
}
