package interface_adapter.explore_ingredient;

import use_case.explore_ingredient.ExploreIngredientInputBoundary;
import use_case.explore_ingredient.ExploreIngredientInputData;

public class ExploreIngredientController {
    private final ExploreIngredientInputBoundary exploreIngredientUseCaseInteractor;

    public ExploreIngredientController(ExploreIngredientInputBoundary exploreIngredientUseCaseInteractor) {
        this.exploreIngredientUseCaseInteractor = exploreIngredientUseCaseInteractor;
    }

    public void execute(String query) {
        ExploreIngredientInputData inputData = new ExploreIngredientInputData(query);
        exploreIngredientUseCaseInteractor.execute(inputData);
    }

    public void loadIngredients() {
        exploreIngredientUseCaseInteractor.loadIngredients();
    }
}