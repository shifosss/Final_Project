package use_case.user_profile;

/**
 * The input data for the user profile usecase.
 */
public class UserProfileInputData {
    private String username;

    public UserProfileInputData(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
