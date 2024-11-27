package use_case.signup;

import entities.user.User;
import entities.user.factory.UserFactory;

public class SignupInteractor implements SignupInputBoundary {
    private final SignupDataAccessInterface signupDataAccessObject;
    private final SignupOutputBoundary signupPresenter;
    private final UserFactory userFactory;

    public SignupInteractor(SignupDataAccessInterface signupDataAccessObject,
                            SignupOutputBoundary signupPresenter,
                            UserFactory userFactory) {
        this.signupDataAccessObject = signupDataAccessObject;
        this.signupPresenter = signupPresenter;
        this.userFactory = userFactory;
    }

    @Override
    public void execute(SignupInputData signupInputData) {
        if (signupDataAccessObject.existsByName(signupInputData.getUsername())) {
            signupPresenter.prepareFailView("User already exists.");
        }
        else if (!signupInputData.getPassword().equals(signupInputData.getRepeatPassword())) {
            signupPresenter.prepareFailView("Passwords don't match.");
        }
        else {
            try {
                final User user = userFactory.create(signupInputData.getUsername(), signupInputData.getPassword());
                signupDataAccessObject.signUp(user);

                final SignupOutputData signupOutputData = new SignupOutputData(user.getName(), false);
                signupPresenter.prepareSuccessView(signupOutputData);
            }
            catch (IllegalArgumentException exception) {
                signupPresenter.prepareFailView(exception.getMessage());
            }
        }
    }

    @Override
    public void switchToLoginView() {
        signupPresenter.switchToLoginView();
    }
}
