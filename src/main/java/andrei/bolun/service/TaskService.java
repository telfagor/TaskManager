package andrei.bolun.service;

import andrei.bolun.model.Task;

import java.util.List;
public interface TaskService {
    List<Task> getUserTasks(String userName);

    void createTask(String userName, Task task);
}
