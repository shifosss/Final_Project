package app.usecase_factory;

import interface_adapter.home_page.HomePageViewModel;
import interface_adapter.recipe_detail.RecipeDetailController;
import interface_adapter.recipe_detail.RecipeDetailPresenter;
import interface_adapter.recipe_detail.RecipeDetailViewModel;
import use_case.search_recipes.SearchRecipeDataAccessInterface;
import use_case.search_recipes.SearchRecipeInputBoundary;
import use_case.search_recipes.SearchRecipeInteractor;
import use_case.search_recipes.SearchRecipeOutputBoundary;
import interface_adapter.ViewManagerModel;
import interface_adapter.search_recipe.SearchRecipeController;
import interface_adapter.search_recipe.SearchRecipePresenter;
import interface_adapter.search_recipe.SearchRecipeViewModel;
import interface_adapter.services.ServiceManager;
import use_case.view_recipe.ViewRecipeDataAccessInterface;
import use_case.view_recipe.ViewRecipeInputBoundary;
import use_case.view_recipe.ViewRecipeInteractor;
import use_case.view_recipe.ViewRecipeOutputBoundary;
import view.SearchRecipeView;

/**
 * This class contains the static factory function for creating the SearchRecipeView.
 */
public final class SearchRecipeUseCaseFactory {

    // Prevents initialization
    private SearchRecipeUseCaseFactory() {
    }

    /**
     * Creates the Search Recipe View.
     * @param viewManagerModel the ViewManagerModel to be injected into the View.
     * @param searchRecipeViewModel the SearchRecipeViewModel to be injected into the View.
     * @param recipeDetailViewModel the RecipeDetailViewModel to be injected into the View.
     * @param homePageViewModel the HomePageViewModel to be injected into the View.
     * @param searchRecipeDataAccessObject the SearchRecipeDAO to be injected into the View.
     * @param viewRecipeDataAccessObject the ViewRecipeDAO to be injected into the View.
     * @param serviceManager the Service Manager that handles service requests.
     * @return the SearchRecipeView given the arguments.
     */
    public static SearchRecipeView create(ViewManagerModel viewManagerModel,
                                          SearchRecipeViewModel searchRecipeViewModel,
                                          RecipeDetailViewModel recipeDetailViewModel,
                                          HomePageViewModel homePageViewModel,
                                          SearchRecipeDataAccessInterface searchRecipeDataAccessObject,
                                          ViewRecipeDataAccessInterface viewRecipeDataAccessObject,
                                          ServiceManager serviceManager) {
        final SearchRecipeController searchRecipeController = createSearchRecipeUseCase(viewManagerModel,
                searchRecipeViewModel, recipeDetailViewModel, homePageViewModel,
                searchRecipeDataAccessObject);
        final RecipeDetailController recipeDetailController = createViewRecipeUseCase(
                viewManagerModel, searchRecipeViewModel, recipeDetailViewModel, viewRecipeDataAccessObject
        );
        return new SearchRecipeView(searchRecipeViewModel,
                searchRecipeController, recipeDetailController,
                serviceManager);
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
