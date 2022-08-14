package andrei.bolun.service;

import java.util.Map;
import java.util.Set;

import andrei.bolun.model.User;
import andrei.bolun.storage.UserRepository;

public class UserService {
    private final UserRepository userRepository = new UserRepository();
    private final Map<String, User> users = userRepository.getUsers();

    public void showUsers() {
        Set<String> userNames = users.keySet();
        userNames.forEach(System.out::println);
    }

    public void createNewUser(User user) {
        userRepository.addNewUser(user);
    }
}
