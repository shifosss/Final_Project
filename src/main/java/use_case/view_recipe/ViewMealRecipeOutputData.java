package use_case.view_recipe;

import entities.recipe.MealRecipe;

/**
 * Recipe detail output data that contains information after execution.
 */
public class ViewMealRecipeOutputData {

    private final MealRecipe recipe;
    private final boolean is_Bookmarked;
    private final boolean use_Case_Failed;

    public ViewMealRecipeOutputData(MealRecipe recipe, boolean isBookmarked, boolean useCaseFailed) {
        this.recipe = recipe;
        this.is_Bookmarked = isBookmarked;
        this.use_Case_Failed = useCaseFailed;
    }

    public boolean isBookmarked() {
        return is_Bookmarked;
    }

    public MealRecipe getRecipe() {
        return recipe;
    }

    public boolean isUseCaseFailed() {
        return use_Case_Failed;
    }
}
