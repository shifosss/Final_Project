package use_case.login;

import java.util.List;

import entities.recipe.Recipe;

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

    /**
     * Returns recipes associated with ids.
     * @param bookmarkedRecipeIds a list of recipe ids.
     * @return the list of recipe.
     */
    List<Recipe> getRecipesByIdList(List<Integer> bookmarkedRecipeIds);

    /**
     * Returns the list of ingredient names.
     * @return list of ingredients.
     */
    List<String> getIngredientsList();
}
