package api;

import entity.Recipe;

/**
 * RecipeDatabase that MealsDatabase and CocktailsDatabase must implement.
 * Reason being is that: both have similar api structure.
 */
public interface RecipeDatabase {
    /**
     * Return an array of categories available.
     * @return array of categories.
     */
    String[] getCategories();

    /**
     * Return an array of ingredients available.
     * @return array of ingredients.
     */
    String[] getIngredients();

    /**
     * Searches recipe.
     * @param query String provided by user.
     * @return array of Recipe.
     */
    Recipe[] searchRecipe(String query);
}
