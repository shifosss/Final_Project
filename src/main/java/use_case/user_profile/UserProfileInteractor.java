package use_case.user_profile;

import java.util.List;

import entities.recipe.Recipe;
import use_case.create_recipe.CustomRecipeDataAccessInterface;
import use_case.view_recipe.ViewRecipeInputData;
import use_case.view_recipe.ViewRecipeOutputData;

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
    public void switchToUserView() {
        final String username = customRecipeDataAccessObject.getCurrentUser();
        if (!customRecipeDataAccessObject.existsByName(username)) {
            userProfilePresenter.presentUserNotFound("User does not exist", username);
        }
        final List<Recipe> createdRecipes = customRecipeDataAccessObject.getCustomRecipes(username);

        final UserProfileOutputData outputData = new UserProfileOutputData(
                username, createdRecipes);
        userProfilePresenter.switchToProfileView(outputData);
    }

    @Override
    public void switchToHomePageView() {
        userProfilePresenter.switchToHomePageView();
    }

    @Override
    public void viewRecipeDetail(ViewRecipeInputData inputData) {
        final int recipeId = inputData.getId();
        final Recipe recipe = customRecipeDataAccessObject.getRecipeById(recipeId);
        final String username = customRecipeDataAccessObject.getCurrentUser();
        final ViewRecipeOutputData outputData = new ViewRecipeOutputData(
                recipe,
                customRecipeDataAccessObject.isBookmarked(username, recipeId),
                false
        );
        userProfilePresenter.prepareSuccessView(outputData);
    }
}
