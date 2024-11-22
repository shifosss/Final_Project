package use_case.explore_ingredient;

import entities.recipe.Ingredient;

import java.util.List;

/**
 * Output Boundary for the explore ingredients.
 */
public interface ExploreIngredientOutputBoundary {
    /**
     * Prepares view for showing the list of all available ingredients
     */
    void prepareIngredientsListView(ExploreIngredientOutputData outputData);

    /**
     * Prepares view for error cases
     */
    void prepareFailView(String error);
}