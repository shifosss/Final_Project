package use_case.explore_ingredient;

import java.util.List;

import entities.recipe.Recipe;
import entities.recipe.Ingredient;

public interface ExploreIngredientDataAccessInterface {
    /**
     * Gets a simplified list of recipes containing the specified ingredient.
     * Returns only name, id, and image for better performance.
     * @param ingredient ingredient to search for
     * @return list of simplified recipes
     */
    List<Recipe> exploreRecipeByIngredients(String ingredient);

    /**
     * Gets the list of available ingredients.
     * @return list of ingredients
     */
    List<Ingredient> getIngredientsList();
}