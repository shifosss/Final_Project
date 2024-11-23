package use_case.signup;

/**
 * Output boundary for the signup usecase.
 */
public interface SignupOutputBoundary {
    /**
     * Prepares the success view. (When the signup is successful)
     * @param data the output data.
     */
    void prepareSuccessView(SignupOutputData data);

    /**
     * Prepares the fail view when the signup fails(ie: username already exists).
     * @param errorMessage the error message.
     */
    void prepareFailView(String errorMessage);

    /**
     * Switches to the login view.
     */
    void switchToLoginView();
}
