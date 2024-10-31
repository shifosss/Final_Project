package usecase.logout;

/**
 * The Input Data for the Logout Use Case.
 */
public class LogoutInputData {
    private final String username;

    public LogoutInputData(String username) {
        this.username = username;
    }

    // Getter method to retrieve the username
    public String getUsername() {
        return username;
    }
}

