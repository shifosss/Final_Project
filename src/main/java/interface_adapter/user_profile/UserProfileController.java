package interface_adapter.user_profile;

import use_case.user_profile.UserProfileInputBoundary;
import use_case.user_profile.UserProfileInputData;

/**
 * Controller for the user profile view.
 */
public class UserProfileController {
    private final UserProfileInputBoundary userProfileInteractor;

    public UserProfileController(UserProfileInputBoundary userProfileInteractor) {
        this.userProfileInteractor = userProfileInteractor;
    }

    /**
     * Switches to the user profile associated with the username.
     * @param username the user.
     */
    public void switchToUserView(String username) {
        final UserProfileInputData inputData = new UserProfileInputData(username);
        userProfileInteractor.switchToUserView(inputData);
    }

    /**
     * Switches to the home page.
     */
    public void switchToHomePage() {
        userProfileInteractor.switchToHomePage();
    }

}
