package use_case.create_recipe;

import entities.recipe.Recipe;

import java.util.List;

/**
 * DAO for the creation of custom recipe usecase.
 */
public interface CustomRecipeDataAccessInterface {

    /**
     * Creates the custom recipe created by the user and links it from the user collection.
     * @param username the username of the user.
     * @param recipe the custom recipe to be saved into user.
     */
    void createCustomRecipe(String username, Recipe recipe);

    /**
     * Removes the custom recipe associated with the given id from the user.
     * Also removes the recipe from the recipe collection.
     * @param username the username of the user.
     * @param id the custom recipe id to be removed.
     */
    void removeCustomRecipe(String username, int id);

    /**
     * Gets the custom recipes created by the user.
     * @param username the username of the user.
     * @return a list of Recipes created by the user.
     */
    List<Recipe> getCustomRecipes(String username);
}
