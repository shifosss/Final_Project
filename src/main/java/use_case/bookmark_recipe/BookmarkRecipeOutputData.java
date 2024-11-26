package use_case.bookmark_recipe;

import entities.recipe.Recipe;

import java.util.List;

/**
 * Output data after bookmark or un-bookmarking a recipe.
 */
public class BookmarkRecipeOutputData {
    private final List<Recipe> bookmarkedRecipes;

    public BookmarkRecipeOutputData(List<Recipe> bookmarkedRecipes) {
        this.bookmarkedRecipes = bookmarkedRecipes;
    }

    public List<Recipe> getBookmarkedRecipes() {
        return bookmarkedRecipes;
    }
}
