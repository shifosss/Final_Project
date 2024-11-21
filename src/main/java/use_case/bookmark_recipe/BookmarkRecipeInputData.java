package use_case.bookmark_recipe;

/**
 * Bookmark input data.
 */
public class BookmarkRecipeInputData {
    private final String username;
    private final int recipeId;

    public BookmarkRecipeInputData(String username, int recipeId) {
        this.username = username;
        this.recipeId = recipeId;
    }

    public String getUsername() {
        return username;
    }

    public int getRecipeId() {
        return recipeId;
    }
}
