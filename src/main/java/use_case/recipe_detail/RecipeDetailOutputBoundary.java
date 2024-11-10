package use_case.recipe_detail;


import use_case.search_recipes.SearchRecipeOutputData;

/**
 * The output boundary for the recipe detail use case.
 */
public interface RecipeDetailOutputBoundary {
    /**
     * Prepares the success view for viewing Recipe Detail Use Case.
     * @param outputData the output data
     */
    void prepareSuccessView(RecipeDetailOutputData outputData);

    /**
     * Prepares the failure view for viewing Recipe Detail Use Case.
     * @param outputData the output data.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(RecipeDetailOutputData outputData, String errorMessage);

    /**
     * Switches to the Search Recipe View, usually triggered when back button is clicked.
     */
    void switchToSearchRecipeView();
}
