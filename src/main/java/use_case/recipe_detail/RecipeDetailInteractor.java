package use_case.recipe_detail;

import entities.recipe.Recipe;

/**
 * The Recipe Detail Interactor such that it processes the input data.
 * Which will be sanity checked and prepare the appropriate presenter.
 */
public class RecipeDetailInteractor implements RecipeDetailInputBoundary {
    private final RecipeDetailDataAccessInterface recipeDetailDataAccessObject;
    private final RecipeDetailOutputBoundary recipeDetailPresenter;

    public RecipeDetailInteractor(RecipeDetailDataAccessInterface recipeDetailDataAccessObject,
                                  RecipeDetailOutputBoundary recipeDetailPresenter) {
        this.recipeDetailDataAccessObject = recipeDetailDataAccessObject;
        this.recipeDetailPresenter = recipeDetailPresenter;
    }

    @Override
    public void execute(RecipeDetailInputData recipeDetailInputData) {
        // TODO: Double check this after dao is finished.
    }

    @Override
    public void switchToSearchView() {
        recipeDetailPresenter.switchToSearchRecipeView();
    }

}
