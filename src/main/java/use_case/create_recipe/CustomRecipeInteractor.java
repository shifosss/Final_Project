package use_case.create_recipe;

import entities.recipe.Recipe;
import use_case.random_recipes.RandomRecipeDataAccessInterface;
import use_case.user_profile.UserProfileOutputData;

import java.util.List;

/**
 * Interactor for the custom recipe usecase.
 */
public class CustomRecipeInteractor implements CustomRecipeInputBoundary {

    private final CustomRecipeDataAccessInterface customRecipeDataAccessObject;
    private final CustomRecipeOutputBoundary customRecipePresenter;

    public CustomRecipeInteractor(CustomRecipeDataAccessInterface customRecipeDataAccessObject,
                                  CustomRecipeOutputBoundary customRecipePresenter) {
        this.customRecipeDataAccessObject = customRecipeDataAccessObject;
        this.customRecipePresenter = customRecipePresenter;
    }

    @Override
    public void switchToHomePageView() {
        customRecipePresenter.switchToHomePageView();
    }

    @Override
    public void switchToCustomRecipeView() {
        customRecipePresenter.switchToRecipeCreationView();
    }

    @Override
    public void saveCustomRecipe(CustomRecipeInputData inputData) {
        customRecipeDataAccessObject.createCustomRecipe(
                customRecipeDataAccessObject.getCurrentUser(),
                inputData.getRecipeName(),
                inputData.getRecipeInstruction(),
                inputData.getIngredients(),
                inputData.getMeasurements(),
                inputData.getIsAlcoholic()
        );
        final String username = customRecipeDataAccessObject.getCurrentUser();
        final List<Recipe> customRecipes = customRecipeDataAccessObject.getCustomRecipes(username);
        final UserProfileOutputData outputData = new UserProfileOutputData(username, customRecipes);
        customRecipePresenter.updateCustomRecipeView(outputData);
    }
}
