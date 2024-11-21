package interface_adapter.explore_ingredient;

import interface_adapter.ViewManagerModel;
import use_case.explore_ingredient.ExploreIngredientOutputBoundary;
import use_case.explore_ingredient.ExploreIngredientOutputData;

/**
 * The presenter for the search recipe use case.
 */
public class ExploreIngredientPresenter implements ExploreIngredientOutputBoundary {
    private final ExploreIngredientViewModel exploreIngredientViewModel;
    private final ViewManagerModel viewManagerModel;

    public ExploreIngredientPresenter(ExploreIngredientViewModel exploreIngredientViewModel,
                                      ViewManagerModel viewManagerModel) {
        this.exploreIngredientViewModel = exploreIngredientViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(ExploreIngredientOutputData outputData) {
        final ExploreIngredientState searchRecipeState = exploreIngredientViewModel.getState();
        searchRecipeState.setIngredients(outputData.getIngredients());

        this.exploreIngredientViewModel.setState(searchRecipeState);
        this.exploreIngredientViewModel.firePropertyChanged();

        this.viewManagerModel.setState(exploreIngredientViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
        System.out.println(outputData.getIngredients());
    }
    }
}
