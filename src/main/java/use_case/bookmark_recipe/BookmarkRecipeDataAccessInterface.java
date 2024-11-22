package use_case.bookmark_recipe;

/**
 * DAO for the bookmark recipe usecase.
 */
public interface BookmarkRecipeDataAccessInterface {

    /**
     * Checks if the user has bookmarked the recipe.
     * @param recipeId the getId of the recipe to be checked.
     * @param username the username of the User.
     * @return true if the user has bookmarked the recipe. Otherwise, return false.
     */
    boolean isBookmarked(String username, int recipeId);

    /**
     * Makes the user toggle the state of bookmark based of the given recipe getId.
     * @param username the username.
     * @param recipeId the getId of recipe.
     */
    void bookmarkRecipe(String username, int recipeId);
}
