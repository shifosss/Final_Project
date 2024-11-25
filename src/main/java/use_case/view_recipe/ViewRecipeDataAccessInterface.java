package use_case.view_recipe;

import entities.recipe.Recipe;

/**
 * Recipe Detail's Data Access Object.
 */
public interface ViewRecipeDataAccessInterface {

    /**
     * Gets the recipe with id.
     * @param id the recipe id.
     * @return the recipe entity.
     */
    Recipe getRecipeById(int id);
}
