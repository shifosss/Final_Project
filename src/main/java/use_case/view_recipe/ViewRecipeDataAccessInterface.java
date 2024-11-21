package use_case.view_recipe;

import entities.recipe.Recipe;

/**
 * Recipe Detail's Data Access Object.
 */
public interface ViewRecipeDataAccessInterface {

    Recipe getRecipeById(int id);
}
