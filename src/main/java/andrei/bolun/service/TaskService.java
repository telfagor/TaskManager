package andrei.bolun.service;

import java.util.List;

import andrei.bolun.storage.TaskRepository;
import andrei.bolun.model.Task;

public class TaskService {
    private final TaskRepository taskRepository = new TaskRepository();

    public void showUserTasks(String userName) {
        List<Task> userTasks = taskRepository.getTaskByUserName(userName);
        userTasks.forEach(System.out::println);
    }
}
