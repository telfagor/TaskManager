package andrei.bolun.app;

import andrei.bolun.model.User;
import andrei.bolun.service.TaskService;
import andrei.bolun.service.UserService;
import andrei.bolun.storage.UserRepository;

import java.util.Map;

public class App {
    
    public static void main(String[] args) {
        UserRepository userRepository = new UserRepository();
        UserService userService = new UserService();
        TaskService taskService = new TaskService();
        Map<String, User> users = userRepository.getUsers();

        if ("-showAllUsers".equals(args[0])) {
            userService.showUsers();
        } else if ("-createUser".equals(args[0])) {
            User user = new User();
            if (args[1].startsWith("-fn")) {
                String firstName = args[1].split("=")[1];
                user.setFirstName(firstName);
            } else if (args[2].startsWith("-ln")) {
                String lastName = args[2].split("=")[1];
                user.setLastName(lastName);
            } else if (args[3].startsWith("-un")) {
                String userName = args[3].split("=")[1];
                user.setUserName(userName);
            }
            userService.createNewUser(user);
        } else if ("showUserTasks".equals(args[0])) {
            if (args[1].startsWith("-un")) {
                String userName = args[1].split("=")[1];
                taskService.showUserTasks(userName);
            }
        }
    }
}
