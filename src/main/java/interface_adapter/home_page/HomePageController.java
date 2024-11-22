package interface_adapter.home_page;

import use_case.explore_ingredient.ExploreIngredientInputBoundary;
import use_case.view_recipe.ViewRecipeInputBoundary;
import use_case.view_recipe.ViewRecipeInputData;

/**
 * Controller for the home view.
 */
public class HomePageController {
    private final ViewRecipeInputBoundary recipeDetailController;
    private final ExploreIngredientInputBoundary ingredientController;

    public HomePageController(ViewRecipeInputBoundary recipeDetailController,
                              ExploreIngredientInputBoundary ingredientController) {
        this.recipeDetailController = recipeDetailController;
        this.ingredientController = ingredientController;
    }

    /**
     * Switch to Recipe View.
     * @param id the given recipe id. (useful for clicking recipes directly on homepage)
     */
    public void switchToRecipeView(int id) {
        final ViewRecipeInputData inputData = new ViewRecipeInputData(id);
        recipeDetailController.execute(inputData);
    }

    /**
     * Switches to the explore ingredient view.
     */
    public void switchToExploreIngredientView() {
        ingredientController.switchToExploreIngredients();
    }

    /**
     * Switches to the search recipes view when textfield is clicked.
     */
    public void switchToSearchRecipeView() {
        recipeDetailController.switchToSearchView();
    }
}
