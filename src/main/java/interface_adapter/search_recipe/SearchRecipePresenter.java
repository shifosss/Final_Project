package interface_adapter.search_recipe;

import domain.use_case.search_recipes.SearchRecipeInputBoundary;
import domain.use_case.search_recipes.SearchRecipeOutputBoundary;
import domain.use_case.search_recipes.SearchRecipeOutputData;
import interface_adapter.ViewManagerModel;

/**
 * The presenter for the search recipe use case.
 */
public class SearchRecipePresenter implements SearchRecipeOutputBoundary {
    private final SearchRecipeViewModel searchRecipeViewModel;
    private final ViewManagerModel viewManagerModel;

    public SearchRecipePresenter(SearchRecipeViewModel searchRecipeViewModel,
                                      ViewManagerModel viewManagerModel) {
        this.searchRecipeViewModel = searchRecipeViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(SearchRecipeOutputData outputData) {
        final SearchRecipeState searchRecipeState = searchRecipeViewModel.getState();
        searchRecipeState.setRecipes(outputData.getRecipes());

        this.searchRecipeViewModel.setState(searchRecipeState);
        this.searchRecipeViewModel.firePropertyChanged();

        this.viewManagerModel.setState(searchRecipeViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(SearchRecipeOutputData outputData, String errorMessage) {
        // TODO: Create a fail view when there are no recipe matches.
        final SearchRecipeState searchRecipeState = searchRecipeViewModel.getState();
        searchRecipeState.setRecipes(outputData.getRecipes());

        this.searchRecipeViewModel.setState(searchRecipeState);
        this.searchRecipeViewModel.firePropertyChanged();

        this.viewManagerModel.setState(searchRecipeViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }
}
