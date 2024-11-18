package interface_adapter.login;

import use_case.login.LoginInputBoundary;
import use_case.login.LoginInputData;

/**
 * Controller for the login view.
 */
public class LoginController {
    private final LoginInputBoundary loginInteractor;

    public LoginController(LoginInputBoundary loginInteractor) {
        this.loginInteractor = loginInteractor;
    }

    /**
     * Attempts to log in the user with given arguments.
     * @param username the username.
     * @param password the password.
     */
    public void execute(String username, String password) {
        final LoginInputData loginInputData = new LoginInputData(username, password);
        loginInteractor.execute(loginInputData);
    }

    /**
     * Switches to the signup view.
     */
    public void switchToSignupView() {
        loginInteractor.switchToSignupView();
    }
}
