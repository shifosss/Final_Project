package domain.use_case.search_recipes;

import java.util.List;

import domain.entities.recipe.Recipe;

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
            recipePresenter.prepareFailView("Search does not match any recipes.");
        }
        else {
            final SearchRecipeOutputData recipeOutputData = new SearchRecipeOutputData(
                    recipeResults,
                    false
            );
            recipePresenter.prepareSuccessView(recipeOutputData);
        }

    }
}
