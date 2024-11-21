package use_case.explore_ingredient;

import entities.recipe.Ingredient;

import java.util.List;

public interface ExploreIngredientOutputBoundary {
    /**
     * Prepares view for showing the list of all available ingredients
     */
    void prepareIngredientsListView(List<Ingredient> ingredients);

    /**
     * Prepares view for showing recipes containing a specific ingredient
     */
    void prepareRecipeListView(ExploreIngredientOutputData outputData);

    /**
     * Prepares view for error cases
     */
    void prepareFailView(String error);
}