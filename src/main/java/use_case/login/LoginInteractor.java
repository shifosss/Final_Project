package use_case.login;

import entities.user.User;

import java.util.List;

/**
 * The interactor for the login usecase.
 */
public class LoginInteractor implements LoginInputBoundary {
    private final LoginDataAccessInterface userDataAccessObject;
    private final LoginOutputBoundary loginPresenter;

    public LoginInteractor(LoginDataAccessInterface userDataAccessObject, LoginOutputBoundary loginPresenter) {
        this.userDataAccessObject = userDataAccessObject;
        this.loginPresenter = loginPresenter;
    }

    @Override
    public void execute(LoginInputData loginInputData) {
        final String username = loginInputData.getUsername();
        final String password = loginInputData.getPassword();

        if (!userDataAccessObject.existsByName(username)) {
            loginPresenter.prepareFailView(username + ": Account does not exist.");
        }
        else {
            final String pwd = userDataAccessObject.getUser(username).getPassword();
            if (!pwd.equals(password)) {
                loginPresenter.prepareFailView(username + ": Wrong password.");
            }
            else {
                final User user = userDataAccessObject.getUser(username);
                userDataAccessObject.setCurrentUser(user.getName());

                final List<String> ingredientsToAvoid = userDataAccessObject.getIngredientsToAvoid(username);
                final LoginOutputData outputData = new LoginOutputData(
                        username,
                        ingredientsToAvoid,
                        false);
                if (ingredientsToAvoid.isEmpty()) {
                    loginPresenter.preparePreferenceView(outputData);
                }
                else {
                    loginPresenter.prepareSuccessView(outputData);
                }
            }
        }
    }

    @Override
    public void switchToSignupView() {
        loginPresenter.switchToSignupView();
    }
}
