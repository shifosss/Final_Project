package use_case.search_recipes;

import java.util.List;

/**
 * The Input data for searching recipe use case.
 */
public class SearchRecipeInputData {
    private final String searchQuery;
    private final List<Integer> recipeIds;

    public SearchRecipeInputData(String searchQuery, List<Integer> recipeIds) {
        this.searchQuery = searchQuery;
        this.recipeIds = recipeIds;
    }

    String getSearchQuery() {
        return searchQuery;
    }

    public List<Integer> getRecipes() {
        return recipeIds;
    }
}
