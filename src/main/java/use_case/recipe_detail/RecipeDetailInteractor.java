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
        final int id = recipeDetailInputData.getId();

        final Recipe recipe = recipeDetailDataAccessObject.getRecipeById(id);

        if (recipe == null) {
            final RecipeDetailOutputData recipeDetailOutputData = new RecipeDetailOutputData(
                    recipe,
                    true
            );
            recipeDetailPresenter.prepareFailView(
                    recipeDetailOutputData,
                    "Recipe id not found");
        }
        else {
            // then recipe exists
            final RecipeDetailOutputData recipeDetailOutputData = new RecipeDetailOutputData(
                    recipe,
                    false
            );
            recipeDetailPresenter.prepareSuccessView(
                    recipeDetailOutputData
            );
        }
    }
}
