package use_case.create_recipe;

import java.util.List;

import entities.recipe.Recipe;

/**
 * DAO for the creation of custom recipe usecase.
 */
public interface CustomRecipeDataAccessInterface {

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

    /**
     * Checks if the user exists within the database.
     * @param username the user.
     * @return true if user exists. Otherwise, return false.
     */
    boolean existsByName(String username);

    /**
     * Gets a list of recipe ids the user has bookmarked.
     * @param username the username.
     * @return the list of recipe ids.
     */
    List<Integer> getBookmarkedRecipes(String username);

    /**
     * Gets the current user of the app.
     * @return the username
     */
    String getCurrentUser();

    /**
     * Creates the custom recipe.
     * @param username the user username.
     * @param recipeName name of recipe.
     * @param recipeInstruction instruction recipe.
     * @param ingredients ingredient names
     * @param measurements measurement of ingredients
     * @param isAlcoholic alcohol state.
     */
    void createCustomRecipe(String username, String recipeName,
                            String recipeInstruction, List<String> ingredients, List<String> measurements,
                            String isAlcoholic);

    /**
     * Gets the custom recipe associated with the recipe id.
     * @param recipeId the recipe id.
     * @return a recipe entity.
     */
    Recipe getRecipeById(int recipeId);

    /**
     * Checks if the user has bookmarked the recipe.
     * @param username the username.
     * @param recipeId the recipe id.
     * @return true if the user bookmarked the recipe. Otherwise, return false.
     */
    boolean isBookmarked(String username, int recipeId);
}
