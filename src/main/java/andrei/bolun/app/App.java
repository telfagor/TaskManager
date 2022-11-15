package andrei.bolun.app;

import andrei.bolun.model.User;
import andrei.bolun.model.Task;
import andrei.bolun.service.*;

import java.util.List;

public class App {
    private static final String SEPARATOR = "=";

    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        TaskService taskService = new TaskServiceImpl();

        if ("-createUser".equals(args[0])) {
            createUser(args, userService);
        } else if ("-showAllUsers".equals(args[0])) {
            showAllUsers(userService);
        } else if ("-addTask".equals(args[0])) {
            addTask(args, taskService);
        } else if ("-showTasks".equals(args[0])) {
            showTasks(args, taskService);
        }
    }

    private static void createUser(String[] args, UserService userService) {
        User user = new User();
        for (String argument : args) {
            if (argument.startsWith("-fn")) {
                user.setFirstName(extractValue(argument));
            } else if (argument.startsWith("-ln")) {
                user.setLastName(extractValue(argument));
            } else if (argument.startsWith("-un")) {
                user.setUserName(extractValue(argument));
            }
        }
        try {
            userService.createUser(user);
            System.out.println("User " + user.getUserName() + " added!");
        } catch (ValidationException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static void showAllUsers(UserService userService) {
        List<User> users = userService.getUsers();
        if (!users.isEmpty()) {
            users.forEach(System.out::println);
        } else {
            System.out.println("No users have been added yet!");
        }
    }

    private static void addTask(String[] args, TaskService taskService) {
        String userName = null;
        Task task = new Task();
        for (String argument : args) {
            if (argument.startsWith("-un")) {
                userName = extractValue(argument);
            } else if (argument.startsWith("-tt")) {
                task.setName(extractValue(argument));
            } else if (argument.startsWith("-td")) {
                task.setDescription(extractValue(argument));
            }
        }
        try {
            taskService.createTask(userName, task);
            System.out.println("New task to " + userName + " added!");
        } catch (ValidationException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static void showTasks(String[] args, TaskService taskService) {
        if (args[1].startsWith("-un")) {
            String userName = extractValue(args[1]);
            List<Task> userTasks = taskService.getUserTasks(userName);
            if (!userTasks.isEmpty()) {
                userTasks.forEach(System.out::println);
            } else {
                System.out.println("User " + userName + " does not exist!");
            }
        } else {
            System.out.println("Flag -un does not exists!");
        }
    }

    private static String extractValue(String argument) {
        return argument.split(SEPARATOR)[1];
    }
}
