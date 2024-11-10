package use_case.recipe_detail;

/**
 * Input Boundary for actions that are relevant to viewing a recipe detail.
 */
public interface RecipeDetailInputBoundary {

    /**
     * Executes viewing the recipe detail use case.
     * @param recipeDetailInputData the view recipe detail input data.
     */
    void execute(RecipeDetailInputData recipeDetailInputData);

    /**
     * Switches to the search view.
     */
    void switchToSearchView();
}
