package present.programmer.spring.boot.sandbox.exception;

public class DatabaseInteractionException extends RuntimeException {

    public DatabaseInteractionException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
