package use_case.explore_ingredient;

/**
 * Input boundary for the explore ingredient usecase.
 */
public interface ExploreIngredientInputBoundary {

    /**
     * Executes so that a list of recipes containing this ingredient shows up.
     * @param exploreIngredientInputData the ingredient input data.
     */
    void switchToRecipes(ExploreIngredientInputData exploreIngredientInputData);

    /**
     * Switches to the explore ingredients.
     */
    void switchToExploreIngredients();
}