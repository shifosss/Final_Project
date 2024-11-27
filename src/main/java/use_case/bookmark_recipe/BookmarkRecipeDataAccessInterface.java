package use_case.bookmark_recipe;

import java.util.List;

/**
 * DAO for the bookmark recipe usecase.
 */
public interface BookmarkRecipeDataAccessInterface {

    /**
     * Checks if the user has bookmarked the recipe.
     * @param recipeId the id of the recipe to be checked.
     * @param username the username of the User.
     * @return true if the user has bookmarked the recipe. Otherwise, return false.
     */
    boolean isBookmarked(String username, int recipeId);

    /**
     * Makes the user toggle the state of bookmark based of the given recipe id.
     * @param username the username.
     * @param recipeId the id of recipe.
     */
    void bookmarkRecipe(String username, int recipeId);

    /**
     * Gets the current signed-in user.
     * @return the username of the user.
     */
    String getCurrentUser();

    /**
     * Returns the list of recipes bookmarked by the user.
     * @param username the username of the user.
     * @return list of recipe ids.
     */
    List<Integer> getBookmarkedRecipes(String username);
}
