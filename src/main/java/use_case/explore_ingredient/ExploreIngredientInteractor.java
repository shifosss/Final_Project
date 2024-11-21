package use_case.explore_ingredient;

import java.util.List;

import entities.recipe.Ingredient;

/**
 * The Explore Ingredient interactor.
 */
public class ExploreIngredientInteractor implements ExploreIngredientInputBoundary {
    private final ExploreIngredientDataAccessInterface ingredientDataAccessObject;
    private final ExploreIngredientOutputBoundary ingredientPresenter;

    public ExploreIngredientInteractor(ExploreIngredientDataAccessInterface ingredientDataAccessObject,
                                       ExploreIngredientOutputBoundary ingredientPresenter) {
        this.ingredientDataAccessObject = ingredientDataAccessObject;
        this.ingredientPresenter = ingredientPresenter;
    }

    @Override
    public void execute(ExploreIngredientInputData exploreIngredientInputData) {
        final String query = exploreIngredientInputData.getQuery();

        final List<Ingredient> ingredientResults = ingredientDataAccessObject.exploreRecipeByIngredients(query);
        // check if the list is empty
        if (ingredientResults.isEmpty()) {
            ingredientPresenter.prepareFailView("Search does not match any ingredient.");
        }
        else {
            final ExploreIngredientOutputData recipeOutputData = new ExploreIngredientOutputData(
                    ingredientResults,
                    false
            );
            ingredientPresenter.prepareSuccessView(recipeOutputData);
        }
    }
}
