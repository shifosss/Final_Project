package use_case.bookmark_recipe;

import java.util.List;

import entities.recipe.Recipe;
import use_case.search_recipes.SearchRecipeDataAccessInterface;
import use_case.view_recipe.ViewRecipeOutputBoundary;

/**
 * Interactor for the bookmark usecase.
 */
public class BookmarkRecipeInteractor implements BookmarkRecipeInputBoundary {
    private final BookmarkRecipeDataAccessInterface bookmarkRecipeDataAccessObject;
    private final SearchRecipeDataAccessInterface searchRecipeDataAccessObject;
    private final ViewRecipeOutputBoundary viewRecipePresenter;

    public BookmarkRecipeInteractor(BookmarkRecipeDataAccessInterface bookmarkRecipeDataAccessObject,
                                    SearchRecipeDataAccessInterface searchRecipeDataAccessObject,
                                    ViewRecipeOutputBoundary viewRecipePresenter) {
        this.bookmarkRecipeDataAccessObject = bookmarkRecipeDataAccessObject;
        this.searchRecipeDataAccessObject = searchRecipeDataAccessObject;
        this.viewRecipePresenter = viewRecipePresenter;
    }

    @Override
    public void bookmarkRecipe(BookmarkRecipeInputData bookmarkRecipeInputData) {
        final String username = bookmarkRecipeDataAccessObject.getCurrentUser();
        final int recipeId = bookmarkRecipeInputData.getRecipeId();
        bookmarkRecipeDataAccessObject.bookmarkRecipe(username, recipeId);

        final List<Integer> bookmarkedRecipeIds = bookmarkRecipeDataAccessObject.getBookmarkedRecipes(username);
        final List<Recipe> bookmarkedRecipes = searchRecipeDataAccessObject.getRecipesByIdList(bookmarkedRecipeIds);
        final BookmarkRecipeOutputData outputData = new BookmarkRecipeOutputData(
                bookmarkedRecipes, bookmarkRecipeDataAccessObject.isBookmarked(username, recipeId));

        viewRecipePresenter.updateBookmarksView(outputData);
    }
}
