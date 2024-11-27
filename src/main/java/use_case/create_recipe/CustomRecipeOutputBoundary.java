package use_case.create_recipe;

import use_case.user_profile.UserProfileOutputData;

/**
 * Creation of custom recipe output boundary.
 */
public interface CustomRecipeOutputBoundary {
    /**
     * Switches to the recipe creation view.
     */
    void switchToRecipeCreationView();

    /**
     * Switches to the Home page view.
     */
    void switchToHomePageView();

    /**
     * Updates the custom recipe view.
     * @param outputData the output data.
     */
    void updateCustomRecipeView(UserProfileOutputData outputData);
}
