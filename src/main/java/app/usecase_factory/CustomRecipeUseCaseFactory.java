package app.usecase_factory;

import data_access.CocktailDataAccessObject;
import data_access.UserDataAccessObject;
import interface_adapter.ViewManagerModel;
import interface_adapter.custom_recipe.CustomRecipeController;
import interface_adapter.custom_recipe.CustomRecipePresenter;
import interface_adapter.custom_recipe.CustomRecipeViewModel;
import interface_adapter.home_page.HomePageViewModel;
import interface_adapter.services.ServiceManager;
import use_case.create_recipe.CustomRecipeInputBoundary;
import use_case.create_recipe.CustomRecipeInteractor;
import use_case.create_recipe.CustomRecipeOutputBoundary;
import view.views_placeholder.CustomRecipeView;

/**
 * Usecase factory for creating custom recipes.
 */
public final class CustomRecipeUseCaseFactory {
    private CustomRecipeUseCaseFactory() {
    }

    public static CustomRecipeView create(ViewManagerModel viewManagerModel,
                                   HomePageViewModel homePageViewModel,
                                   CustomRecipeViewModel customRecipeViewModel,
                                   CocktailDataAccessObject cocktailDataAccessObject,
                                   UserDataAccessObject userDataAccessObject,
                                   ServiceManager serviceManager) {
        final CustomRecipeController customRecipeController = createCustomRecipeUseCases(
                viewManagerModel, homePageViewModel, customRecipeViewModel,
                cocktailDataAccessObject, userDataAccessObject);
        return new CustomRecipeView(customRecipeViewModel, customRecipeController, serviceManager);
    }

    private static CustomRecipeController createCustomRecipeUseCases(ViewManagerModel viewManagerModel,
                                                              HomePageViewModel homePageViewModel,
                                                              CustomRecipeViewModel customRecipeViewModel,
                                                              CocktailDataAccessObject cocktailDataAccessObject,
                                                              UserDataAccessObject userDataAccessObject) {
        final CustomRecipeOutputBoundary customRecipeOutputBoundary = new CustomRecipePresenter(
                homePageViewModel, customRecipeViewModel, viewManagerModel);
        final CustomRecipeInputBoundary customRecipeInteractor = new CustomRecipeInteractor(
                userDataAccessObject, cocktailDataAccessObject, customRecipeOutputBoundary
        );
        return new CustomRecipeController(customRecipeInteractor);
    }
}
