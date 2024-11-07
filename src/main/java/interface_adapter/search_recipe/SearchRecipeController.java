package interface_adapter.search_recipe;

import domain.entities.recipe.Recipe;
import domain.use_case.search_recipes.SearchRecipeInputBoundary;
import domain.use_case.search_recipes.SearchRecipeInputData;

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
     */
    public void execute(String query) {
        final SearchRecipeInputData searchRecipeInputData = new SearchRecipeInputData(query);
        searchRecipeUseCaseInteractor.execute(searchRecipeInputData);
    }
}
