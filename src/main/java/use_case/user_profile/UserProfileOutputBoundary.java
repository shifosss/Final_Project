package use_case.user_profile;

/**
 * Output boundary for the user profile usecase.
 */
public interface UserProfileOutputBoundary {
    /**
     * Switches to the home view.
     */
    void switchToHomeView();

    /**
     * Prepares the profile view.
     * @param outputData the output data containing user data.
     */
    void switchToProfileView(UserProfileOutputData outputData);

    /**
     * Prepares the fail view when there is a problem viewing a username profile.
     * @param error the error.
     * @param username the username associated with the error.
     */
    void presentUserNotFound(String error, String username);
}
