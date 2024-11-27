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

    /**
     * Creates the Custom Recipe View.
     * @param viewManagerModel the ViewManagerModel to be injected into the View.
     * @param homePageViewModel the HomePageViewModel to be injected into the View.
     * @param customRecipeViewModel the CustomRecipeViewModel to be injected into the View.
     * @param userProfileViewModel the UserProfileViewModel to be injected into the View.
     * @param cocktailDataAccessObject the CocktailDataAccessObject to be injected into the View.
     * @param userDataAccessObject the UserDataAccessObject to be injected into the View.
     * @param serviceManager the ServiceManager to be injected into the View.
     * @return the Custom Recipe View.
     */
    public static CustomRecipeView create(ViewManagerModel viewManagerModel,
                                   HomePageViewModel homePageViewModel,
                                   CustomRecipeViewModel customRecipeViewModel,
                                   UserProfileViewModel userProfileViewModel,
                                   CocktailDataAccessObject cocktailDataAccessObject,
                                   UserDataAccessObject userDataAccessObject,
                                   ServiceManager serviceManager) {
        final CustomRecipeController customRecipeController = createCustomRecipeUseCases(
                viewManagerModel, homePageViewModel, customRecipeViewModel, userProfileViewModel,
                cocktailDataAccessObject, userDataAccessObject);
        return new CustomRecipeView(customRecipeViewModel, customRecipeController, serviceManager);
    }

    private static CustomRecipeController createCustomRecipeUseCases(ViewManagerModel viewManagerModel,
                                                              HomePageViewModel homePageViewModel,
                                                              CustomRecipeViewModel customRecipeViewModel,
                                                              UserProfileViewModel userProfileViewModel,
                                                              CocktailDataAccessObject cocktailDataAccessObject,
                                                              UserDataAccessObject userDataAccessObject) {
        final CustomRecipeOutputBoundary customRecipeOutputBoundary = new CustomRecipePresenter(
                homePageViewModel, customRecipeViewModel, userProfileViewModel, viewManagerModel);
        final CustomRecipeInputBoundary customRecipeInteractor = new CustomRecipeInteractor(
                userDataAccessObject, customRecipeOutputBoundary
        );
        return new CustomRecipeController(customRecipeInteractor);
    }
}
