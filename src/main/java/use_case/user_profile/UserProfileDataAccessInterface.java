package use_case.user_profile;

import entities.recipe.Recipe;

/**
 * DAO for the user profile usecase.
 */
public interface UserProfileDataAccessInterface {
    /**
     * Gets the recipe from the user database.
     * @param id the recipe id.
     * @return an instance of recipe.
     */
    Recipe getRecipeById(int id);
}
