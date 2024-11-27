package interface_adapter.user_profile;

import use_case.change_preference.ChangePreferenceInputBoundary;
import use_case.user_profile.UserProfileInputBoundary;
import use_case.view_recipe.ViewRecipeInputData;

/**
 * Controller for the user profile view.
 */
public class UserProfileController {
    private final UserProfileInputBoundary userProfileInteractor;
    private final ChangePreferenceInputBoundary changePreferenceInteractor;

    public UserProfileController(UserProfileInputBoundary userProfileInteractor,
                                 ChangePreferenceInputBoundary changePreferenceInteractor) {
        this.userProfileInteractor = userProfileInteractor;
        this.changePreferenceInteractor = changePreferenceInteractor;
    }

    /**
     * Switches to the user profile associated with the username.
     */
    public void switchToUserView() {
        userProfileInteractor.switchToUserView();
    }

    /**
     * Switches to the home page.
     */
    public void switchToHomePage() {
        userProfileInteractor.switchToHomePageView();
    }

    /**
     * Shows the recipe view.
     * @param id the recipe id.
     */
    public void showRecipe(int id) {
        final ViewRecipeInputData inputData = new ViewRecipeInputData(id);
        userProfileInteractor.viewRecipeDetail(inputData);
    }

    /**
     * Switches to the preference view.
     */
    public void switchToPreferenceView() {
        changePreferenceInteractor.switchToPreferenceView();
    }
}
