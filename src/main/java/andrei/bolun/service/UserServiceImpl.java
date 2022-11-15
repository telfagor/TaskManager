package andrei.bolun.service;

import java.util.List;

import andrei.bolun.model.User;
import andrei.bolun.repository.UserRepository;

public class UserServiceImpl implements UserService {
    private final UserRepository userRepository = UserRepository.getInstance();

    @Override
    public List<User> getUsers() {
        return userRepository.getUsers();
    }

    @Override
    public void createUser(User user) {
       Validation validator = new ValidationImpl();
       validator.validateUser(user);

        if (!userRepository.isExist(user.getUserName())) {
            userRepository.addUser(user);
        } else {
            throw new ValidationException("This user " + user.getUserName() + " exists!");
        }
    }
}
