package interface_adapter.search_recipe;

import use_case.recipe_detail.RecipeDetailInputData;
import use_case.search_recipes.SearchRecipeInputBoundary;
import use_case.search_recipes.SearchRecipeInputData;

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
     */
    public void execute(String query) {
        final SearchRecipeInputData loginInputData = new SearchRecipeInputData(query);

        searchRecipeUseCaseInteractor.execute(loginInputData);
    }

    /**
     * Switches the home page view.
     */
    public void switchToHomeView() {
        searchRecipeUseCaseInteractor.switchToHomeView();
    }

    /**
     * Switches to the recipe detail view.
     * @param recipeId id of recipe.
     */
    public void switchToRecipeView(int recipeId) {
        final RecipeDetailInputData recipeDetailInputData = new RecipeDetailInputData(recipeId);
        searchRecipeUseCaseInteractor.switchToRecipeDetailView(recipeDetailInputData);
    }
}
