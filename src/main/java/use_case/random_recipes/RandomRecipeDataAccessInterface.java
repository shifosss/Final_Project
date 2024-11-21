package use_case.random_recipes;

import entities.recipe.Recipe;

import java.util.List;

/**
 * DAO for the random recipe usecase.
 */
public interface RandomRecipeDataAccessInterface {

    /**
     * Searches for random recipes at most given limit.
     * @param limit the number of recipes to be searched.
     * @return a list of random recipes.
     */
    List<Recipe> getRandomRecipes(int limit);
}
