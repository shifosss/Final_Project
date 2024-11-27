package exceptions;

/**
 * Throws when user not found.
 */
public class UserNotFound extends RuntimeException {
    public UserNotFound() {
        super("User not found");
    }

    public UserNotFound(String message) {
        super("User not found: " + message);
    }
}
