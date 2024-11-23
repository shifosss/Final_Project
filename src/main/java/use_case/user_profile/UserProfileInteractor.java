package use_case.user_profile;

import entities.recipe.Recipe;
import use_case.create_recipe.CustomRecipeDataAccessInterface;

import java.util.List;

/**
 * Interactor for the user profile usecase.
 */
public class UserProfileInteractor implements UserProfileInputBoundary {
    private final UserProfileOutputBoundary userProfilePresenter;
    private final CustomRecipeDataAccessInterface customRecipeDataAccessObject;

    public UserProfileInteractor(UserProfileOutputBoundary userProfilePresenter,
                                 CustomRecipeDataAccessInterface customRecipeDataAccessObject) {
        this.userProfilePresenter = userProfilePresenter;
        this.customRecipeDataAccessObject = customRecipeDataAccessObject;
    }

    @Override
    public void switchToUserView(UserProfileInputData userProfileInputData) {
        final String username = userProfileInputData.getUsername();
        if (!customRecipeDataAccessObject.existsByName(username)) {
            userProfilePresenter.presentUserNotFound("User does not exist", username);
        }
        final List<Recipe> createdRecipes = customRecipeDataAccessObject.getCustomRecipes(username);

        userProfilePresenter.switchToProfileView(new UserProfileOutputData(
                username, createdRecipes));
    }

    @Override
    public void switchToHomePage() {
        userProfilePresenter.switchToHomeView();
    }
}
