package use_case.user_profile;

import use_case.view_recipe.ViewRecipeInputData;

/**
 * Input boundary for the user profile interface.
 */
public interface UserProfileInputBoundary {
    /**
     * Switches to the user profile view.
     */
    void switchToUserView();

    /**
     * Switches to the homepage view.
     */
    void switchToHomePageView();

    /**
     * Switches to the custom recipe view detail.
     * @param inputData the input data.
     */
    void viewRecipeDetail(ViewRecipeInputData inputData);
}
