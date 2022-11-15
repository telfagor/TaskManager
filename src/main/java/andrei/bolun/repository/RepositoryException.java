package andrei.bolun.repository;

public class RepositoryException extends RuntimeException {
    public RepositoryException(String message, Throwable ex) {
        super(message, ex);
    }
}
