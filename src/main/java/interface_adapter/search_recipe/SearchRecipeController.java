package interface_adapter.search_recipe;

import entities.recipe.Recipe;
import use_case.view_recipe.ViewRecipeInputData;
import use_case.search_recipes.SearchRecipeInputBoundary;
import use_case.search_recipes.SearchRecipeInputData;

import java.util.List;

/**
 * The controller for the search recipe use case.
 */
public class SearchRecipeController {

    private final SearchRecipeInputBoundary searchRecipeUseCaseInteractor;

    public SearchRecipeController(SearchRecipeInputBoundary searchRecipeUseCaseInteractor) {
        this.searchRecipeUseCaseInteractor = searchRecipeUseCaseInteractor;
    }

    /**
     * Executes the Search Recipe Use Case.
     * @param query user input to be searched.
     * @param recipes the input list of recipe.
     */
    public void execute(String query, List<Recipe> recipes) {
        final SearchRecipeInputData inputData = new SearchRecipeInputData(query, recipes);

        searchRecipeUseCaseInteractor.execute(inputData);
    }

    /**
     * Switches the home page view.
     * @param query the string query that stays from search to home.
     */
    public void switchToHomeView(String query) {
        final SearchRecipeInputData inputData = new SearchRecipeInputData(query, List.of());
        searchRecipeUseCaseInteractor.switchToHomeView(inputData);
    }

    /**
     * Switches to the recipe detail view.
     * @param recipeId id of recipe.
     */
    public void switchToRecipeView(int recipeId) {
        final ViewRecipeInputData recipeDetailInputData = new ViewRecipeInputData(recipeId);
        searchRecipeUseCaseInteractor.switchToRecipeDetailView(recipeDetailInputData);
    }

    public void switchToSearchRecipeView() {

    }
}
