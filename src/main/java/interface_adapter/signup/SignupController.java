package interface_adapter.signup;

import use_case.signup.SignupInputBoundary;
import use_case.signup.SignupInputData;

/**
 * Controller for the signup view.
 */
public class SignupController {
    private final SignupInputBoundary signupInteractor;

    public SignupController(SignupInputBoundary signupInteractor) {
        this.signupInteractor = signupInteractor;
    }

    /**
     * Attempts to sign up an account with given arguments.
     * @param username the username.
     * @param password the password.
     * @param repeatPassword the repeatPassword.
     */
    public void execute(String username, String password, String repeatPassword) {
        final SignupInputData signupInputData = new SignupInputData(username, password, repeatPassword);
        signupInteractor.execute(signupInputData);
    }

    /**
     * Switches to the login view (when the use wants to log in instead).
     */
    public void switchToLoginView() {
        signupInteractor.switchToLoginView();
    }
}
