package interface_adapter.explore_ingredient;

import interface_adapter.ViewManagerModel;
import interface_adapter.home_page.HomePageViewModel;
import interface_adapter.search_recipe.SearchRecipeState;
import interface_adapter.search_recipe.SearchRecipeViewModel;
import use_case.explore_ingredient.ExploreIngredientOutputBoundary;
import use_case.explore_ingredient.ExploreIngredientOutputData;
import use_case.search_recipes.SearchRecipeOutputBoundary;
import use_case.search_recipes.SearchRecipeOutputData;

/**
 * Presenter for the explore ingredient view.
 */
public class ExploreIngredientPresenter implements ExploreIngredientOutputBoundary, SearchRecipeOutputBoundary {
    private final ExploreIngredientViewModel exploreIngredientViewModel;
    private final SearchRecipeViewModel searchRecipeViewModel;
    private final HomePageViewModel homePageViewModel;
    private final ViewManagerModel viewManagerModel;

    public ExploreIngredientPresenter(ExploreIngredientViewModel exploreIngredientViewModel,
            HomePageViewModel homePageViewModel,
            SearchRecipeViewModel searchRecipeViewModel,
            ViewManagerModel viewManagerModel) {
        this.exploreIngredientViewModel = exploreIngredientViewModel;
        this.homePageViewModel = homePageViewModel;
        this.searchRecipeViewModel = searchRecipeViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareIngredientsListView(ExploreIngredientOutputData outputData) {
        final ExploreIngredientState exploreIngredientState = exploreIngredientViewModel.getState();
        exploreIngredientState.setIngredients(outputData.getIngredientsList());

        this.exploreIngredientViewModel.setState(exploreIngredientState);
        this.exploreIngredientViewModel.firePropertyChanged();

        this.viewManagerModel.setState(exploreIngredientViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        final ExploreIngredientState exploreIngredientState = exploreIngredientViewModel.getState();
        exploreIngredientState.setIngredients(null);

        // You might want to add error handling in your state and view
        this.exploreIngredientViewModel.setState(exploreIngredientState);
        this.exploreIngredientViewModel.firePropertyChanged();

        viewManagerModel.setState(exploreIngredientViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareSuccessView(SearchRecipeOutputData outputData) {
        final SearchRecipeState searchRecipeState = searchRecipeViewModel.getState();
        searchRecipeState.setQuery(searchRecipeState.getQuery());
        searchRecipeState.setRecipes(outputData.getRecipes());

        this.searchRecipeViewModel.setState(searchRecipeState);
        this.searchRecipeViewModel.firePropertyChanged();

        this.viewManagerModel.setState(searchRecipeViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(SearchRecipeOutputData outputData, String errorMessage) {
        final SearchRecipeState searchRecipeState = searchRecipeViewModel.getState();
        searchRecipeState.setQuery(searchRecipeState.getQuery());
        searchRecipeState.setRecipes(outputData.getRecipes());

        this.searchRecipeViewModel.setState(searchRecipeState);
        this.searchRecipeViewModel.firePropertyChanged();

        this.viewManagerModel.setState(searchRecipeViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToHomePageView() {
        this.viewManagerModel.setState(homePageViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }
}