package use_case.login;

public interface LoginInputBoundary {
    /**
     * Attempts to login the user.
     * @param loginInputData
     */
    void execute(LoginInputData loginInputData);
}
