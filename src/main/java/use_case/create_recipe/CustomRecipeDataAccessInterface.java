package use_case.create_recipe;

import entities.recipe.Recipe;

/**
 * DAO for the creation of custom recipe usecase.
 */
public interface CustomRecipeDataAccessInterface {
    void createCustomRecipe(Recipe recipe);

    void removeCustomRecipe(int id);

}
