package use_case.view_recipe;

import entities.recipe.Recipe;

/**
 * The Recipe Detail Interactor such that it processes the input data.
 * Which will be sanity checked and prepare the appropriate presenter.
 */
public class ViewRecipeInteractor implements ViewRecipeInputBoundary {
    private final ViewRecipeDataAccessInterface recipeDetailDataAccessObject;
    private final ViewRecipeOutputBoundary recipeDetailPresenter;

    public ViewRecipeInteractor(ViewRecipeDataAccessInterface recipeDetailDataAccessObject,
                                ViewRecipeOutputBoundary recipeDetailPresenter) {
        this.recipeDetailDataAccessObject = recipeDetailDataAccessObject;
        this.recipeDetailPresenter = recipeDetailPresenter;
    }

    @Override
    public void execute(ViewRecipeInputData recipeDetailInputData) {
        // TODO: Double check this after dao is finished.
        final int recipeId = recipeDetailInputData.getId();

        final Recipe recipe = recipeDetailDataAccessObject.getRecipeById(recipeId);

        if (recipe == null) {
            final ViewRecipeOutputData recipeDetailOutputData = new ViewRecipeOutputData(
                    recipe,
                    true
            );
            recipeDetailPresenter.prepareFailView(recipeDetailOutputData, "Recipe not found.");
        }
        else {
            final ViewRecipeOutputData recipeDetailOutputData = new ViewRecipeOutputData(
                    recipe,
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
        // TODO: implement this in dao
        // recipeDetailDataAccessObject.bookmarkRecipe();
    }

}
