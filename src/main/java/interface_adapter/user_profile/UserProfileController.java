package interface_adapter.user_profile;

import entities.user.User;
import use_case.user_profile.UserProfileInputBoundary;
import use_case.user_profile.UserProfileInputData;
import use_case.user_profile.UserProfileInteractor;
import use_case.view_recipe.ViewRecipeInputBoundary;
import use_case.view_recipe.ViewRecipeInputData;

/**
 * Controller for the user profile view.
 */
public class UserProfileController {
    private final UserProfileInputBoundary userProfileInteractor;
    private final ViewRecipeInputBoundary viewRecipeInteractor;

    public UserProfileController(UserProfileInputBoundary userProfileInteractor,
                                 ViewRecipeInputBoundary viewRecipeInteractor) {
        this.userProfileInteractor = userProfileInteractor;
        this.viewRecipeInteractor = viewRecipeInteractor;
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

    /**
     * Shows the recipe view.
     * @param id the recipe id.
     */
    public void showRecipe(int id) {
        final ViewRecipeInputData inputData = new ViewRecipeInputData(id);
        viewRecipeInteractor.execute(inputData);
    }
}
