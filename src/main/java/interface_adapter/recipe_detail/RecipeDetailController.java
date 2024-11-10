package interface_adapter.recipe_detail;

import use_case.recipe_detail.RecipeDetailInputBoundary;
import use_case.recipe_detail.RecipeDetailInputData;
import use_case.recipe_detail.RecipeDetailInteractor;

/**
 * The controller for the recipe detail use case.
 */
public class RecipeDetailController {
    private final RecipeDetailInputBoundary recipeDetailInteractor;

    public RecipeDetailController(RecipeDetailInputBoundary recipeDetailInteractor) {
        this.recipeDetailInteractor = recipeDetailInteractor;
    }

    /**
     * Executes the interactor for recipe detail use case.
     * @param id recipe id.
     */
    public void execute(int id) {
        final RecipeDetailInputData recipeDetailInputData = new RecipeDetailInputData(id);
        recipeDetailInteractor.execute(recipeDetailInputData);
    }

    /**
     * Switches to the search view.
     */
    public void switchToSearchView() {
        recipeDetailInteractor.switchToSearchView();
    }
}
