package app.usecase_factory;

import data_access.CocktailDataAccessObject;
import data_access.UserDataAccessObject;
import interface_adapter.ViewManagerModel;
import interface_adapter.explore_ingredient.ExploreIngredientController;
import interface_adapter.explore_ingredient.ExploreIngredientPresenter;
import interface_adapter.explore_ingredient.ExploreIngredientViewModel;
import interface_adapter.home_page.HomePageViewModel;
import interface_adapter.recipe_detail.RecipeDetailController;
import interface_adapter.recipe_detail.RecipeDetailPresenter;
import interface_adapter.recipe_detail.RecipeDetailViewModel;
import interface_adapter.search_recipe.SearchRecipeController;
import interface_adapter.search_recipe.SearchRecipePresenter;
import interface_adapter.search_recipe.SearchRecipeViewModel;
import interface_adapter.services.ServiceManager;
import use_case.bookmark_recipe.BookmarkRecipeDataAccessInterface;
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
     * @param cocktailDataAccessObject the CocktailDAO to be injected into the View
     * @param userDataAccessObject the UserDAO to be injected into the View.
     * @param serviceManager the ServiceManager to be injected into the View
     * @return the home view.
     */
    public static HomeView create(ViewManagerModel viewManagerModel,
                                  HomePageViewModel homePageViewModel,
                                  SearchRecipeViewModel searchRecipeViewModel,
                                  RecipeDetailViewModel recipeDetailViewModel,
                                  ExploreIngredientViewModel exploreIngredientViewModel,
                                  CocktailDataAccessObject cocktailDataAccessObject,
                                  UserDataAccessObject userDataAccessObject,
                                  ServiceManager serviceManager) {
        final SearchRecipeController searchRecipeController = createSearchRecipeUseCase(viewManagerModel,
                searchRecipeViewModel, recipeDetailViewModel, homePageViewModel,
                cocktailDataAccessObject, userDataAccessObject);
        final RecipeDetailController recipeDetailController = createViewRecipeUseCase(
                viewManagerModel, searchRecipeViewModel, recipeDetailViewModel,
                cocktailDataAccessObject, userDataAccessObject
        );
        final ExploreIngredientController exploreIngredientController = createExploreIngredientUseCase(
                viewManagerModel, exploreIngredientViewModel, homePageViewModel,
                searchRecipeViewModel, cocktailDataAccessObject);
        return new HomeView(homePageViewModel,
                searchRecipeController, recipeDetailController, exploreIngredientController,
                serviceManager);
    }

    private static ExploreIngredientController createExploreIngredientUseCase(
            ViewManagerModel viewManagerModel,
            ExploreIngredientViewModel exploreIngredientViewModel,
            HomePageViewModel homePageViewModel,
            SearchRecipeViewModel searchRecipeViewModel,
            ExploreIngredientDataAccessInterface exploreIngredientDataAccessObject) {
        final ExploreIngredientOutputBoundary exploreIngredientOutputBoundary = new ExploreIngredientPresenter(
                exploreIngredientViewModel, homePageViewModel, searchRecipeViewModel, viewManagerModel
        );

        final ExploreIngredientInputBoundary exploreIngredientInteractor = new ExploreIngredientInteractor(
               exploreIngredientDataAccessObject, exploreIngredientOutputBoundary);
        return new ExploreIngredientController(exploreIngredientInteractor);
    }

    private static RecipeDetailController createViewRecipeUseCase(
            ViewManagerModel viewManagerModel,
            SearchRecipeViewModel searchRecipeViewModel,
            RecipeDetailViewModel recipeDetailViewModel,
            ViewRecipeDataAccessInterface viewRecipeDataAccessObject,
            BookmarkRecipeDataAccessInterface bookmarkRecipeDataAccessObject) {
        final ViewRecipeOutputBoundary viewRecipeOutputBoundary = new RecipeDetailPresenter(
                recipeDetailViewModel, searchRecipeViewModel, viewManagerModel);
        final ViewRecipeInputBoundary viewRecipeInteractor = new ViewRecipeInteractor(
                viewRecipeDataAccessObject, bookmarkRecipeDataAccessObject, viewRecipeOutputBoundary
        );

        return new RecipeDetailController(viewRecipeInteractor);
    }

    private static SearchRecipeController createSearchRecipeUseCase(
            ViewManagerModel viewManagerModel,
            SearchRecipeViewModel searchRecipeViewModel,
            RecipeDetailViewModel recipeDetailViewModel,
            HomePageViewModel homepageViewModel,
            SearchRecipeDataAccessInterface searchRecipeDataAccessObject,
            BookmarkRecipeDataAccessInterface bookmarkRecipeDataAccessObject) {

        final SearchRecipeOutputBoundary searchRecipeOutputBoundary = new SearchRecipePresenter(viewManagerModel,
                searchRecipeViewModel, recipeDetailViewModel, homepageViewModel);
        final SearchRecipeInputBoundary searchRecipeInteractor = new SearchRecipeInteractor(
                searchRecipeDataAccessObject, bookmarkRecipeDataAccessObject,
                searchRecipeOutputBoundary);

        return new SearchRecipeController(searchRecipeInteractor);
    }
}
