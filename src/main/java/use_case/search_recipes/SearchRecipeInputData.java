package use_case.search_recipes;

/**
 * The Input data for searching recipe use case.
 */
public class SearchRecipeInputData {
    private final String searchQuery;

    public SearchRecipeInputData(String searchQuery) {
        this.searchQuery = searchQuery;
    }

    String getSearchQuery() {
        return searchQuery;
    }
}
