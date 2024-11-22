package use_case.explore_ingredient;

public interface ExploreIngredientOutputBoundary {
    /**
     * Prepares view for showing the list of all available ingredients
     */
//    void prepareIngredientsListView(List<Ingredient> ingredients);

    /**
     * Prepares view for showing recipes containing a specific ingredient
     */
//    void prepareRecipeListView(ExploreIngredientOutputData outputData);

    /**
     * Prepares view for error cases
     */

    void prepareSuccessView(ExploreIngredientOutputData outputData);

//    void prepareFailView(String _error);
}