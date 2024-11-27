package use_case.login;

import java.util.List;

import entities.recipe.Recipe;
import entities.user.User;
import exceptions.UserNotFound;

/**
 * The interactor for the login usecase.
 */
public class LoginInteractor implements LoginInputBoundary {
    private static final int LIMIT = 9;

    private final LoginDataAccessInterface loginDataAccessObject;
    private final RandomRecipeDataAccessInterface randomRecipeDataAccessObject;
    private final LoginOutputBoundary loginPresenter;

    public LoginInteractor(LoginDataAccessInterface loginDataAccessObject,
                           RandomRecipeDataAccessInterface randomRecipeDataAccessObject,
                           LoginOutputBoundary loginPresenter) {
        this.loginDataAccessObject = loginDataAccessObject;
        this.randomRecipeDataAccessObject = randomRecipeDataAccessObject;
        this.loginPresenter = loginPresenter;
    }

    @Override
    public void execute(LoginInputData loginInputData) {
        final String username = loginInputData.getUsername();
        final String password = loginInputData.getPassword();

        if (!loginDataAccessObject.existsByName(username)) {
            loginPresenter.prepareFailView(username + ": Account does not exist.");
        }
        else {
            try {
                final User user = loginDataAccessObject.getUser(username);
                final String pwd = user.getPassword();
                if (!pwd.equals(password)) {
                    loginPresenter.prepareFailView(username + ": Wrong password.");
                }
                else {
                    loginDataAccessObject.setCurrentUser(user.getName());
                    final List<Recipe> randomRecipes = randomRecipeDataAccessObject.getRandomRecipes(LIMIT);
                    final List<Integer> bookmarkedRecipeIds = loginDataAccessObject.getBookmarkedRecipes(username);
                    final List<Recipe> bookmarkedRecipes = randomRecipeDataAccessObject
                            .getRecipesByIdList(bookmarkedRecipeIds);
                    final List<String> ingredients = randomRecipeDataAccessObject.getIngredientsList();

                    final List<String> ingredientsToAvoid = loginDataAccessObject.getIngredientsToAvoid(username);
                    final LoginOutputData outputData = new LoginOutputData(
                            username,
                            ingredientsToAvoid,
                            randomRecipes,
                            bookmarkedRecipes,
                            ingredients,
                            false);
                    if (ingredientsToAvoid.isEmpty()) {
                        loginPresenter.preparePreferenceView(outputData);
                    }
                    else {
                        loginPresenter.prepareSuccessView(outputData);
                    }
                }
            }
            catch (UserNotFound exception) {
                loginPresenter.prepareFailView(exception.getMessage());
            }
        }
    }

    @Override
    public void switchToSignupView() {
        loginPresenter.switchToSignupView();
    }
}
