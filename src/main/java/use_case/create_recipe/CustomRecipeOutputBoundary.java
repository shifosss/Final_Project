package use_case.create_recipe;

/**
 * Creation of custom recipe output boundary.
 */
public interface CustomRecipeOutputBoundary {
    /**
     * Switches to the recipe creation view.
     */
    void switchToRecipeCreationView();

    /**
     * Switches to the Home page view.
     * @param outputData the output data.
     */
    void switchToHomeView(CustomRecipeOutputData outputData);
}
