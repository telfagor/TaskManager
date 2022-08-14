package andrei.bolun.storage;

import java.util.Map;
import java.util.HashMap;
import java.util.List;

import andrei.bolun.model.Task;
import andrei.bolun.model.User;

public class TaskRepository {
    private final Map<String, List<Task>> userTasks = new HashMap<>();

    public List<Task> getTaskByUserName(String userName) {
        if (userTasks.containsKey(userName)) {
            return userTasks.get(userName);
        }
        return null;
    }

    public void addNewTask(User user, Task task) {
        String userName = user.getUserName();
        if (userTasks.containsKey(userName)) {
            userTasks.get(userName).add(task);
        }
    }
}
