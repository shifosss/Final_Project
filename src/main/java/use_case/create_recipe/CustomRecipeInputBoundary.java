package use_case.create_recipe;

/**
 * Input boundary for the custom recipe usecase.
 */
public interface CustomRecipeInputBoundary {
    /**
     * Switches to the home page view.
     */
    void switchToHomePage();

    /**
     * Switches to the custom recipe view.
     */
    void switchToCustomRecipeView();

    /**
     * Saves the custom recipe.
     * @param inputData the input data =.
     */
    void saveCustomRecipe(CustomRecipeInputData inputData);
}
