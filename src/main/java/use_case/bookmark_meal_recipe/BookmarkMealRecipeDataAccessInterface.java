package use_case.bookmark_meal_recipe;

public interface BookmarkMealRecipeDataAccessInterface {

    /**
     * Checks if the user has bookmarked the recipe.
     * @param meal_recipe_Id the id of the recipe to be checked.
     * @param username the username of the User.
     * @return true if the user has bookmarked the recipe. Otherwise, return false.
     */
    boolean isBookmarked(String username, String meal_recipe_Id);

    /**
     * Makes the user toggle the state of bookmark based of the given recipe id.
     * @param username the username.
     * @param meal_recipe_Id the id of recipe.
     */
    void bookmarkRecipe(String username, String meal_recipe_Id);

    /**
     * Gets the current signed-in user.
     * @return the username of the user.
     */
    String getCurrentUser();
}
