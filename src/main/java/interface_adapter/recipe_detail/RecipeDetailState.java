package interface_adapter.recipe_detail;

import entities.recipe.CocktailRecipe;
import entities.recipe.Recipe;


/**
 * The state information regarding the recipe detail use case.
 */
public class RecipeDetailState {
    private CocktailRecipe recipe;

    public RecipeDetailState(RecipeDetailState copy) {
        this.recipe = copy.recipe;
    }

    public RecipeDetailState() {
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(CocktailRecipe recipe) {
        this.recipe = recipe;
    }
}
