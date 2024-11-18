package interface_adapter.home_page;

import interface_adapter.recipe_detail.RecipeDetailController;
import use_case.view_recipe.ViewRecipeInputBoundary;

/**
 * Controller for the home view.
 */
public class HomePageController {
    private final ViewRecipeInputBoundary recipeDetailController;

    public HomePageController(ViewRecipeInputBoundary recipeDetailController) {
        this.recipeDetailController = recipeDetailController;
    }

    /**
     * Switches to the search recipes view when textfield is clicked.
     */
    public void switchToSearchRecipeView() {
        recipeDetailController.switchToSearchView();
    }
}
