package interface_adapter.search_recipe;

import java.util.List;

import use_case.search_recipes.SearchRecipeInputBoundary;
import use_case.search_recipes.SearchRecipeInputData;
import use_case.view_recipe.ViewRecipeInputBoundary;
import use_case.view_recipe.ViewRecipeInputData;

/**
 * The controller for the search recipe use case.
 */
public class SearchRecipeController {

    private final SearchRecipeInputBoundary searchRecipeUseCaseInteractor;
    private final ViewRecipeInputBoundary viewRecipeUseCaseInteractor;

    public SearchRecipeController(SearchRecipeInputBoundary searchRecipeUseCaseInteractor,
                                  ViewRecipeInputBoundary viewRecipeUseCaseInteractor) {
        this.searchRecipeUseCaseInteractor = searchRecipeUseCaseInteractor;
        this.viewRecipeUseCaseInteractor = viewRecipeUseCaseInteractor;
    }

    /**
     * Executes the Search Recipe Use Case.
     * @param query user input to be searched.
     * @param recipeIds the input list of recipe ids.
     */
    public void execute(String query, List<Integer> recipeIds) {
        final SearchRecipeInputData inputData = new SearchRecipeInputData(query, recipeIds);

        searchRecipeUseCaseInteractor.execute(inputData);
    }

    /**
     * Switches the home page view.
     * @param query the string query that stays from search to home.
     */
    public void switchToHomeView(String query) {
        final SearchRecipeInputData inputData = new SearchRecipeInputData(query, List.of());
        searchRecipeUseCaseInteractor.switchToHomePageView(inputData);
    }

    /**
     * Switches to the recipe detail view.
     * @param recipeId id of recipe.
     */
    public void switchToRecipeView(int recipeId) {
        final ViewRecipeInputData recipeDetailInputData = new ViewRecipeInputData(recipeId);
        viewRecipeUseCaseInteractor.execute(recipeDetailInputData);
    }
}
