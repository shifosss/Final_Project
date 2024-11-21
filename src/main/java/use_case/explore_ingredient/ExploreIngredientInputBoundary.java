package use_case.explore_ingredient;

public interface ExploreIngredientInputBoundary {
    void execute(ExploreIngredientInputData exploreIngredientInputData);
    void loadIngredients();
}