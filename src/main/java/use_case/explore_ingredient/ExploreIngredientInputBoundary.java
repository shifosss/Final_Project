package use_case.explore_ingredient;

/**
 * Input Boundary for actions which are related to exploring an ingredient.
 */
public interface ExploreIngredientInputBoundary {
    /**
     * Executes the explore ingredient use case.
     * @param exploreIngredientInputData the explore ingredient input data.
     */
    void execute(ExploreIngredientInputData exploreIngredientInputData);
}
