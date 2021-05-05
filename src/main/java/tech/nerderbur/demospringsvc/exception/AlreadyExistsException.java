package tech.nerderbur.demospringsvc.exception;

public class AlreadyExistsException extends IllegalStateException {
    public AlreadyExistsException(String message) {
        super(message);
    }

    public AlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
