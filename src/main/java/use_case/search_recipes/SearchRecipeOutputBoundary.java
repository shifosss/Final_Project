package use_case.search_recipes;

/**
 * The output boundary for the Search Recipe use case.
 */
public interface SearchRecipeOutputBoundary {
    /**
     * Prepares the success view for the Search Recipe Use Case.
     * @param outputData the output data
     */
    void prepareSuccessView(SearchRecipeOutputData outputData);

    /**
     * Prepares the failure view for the Search Recipe Use Case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);
}
