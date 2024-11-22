package interface_adapter.home_page;

import use_case.view_recipe.ViewRecipeInputBoundary;
import use_case.view_recipe.ViewRecipeInputData;
import interface_adapter.ViewManagerModel;
import use_case.explore_ingredient.ExploreIngredientInputBoundary;

public class HomePageController {
    private final ViewRecipeInputBoundary recipeDetailController;
    private final ExploreIngredientInputBoundary exploreIngredientController;
    private final ViewManagerModel viewManagerModel;
    private final HomePagePresenter homePagePresenter;

    public HomePageController(
            ViewRecipeInputBoundary recipeDetailController,
            ExploreIngredientInputBoundary exploreIngredientController,
            ViewManagerModel viewManagerModel,
            HomePagePresenter homePagePresenter) {
        this.recipeDetailController = recipeDetailController;
        this.exploreIngredientController = exploreIngredientController;
        this.viewManagerModel = viewManagerModel;
        this.homePagePresenter = homePagePresenter;
    }

    public void switchToRecipeView(int id) {
        final ViewRecipeInputData inputData = new ViewRecipeInputData(id);
        recipeDetailController.execute(inputData);
    }

    public void switchToSearchRecipeView() {
        recipeDetailController.switchToSearchView();
    }

    public void switchToExploreIngredientView() {
        exploreIngredientController.loadIngredients();
        viewManagerModel.setState("explore ingredient");
        viewManagerModel.firePropertyChanged();
    }
}