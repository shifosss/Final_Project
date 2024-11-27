package use_case.create_recipe;

import entities.recipe.Recipe;
import use_case.random_recipes.RandomRecipeDataAccessInterface;

import java.util.List;

/**
 * Interactor for the custom recipe usecase.
 */
public class CustomRecipeInteractor implements CustomRecipeInputBoundary {
    private static final int LIMIT = 9;

    private final CustomRecipeDataAccessInterface customRecipeDataAccessObject;
    private final CustomRecipeOutputBoundary customRecipePresenter;
    private final RandomRecipeDataAccessInterface randomRecipeDataAccessObject;

    public CustomRecipeInteractor(CustomRecipeDataAccessInterface customRecipeDataAccessObject,
                                  RandomRecipeDataAccessInterface randomRecipeDataAccessObject,
                                  CustomRecipeOutputBoundary customRecipePresenter) {
        this.customRecipeDataAccessObject = customRecipeDataAccessObject;
        this.randomRecipeDataAccessObject = randomRecipeDataAccessObject;
        this.customRecipePresenter = customRecipePresenter;
    }

    @Override
    public void switchToHomePage() {
        final String username = customRecipeDataAccessObject.getCurrentUser();

        final List<Integer> bookmarkedRecipeIds = customRecipeDataAccessObject.getBookmarkedRecipes(username);
        final List<Recipe> bookmarkedRecipes = randomRecipeDataAccessObject.getRecipesByIdList(bookmarkedRecipeIds);

        final List<Recipe> randomRecipes = randomRecipeDataAccessObject.getRandomRecipes(LIMIT);

        final CustomRecipeOutputData customRecipeOutputData = new CustomRecipeOutputData(
                bookmarkedRecipes, randomRecipes);
        customRecipePresenter.switchToHomeView(customRecipeOutputData);
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
    }
}
