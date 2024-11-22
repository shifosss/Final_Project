package interface_adapter.custom_recipe;

import entities.recipe.Recipe;

/**
 * State of the custom recipe view.
 */
public class CustomRecipeState {
    private Recipe recipe;

    public CustomRecipeState(CustomRecipeState copy) {
        this.recipe = copy.recipe;
    }

    public CustomRecipeState() {
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }
}
