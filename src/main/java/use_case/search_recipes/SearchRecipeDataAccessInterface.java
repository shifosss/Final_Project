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

    /**
     * Returns a recipe given the id input.
     * @param id id associated with recipe.
     * @return the recipe.
     */
    Recipe getRecipeById(int id);

    /**
     * Returns a list of recipes associated with each id.
     * @param bookmarkedIds the list of recipe ids.
     * @return a list of recipes.
     */
    List<Recipe> getRecipesByIdList(List<Integer> bookmarkedIds);
}
