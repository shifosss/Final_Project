package exceptions;

/**
 * Throw when a user tries using an invalid username.
 */
public class InvalidUsername extends RuntimeException {
    public InvalidUsername(String message) {
        super(message);
    }
}
