package interface_adapter.signup;

import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;
import interface_adapter.preference.PreferenceViewModel;
import use_case.signup.SignupOutputBoundary;
import use_case.signup.SignupOutputData;

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

    }

    @Override
    public void prepareFailView(SignupOutputData data) {

    }

    @Override
    public void switchToLoginView() {

    }
}
