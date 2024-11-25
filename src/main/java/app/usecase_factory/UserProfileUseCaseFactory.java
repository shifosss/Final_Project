package app.usecase_factory;

import data_access.UserDataAccessObject;
import interface_adapter.ViewManagerModel;
import interface_adapter.home_page.HomePageViewModel;
import interface_adapter.services.ServiceManager;
import interface_adapter.user_profile.UserProfileController;
import interface_adapter.user_profile.UserProfilePresenter;
import interface_adapter.user_profile.UserProfileViewModel;
import use_case.user_profile.UserProfileInputBoundary;
import use_case.user_profile.UserProfileInteractor;
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
                                         UserDataAccessObject userDataAccessObject,
                                         ServiceManager serviceManager) {
        final UserProfileController userProfileController = createUserProfileUseCases(
                viewManagerModel, userProfileViewModel, userDataAccessObject, homePageViewModel);

        return new UserProfileView(userProfileViewModel, userProfileController, serviceManager);
    }

    private static UserProfileController createUserProfileUseCases(
            ViewManagerModel viewManagerModel,
            UserProfileViewModel userProfileViewModel,
            UserDataAccessObject userDataAccessObject,
            HomePageViewModel homePageViewModel) {
        final UserProfilePresenter userProfilePresenter = new UserProfilePresenter(
                homePageViewModel, userProfileViewModel, viewManagerModel);
        final UserProfileInputBoundary userProfileInteractor = new UserProfileInteractor(
                userProfilePresenter, userDataAccessObject);

        return new UserProfileController(userProfileInteractor);
    }

}
