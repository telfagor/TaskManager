package andrei.bolun.repository;

import java.io.*;
import java.util.Map;
import java.util.HashMap;
import java.util.Objects;

import andrei.bolun.model.User;

public class UserRepository {
    private final Map<String, User> users;

    private final static UserRepository instance = new UserRepository();

    private UserRepository() {
        Map<String, User> copyOfUsers = load();
        users = Objects.requireNonNullElseGet(copyOfUsers, HashMap::new);
    }

    public static UserRepository getInstance() {
        return instance;
    }

    public Map<String, User> getUsers() {
        return users;
    }

    //как было раньше
    //зачем нам два раза проверять одно и тоже?
    //проверку на пользователя мы сделали на уровень выше, в service
    /*public void addNewUser(User user) {
        String userName = user.getUserName();
        if (!isExist(userName)) {
            users.put(userName, user);
        }
        save();
    }*/

    //что я предлагаю
    public void addUser(User user) {
        users.put(user.getUserName(), user);
        save();
    }

    public boolean isExist(String userName) {
        return users.containsKey(userName);
    }

    private void save() {
        try(FileOutputStream fileOutputStream = new FileOutputStream("storage.txt");
            ObjectOutputStream objectOutputStream =  new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(users);
            objectOutputStream.flush();
        } catch (IOException  | ClassCastException ex) {
            String message = "Cannot serialize the user repository";
            throw new StorageException(message, ex);
        }
    }

    private Map<String, User> load() {
        try(FileInputStream fileInputStream = new FileInputStream("storage.txt");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            Map<String, User> users = (Map<String, User>) objectInputStream.readObject();
                return users;
            } catch (IOException | ClassNotFoundException ex) {
                return null;
            }
    }
}
