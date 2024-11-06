package app.usecase_factory;

import domain.use_case.search_recipes.SearchRecipeDataAccessInterface;
import domain.use_case.search_recipes.SearchRecipeInputBoundary;
import domain.use_case.search_recipes.SearchRecipeInteractor;
import domain.use_case.search_recipes.SearchRecipeOutputBoundary;
import interface_adapter.ViewManagerModel;
import interface_adapter.search_recipe.SearchRecipeController;
import interface_adapter.search_recipe.SearchRecipePresenter;
import interface_adapter.search_recipe.SearchRecipeViewModel;
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
     * @param searchRecipeDataAccessObject the SearchRecipeDAO to be injected into the View.
     * @return the SearchRecipeView given the arguments.
     */
    public static SearchRecipeView create(ViewManagerModel viewManagerModel,
                                          SearchRecipeViewModel searchRecipeViewModel,
                                          SearchRecipeDataAccessInterface searchRecipeDataAccessObject) {
        final SearchRecipeController searchRecipeController = createSearchRecipeUseCase(viewManagerModel,
                searchRecipeViewModel, searchRecipeDataAccessObject);
        return new SearchRecipeView(searchRecipeViewModel, searchRecipeController);
    }

    private static SearchRecipeController createSearchRecipeUseCase(
            ViewManagerModel viewManagerModel,
            SearchRecipeViewModel searchRecipeViewModel,
            SearchRecipeDataAccessInterface searchRecipeDataAccessObject) {

        final SearchRecipeOutputBoundary searchRecipeOutputBoundary = new SearchRecipePresenter(
                searchRecipeViewModel, viewManagerModel);
        final SearchRecipeInputBoundary searchRecipeInteractor = new SearchRecipeInteractor(
                searchRecipeDataAccessObject, searchRecipeOutputBoundary);

        return new SearchRecipeController(searchRecipeInteractor);
    }

}
