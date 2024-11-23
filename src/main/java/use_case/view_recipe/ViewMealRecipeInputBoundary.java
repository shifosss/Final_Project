package use_case.view_recipe;

public interface ViewMealRecipeInputBoundary {

    /**
     * Executes viewing the recipe detail use case.
     * @param recipeDetailInputData the view recipe detail input data.
     */
    void execute(ViewMealRecipeInputData recipeDetailInputData);

    /**
     * Switches to the search view.
     */
    void switchToSearchView();

    /**
     * Bookmarks the recipe.
     * @param recipeDetailInputData the recipe input data.
     */
    void bookmarkRecipe(ViewMealRecipeInputData recipeDetailInputData);
}
