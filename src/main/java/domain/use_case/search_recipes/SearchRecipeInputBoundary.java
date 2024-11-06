package domain.use_case.search_recipes;

/**
 * Input Boundary for actions which are related to searching for a recipe.
 */
public interface SearchRecipeInputBoundary {
    /**
     * Executes the search recipe use case.
     * @param searchRecipeInputData the search recipe input data.
     */
    void execute(SearchRecipeInputData searchRecipeInputData);
}
