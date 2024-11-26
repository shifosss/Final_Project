package use_case.explore_ingredient;

import java.util.List;

import entities.recipe.Recipe;
import entities.recipe.SimpleRecipe;
import entities.recipe.Ingredient;
import interface_adapter.explore_ingredient.ExploreIngredientPresenter;
import use_case.search_recipes.SearchRecipeInputBoundary;
import use_case.search_recipes.SearchRecipeInputData;
import use_case.search_recipes.SearchRecipeOutputBoundary;
import use_case.search_recipes.SearchRecipeOutputData;
import use_case.view_recipe.ViewRecipeInputData;

/**
 * Interactor for the explore ingredient usecase.
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
    public void switchToRecipes(ExploreIngredientInputData exploreIngredientInputData) {
        final String ingredient = exploreIngredientInputData.getIngredientName();

        final List<Recipe> recipeResults = ingredientDataAccessObject.exploreRecipeByIngredients(ingredient);
        if (recipeResults.isEmpty()) {
            ingredientPresenter.prepareFailView("No recipes found with this ingredient.");
        }
        else {
            final SearchRecipeOutputData outputData = new SearchRecipeOutputData(
                    "",
                    recipeResults,
                    false
            );
            ingredientPresenter.prepareSuccessView(outputData);
        }
    }

    @Override
    public void switchToExploreIngredients() {
        final List<String> ingredientList = ingredientDataAccessObject.getIngredientsList();

        final ExploreIngredientOutputData outputData = new ExploreIngredientOutputData(
                ingredientList, false);
        ingredientPresenter.prepareIngredientsListView(outputData);
    }

    @Override
    public void switchToHome() {
        ingredientPresenter.switchToHome();
    }
}