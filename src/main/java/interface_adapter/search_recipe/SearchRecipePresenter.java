package interface_adapter.search_recipe;

import interface_adapter.ViewManagerModel;
import interface_adapter.home_page.HomePageViewModel;
import interface_adapter.recipe_detail.RecipeDetailState;
import interface_adapter.recipe_detail.RecipeDetailViewModel;
import use_case.bookmark_recipe.BookmarkRecipeOutputData;
import use_case.search_recipes.SearchRecipeOutputBoundary;
import use_case.search_recipes.SearchRecipeOutputData;
import use_case.view_recipe.ViewRecipeOutputBoundary;
import use_case.view_recipe.ViewRecipeOutputData;

/**
 * The presenter for the search recipe use case.
 */
public class SearchRecipePresenter implements SearchRecipeOutputBoundary, ViewRecipeOutputBoundary {
    private final SearchRecipeViewModel searchRecipeViewModel;
    private final RecipeDetailViewModel recipeDetailViewModel;
    private final HomePageViewModel homePageViewModel;
    private final ViewManagerModel viewManagerModel;

    public SearchRecipePresenter(ViewManagerModel viewManagerModel,
                                 SearchRecipeViewModel searchRecipeViewModel,
                                 RecipeDetailViewModel recipeDetailViewModel,
                                 HomePageViewModel homePageViewModel) {
        this.searchRecipeViewModel = searchRecipeViewModel;
        this.viewManagerModel = viewManagerModel;
        this.recipeDetailViewModel = recipeDetailViewModel;
        this.homePageViewModel = homePageViewModel;
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
        this.searchRecipeViewModel.firePropertyChanged("usecaseFailed");

        this.viewManagerModel.setState(searchRecipeViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(ViewRecipeOutputData outputData, String errorMessage) {
        // This case would most likely not fail.
    }

    @Override
    public void switchToSearchRecipeView() {
        this.viewManagerModel.setState(searchRecipeViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void updateBookmarksView(BookmarkRecipeOutputData outputData) {

    }

    @Override
    public void switchToHomePageView() {
        this.viewManagerModel.setState(homePageViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }
}
