package interface_adapter.login;

import use_case.login.LoginOutputBoundary;
import use_case.login.LoginOutputData;

public class LoginPresenter implements LoginOutputBoundary {
    private final LoginViewModel loginViewModel;

    public LoginPresenter(LoginViewModel loginViewModel) {
        this.loginViewModel = loginViewModel;
    }

    @Override
    public void prepareSuccessView(LoginOutputData user) {
        LoginState loginState = loginViewModel.getState();
        loginState.setUsername(user.getUsername());
        loginState.setLoggedIn(true);
        loginViewModel.setState(loginState);
        loginViewModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        LoginState loginState = loginViewModel.getState();
        loginState.setError(error);
        loginViewModel.setState(loginState);
        loginViewModel.firePropertyChanged();
    }
}
