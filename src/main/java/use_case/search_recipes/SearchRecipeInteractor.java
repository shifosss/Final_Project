package use_case.search_recipes;

import java.util.List;

import entities.recipe.Recipe;
import interface_adapter.search_recipe.SearchRecipePresenter;
import use_case.view_recipe.ViewRecipeInputData;
import use_case.view_recipe.ViewRecipeOutputBoundary;
import use_case.view_recipe.ViewRecipeOutputData;

/**
 * The Search Recipe interactor.
 */
public class SearchRecipeInteractor implements SearchRecipeInputBoundary {
    private final SearchRecipeDataAccessInterface recipeDataAccessObject;
    private final SearchRecipePresenter recipePresenter;

    public SearchRecipeInteractor(SearchRecipeDataAccessInterface recipeDataAccessObject,
                                  SearchRecipeOutputBoundary recipePresenter) {
        this.recipeDataAccessObject = recipeDataAccessObject;
        // TODO: is this casting allowed?? i mean interactor is not interface adapter specific so....
        // my point being that interactor uses a presenter which implements two use cases output boundary.
        this.recipePresenter = (SearchRecipePresenter) recipePresenter;
    }

    @Override
    public void execute(SearchRecipeInputData searchRecipeInputData) {
        final String query = searchRecipeInputData.getSearchQuery();

        final List<Recipe> recipeResults = recipeDataAccessObject.searchRecipeByKeyword(query);
        // check if the list is empty
        if (recipeResults.isEmpty()) {
            final SearchRecipeOutputData recipeOutputData = new SearchRecipeOutputData(
                    query,
                    recipeResults,
                    true
            );
            recipePresenter.prepareFailView(
                    recipeOutputData,
                    "Search does not match any recipes.");
        }
        else {
            final SearchRecipeOutputData recipeOutputData = new SearchRecipeOutputData(
                    query,
                    recipeResults,
                    false
            );
            recipePresenter.prepareSuccessView(recipeOutputData);
        }

    }

    @Override
    public void switchToHomeView(SearchRecipeInputData searchRecipeInputData) {

        final SearchRecipeOutputData recipeOutputData = new SearchRecipeOutputData(
                searchRecipeInputData.getSearchQuery(),
                searchRecipeInputData.getRecipes(),
                false);
        recipePresenter.switchToHomePageView(recipeOutputData);
    }

    @Override
    public void switchToRecipeDetailView(ViewRecipeInputData recipeDetailInputData) {
        final int recipeId = recipeDetailInputData.getId();

        final Recipe recipe = recipeDataAccessObject.getRecipeById(recipeId);

        if (recipe == null) {
            final ViewRecipeOutputData recipeDetailOutputData = new ViewRecipeOutputData(
                    recipe,
                    true
            );
            recipePresenter.prepareFailView(recipeDetailOutputData, "Recipe not found.");
        }
        else {
            final ViewRecipeOutputData recipeDetailOutputData = new ViewRecipeOutputData(
                    recipe,
                    false
            );
            recipePresenter.prepareSuccessView(recipeDetailOutputData);
        }
    }
}
