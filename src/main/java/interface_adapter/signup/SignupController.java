package interface_adapter.signup;

import use_case.signup.SignupInputBoundary;
import use_case.signup.SignupInputData;

public class SignupController {
    final SignupInputBoundary signupUseCaseInteractor;

    public SignupController(SignupInputBoundary signupUseCaseInteractor) {
        this.signupUseCaseInteractor = signupUseCaseInteractor;
    }

    public void execute(String username, String password) {
        SignupInputData signupInputData = new SignupInputData(username, password, password);
        signupUseCaseInteractor.execute(signupInputData);
    }
}