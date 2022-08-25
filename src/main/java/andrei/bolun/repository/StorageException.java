package andrei.bolun.repository;

public class StorageException extends RuntimeException {
    public StorageException(String message, Throwable th) {
        super(message, th);
    }
}
