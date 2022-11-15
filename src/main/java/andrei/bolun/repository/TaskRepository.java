package andrei.bolun.repository;

import java.io.*;
import java.util.*;

import andrei.bolun.model.Task;

public class TaskRepository {
    private static final String STORAGE_FILE_NAME = "storage.txt";
    private final Map<String, List<Task>> userTasks;

    private final static TaskRepository instance = new TaskRepository();

    private TaskRepository() {
        Map<String, List<Task>> userTasksCopy = load();
        userTasks = Objects.requireNonNullElseGet(userTasksCopy, HashMap::new);
    }

    public static TaskRepository getInstance() {
        return instance;
    }

    public List<Task> getTasksByUserName(String userName) {
        return userTasks.getOrDefault(userName, List.of());
    }

    public void addNewTask(String userName, Task task) {
        userTasks.computeIfAbsent(userName, s -> new ArrayList<>()).add(task);
        save();
    }

    public boolean isExist(String userName, Task task) {
        if (userTasks.containsKey(userName)) {
            List<Task> tasks = userTasks.get(userName);
            for (Task t : tasks) {
                if (task.equals(t)) {
                    return true;
                }
            }
        }
        return false;
    }


    //serialization
    private void save() {
        try (FileOutputStream fileOutputStream = new FileOutputStream(STORAGE_FILE_NAME);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(userTasks);
            objectOutputStream.flush();
        } catch (IOException ex) {
            String message = "Cannot serialize the user repository";
            throw new RepositoryException(message, ex);
        }
    }

    //deserialization
    private Map<String, List<Task>> load() {
        try (FileInputStream fileInputStream = new FileInputStream(STORAGE_FILE_NAME);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            Map<String, List<Task>> tasks = (Map<String, List<Task>>) objectInputStream.readObject();
            return tasks;
        } catch (IOException | ClassNotFoundException ex) {
            return null;
        }
    }
}
