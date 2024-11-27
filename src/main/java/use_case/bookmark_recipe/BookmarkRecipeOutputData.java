package use_case.bookmark_recipe;

import entities.recipe.Recipe;

import java.util.List;

/**
 * Output data after bookmark or un-bookmarking a recipe.
 */
public class BookmarkRecipeOutputData {
    private final List<Recipe> bookmarkedRecipes;
    private final boolean isBookmarked;

    public BookmarkRecipeOutputData(List<Recipe> bookmarkedRecipes, boolean isBookmarked) {
        this.bookmarkedRecipes = bookmarkedRecipes;
        this.isBookmarked = isBookmarked;
    }

    public List<Recipe> getBookmarkedRecipes() {
        return bookmarkedRecipes;
    }

    public boolean isBookmarked() {
        return isBookmarked;
    }
}
