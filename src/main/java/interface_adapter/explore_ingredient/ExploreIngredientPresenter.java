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

    @Override
    public void prepareIngredientsListView(List<Ingredient> ingredients) {
        ExploreIngredientState exploreIngredientState = exploreIngredientViewModel.getState();
        exploreIngredientState.setIngredients(ingredients);
        exploreIngredientState.setRecipes(null);  // Clear any previous recipes
        this.exploreIngredientViewModel.setState(exploreIngredientState);
        this.exploreIngredientViewModel.firePropertyChanged();

        viewManagerModel.setState("explore ingredient");  // Must match view name
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareSuccessView(ExploreIngredientOutputData outputData) {
        final ExploreIngredientState exploreIngredientState = exploreIngredientViewModel.getState();
        if (outputData.isIngredientList()) {
            exploreIngredientState.setIngredients(outputData.getIngredients());
            exploreIngredientState.setRecipes(null);
        } else {
            exploreIngredientState.setRecipes(outputData.getSimpleRecipes());
            exploreIngredientState.setIngredients(null);
        }
        this.exploreIngredientViewModel.setState(exploreIngredientState);
        this.viewManagerModel.setState("explore ingredient");
        this.viewManagerModel.firePropertyChanged();
        this.exploreIngredientViewModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        ExploreIngredientState exploreIngredientState = exploreIngredientViewModel.getState();
        // Clear data but maintain current view
        if (exploreIngredientState.getIngredients() != null) {
            exploreIngredientState.setRecipes(null);
        } else {
            exploreIngredientState.setIngredients(null);
            exploreIngredientState.setRecipes(null);
        }
        this.exploreIngredientViewModel.setState(exploreIngredientState);
        this.exploreIngredientViewModel.firePropertyChanged();
    }
}