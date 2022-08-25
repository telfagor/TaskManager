package andrei.bolun.service;

import java.util.List;

import andrei.bolun.repository.TaskRepository;
import andrei.bolun.model.Task;

public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository = TaskRepository.getInstance();

    @Override
    public List<Task> getUserTasks(String userName) {
        return taskRepository.getTasksByUserName(userName);
    }

    //я добавил проверки
    @Override
    public void createTask(String userName, Task task) {
        if (task.getName() == null && task.getDescription() == null) {
            throw new ValidationException("Data is invalid!");
        }
        if (!taskRepository.isExist(userName, task)) {
            taskRepository.addNewTask(userName, task);
        } else {
            throw new ValidationException("The task already exists!");
        }

    }
}
