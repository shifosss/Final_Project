package use_case.signup;

import interface_adapter.signup.SignupPresenter;

public class SignupInteractor implements SignupInputBoundary {
    private final SignupDataAccessInterface signupDataAccessObject;
    private final SignupPresenter signupPresenter;

    public SignupInteractor(SignupDataAccessInterface signupDataAccessObject,
                            SignupPresenter signupPresenter) {
        this.signupDataAccessObject = signupDataAccessObject;
        this.signupPresenter = signupPresenter;
    }

    @Override
    public void execute(SignupInputData signupInputData) {
    }

    @Override
    public void switchToLoginView() {
    }
}
