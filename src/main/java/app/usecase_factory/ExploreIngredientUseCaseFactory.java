package app.usecase_factory;

import interface_adapter.ViewManagerModel;
import interface_adapter.explore_ingredient.*;
import data_access.CocktailDataAccessObject;
import interface_adapter.services.ServiceManager;
import use_case.explore_ingredient.*;
import view.ExploreIngredientRecipeView;

public class ExploreIngredientUseCaseFactory {
    public static ExploreIngredientRecipeView create(
            ViewManagerModel viewManagerModel,
            CocktailDataAccessObject dataAccessObject,
            ServiceManager serviceManager) {

        ExploreIngredientViewModel viewModel = new ExploreIngredientViewModel();

        ExploreIngredientPresenter presenter = new ExploreIngredientPresenter(
                viewModel,
                viewManagerModel
        );

        ExploreIngredientInteractor interactor = new ExploreIngredientInteractor(
                dataAccessObject,
                presenter
        );

        ExploreIngredientController controller = new ExploreIngredientController(interactor);

        return new ExploreIngredientRecipeView(
                viewModel,
                controller,
                serviceManager,
                viewManagerModel
        );
    }
}