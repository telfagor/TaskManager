package andrei.bolun.repository;

import java.io.*;
import java.util.*;

import andrei.bolun.model.Task;

public class TaskRepository {

    //здесь оставил List, так как мы проверяем слоем выше чтобы не было дубликатов
    private Map<String, List<Task>> userTasks;

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

    //я добавил этот метод
    public boolean isExist(String userName, Task task) {
        if (userTasks.containsKey(userName)) {
            List<Task> tasks = userTasks.get(userName);
            for (Task t : tasks) { //возможно это можно сделать через lambda
                if (t.equals(task)) {
                    return true;
                }
            }
        }
        return false;
    }


    //serialization
    private void save() {
        try (FileOutputStream fileOutputStream = new FileOutputStream("storage.txt");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(userTasks);
            objectOutputStream.flush();
        } catch (IOException ex) {
            String message = "Cannot serialize the user repository";
            throw new StorageException(message, ex);
        }
    }

    //deserialization
    private Map<String, List<Task>> load() {
        try (FileInputStream fileInputStream = new FileInputStream("storage.txt");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            Map<String, List<Task>> tasks = (Map<String, List<Task>>) objectInputStream.readObject();
            return tasks;
        } catch (IOException | ClassNotFoundException ex) {
            return null;
        }
    }
}
