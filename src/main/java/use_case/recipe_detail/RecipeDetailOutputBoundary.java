package use_case.recipe_detail;


/**
 * The output boundary for the recipe detail use case.
 */
public interface RecipeDetailOutputBoundary {
    /**
     * Prepares the success view for the Search Recipe Use Case.
     * @param outputData the output data
     */
    void prepareSuccessView(RecipeDetailOutputData outputData);

    /**
     * Prepares the failure view for the Search Recipe Use Case.
     * @param outputData the output data.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(RecipeDetailOutputData outputData, String errorMessage);
}
