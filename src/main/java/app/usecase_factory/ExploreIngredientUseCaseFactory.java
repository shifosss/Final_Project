package app.usecase_factory;

import interface_adapter.ViewManagerModel;
import interface_adapter.explore_ingredient.ExploreIngredientController;
import interface_adapter.explore_ingredient.ExploreIngredientPresenter;
import interface_adapter.explore_ingredient.ExploreIngredientViewModel;
import interface_adapter.services.ServiceManager;
import use_case.explore_ingredient.ExploreIngredientDataAccessInterface;
import use_case.explore_ingredient.ExploreIngredientInputBoundary;
import use_case.explore_ingredient.ExploreIngredientInteractor;
import use_case.explore_ingredient.ExploreIngredientOutputBoundary;
import view.ExploreIngredientRecipeView;

public class ExploreIngredientUseCaseFactory {
    // Prevents initialization
    private ExploreIngredientUseCaseFactory() {
    }

    /**
     * Creates the Search Recipe View.
     * @param viewManagerModel the ViewManagerModel to be injected into the View.
     * @param exploreIngredientViewModel the SearchRecipeViewModel to be injected into the View.
     * @param exploreIngredientDataAccessObject the SearchRecipeDAO to be injected into the View.
     * @return the SearchRecipeView given the arguments.
     */
    public static ExploreIngredientRecipeView create(ViewManagerModel viewManagerModel,
                                          ExploreIngredientViewModel exploreIngredientViewModel,
                                          ExploreIngredientDataAccessInterface exploreIngredientDataAccessObject,
                                          ServiceManager serviceManager) {
        final ExploreIngredientController exploreIngredientController = createExploreIngredientUseCase(viewManagerModel,
                exploreIngredientViewModel, exploreIngredientDataAccessObject);
        return new ExploreIngredientRecipeView(exploreIngredientViewModel, exploreIngredientController, serviceManager, viewManagerModel);
    }

    private static ExploreIngredientController createExploreIngredientUseCase(
            ViewManagerModel viewManagerModel,
            ExploreIngredientViewModel exploreIngredientViewModel,
            ExploreIngredientDataAccessInterface exploreIngredientDataAccessObject) {

        final ExploreIngredientOutputBoundary exploreIngredientOutputBoundary = new ExploreIngredientPresenter(
                exploreIngredientViewModel, viewManagerModel);
        final ExploreIngredientInputBoundary exploreIngredientInteractor = new ExploreIngredientInteractor(
                exploreIngredientDataAccessObject, exploreIngredientOutputBoundary);

        return new ExploreIngredientController(exploreIngredientInteractor);
    }
}
