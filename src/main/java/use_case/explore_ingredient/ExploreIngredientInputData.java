package use_case.explore_ingredient;

/**
 * The Input data for exploring ingredient use case.
 */
public class ExploreIngredientInputData {
    private final String searchQuery;

    public ExploreIngredientInputData(String searchQuery) {
        this.searchQuery = searchQuery;
    }

    String getQuery() {
        return searchQuery;
    }
}
