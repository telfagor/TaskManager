package andrei.bolun.repository;

import java.io.*;
import java.util.*;

import andrei.bolun.model.User;

public class UserRepository {
    private static final String STORAGE_FILE_NAME = "storage.txt";
    private final static UserRepository INSTANCE = new UserRepository();
    private final Map<String, User> users;

    private UserRepository() {
        Map<String, User> copyOfUsers = load();
        users = Objects.requireNonNullElseGet(copyOfUsers, HashMap::new);
    }

    public static UserRepository getInstance() {
        return INSTANCE;
    }

    public List<User> getUsers() {
        return new ArrayList<>(users.values());
    }

    public void addUser(User user) {
        users.put(user.getUserName(), user);
        save();
    }

    public boolean isExist(String userName) {
        return users.containsKey(userName);
    }

    private void save() {
        try (FileOutputStream fileOutputStream = new FileOutputStream(STORAGE_FILE_NAME);
            ObjectOutputStream objectOutputStream =  new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(users);
            objectOutputStream.flush();
        } catch (IOException  | ClassCastException ex) {
            String message = "Cannot serialize the user repository";
            throw new RepositoryException(message, ex);
        }
    }

    private Map<String, User> load() {
        try (FileInputStream fileInputStream = new FileInputStream(STORAGE_FILE_NAME);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            Map<String, User> users = (Map<String, User>) objectInputStream.readObject();
                return users;
            } catch (IOException | ClassNotFoundException ex) {
                return null;
            }
    }
}
