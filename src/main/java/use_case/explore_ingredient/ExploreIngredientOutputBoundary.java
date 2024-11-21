package use_case.explore_ingredient;

public interface ExploreIngredientOutputBoundary {
    void prepareSuccessView(ExploreIngredientOutputData outputData);
    void prepareFailView(String error);
}