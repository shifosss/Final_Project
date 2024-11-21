package use_case.explore_ingredient;

import java.util.List;

import entities.recipe.Ingredient;
import entities.recipe.Recipe;

/**
 * Data Access Object for the search recipe usecase.
 */
public interface ExploreIngredientDataAccessInterface {
    /**
     * Searches for recipes that matches the keyword.
     * @param ingredient user query.
     * @return a list of recipe.
     */
    List<Recipe> exploreRecipeByIngredients(String ingredient);

    List<Ingredient> getIngredientsList();
}