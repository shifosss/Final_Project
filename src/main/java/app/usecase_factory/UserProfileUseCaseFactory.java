package app.usecase_factory;

import data_access.CocktailDataAccessObject;
import data_access.UserDataAccessObject;
import interface_adapter.ViewManagerModel;
import interface_adapter.home_page.HomePageViewModel;
import interface_adapter.preference.PreferenceViewModel;
import interface_adapter.recipe_detail.RecipeDetailViewModel;
import interface_adapter.services.ServiceManager;
import interface_adapter.user_profile.UserProfileController;
import interface_adapter.user_profile.UserProfilePresenter;
import interface_adapter.user_profile.UserProfileViewModel;
import use_case.change_preference.ChangePreferenceInputBoundary;
import use_case.change_preference.ChangePreferenceInteractor;
import use_case.user_profile.UserProfileInputBoundary;
import use_case.user_profile.UserProfileInteractor;
import view.views_placeholder.UserProfileView;

/**
 * The user profile use case factory.
 */
public final class UserProfileUseCaseFactory {
    private UserProfileUseCaseFactory() {
    }

    /**
     * Creates the User profile view.
     * @param viewManagerModel the ViewManagerModel to be injected into the View.
     * @param userProfileViewModel the UserProfileViewModel to be injected into the View.
     * @param homePageViewModel the HomePageViewModel to be injected into the View.
     * @param recipeDetailViewModel the RecipeDetailViewModel to be injected into the View.
     * @param preferenceViewModel the PreferenceViewModel to be injected into the view.
     * @param userDataAccessObject the UserDAO to be injected into the View.
     * @param cocktailDataAccessObject the CocktailDAO to be injected into the view.
     * @param serviceManager the ServiceManager to be injected into the View.
     * @return the view.
     */
    public static UserProfileView create(ViewManagerModel viewManagerModel,
                                         UserProfileViewModel userProfileViewModel,
                                         HomePageViewModel homePageViewModel,
                                         RecipeDetailViewModel recipeDetailViewModel,
                                         PreferenceViewModel preferenceViewModel,
                                         UserDataAccessObject userDataAccessObject,
                                         CocktailDataAccessObject cocktailDataAccessObject,
                                         ServiceManager serviceManager) {
        final UserProfileController userProfileController = createUserProfileUseCases(
                viewManagerModel, userProfileViewModel, recipeDetailViewModel, preferenceViewModel,
                userDataAccessObject, cocktailDataAccessObject, homePageViewModel);

        return new UserProfileView(userProfileViewModel, userProfileController, serviceManager);
    }

    private static UserProfileController createUserProfileUseCases(
            ViewManagerModel viewManagerModel,
            UserProfileViewModel userProfileViewModel,
            RecipeDetailViewModel recipeDetailViewModel,
            PreferenceViewModel preferenceViewModel,
            UserDataAccessObject userDataAccessObject,
            CocktailDataAccessObject cocktailDataAccessObject,
            HomePageViewModel homePageViewModel) {
        final UserProfilePresenter userProfilePresenter = new UserProfilePresenter(
                homePageViewModel, userProfileViewModel, recipeDetailViewModel, preferenceViewModel,
                viewManagerModel);
        final UserProfileInputBoundary userProfileInteractor = new UserProfileInteractor(
                userProfilePresenter, userDataAccessObject);
        final ChangePreferenceInputBoundary changePreferenceInteractor = new ChangePreferenceInteractor(
                userProfilePresenter, userDataAccessObject, cocktailDataAccessObject);

        return new UserProfileController(userProfileInteractor, changePreferenceInteractor);
    }

}
