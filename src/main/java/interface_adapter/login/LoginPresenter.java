package interface_adapter.login;

import interface_adapter.ViewManagerModel;
import interface_adapter.home_page.HomePageState;
import interface_adapter.home_page.HomePageViewModel;
import interface_adapter.preference.PreferenceState;
import interface_adapter.preference.PreferenceViewModel;
import interface_adapter.signup.SignupViewModel;
import use_case.login.LoginOutputBoundary;
import use_case.login.LoginOutputData;

/**
 * Presenter for the login use case.
 */
public class LoginPresenter implements LoginOutputBoundary {
    private final LoginViewModel loginViewModel;
    private final SignupViewModel signupViewModel;
    private final PreferenceViewModel preferenceViewModel;
    private final HomePageViewModel homePageViewModel;
    private final ViewManagerModel viewManagerModel;

    public LoginPresenter(ViewManagerModel viewManagerModel,
                          SignupViewModel signupViewModel,
                          LoginViewModel loginViewModel,
                          PreferenceViewModel preferenceViewModel,
                          HomePageViewModel homePageViewModel) {
        this.loginViewModel = loginViewModel;
        this.preferenceViewModel = preferenceViewModel;
        this.signupViewModel = signupViewModel;
        this.homePageViewModel = homePageViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(LoginOutputData loginOutputData) {
        final HomePageState homePageState = homePageViewModel.getState();
        homePageState.setUsername(loginOutputData.getUsername());
        homePageState.setIngredientsToAvoidId(loginOutputData.getIngredientsToAvoidId());

        homePageViewModel.setState(homePageState);
        homePageViewModel.firePropertyChanged();

        viewManagerModel.setState(homePageViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void preparePreferenceView(LoginOutputData loginOutputData) {
        final PreferenceState preferenceState = preferenceViewModel.getState();
        preferenceState.setUsername(loginOutputData.getUsername());

        preferenceViewModel.setState(preferenceState);
        preferenceViewModel.firePropertyChanged();

        viewManagerModel.setState(preferenceViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String errorMessage) {
        final LoginState loginState = loginViewModel.getState();
        loginState.setUsername("");
        loginState.setPassword("");

        loginViewModel.setState(loginState);
        loginViewModel.firePropertyChanged();

        viewManagerModel.setState(loginViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToSignupView() {
        viewManagerModel.setState(signupViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

}
