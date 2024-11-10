package interface_adapter.recipe_detail;

import interface_adapter.ViewManagerModel;
import interface_adapter.search_recipe.SearchRecipeViewModel;
import use_case.recipe_detail.RecipeDetailOutputBoundary;
import use_case.recipe_detail.RecipeDetailOutputData;

/**
 * Presenter for the recipe detail.
 */
public class RecipeDetailPresenter implements RecipeDetailOutputBoundary {
    private final RecipeDetailViewModel recipeDetailViewModel;
    private final SearchRecipeViewModel searchRecipeViewModel;
    private final ViewManagerModel viewManagerModel;

    public RecipeDetailPresenter(RecipeDetailViewModel recipeDetailViewModel,
                                 SearchRecipeViewModel searchRecipeViewModel,
                                 ViewManagerModel viewManagerModel) {
        this.recipeDetailViewModel = recipeDetailViewModel;
        this.searchRecipeViewModel = searchRecipeViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(RecipeDetailOutputData outputData) {
        final RecipeDetailState recipeDetailState = recipeDetailViewModel.getState();
        recipeDetailState.setRecipe(outputData.getRecipe());

        // updates the recipe detail state
        this.recipeDetailViewModel.setState(recipeDetailState);
        this.recipeDetailViewModel.firePropertyChanged();

        // updates the view manager model
        this.viewManagerModel.setState(recipeDetailViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(RecipeDetailOutputData outputData, String errorMessage) {
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
}