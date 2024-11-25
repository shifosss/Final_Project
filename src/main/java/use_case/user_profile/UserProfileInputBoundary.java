package use_case.user_profile;

/**
 * Input boundary for the user profile interface.
 */
public interface UserProfileInputBoundary {
    /**
     * Switches to the user profile view.
     * @param userProfileInputData the input data.
     */
    void switchToUserView(UserProfileInputData userProfileInputData);

    /**
     * Switches to the homepage view.
     */
    void switchToHomePage();
}
