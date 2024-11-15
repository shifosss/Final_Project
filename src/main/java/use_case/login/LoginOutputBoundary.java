package use_case.login;

/**
 * Output boundary for the login usecase.
 */
public interface LoginOutputBoundary {
    /**
     * Sends the user to the home page view.
     * @param loginOutputData the output data.
     */
    void prepareSuccessView(LoginOutputData loginOutputData);

    /**
     * Sends the user to the preference view.
     * @param loginOutputData the output data.
     */
    void preparePreferenceView(LoginOutputData loginOutputData);

    /**
     * Sends the user back to the login view when there is an error with logging in.
     * @param errorMessage the error message.
     */
    void prepareFailView(String errorMessage);

    /**
     * Switches to the signup view.
     */
    void switchToSignupView();
}
