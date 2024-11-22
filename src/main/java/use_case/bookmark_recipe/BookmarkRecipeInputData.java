package use_case.bookmark_recipe;

import entities.recipe.Recipe;

/**
 * Bookmark input data.
 */
public class BookmarkRecipeInputData {
    private final int recipeId;
    private final Recipe recipe;

    public BookmarkRecipeInputData(int recipeId, Recipe recipe) {
        this.recipeId = recipeId;
        this.recipe = recipe;
    }

    public int getRecipeId() {
        return recipeId;
    }

    public Recipe getRecipe() {
        return recipe;
    }
}
