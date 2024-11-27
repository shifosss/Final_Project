package use_case.search_recipes;

import use_case.view_recipe.ViewRecipeInputData;

/**
 * Input Boundary for actions which are related to searching for a recipe.
 */
public interface SearchRecipeInputBoundary {
    /**
     * Executes the search recipe use case.
     * @param searchRecipeInputData the search recipe input data.
     */
    void execute(SearchRecipeInputData searchRecipeInputData);

    /**
     * Switches to the recipe detail view.
     * @param recipeDetailInputData the recipe detail input data.
     */
    void execute(ViewRecipeInputData recipeDetailInputData);

    /**
     * Switches to the home page view.
     * @param searchRecipeInputData the search recipe input data.
     */
    void switchToHomePageView(SearchRecipeInputData searchRecipeInputData);
}
