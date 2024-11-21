package use_case.explore_ingredient;

/**
 * Input Boundary for actions which are related to searching for a recipe.
 */
public interface ExploreIngredientInputBoundary {
    /**
     * Executes the search recipe use case.
     * @param searchRecipeInputData the search recipe input data.
     */
    void execute(ExploreIngredientInputData searchRecipeInputData);
}
