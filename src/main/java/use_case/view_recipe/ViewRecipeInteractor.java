package use_case.view_recipe;

import entities.recipe.Recipe;
import exceptions.RecipeNotFound;
import use_case.bookmarkRecipe.BookmarkRecipeDataAccessInterface;

/**
 * The Recipe Detail Interactor such that it processes the input data.
 * Which will be sanity checked and prepare the appropriate presenter.
 */
public class ViewRecipeInteractor implements ViewRecipeInputBoundary {
    private final ViewRecipeDataAccessInterface recipeDetailDataAccessObject;
    private final BookmarkRecipeDataAccessInterface bookmarkRecipeDataAccessObject;
    private final ViewRecipeOutputBoundary recipeDetailPresenter;

    public ViewRecipeInteractor(ViewRecipeDataAccessInterface recipeDetailDataAccessObject,
                                BookmarkRecipeDataAccessInterface bookmarkRecipeDataAccessObject,
                                ViewRecipeOutputBoundary recipeDetailPresenter) {
        this.recipeDetailDataAccessObject = recipeDetailDataAccessObject;
        this.bookmarkRecipeDataAccessObject = bookmarkRecipeDataAccessObject;
        this.recipeDetailPresenter = recipeDetailPresenter;
    }

    @Override
    public void execute(ViewRecipeInputData recipeDetailInputData) {
        final int recipeId = recipeDetailInputData.getId();
        final String currentUser = bookmarkRecipeDataAccessObject.getCurrentUser();
        final boolean isBookmarked = bookmarkRecipeDataAccessObject.isBookmarked(
                currentUser, recipeId
        );

        final Recipe recipe = recipeDetailDataAccessObject.getRecipeById(recipeId);
        if (recipe == null) {
            final ViewRecipeOutputData recipeDetailOutputData = new ViewRecipeOutputData(
                    null,
                    isBookmarked,
                    true
            );
            recipeDetailPresenter.prepareFailView(recipeDetailOutputData, "Recipe not found.");
        }
        else {
            final ViewRecipeOutputData recipeDetailOutputData = new ViewRecipeOutputData(
                    recipe,
                    isBookmarked,
                    false
            );
            recipeDetailPresenter.prepareSuccessView(recipeDetailOutputData);
        }
    }

    @Override
    public void switchToSearchView() {
        recipeDetailPresenter.switchToSearchRecipeView();
    }

    @Override
    public void bookmarkRecipe(ViewRecipeInputData recipeDetailInputData) {
        final Recipe recipe = recipeDetailDataAccessObject.getRecipeById(recipeDetailInputData.getId());
        if (recipe == null) {
            throw new RecipeNotFound("Recipe not found.");
        }
        final String currentUser = bookmarkRecipeDataAccessObject.getCurrentUser();
        bookmarkRecipeDataAccessObject.bookmarkRecipe(
                currentUser, recipeDetailInputData.getId());

        final ViewRecipeOutputData recipeDetailOutputData = new ViewRecipeOutputData(
                recipe,
                bookmarkRecipeDataAccessObject.isBookmarked(currentUser, recipeDetailInputData.getId()),
                false);
        recipeDetailPresenter.prepareSuccessView(recipeDetailOutputData);
    }
}
