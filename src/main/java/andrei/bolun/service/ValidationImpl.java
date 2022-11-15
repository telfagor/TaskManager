package andrei.bolun.service;

import andrei.bolun.model.Task;
import andrei.bolun.model.User;

public class ValidationImpl implements Validation {

    @Override
    public void validateUser(User user) {
        if (user.getUserName() == null) {
            String message = "Flag -un not found!";
            throw new ValidationException(message);
        } else if (user.getUserName().isEmpty()) {
            String message = "First name cannot be empty!";
            throw new ValidationException(message);
        } else if (user.getFirstName() == null) {
            String message = "Flag -fn not found!";
            throw new ValidationException(message);
        } else if (user.getFirstName().isEmpty()) {
            String message = "First name cannot be empty!";
            throw new ValidationException(message);
        } else if (user.getLastName() == null) {
            String message = "Flag -ln not found!";
            throw new ValidationException(message);
        }
    }

    @Override
    public void validateTask(String userName, Task task) {
        if (userName == null) {
            String message = "Flag -un not found!";
            throw new ValidationException(message);
        } else if (userName.isEmpty()) {
            String message = "Username cannot be empty!";
            throw new ValidationException(message);
        } else if (task.getName() == null) {
            String message = "Flag -tt not found!";
            throw new ValidationException(message);
        } else if (task.getName().isEmpty()) {
            String message = "Task title cannot be empty!";
            throw new ValidationException(message);
        } else if (task.getDescription() == null) {
            String message = "Flag -td not found!";
            throw new ValidationException(message);
        } else if (task.getDescription().isEmpty()) {
            String message = "Task description cannot be empty";
            throw new ValidationException(message);
        }
    }
}
