package data_access.exceptions;

/**
 * Throws when user not found.
 */
public class UserNotFound extends RuntimeException {
    public UserNotFound() {
        super("User not found");
    }

    public UserNotFound(String message) {
        super(message);
    }
}
