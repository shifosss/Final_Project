package interface_adapter.search_recipe;

import interface_adapter.recipe_detail.RecipeDetailState;
import interface_adapter.recipe_detail.RecipeDetailViewModel;
import use_case.view_recipe.ViewRecipeOutputData;
import use_case.search_recipes.SearchRecipeOutputBoundary;
import use_case.search_recipes.SearchRecipeOutputData;
import interface_adapter.ViewManagerModel;

/**
 * The presenter for the search recipe use case.
 */
public class SearchRecipePresenter implements SearchRecipeOutputBoundary {
    private final SearchRecipeViewModel searchRecipeViewModel;
    private final RecipeDetailViewModel recipeDetailViewModel;
    // TODO: Add the Home Page View Model here
    private final ViewManagerModel viewManagerModel;

    public SearchRecipePresenter(ViewManagerModel viewManagerModel,
                                 SearchRecipeViewModel searchRecipeViewModel,
                                 RecipeDetailViewModel recipeDetailViewModel) {
        this.searchRecipeViewModel = searchRecipeViewModel;
        this.viewManagerModel = viewManagerModel;
        this.recipeDetailViewModel = recipeDetailViewModel;
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
    public void prepareSuccessView(ViewRecipeOutputData outputData) {
        final RecipeDetailState recipeDetailState = recipeDetailViewModel.getState();
        recipeDetailState.setRecipe(outputData.getRecipe());

        this.recipeDetailViewModel.setState(recipeDetailState);
        this.recipeDetailViewModel.firePropertyChanged();

        this.viewManagerModel.setState(recipeDetailViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(SearchRecipeOutputData outputData, String errorMessage) {
        final SearchRecipeState searchRecipeState = searchRecipeViewModel.getState();
        searchRecipeState.setRecipes(outputData.getRecipes());

        this.searchRecipeViewModel.setState(searchRecipeState);
        this.searchRecipeViewModel.firePropertyChanged();

        this.viewManagerModel.setState(searchRecipeViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(ViewRecipeOutputData outputData, String errorMessage) {
        // TODO: Implement prepare fail view for viewing recipe detail use case.
    }

    @Override
    public void switchToHomePageView() {
        // TODO: Implement the home page view model.
        // this.viewManagerModel.setState(homePageViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }
}
