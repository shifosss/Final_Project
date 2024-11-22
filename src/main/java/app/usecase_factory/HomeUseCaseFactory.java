package app.usecase_factory;

import data_access.ExploreIngredientDataAccessObject;
import entities.recipe.factory.CocktailFactory;
import entities.recipe.factory.RecipeFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.explore_ingredient.ExploreIngredientController;
import interface_adapter.explore_ingredient.ExploreIngredientPresenter;
import interface_adapter.explore_ingredient.ExploreIngredientViewModel;
import interface_adapter.home_page.HomePageController;
import interface_adapter.home_page.HomePageViewModel;
import interface_adapter.recipe_detail.RecipeDetailController;
import interface_adapter.recipe_detail.RecipeDetailPresenter;
import interface_adapter.recipe_detail.RecipeDetailViewModel;
import interface_adapter.search_recipe.SearchRecipeController;
import interface_adapter.search_recipe.SearchRecipePresenter;
import interface_adapter.search_recipe.SearchRecipeViewModel;
import interface_adapter.services.ServiceManager;
import use_case.explore_ingredient.ExploreIngredientDataAccessInterface;
import use_case.explore_ingredient.ExploreIngredientInputBoundary;
import use_case.explore_ingredient.ExploreIngredientInteractor;
import use_case.explore_ingredient.ExploreIngredientOutputBoundary;
import use_case.search_recipes.SearchRecipeDataAccessInterface;
import use_case.search_recipes.SearchRecipeInputBoundary;
import use_case.search_recipes.SearchRecipeInteractor;
import use_case.search_recipes.SearchRecipeOutputBoundary;
import use_case.view_recipe.ViewRecipeDataAccessInterface;
import use_case.view_recipe.ViewRecipeInputBoundary;
import use_case.view_recipe.ViewRecipeInteractor;
import use_case.view_recipe.ViewRecipeOutputBoundary;
import view.ViewPlaceholder.HomeView;

/**
 * home page factory that creates necessary usecases.
 */
public final class HomeUseCaseFactory {
    private HomeUseCaseFactory() {
    }

    /**
     * The Home View.
     * @param viewManagerModel the ViewManagerModel to be injected into the View
     * @param homePageViewModel the HomePageViewModel to be injected into the View.
     * @param searchRecipeViewModel the SearchRecipeViewModel to be injected into the View
     * @param recipeDetailViewModel the RecipeDetailViewModel to be injected into the View.
     * @param searchRecipeDataAccessObject the SearchRecipeDAO to be injected into the View
     * @param viewRecipeDataAccessObject the ViewRecipeDAO to be injected into the View.
     * @param serviceManager the ServiceManager to be injected into the View
     * @return the home view.
     */
    public static HomeView create(ViewManagerModel viewManagerModel,
                                  HomePageViewModel homePageViewModel,
                                  SearchRecipeViewModel searchRecipeViewModel,
                                  RecipeDetailViewModel recipeDetailViewModel,
                                  SearchRecipeDataAccessInterface searchRecipeDataAccessObject,
                                  ViewRecipeDataAccessInterface viewRecipeDataAccessObject,
                                  ServiceManager serviceManager) {

        // Create ExploreIngredientController
        ExploreIngredientInputBoundary exploreIngredientController = createExploreIngredientUseCase(
                viewManagerModel,
                new ExploreIngredientViewModel(), // You might need to create this ViewModel
                searchRecipeViewModel
        );

        final HomePageController homePageController = createHomePageUseCase(viewManagerModel,
                searchRecipeViewModel, recipeDetailViewModel, homePageViewModel,
                exploreIngredientController,
                viewRecipeDataAccessObject);
        final RecipeDetailController recipeDetailController = createViewRecipeUseCase(
                viewManagerModel, searchRecipeViewModel, recipeDetailViewModel, viewRecipeDataAccessObject
        );
        return new HomeView(homePageViewModel,
                homePageController, recipeDetailController,
                serviceManager);
    }

    // Add this method to create ExploreIngredientController
    private static ExploreIngredientInputBoundary createExploreIngredientUseCase(
            ViewManagerModel viewManagerModel,
            ExploreIngredientViewModel exploreIngredientViewModel,
            SearchRecipeViewModel searchRecipeViewModel) {

        final RecipeFactory cocktailFactory = new CocktailFactory(); // Implement this

        ExploreIngredientOutputBoundary exploreIngredientPresenter = new ExploreIngredientPresenter(
                exploreIngredientViewModel,
                viewManagerModel
        );

        ExploreIngredientDataAccessInterface exploreIngredientDataAccessObject =
                new ExploreIngredientDataAccessObject(cocktailFactory); // Implement this

        return new ExploreIngredientInteractor(
                exploreIngredientDataAccessObject,
                exploreIngredientPresenter
        );
    }

    private static HomePageController createHomePageUseCase(
            ViewManagerModel viewManagerModel,
            SearchRecipeViewModel searchRecipeViewModel,
            RecipeDetailViewModel recipeDetailViewModel,
            HomePageViewModel homepageViewModel,
            ExploreIngredientInputBoundary exploreIngredientController,
            ViewRecipeDataAccessInterface viewRecipeDataAccessObject) {
        final ViewRecipeOutputBoundary viewRecipeOutputBoundary = new RecipeDetailPresenter(
                recipeDetailViewModel, searchRecipeViewModel, viewManagerModel);
        final ViewRecipeInputBoundary viewRecipeInteractor = new ViewRecipeInteractor(
                viewRecipeDataAccessObject, viewRecipeOutputBoundary
        );
        return new HomePageController(viewRecipeInteractor, exploreIngredientController);
    }

    private static RecipeDetailController createViewRecipeUseCase(
            ViewManagerModel viewManagerModel,
            SearchRecipeViewModel searchRecipeViewModel,
            RecipeDetailViewModel recipeDetailViewModel,
            ViewRecipeDataAccessInterface viewRecipeDataAccessObject) {
        final ViewRecipeOutputBoundary viewRecipeOutputBoundary = new RecipeDetailPresenter(
                recipeDetailViewModel, searchRecipeViewModel, viewManagerModel);
        final ViewRecipeInputBoundary viewRecipeInteractor = new ViewRecipeInteractor(
                viewRecipeDataAccessObject, viewRecipeOutputBoundary
        );

        return new RecipeDetailController(viewRecipeInteractor);
    }

    private static SearchRecipeController createSearchRecipeUseCase(
            ViewManagerModel viewManagerModel,
            SearchRecipeViewModel searchRecipeViewModel,
            RecipeDetailViewModel recipeDetailViewModel,
            HomePageViewModel homepageViewModel,
            SearchRecipeDataAccessInterface searchRecipeDataAccessObject) {

        final SearchRecipeOutputBoundary searchRecipeOutputBoundary = new SearchRecipePresenter(viewManagerModel,
                searchRecipeViewModel, recipeDetailViewModel, homepageViewModel);
        final SearchRecipeInputBoundary searchRecipeInteractor = new SearchRecipeInteractor(
                searchRecipeDataAccessObject, searchRecipeOutputBoundary);

        return new SearchRecipeController(searchRecipeInteractor);
    }
}
