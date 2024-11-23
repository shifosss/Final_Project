package use_case.login;

import entities.recipe.Recipe;
import entities.user.User;
import use_case.random_recipes.RandomRecipeDataAccessInterface;

import java.util.List;

/**
 * The interactor for the login usecase.
 */
public class LoginInteractor implements LoginInputBoundary {
    private static final int LIMIT = 9;

    private final LoginDataAccessInterface userDataAccessObject;
    private final RandomRecipeDataAccessInterface cocktailDataAccessObject;
    private final LoginOutputBoundary loginPresenter;

    public LoginInteractor(LoginDataAccessInterface userDataAccessObject,
                           RandomRecipeDataAccessInterface cocktailDataAccessObject,
                           LoginOutputBoundary loginPresenter) {
        this.userDataAccessObject = userDataAccessObject;
        this.cocktailDataAccessObject = cocktailDataAccessObject;
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
                if (user == null) {
                    loginPresenter.prepareFailView(username + ": User not found.");
                }
                else {
                    userDataAccessObject.setCurrentUser(user.getName());
                    final List<Recipe> randomRecipes = cocktailDataAccessObject.getRandomRecipes(LIMIT);
                    final List<Integer> bookmarkedRecipeIds = userDataAccessObject.getBookmarkedRecipes(username);
                    final List<Recipe> bookmarkedRecipes = cocktailDataAccessObject.getRecipesByIdList(bookmarkedRecipeIds);

                    final List<Integer> ingredientsToAvoid = userDataAccessObject.getIngredientsToAvoid(username);
                    final LoginOutputData outputData = new LoginOutputData(
                            username,
                            ingredientsToAvoid,
                            randomRecipes,
                            bookmarkedRecipes,
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
    }

    @Override
    public void switchToSignupView() {
        loginPresenter.switchToSignupView();
    }
}
