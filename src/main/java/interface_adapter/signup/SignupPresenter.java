package interface_adapter.signup;

import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;
import use_case.signup.SignupOutputBoundary;
import use_case.signup.SignupOutputData;

/**
 * The presenter for the sign-up view.
 */
public class SignupPresenter implements SignupOutputBoundary {
    // View models for this usecase.
    private final SignupViewModel signupViewModel;
    private final LoginViewModel loginViewModel;

    private final ViewManagerModel viewManagerModel;

    public SignupPresenter(SignupViewModel signupViewModel,
                           LoginViewModel loginViewModel,
                           ViewManagerModel viewManagerModel) {
        this.signupViewModel = signupViewModel;
        this.loginViewModel = loginViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(SignupOutputData data) {
        final LoginState loginState = loginViewModel.getState();
        loginState.setUsername(data.getUsername());
        loginState.setPassword("");
        this.loginViewModel.setState(loginState);
        loginViewModel.firePropertyChanged();
        loginViewModel.firePropertyChanged("successful signup");

        viewManagerModel.setState(loginViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String errorMessage) {
        final SignupState signupState = signupViewModel.getState();
        signupState.setUsernameError(errorMessage);
        signupViewModel.firePropertyChanged();
    }

    @Override
    public void switchToLoginView() {
        viewManagerModel.setState(loginViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
