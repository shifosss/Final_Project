package use_case.explore_ingredient;

/**
 * The Input data for exploring ingredient use case.
 */
public class ExploreIngredientInputData {
    private final String ingredientName;

    public ExploreIngredientInputData(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    String getIngredientName() {
        return ingredientName;
    }
}
