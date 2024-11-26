package app.usecase_factory;

import data_access.CocktailDataAccessObject;
import data_access.UserDataAccessObject;
import interface_adapter.ViewManagerModel;
import interface_adapter.home_page.HomePageViewModel;
import interface_adapter.recipe_detail.RecipeDetailViewModel;
import interface_adapter.search_recipe.SearchRecipeController;
import interface_adapter.search_recipe.SearchRecipePresenter;
import interface_adapter.search_recipe.SearchRecipeViewModel;
import interface_adapter.services.ServiceManager;
import use_case.search_recipes.SearchRecipeInteractor;
import use_case.view_recipe.ViewRecipeInputBoundary;
import use_case.view_recipe.ViewRecipeInteractor;
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
     * @param cocktailDataAccessObject the CocktailDAO to be injected into the View.
     * @param userDataAccessObject the UserDAO to be injected into the View.
     * @param serviceManager the Service Manager that handles service requests.
     * @return the SearchRecipeView given the arguments.
     */
    public static SearchRecipeView create(ViewManagerModel viewManagerModel,
                                          SearchRecipeViewModel searchRecipeViewModel,
                                          RecipeDetailViewModel recipeDetailViewModel,
                                          HomePageViewModel homePageViewModel,
                                          CocktailDataAccessObject cocktailDataAccessObject,
                                          UserDataAccessObject userDataAccessObject,
                                          ServiceManager serviceManager) {
        final SearchRecipeController searchRecipeController = createSearchRecipeUseCases(viewManagerModel,
                searchRecipeViewModel, recipeDetailViewModel, homePageViewModel,
                cocktailDataAccessObject, userDataAccessObject);
        return new SearchRecipeView(searchRecipeViewModel, searchRecipeController, serviceManager);
    }

    private static SearchRecipeController createSearchRecipeUseCases(ViewManagerModel viewManagerModel,
                                                                     SearchRecipeViewModel searchRecipeViewModel,
                                                                     RecipeDetailViewModel recipeDetailViewModel,
                                                                     HomePageViewModel homePageViewModel,
                                                                     CocktailDataAccessObject cocktailDataAccessObject,
                                                                     UserDataAccessObject userDataAccessObject) {
        final SearchRecipePresenter searchRecipePresenter = new SearchRecipePresenter(
                viewManagerModel, searchRecipeViewModel, recipeDetailViewModel, homePageViewModel);
        final SearchRecipeInteractor searchRecipeInteractor = new SearchRecipeInteractor(
                cocktailDataAccessObject, userDataAccessObject, searchRecipePresenter);
        final ViewRecipeInputBoundary recipeDetailInteractor = new ViewRecipeInteractor(
                cocktailDataAccessObject, userDataAccessObject, searchRecipePresenter);
        return new SearchRecipeController(searchRecipeInteractor, recipeDetailInteractor);
    }
}
