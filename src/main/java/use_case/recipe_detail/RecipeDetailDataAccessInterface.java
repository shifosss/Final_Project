package use_case.recipe_detail;

import entities.recipe.Recipe;

/**
 * Recipe Detail's Data Access Object.
 */
public interface RecipeDetailDataAccessInterface {
    /**
     * Returns a recipe given the id input.
     * @param id id associated with recipe.
     * @return the recipe.
     */
    Recipe getRecipeById(int id);
}
