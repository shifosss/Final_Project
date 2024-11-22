package interface_adapter.home_page;

import use_case.view_recipe.ViewRecipeInputBoundary;
import use_case.view_recipe.ViewRecipeInputData;
import interface_adapter.ViewManagerModel;

/**
 * Controller for the home view.
 */
public class HomePageController {
    private final ViewRecipeInputBoundary recipeDetailController;
    private final ViewManagerModel viewManagerModel;

    public HomePageController(ViewRecipeInputBoundary recipeDetailController,
                              ViewManagerModel viewManagerModel) {
        this.recipeDetailController = recipeDetailController;
        this.viewManagerModel = viewManagerModel;
    }

    public void switchToRecipeView(int id) {
        final ViewRecipeInputData inputData = new ViewRecipeInputData(id);
        recipeDetailController.execute(inputData);
    }

    /**
     * Switches to the search recipes view when textfield is clicked.
     */
    public void switchToSearchRecipeView() {
        recipeDetailController.switchToSearchView();
    }

    /**
     * Switches to the explore ingredients view.
     */
    public void switchToExploreIngredientView() {
        viewManagerModel.setState("explore ingredient");
        viewManagerModel.firePropertyChanged();
    }

}
