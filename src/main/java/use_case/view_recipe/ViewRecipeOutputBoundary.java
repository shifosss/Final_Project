package use_case.view_recipe;


/**
 * The output boundary for the recipe detail use case.
 */
public interface ViewRecipeOutputBoundary {
    /**
     * Prepares the success view for viewing Meal Recipe Detail Use Case.
     * @param outputData the output data
     */
    void prepareSuccessView(ViewRecipeOutputData outputData);

    /**
     * Prepares the failure view for viewing Meal Recipe Detail Use Case.
     * @param outputData the output data.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(ViewRecipeOutputData outputData, String errorMessage);

    /**
     * Switches to the Search Recipe View, usually triggered when back button is clicked.
     */
    void switchToSearchRecipeView();
}
