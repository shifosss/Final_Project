package use_case.explore_ingredient;

/**
 * The output boundary for the Search Recipe use case.
 */
public interface ExploreIngredientOutputBoundary {
    /**
     * Prepares the success view for the Explore Ingredient Use Case.
     * @param outputData the output data
     */
    void prepareSuccessView(ExploreIngredientOutputData outputData);

    /**
     * Prepares the failure view for the Explore Ingredient Use Case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);
}
