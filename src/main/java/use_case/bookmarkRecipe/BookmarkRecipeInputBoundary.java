package use_case.bookmarkRecipe;

/**
 * Input Boundary for the bookmark recipe usecase.
 */
public interface BookmarkRecipeInputBoundary {

    /**
     * Bookmarks the recipe given the inputs.
     * @param bookmarkRecipeInputData the input data.
     */
    void bookmarkRecipe(BookmarkRecipeInputData bookmarkRecipeInputData);
}
