package interface_adapter.explore_ingredient;

import use_case.explore_ingredient.ExploreIngredientOutputBoundary;
import use_case.explore_ingredient.ExploreIngredientOutputData;
import entities.recipe.Ingredient;
import interface_adapter.ViewManagerModel;
import java.util.List;

public class ExploreIngredientPresenter implements ExploreIngredientOutputBoundary {
    private final ExploreIngredientViewModel exploreIngredientViewModel;
    private final ViewManagerModel viewManagerModel;

    public ExploreIngredientPresenter(
            ExploreIngredientViewModel exploreIngredientViewModel,
            ViewManagerModel viewManagerModel) {
        this.exploreIngredientViewModel = exploreIngredientViewModel;
        this.viewManagerModel = viewManagerModel;
    }

//    @Override
//    public void prepareIngredientsListView(List<Ingredient> ingredients) {
//        ExploreIngredientState exploreIngredientState = exploreIngredientViewModel.getState();
//        exploreIngredientState.setIngredients(ingredients);
//        exploreIngredientState.setRecipes(null);  // Clear any previous recipes
//        this.exploreIngredientViewModel.setState(exploreIngredientState);
//        this.exploreIngredientViewModel.firePropertyChanged();
//
//        viewManagerModel.setState(exploreIngredientViewModel.getViewName());
//        viewManagerModel.firePropertyChanged();
//    }

    @Override
    public void prepareSuccessView(ExploreIngredientOutputData outputData) {
        final ExploreIngredientState searchRecipeState = exploreIngredientViewModel.getState();
        searchRecipeState.setIngredients(outputData.getIngredients());
        this.exploreIngredientViewModel.setState(searchRecipeState);
        this.viewManagerModel.setState(exploreIngredientViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
        System.out.println(outputData.getIngredients());

//    @Override
//    public void prepareRecipeListView(ExploreIngredientOutputData outputData) {
//        ExploreIngredientState exploreIngredientState = exploreIngredientViewModel.getState();
//        exploreIngredientState.setRecipes(outputData.getSimpleRecipes());
//        exploreIngredientState.setIngredients(null);  // Clear ingredients list since we're showing recipes
//        this.exploreIngredientViewModel.setState(exploreIngredientState);
//        this.exploreIngredientViewModel.firePropertyChanged();
    }

//    @Override
//    public void prepareFailView(String _error) {
//        ExploreIngredientState exploreIngredientState = exploreIngredientViewModel.getState();
//        if (exploreIngredientState.getIngredients() != null) {
//            // If we're in ingredients view, show error but keep ingredients list
//            exploreIngredientState.setRecipes(null);
//        } else {
//            // If we're in recipes view, clear everything
//            exploreIngredientState.setIngredients(null);
//            exploreIngredientState.setRecipes(null);
//        }
//        // You might want to add error handling in your state and view
//        this.exploreIngredientViewModel.setState(exploreIngredientState);
//        this.exploreIngredientViewModel.firePropertyChanged();
//    }
}