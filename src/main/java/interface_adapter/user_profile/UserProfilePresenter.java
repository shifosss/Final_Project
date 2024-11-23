package interface_adapter.user_profile;


import interface_adapter.ViewManagerModel;
import interface_adapter.home_page.HomePageState;
import interface_adapter.home_page.HomePageViewModel;
import use_case.user_profile.UserProfileOutputBoundary;
import use_case.user_profile.UserProfileOutputData;

/**
 * Presenter for the userprofile view.
 */
public class UserProfilePresenter implements UserProfileOutputBoundary {
    private final HomePageViewModel homePageViewModel;
    private final UserProfileViewModel userProfileViewModel;
    private final ViewManagerModel viewManagerModel;

    public UserProfilePresenter(HomePageViewModel homePageViewModel,
                                UserProfileViewModel userProfileViewModel,
                                ViewManagerModel viewManagerModel) {
        this.homePageViewModel = homePageViewModel;
        this.userProfileViewModel = userProfileViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void switchToHomeView() {
        viewManagerModel.setState(homePageViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToProfileView(UserProfileOutputData outputData) {
        final UserProfileState state = userProfileViewModel.getState();
        state.setUsername(outputData.getUsername());
        state.setCreatedRecipes(outputData.getCreatedRecipes());

        userProfileViewModel.setState(state);
        userProfileViewModel.firePropertyChanged();

        viewManagerModel.setState(userProfileViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void presentUserNotFound(String error, String username) {

    }
}
