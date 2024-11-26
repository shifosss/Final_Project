package use_case.create_recipe;

import entities.recipe.Recipe;

import java.util.List;

/**
 * Output data for the custom recipe usecase.
 */
public class CustomRecipeOutputData {
    private final List<Recipe> customRecipes;

    public CustomRecipeOutputData(List<Recipe> customRecipes) {
        this.customRecipes = customRecipes;
    }

    public List<Recipe> getCustomRecipes() {
        return customRecipes;
    }
}
