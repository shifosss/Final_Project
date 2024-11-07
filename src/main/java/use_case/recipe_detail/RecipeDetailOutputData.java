package use_case.recipe_detail;

import entities.recipe.Recipe;

/**
 * Recipe detail output data that contains information after execution.
 */
public class RecipeDetailOutputData {
    private final Recipe recipe;
    private final boolean useCaseFailed;

    public RecipeDetailOutputData(Recipe recipe, boolean useCaseFailed) {
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
