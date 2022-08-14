package andrei.bolun.storage;

import java.util.Map;
import java.util.HashMap;

import andrei.bolun.model.User;
public class UserRepository {
    private final Map<String, User> users = new HashMap<>();

    public Map<String, User> getUsers() {
        return users;
    }

    public void addNewUser(User user) {
        String userName = user.getUserName();
        if (!users.containsKey(userName)) {
            users.put(userName, user);
        }
    }
}
