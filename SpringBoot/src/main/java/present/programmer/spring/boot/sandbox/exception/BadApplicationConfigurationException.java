package present.programmer.spring.boot.sandbox.exception;

/**
 * If the exception is thrown, it means that application is configured incorrectly.
 * E.g., some resource is missing or misplaced.
 */
public class BadApplicationConfigurationException extends RuntimeException {

    public BadApplicationConfigurationException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
