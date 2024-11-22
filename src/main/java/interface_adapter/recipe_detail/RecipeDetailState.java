package interface_adapter.recipe_detail;

import entities.recipe.Recipe;


/**
 * The state information regarding the recipe detail use case.
 */
public class RecipeDetailState {
    private Recipe recipe;
    private boolean isBookmarked;

    public RecipeDetailState(RecipeDetailState copy) {
        this.recipe = copy.recipe;
    }

    public RecipeDetailState() {
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public void setIsBookmarked(boolean bookmarked) {
        isBookmarked = bookmarked;
    }

    public boolean getIsBookmarked() {
        return isBookmarked;
    }
}
