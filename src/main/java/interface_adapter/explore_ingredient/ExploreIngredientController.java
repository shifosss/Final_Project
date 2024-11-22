package interface_adapter.explore_ingredient;

import use_case.explore_ingredient.ExploreIngredientInputBoundary;
import use_case.explore_ingredient.ExploreIngredientInputData;

public class ExploreIngredientController {
    private final ExploreIngredientInputBoundary exploreIngredientUseCaseInteractor;

    public ExploreIngredientController(ExploreIngredientInputBoundary exploreIngredientUseCaseInteractor) {
        this.exploreIngredientUseCaseInteractor = exploreIngredientUseCaseInteractor;
    }

    /**
     * Switches to the recipes that contains the ingredient.
     * @param ingredient the ingredient name.
     */
    public void switchToRecipes(String ingredient) {
        final ExploreIngredientInputData inputData = new ExploreIngredientInputData(ingredient);
        exploreIngredientUseCaseInteractor.switchToRecipes(inputData);
    }

    /**
     * Switches to the explore ingredients view.
     */
    public void switchToExploreIngredients() {
        exploreIngredientUseCaseInteractor.switchToExploreIngredients();
    }
}