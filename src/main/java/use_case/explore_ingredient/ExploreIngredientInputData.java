package use_case.explore_ingredient;

/**
 * The Input data for searching recipe use case.
 */
public class ExploreIngredientInputData {
    private final String searchQuery;

    public ExploreIngredientInputData(String searchQuery) {
        this.searchQuery = searchQuery;
    }

    String getSearchQuery() {
        return searchQuery;
    }
}
