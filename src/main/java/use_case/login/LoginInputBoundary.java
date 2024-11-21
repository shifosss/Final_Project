package use_case.login;

public interface LoginInputBoundary {
    /**
     * Attempts to log in the user.
     * @param loginInputData the input data.
     */
    void execute(LoginInputData loginInputData);

    /**
     * Switches to the signup view.
     */
    void switchToSignupView();
}
