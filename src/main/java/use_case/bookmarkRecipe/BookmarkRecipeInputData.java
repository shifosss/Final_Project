package use_case.bookmarkRecipe;

/**
 * Bookmark input data.
 */
public class BookmarkRecipeInputData {
    private final int recipeId;

    public BookmarkRecipeInputData(int recipeId) {
        this.recipeId = recipeId;
    }

    public int getRecipeId() {
        return recipeId;
    }
}
