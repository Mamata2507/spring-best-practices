package tech.nerderbur.demospringsvc.exception;

public class NotFoundException extends NullPointerException {
    public NotFoundException(String message) {
        super(message);
    }
}
