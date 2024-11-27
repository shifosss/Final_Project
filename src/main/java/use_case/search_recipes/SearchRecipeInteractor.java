package use_case.search_recipes;

import java.util.List;

import entities.recipe.Recipe;
import interface_adapter.search_recipe.SearchRecipePresenter;
import use_case.bookmarkRecipe.BookmarkRecipeDataAccessInterface;
import use_case.view_recipe.ViewRecipeInputData;
import use_case.view_recipe.ViewRecipeOutputData;

/**
 * The Search Recipe interactor.
 */
public class SearchRecipeInteractor implements SearchRecipeInputBoundary {
    private final SearchRecipeDataAccessInterface recipeDataAccessObject;
    private final BookmarkRecipeDataAccessInterface bookmarkRecipeDataAccessObject;
    private final SearchRecipePresenter recipePresenter;

    public SearchRecipeInteractor(SearchRecipeDataAccessInterface recipeDataAccessObject,
                                  BookmarkRecipeDataAccessInterface bookmarkRecipeDataAccessObject,
                                  SearchRecipeOutputBoundary recipePresenter) {
        this.recipeDataAccessObject = recipeDataAccessObject;
        this.bookmarkRecipeDataAccessObject = bookmarkRecipeDataAccessObject;
        this.recipePresenter = (SearchRecipePresenter) recipePresenter;
    }

    @Override
    public void execute(SearchRecipeInputData searchRecipeInputData) {
        final String query = searchRecipeInputData.getSearchQuery();
        final String username = bookmarkRecipeDataAccessObject.getCurrentUser();
        final List<String> ingredientsToAvoid = bookmarkRecipeDataAccessObject.getIngredientsToAvoid(username);

        final List<Recipe> recipeResults = recipeDataAccessObject.searchRecipeByKeyword(
                query, ingredientsToAvoid);
        if (recipeResults.isEmpty()) {
            final SearchRecipeOutputData recipeOutputData = new SearchRecipeOutputData(
                    query,
                    recipeResults,
                    true
            );
            recipePresenter.prepareFailView(
                    recipeOutputData,
                    "Search does not match any recipes.");
        }
        else {
            final SearchRecipeOutputData recipeOutputData = new SearchRecipeOutputData(
                    query,
                    recipeResults,
                    false
            );
            recipePresenter.prepareSuccessView(recipeOutputData);
        }

    }

    @Override
    public void execute(ViewRecipeInputData recipeDetailInputData) {
        final int recipeId = recipeDetailInputData.getId();
        final String currentUser = bookmarkRecipeDataAccessObject.getCurrentUser();
        final boolean isBookmarked = bookmarkRecipeDataAccessObject.isBookmarked(currentUser, recipeId);

        final Recipe recipe = recipeDataAccessObject.getRecipeById(recipeId);

        if (recipe == null) {
            final ViewRecipeOutputData recipeDetailOutputData = new ViewRecipeOutputData(
                    recipe,
                    isBookmarked,
                    true
            );
            recipePresenter.prepareFailView(recipeDetailOutputData, "Recipe not found.");
        }
        else {
            final ViewRecipeOutputData recipeDetailOutputData = new ViewRecipeOutputData(
                    recipe,
                    isBookmarked,
                    false
            );
            recipePresenter.prepareSuccessView(recipeDetailOutputData);
        }
    }

    @Override
    public void switchToHomePageView(SearchRecipeInputData searchRecipeInputData) {
        recipePresenter.switchToHomePageView();
    }
}
