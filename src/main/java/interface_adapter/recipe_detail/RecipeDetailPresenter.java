package interface_adapter.recipe_detail;

import interface_adapter.ViewManagerModel;
import interface_adapter.home_page.HomePageState;
import interface_adapter.home_page.HomePageViewModel;
import interface_adapter.search_recipe.SearchRecipeViewModel;
import use_case.bookmark_recipe.BookmarkRecipeOutputData;
import use_case.view_recipe.ViewRecipeOutputBoundary;
import use_case.view_recipe.ViewRecipeOutputData;

/**
 * Presenter for the recipe detail.
 */
public class RecipeDetailPresenter implements ViewRecipeOutputBoundary {
    private final RecipeDetailViewModel recipeDetailViewModel;
    private final SearchRecipeViewModel searchRecipeViewModel;
    private final HomePageViewModel homePageViewModel;
    private final ViewManagerModel viewManagerModel;

    public RecipeDetailPresenter(RecipeDetailViewModel recipeDetailViewModel,
                                 SearchRecipeViewModel searchRecipeViewModel,
                                 HomePageViewModel homePageViewModel,
                                 ViewManagerModel viewManagerModel) {
        this.recipeDetailViewModel = recipeDetailViewModel;
        this.searchRecipeViewModel = searchRecipeViewModel;
        this.homePageViewModel = homePageViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(ViewRecipeOutputData outputData) {
        final RecipeDetailState recipeDetailState = recipeDetailViewModel.getState();
        recipeDetailState.setRecipe(outputData.getRecipe());
        recipeDetailState.setIsBookmarked(outputData.isBookmarked());

        // updates the recipe detail state
        this.recipeDetailViewModel.setState(recipeDetailState);
        this.recipeDetailViewModel.firePropertyChanged();

        // updates the view manager model
        this.viewManagerModel.setState(recipeDetailViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(ViewRecipeOutputData outputData, String errorMessage) {
        final RecipeDetailState recipeDetailState = recipeDetailViewModel.getState();
        recipeDetailState.setRecipe(outputData.getRecipe());

        this.recipeDetailViewModel.setState(recipeDetailState);
        this.recipeDetailViewModel.firePropertyChanged();

        this.viewManagerModel.setState(recipeDetailViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToSearchRecipeView() {
        viewManagerModel.setState(searchRecipeViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void updateBookmarksView(BookmarkRecipeOutputData outputData) {
        final HomePageState state = homePageViewModel.getState();

        state.setBookmarkedRecipes(outputData.getBookmarkedRecipes());
        this.homePageViewModel.setState(state);
        this.homePageViewModel.firePropertyChanged();
    }
}
