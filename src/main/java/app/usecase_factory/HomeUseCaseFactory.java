package app.usecase_factory;

import interface_adapter.ViewManagerModel;
import interface_adapter.home_page.*;
import interface_adapter.recipe_detail.*;
import interface_adapter.search_recipe.SearchRecipeViewModel;
import interface_adapter.explore_ingredient.*;
import interface_adapter.services.ServiceManager;
import use_case.view_recipe.*;
import use_case.explore_ingredient.*;
import data_access.CocktailDataAccessObject;
import view.ViewPlaceholder.HomeView;

public class HomeUseCaseFactory {
    public static HomeView create(
            ViewManagerModel viewManagerModel,
            HomePageViewModel homePageViewModel,
            SearchRecipeViewModel searchRecipeViewModel,
            RecipeDetailViewModel recipeDetailViewModel,
            CocktailDataAccessObject cocktailDataAccessObject,  // Only one CocktailDataAccessObject parameter
            ServiceManager serviceManager) {

        // Create RecipeDetail components
        RecipeDetailPresenter recipeDetailPresenter = new RecipeDetailPresenter(
                recipeDetailViewModel,
                searchRecipeViewModel,
                viewManagerModel
        );

        ViewRecipeInputBoundary recipeDetailInteractor = new ViewRecipeInteractor(
                cocktailDataAccessObject,
                recipeDetailPresenter
        );

        RecipeDetailController recipeDetailController = new RecipeDetailController(
                recipeDetailInteractor
        );

        // Create explore ingredient components
        ExploreIngredientPresenter exploreIngredientPresenter = new ExploreIngredientPresenter(
                new ExploreIngredientViewModel(),
                viewManagerModel
        );

        ExploreIngredientInteractor exploreIngredientInteractor = new ExploreIngredientInteractor(
                cocktailDataAccessObject,
                exploreIngredientPresenter
        );

        // Create home page presenter
        HomePagePresenter homePagePresenter = new HomePagePresenter(
                homePageViewModel,
                viewManagerModel
        );

        // Create home page controller with all dependencies
        HomePageController homePageController = new HomePageController(
                recipeDetailInteractor,  // This one stays as ViewRecipeInputBoundary
                exploreIngredientInteractor,
                viewManagerModel,
                homePagePresenter
        );

        return new HomeView(
                homePageViewModel,
                homePageController,
                recipeDetailController,  // Now correctly providing RecipeDetailController
                serviceManager
        );
    }
}