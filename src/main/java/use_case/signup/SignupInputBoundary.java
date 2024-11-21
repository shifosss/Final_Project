package use_case.signup;

/**
 * The Input boundary for the signup usecase.
 */
public interface SignupInputBoundary {
    /**
     * Attempts to sign up the given input data.
     * @param signupInputData the input data.
     */
    void execute(SignupInputData signupInputData);

    /**
     * Switches to the login view.
     */
    void switchToLoginView();
}
