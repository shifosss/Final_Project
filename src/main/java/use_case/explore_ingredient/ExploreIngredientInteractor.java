package use_case.explore_ingredient;

import java.util.List;

import entities.recipe.Recipe;
import entities.recipe.Ingredient;

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

        final List<Recipe> recipeResults = ingredientDataAccessObject.exploreRecipeByIngredients(query);
        if (recipeResults.isEmpty()) {
            ingredientPresenter.prepareFailView("No recipes found with this ingredient.");
        }
        else {
            final ExploreIngredientOutputData recipeOutputData = new ExploreIngredientOutputData(
                    recipeResults,
                    false
            );
            ingredientPresenter.prepareRecipeListView(recipeOutputData);
        }
    }

    @Override
    public void loadIngredients() {
        final List<Ingredient> ingredients = ingredientDataAccessObject.getIngredientsList();
        if (ingredients.isEmpty()) {
            ingredientPresenter.prepareFailView("Failed to load ingredients.");
        } else {
            ingredientPresenter.prepareIngredientsListView(ingredients);
        }
    }
}