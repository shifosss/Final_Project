package exceptions;

/**
 * Throw when a user tries using an invalid password.
 */
public class InvalidPassword extends RuntimeException {
    public InvalidPassword(String message) {
        super(message);
    }
}
