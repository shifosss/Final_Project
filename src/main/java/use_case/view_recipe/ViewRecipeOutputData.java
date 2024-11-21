package use_case.view_recipe;

import entities.recipe.Recipe;

/**
 * Recipe detail output data that contains information after execution.
 */
public class ViewRecipeOutputData {
    private final Recipe recipe;
    private final boolean useCaseFailed;

    public ViewRecipeOutputData(Recipe recipe, boolean useCaseFailed) {
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
