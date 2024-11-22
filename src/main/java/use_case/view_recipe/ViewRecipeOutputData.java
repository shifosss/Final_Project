package use_case.view_recipe;

import entities.recipe.CocktailRecipe;
import entities.recipe.Recipe;

/**
 * Recipe detail output data that contains information after execution.
 */
public class ViewRecipeOutputData {
    private final CocktailRecipe recipe;
    private final boolean useCaseFailed;

    public ViewRecipeOutputData(CocktailRecipe recipe, boolean useCaseFailed) {
        this.recipe = recipe;
        this.useCaseFailed = useCaseFailed;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }
}
