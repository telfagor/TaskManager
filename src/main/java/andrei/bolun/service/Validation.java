package andrei.bolun.service;

import andrei.bolun.model.Task;
import andrei.bolun.model.User;

public interface Validation {
    void validateUser(User user);
    void validateTask(String userName, Task task);
}
