package use_case.search_recipes;

import entities.recipe.Recipe;

import java.util.List;

/**
 * The Input data for searching recipe use case.
 */
public class SearchRecipeInputData {
    private final String searchQuery;
    private final List<Recipe> recipes;

    public SearchRecipeInputData(String searchQuery, List<Recipe> recipes) {
        this.searchQuery = searchQuery;
        this.recipes = recipes;
    }

    String getSearchQuery() {
        return searchQuery;
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }
}
