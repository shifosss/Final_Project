package use_case.search_recipes;

import java.util.List;

import entities.recipe.Recipe;

/**
 * Data Access Object for the search recipe usecase.
 */
public interface SearchRecipeDataAccessInterface {
    /**
     * Searches for recipes that matches the keyword.
     * @param keyword user query.
     * @return a list of recipe.
     */
    List<Recipe> searchRecipeByKeyword(String keyword);

    Recipe searchRandomRecipe();
    // TODO: Add more methods here.
}
