package use_case.bookmark_recipe;

import entities.recipe.Recipe;
import use_case.random_recipes.RandomRecipeDataAccessInterface;
import use_case.search_recipes.SearchRecipeDataAccessInterface;
import use_case.view_recipe.ViewRecipeOutputBoundary;
import use_case.view_recipe.ViewRecipeOutputData;

import java.util.List;

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
        final Recipe recipe = bookmarkRecipeInputData.getRecipe();
        final int recipeId = bookmarkRecipeInputData.getRecipeId();

        bookmarkRecipeDataAccessObject.bookmarkRecipe(username, recipeId);

        final ViewRecipeOutputData outputData = new ViewRecipeOutputData(
                recipe,
                bookmarkRecipeDataAccessObject.isBookmarked(username, recipeId),
                false
        );
        viewRecipePresenter.prepareSuccessView(outputData);
    }
}
