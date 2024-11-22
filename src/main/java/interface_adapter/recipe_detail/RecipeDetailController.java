package interface_adapter.recipe_detail;

import entities.recipe.Recipe;
import use_case.view_recipe.ViewRecipeInputBoundary;
import use_case.view_recipe.ViewRecipeInputData;

/**
 * The controller for the recipe detail use case.
 */
public class RecipeDetailController {
    private final ViewRecipeInputBoundary recipeDetailInteractor;

    public RecipeDetailController(ViewRecipeInputBoundary recipeDetailInteractor) {
        this.recipeDetailInteractor = recipeDetailInteractor;
    }

    /**
     * Executes the interactor for recipe detail use case.
     * @param id recipe id.
     */
    public void execute(int id) {
        final ViewRecipeInputData recipeDetailInputData = new ViewRecipeInputData(id);
        recipeDetailInteractor.execute(recipeDetailInputData);
    }

    public void switchToRecipe(int id) {
        final ViewRecipeInputData recipeDetailInputData = new ViewRecipeInputData(id);

    }

    /**
     * Switches to the search view.
     */
    public void switchToSearchView() {
        recipeDetailInteractor.switchToSearchView();
    }

    /**
     * Attempts to bookmark the recipe.
     * @param recipe the recipe entity.
     */
    public void bookmarkRecipe(Recipe recipe) {
        final ViewRecipeInputData recipeDetailInputData = new ViewRecipeInputData(recipe.getId());
        recipeDetailInteractor.bookmarkRecipe(recipeDetailInputData);
    }
}
