package use_case.explore_ingredient;

import use_case.search_recipes.SearchRecipeOutputData;

/**
 * Output Boundary for the explore ingredients.
 */
public interface ExploreIngredientOutputBoundary {
    /**
     * Prepares view for showing the list of all available ingredients.
     * @param outputData the output data.
     */
    void prepareIngredientsListView(ExploreIngredientOutputData outputData);

    /**
     * Prepares view for error cases.
     * @param error the error message.
     */
    void prepareFailView(String error);

    /**
     * Prepares the view for the list of recipes.
     * @param outputData the output data.
     */
    void prepareSuccessView(SearchRecipeOutputData outputData);

    /**
     * Switches to the home view.
     */
    void switchToHomePageView();
}