package interface_adapter.explore_ingredient;

import use_case.explore_ingredient.ExploreIngredientInputBoundary;
import use_case.explore_ingredient.ExploreIngredientInputData;

/**
 * The controller for the explore ingredient use case.
 */
public class ExploreIngredientController {

    private final ExploreIngredientInputBoundary exploreIngredientUseCaseInteractor;

    public ExploreIngredientController(ExploreIngredientInputBoundary searchRecipeUseCaseInteractor) {
        this.exploreIngredientUseCaseInteractor = searchRecipeUseCaseInteractor;
    }

    public void loadIngredients() {
        exploreIngredientUseCaseInteractor.loadIngredients();
    }

    /**
     * Executes the Explore Ingredient Use Case.
     * @param query user input to be searched.
     */
    public void execute(String query) {
        final ExploreIngredientInputData loginInputData = new ExploreIngredientInputData(query);

        exploreIngredientUseCaseInteractor.execute(loginInputData);
    }
}
