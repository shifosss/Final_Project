package app.usecase_factory;

import data_access.UserDataAccessObject;
import entities.user.factory.CommonUserFactory;
import entities.user.factory.UserFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.signup.SignupController;
import interface_adapter.signup.SignupPresenter;
import interface_adapter.signup.SignupViewModel;
import use_case.signup.SignupInputBoundary;
import use_case.signup.SignupInteractor;
import use_case.signup.SignupOutputBoundary;
import view.views_placeholder.SignupView;

/**
 * Factory for the signup usecase.
 */
public final class SignupUseCaseFactory {
    private SignupUseCaseFactory() {
    }

    /**
     * Creates the signup view.
     * @param viewManagerModel the ViewManagerModel to be injected into the view.
     * @param signupViewModel the SignupViewModel to be injected into the view.
     * @param loginViewModel the LoginViewModel to be injected into the view.
     * @param userDataAccessObject the user DAO to be injected into the view.
     * @return the signup view.
     */
    public static SignupView create(ViewManagerModel viewManagerModel,
                                    SignupViewModel signupViewModel,
                                    LoginViewModel loginViewModel,
                                    UserDataAccessObject userDataAccessObject) {
        final SignupController signupController = createUserSignupUseCase(viewManagerModel, signupViewModel,
                loginViewModel, userDataAccessObject);
        return new SignupView(signupController, signupViewModel);
    }

    private static SignupController createUserSignupUseCase(ViewManagerModel viewManagerModel,
                                                        SignupViewModel signupViewModel,
                                                        LoginViewModel loginViewModel,
                                                        UserDataAccessObject userDataAccessObject) {
        final SignupOutputBoundary signupOutputBoundary = new SignupPresenter(
                signupViewModel, loginViewModel, viewManagerModel);
        final UserFactory userFactory = new CommonUserFactory();
        final SignupInputBoundary signupInteractor = new SignupInteractor(
                userDataAccessObject, signupOutputBoundary, userFactory);
        return new SignupController(signupInteractor);
    }
}
