package andrei.bolun.app;

import andrei.bolun.model.User;
import andrei.bolun.model.Task;
import andrei.bolun.service.TaskServiceImpl;
import andrei.bolun.service.UserServiceImpl;
import andrei.bolun.service.ValidationException;

import java.util.List;

public class App {
    
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        TaskServiceImpl taskService = new TaskServiceImpl();

        if ("-createUser".equals(args[0])) {
            User user = new User();
            for (String str : args) {
                if (str.startsWith("-fn")) {
                    user.setFirstName(str.split("=")[1]);
                } else if (str.startsWith("-ln")) {
                    user.setLastName(str.split("=")[1]);
                } else if (str.startsWith("-un")) {
                    user.setUserName(str.split("=")[1]);
                }
            }
            try {
                userService.createUser(user);
                System.out.println("New user added!");
            } catch (ValidationException ex) {
                System.out.println(ex.getMessage());
            }

        } else if ("-showAllUsers".equals(args[0])) {
            List<User> users = userService.getUsers();
            if (!users.isEmpty()) {
                users.forEach(System.out::println);
            } else {
                System.out.println("No users have been added yet!");
            }
        } else if ("-addTask".equals(args[0])) {
            String userName = null; //приходится userName присвоить null
            Task task = new Task();
            for (String str : args) {
                if (str.startsWith("-un")) {
                    userName = str.split("=")[1];
                } else if (str.startsWith("-tt")) {
                    task.setName(str.split("=")[1]);
                } else if (str.startsWith("-td")) {
                    task.setDescription(str.split("=")[1]);
                }
                try {
                    taskService.createTask(userName, task);
                    System.out.println("New task added!");
                } catch (ValidationException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        } else if ("-showTasks".equals(args[0])) {
            if (args[1].startsWith("-un")) {
                String userName = args[1].split("=")[1];
                List<Task> userTasks = taskService.getUserTasks(userName);
                if (!userTasks.isEmpty()) {
                    userTasks.forEach(System.out::println);
                } else {
                    System.out.println("This user does not exist!");
                }
            }
        }
    }
}
