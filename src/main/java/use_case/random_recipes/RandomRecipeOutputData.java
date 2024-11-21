package use_case.random_recipes;

import entities.recipe.Recipe;

import java.util.List;

/**
 * Output data for random recipe usecase.
 */
public class RandomRecipeOutputData {
    private final List<Recipe> recipes;

    public RandomRecipeOutputData(List<Recipe> recipes) {
        this.recipes = recipes;
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }
}
