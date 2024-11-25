package use_case.view_recipe;

import entities.recipe.Recipe;

import java.util.List;

/**
 * Recipe detail output data that contains information after execution.
 */
public class ViewRecipeOutputData {
    private final Recipe recipe;
    private final boolean isBookmarked;
    private final boolean useCaseFailed;

    public ViewRecipeOutputData(Recipe recipe, boolean isBookmarked, boolean useCaseFailed) {
        this.recipe = recipe;
        this.isBookmarked = isBookmarked;
        this.useCaseFailed = useCaseFailed;
    }

    public boolean isBookmarked() {
        return isBookmarked;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }
}
