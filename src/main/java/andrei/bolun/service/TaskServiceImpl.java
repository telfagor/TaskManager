package andrei.bolun.service;

import java.util.List;

import andrei.bolun.repository.TaskRepository;
import andrei.bolun.model.Task;

public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository = TaskRepository.getInstance();

    @Override
    public List<Task> getUserTasks(String userName) {
        if (userName == null || userName.isEmpty()) {
            throw new ValidationException("Username cannot be empty!");
        }
        return taskRepository.getTasksByUserName(userName);
    }

    @Override
    public void createTask(String userName, Task task) {
        Validation validator = new ValidationImpl();
        validator.validateTask(userName, task);

        if (!taskRepository.isExist(userName, task)) {
            taskRepository.addNewTask(userName, task);
        } else {
            throw new ValidationException("Task " + task.getName() + " already exists!");
        }
    }
}
