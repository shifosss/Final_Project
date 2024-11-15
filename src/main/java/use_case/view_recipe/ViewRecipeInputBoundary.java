package use_case.view_recipe;

/**
 * Input Boundary for actions that are relevant to viewing a recipe detail.
 */
public interface ViewRecipeInputBoundary {

    /**
     * Executes viewing the recipe detail use case.
     * @param recipeDetailInputData the view recipe detail input data.
     */
    void execute(ViewRecipeInputData recipeDetailInputData);

    /**
     * Switches to the search view.
     */
    void switchToSearchView();

    /**
     * Bookmarks the recipe.
     * @param recipeDetailInputData the recipe input data.
     */
    void bookmarkRecipe(ViewRecipeInputData recipeDetailInputData);
}
