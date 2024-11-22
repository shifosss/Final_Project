package app.usecase_factory;

import data_access.CocktailDataAccessObject;
import data_access.UserDataAccessObject;
import interface_adapter.ViewManagerModel;
import interface_adapter.home_page.HomePageViewModel;
import interface_adapter.login.LoginController;
import interface_adapter.login.LoginPresenter;
import interface_adapter.login.LoginViewModel;
import interface_adapter.preference.PreferenceViewModel;
import interface_adapter.signup.SignupViewModel;
import use_case.login.LoginInputBoundary;
import use_case.login.LoginInteractor;
import use_case.login.LoginOutputBoundary;
import view.views_placeholder.LoginView;

/**
 * Factory for the login usecase.
 */
public final class LoginUseCaseFactory {
    private LoginUseCaseFactory() {
    }

    /**
     * Creates the login view.
     * @param viewManagerModel the ViewManagerModel to be injected into the view.
     * @param signupViewModel the SignupViewModel to be injected into the view.
     * @param loginViewModel the LoginViewModel to be injected into the view.
     * @param homePageViewModel the HomePageViewModel to be injected into the view.
     * @param preferenceViewModel the PreferenceViewModel to be injected into the view.
     * @param userDataAccessObject the user DAO to be injected into the view.
     * @return the login view.
     */
    public static LoginView create(ViewManagerModel viewManagerModel,
                                    SignupViewModel signupViewModel,
                                    LoginViewModel loginViewModel,
                                    PreferenceViewModel preferenceViewModel,
                                    HomePageViewModel homePageViewModel,
                                    UserDataAccessObject userDataAccessObject,
                                   CocktailDataAccessObject cocktailDataAccessObject) {
        final LoginController loginController = createLoginUseCase(viewManagerModel,
                signupViewModel, loginViewModel, preferenceViewModel, homePageViewModel,
                userDataAccessObject, cocktailDataAccessObject);
        return new LoginView(loginViewModel, loginController);
    }

    private static LoginController createLoginUseCase(ViewManagerModel viewManagerModel,
                                                      SignupViewModel signupViewModel,
                                                      LoginViewModel loginViewModel,
                                                      PreferenceViewModel preferenceViewModel,
                                                      HomePageViewModel homePageViewModel,
                                                      UserDataAccessObject userDataAccessObject,
                                                      CocktailDataAccessObject cocktailDataAccessObject) {
        final LoginOutputBoundary loginOutputBoundary = new LoginPresenter(viewManagerModel,
                signupViewModel, loginViewModel, preferenceViewModel, homePageViewModel);
        final LoginInputBoundary loginInteractor = new LoginInteractor(
                userDataAccessObject, cocktailDataAccessObject, loginOutputBoundary);

        return new LoginController(loginInteractor);
    }
}
