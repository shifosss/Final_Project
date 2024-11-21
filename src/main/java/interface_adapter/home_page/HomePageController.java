package interface_adapter.home_page;

import use_case.view_recipe.ViewRecipeInputBoundary;
import use_case.view_recipe.ViewRecipeInputData;

/**
 * Controller for the home view.
 */
public class HomePageController {
    private final ViewRecipeInputBoundary recipeDetailController;

    public HomePageController(ViewRecipeInputBoundary recipeDetailController) {
        this.recipeDetailController = recipeDetailController;
    }

    public void switchToRecipeView(int id) {
        final ViewRecipeInputData inputData = new ViewRecipeInputData(id);
        recipeDetailController.switchToRecipe(inputData);
    }

    /**
     * Switches to the search recipes view when textfield is clicked.
     */
    public void switchToSearchRecipeView() {
        recipeDetailController.switchToSearchView();
    }
}
