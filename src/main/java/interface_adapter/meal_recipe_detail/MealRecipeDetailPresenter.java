package interface_adapter.meal_recipe_detail;

import interface_adapter.ViewManagerModel;
import interface_adapter.recipe_detail.RecipeDetailState;
import interface_adapter.recipe_detail.RecipeDetailViewModel;
import interface_adapter.search_recipe.SearchRecipeViewModel;
import use_case.view_recipe.ViewMealRecipeOutputBoundary;
import use_case.view_recipe.ViewMealRecipeOutputData;
import use_case.view_recipe.ViewRecipeOutputBoundary;
import use_case.view_recipe.ViewRecipeOutputData;

public class MealRecipeDetailPresenter implements ViewMealRecipeOutputBoundary {
    private final MealRecipeDetailViewModel mealrecipeDetailViewModel;
    private final ViewManagerModel viewManagerModel;
    private final SearchRecipeViewModel searchRecipeViewModel;

    public MealRecipeDetailPresenter(MealRecipeDetailViewModel mealrecipeDetailViewModel,
                                     ViewManagerModel viewManagerModel, SearchRecipeViewModel searchRecipeViewModel) {
        this.mealrecipeDetailViewModel = mealrecipeDetailViewModel;
        this.viewManagerModel = viewManagerModel;
        this.searchRecipeViewModel = searchRecipeViewModel;
    }

    @Override
    public void prepareSuccessView(ViewMealRecipeOutputData outputData) {
        final MealRecipeDetailState mealrecipeDetailState = mealrecipeDetailViewModel.getState();
        mealrecipeDetailState.setRecipe(outputData.getRecipe());
        mealrecipeDetailState.setIsBookmarked(outputData.isBookmarked());

        // updates the recipe detail state
        this.mealrecipeDetailViewModel.setState(mealrecipeDetailState);
        this.mealrecipeDetailViewModel.firePropertyChanged();

        // updates the view manager model
        this.viewManagerModel.setState(mealrecipeDetailViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareSuccessView(ViewRecipeOutputData outputData) {

    }

    @Override
    public void prepareFailView(ViewRecipeOutputData outputData, String errorMessage) {

    }

    @Override
    public void prepareFailView(ViewMealRecipeOutputData outputData, String errorMessage) {
        final MealRecipeDetailState mealrecipeDetailState = mealrecipeDetailViewModel.getState();
        mealrecipeDetailState.setRecipe(outputData.getRecipe());

        this.mealrecipeDetailViewModel.setState(mealrecipeDetailState);
        this.mealrecipeDetailViewModel.firePropertyChanged();

        this.viewManagerModel.setState(mealrecipeDetailViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

}
