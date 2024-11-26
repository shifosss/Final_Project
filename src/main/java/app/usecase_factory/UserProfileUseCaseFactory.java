package app.usecase_factory;

import data_access.CocktailDataAccessObject;
import data_access.UserDataAccessObject;
import interface_adapter.ViewManagerModel;
import interface_adapter.home_page.HomePageViewModel;
import interface_adapter.recipe_detail.RecipeDetailViewModel;
import interface_adapter.services.ServiceManager;
import interface_adapter.user_profile.UserProfileController;
import interface_adapter.user_profile.UserProfilePresenter;
import interface_adapter.user_profile.UserProfileViewModel;
import use_case.user_profile.UserProfileInputBoundary;
import use_case.user_profile.UserProfileInteractor;
import use_case.user_profile.UserProfileOutputBoundary;
import use_case.view_recipe.ViewRecipeInputBoundary;
import use_case.view_recipe.ViewRecipeInteractor;
import view.views_placeholder.UserProfileView;

/**
 * The user profile use case factory.
 */
public final class UserProfileUseCaseFactory {
    private UserProfileUseCaseFactory() {
    }

    public static UserProfileView create(ViewManagerModel viewManagerModel,
                                         UserProfileViewModel userProfileViewModel,
                                         HomePageViewModel homePageViewModel,
                                         RecipeDetailViewModel recipeDetailViewModel,
                                         UserDataAccessObject userDataAccessObject,
                                         CocktailDataAccessObject cocktailDataAccessObject,
                                         ServiceManager serviceManager) {
        final UserProfileController userProfileController = createUserProfileUseCases(
                viewManagerModel, userProfileViewModel, recipeDetailViewModel,
                userDataAccessObject, cocktailDataAccessObject, homePageViewModel);

        return new UserProfileView(userProfileViewModel, userProfileController, serviceManager);
    }

    private static UserProfileController createUserProfileUseCases(
            ViewManagerModel viewManagerModel,
            UserProfileViewModel userProfileViewModel,
            RecipeDetailViewModel recipeDetailViewModel,
            UserDataAccessObject userDataAccessObject,
            CocktailDataAccessObject cocktailDataAccessObject,
            HomePageViewModel homePageViewModel) {
        final UserProfilePresenter userProfilePresenter = new UserProfilePresenter(
                homePageViewModel, userProfileViewModel, recipeDetailViewModel, viewManagerModel);
        final UserProfileInputBoundary userProfileInteractor = new UserProfileInteractor(
                userProfilePresenter, userDataAccessObject);

        return new UserProfileController(userProfileInteractor);
    }

}
