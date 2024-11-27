package app.usecase_factory;

import interface_adapter.ViewManagerModel;
import interface_adapter.explore_ingredient.ExploreIngredientController;
import interface_adapter.explore_ingredient.ExploreIngredientPresenter;
import interface_adapter.explore_ingredient.ExploreIngredientViewModel;
import interface_adapter.home_page.HomePageViewModel;
import interface_adapter.search_recipe.SearchRecipeViewModel;
import interface_adapter.services.ServiceManager;
import use_case.explore_ingredient.ExploreIngredientDataAccessInterface;
import use_case.explore_ingredient.ExploreIngredientInputBoundary;
import use_case.explore_ingredient.ExploreIngredientInteractor;
import use_case.explore_ingredient.ExploreIngredientOutputBoundary;
import view.ExploreIngredientRecipeView;

/**
 * Creates the use cases needed for the xplore ingredients view.
 */
public final class ExploreIngredientUseCaseFactory {
    // Prevents initialization
    private ExploreIngredientUseCaseFactory() {
    }

    /**
     * Return the Explore Ingredient View.
     * @param viewManagerModel  the ViewManagerModel to be injected into the View.
     * @param homePageViewModel the HomePageViewModel to be injected into the View.
     * @param searchRecipeViewModel the SearchRecipeViewModel to be injected into the View.
     * @param exploreIngredientViewModel the ExploreIngredientViewModel to be injected into the View.
     * @param exploreIngredientDataAccessObject the ExploreIngredientDAO to be injected into the View.
     * @param serviceManager the ServiceManager to be injected into the View.
     * @return the Explore by ingredient View.
     */
    public static ExploreIngredientRecipeView create(
            ViewManagerModel viewManagerModel,
            HomePageViewModel homePageViewModel,
            SearchRecipeViewModel searchRecipeViewModel,
            ExploreIngredientViewModel exploreIngredientViewModel,
            ExploreIngredientDataAccessInterface exploreIngredientDataAccessObject,
            ServiceManager serviceManager) {
        final ExploreIngredientController exploreIngredientController = createExploreIngredientUseCase(viewManagerModel,
                homePageViewModel, searchRecipeViewModel, exploreIngredientViewModel,
                exploreIngredientDataAccessObject);
        return new ExploreIngredientRecipeView(
                exploreIngredientViewModel, exploreIngredientController, serviceManager, viewManagerModel);
    }

    private static ExploreIngredientController createExploreIngredientUseCase(
            ViewManagerModel viewManagerModel,
            HomePageViewModel homePageViewModel,
            SearchRecipeViewModel searchRecipeViewModel,
            ExploreIngredientViewModel exploreIngredientViewModel,
            ExploreIngredientDataAccessInterface exploreIngredientDataAccessObject) {

        final ExploreIngredientOutputBoundary exploreIngredientOutputBoundary = new ExploreIngredientPresenter(
                exploreIngredientViewModel, homePageViewModel, searchRecipeViewModel, viewManagerModel);
        final ExploreIngredientInputBoundary exploreIngredientInteractor = new ExploreIngredientInteractor(
                exploreIngredientDataAccessObject, exploreIngredientOutputBoundary);

        return new ExploreIngredientController(exploreIngredientInteractor);
    }
}
