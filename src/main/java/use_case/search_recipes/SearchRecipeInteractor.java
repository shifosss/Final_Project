package use_case.search_recipes;

import java.util.List;

import entities.recipe.Recipe;
import use_case.recipe_detail.RecipeDetailInputData;
import use_case.recipe_detail.RecipeDetailOutputData;

/**
 * The Search Recipe interactor.
 */
public class SearchRecipeInteractor implements SearchRecipeInputBoundary {
    private final SearchRecipeDataAccessInterface recipeDataAccessObject;
    private final SearchRecipeOutputBoundary recipePresenter;

    public SearchRecipeInteractor(SearchRecipeDataAccessInterface recipeDataAccessObject,
                                  SearchRecipeOutputBoundary recipePresenter) {
        this.recipeDataAccessObject = recipeDataAccessObject;
        this.recipePresenter = recipePresenter;
    }

    @Override
    public void execute(SearchRecipeInputData searchRecipeInputData) {
        final String query = searchRecipeInputData.getSearchQuery();

        final List<Recipe> recipeResults = recipeDataAccessObject.searchRecipeByKeyword(query);
        // check if the list is empty
        if (recipeResults.isEmpty()) {
            final SearchRecipeOutputData recipeOutputData = new SearchRecipeOutputData(
                    recipeResults,
                    true
            );
            recipePresenter.prepareFailView(
                    recipeOutputData,
                    "Search does not match any recipes.");
        }
        else {
            final SearchRecipeOutputData recipeOutputData = new SearchRecipeOutputData(
                    recipeResults,
                    false
            );
            recipePresenter.prepareSuccessView(recipeOutputData);
        }

    }

    @Override
    public void switchToHomeView() {
        recipePresenter.switchToHomePageView();
    }

    @Override
    public void switchToRecipeDetailView(RecipeDetailInputData recipeDetailInputData) {
        final int recipeId = recipeDetailInputData.getId();

        final Recipe recipe = recipeDataAccessObject.getRecipeById(recipeId);

        if (recipe == null) {
            final RecipeDetailOutputData recipeDetailOutputData = new RecipeDetailOutputData(
                    recipe,
                    true
            );
            recipePresenter.prepareFailView(recipeDetailOutputData, "Recipe not found.");
        }
        else {
            final RecipeDetailOutputData recipeDetailOutputData = new RecipeDetailOutputData(
                    recipe,
                    false
            );
            recipePresenter.prepareSuccessView(recipeDetailOutputData);
        }
    }
}
