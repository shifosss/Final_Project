package use_case.create_recipe;

import entities.recipe.Recipe;

/**
 * Input data for the custom recipe usecase.
 */
public class CustomRecipeInputData {
    private final Recipe recipe;

    public CustomRecipeInputData(Recipe recipe) {
        this.recipe = recipe;
    }

    public Recipe getRecipe() {
        return recipe;
    }
}
