package use_case.explore_ingredient;

import java.util.List;

import entities.recipe.Ingredient;
import entities.recipe.Recipe;

public interface ExploreIngredientDataAccessInterface {
    /**
     * Searches for recipes that contain the specified ingredient.
     * @param ingredient ingredient name to search for
     * @return a list of recipes containing the ingredient
     */
    List<Recipe> exploreRecipeByIngredients(String ingredient);

    /**
     * Gets the list of all available ingredients.
     * @return a list of all ingredients
     */
    List<Ingredient> getIngredientsList();
}