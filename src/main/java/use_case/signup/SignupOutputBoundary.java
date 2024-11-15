package use_case.signup;

public interface SignupOutputBoundary {
    /**
     * Prepares the success view. (When the signup is successful)
     * @param data the output data.
     */
    void prepareSuccessView(SignupOutputData data);

    /**
     * Prepares the fail view when the signup fails(ie: username already exists).
     * @param data the output data.
     */
    void prepareFailView(SignupOutputData data);

    /**
     * Switches to the login view.
     */
    void switchToLoginView();
}
