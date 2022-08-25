package andrei.bolun.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import andrei.bolun.model.User;
import andrei.bolun.repository.UserRepository;

public class UserServiceImpl implements UserService {
    private final UserRepository userRepository = UserRepository.getInstance();
    private final Map<String, User> users = userRepository.getUsers();

    @Override
    public List<User> getUsers() {
        List<User> copyOfUsers = new ArrayList<>();
        users.forEach((String str, User user) -> {
            copyOfUsers.add(user);
        });
        return copyOfUsers;
    }

    @Override
    public void createUser(User user) {
        if (user.getUserName() == null || user.getFirstName() == null || user.getLastName() == null) {
            throw new ValidationException("Data is invalid!");
        }
        if (!userRepository.isExist(user.getUserName())) {
            userRepository.addUser(user);
        } else {
            throw new ValidationException("This user exists!");
        }
    }
}
