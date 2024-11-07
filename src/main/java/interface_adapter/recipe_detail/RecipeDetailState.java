package interface_adapter.recipe_detail;

import entities.recipe.Recipe;


/**
 * The state information regarding the recipe detail use case.
 */
public class RecipeDetailState {
    private int id;
    private Recipe recipe;

    public RecipeDetailState(RecipeDetailState copy) {
        this.id = copy.id;
        this.recipe = copy.recipe;
    }

    public RecipeDetailState() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }
}
