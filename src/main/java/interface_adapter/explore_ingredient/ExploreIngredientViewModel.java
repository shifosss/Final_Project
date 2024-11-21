package interface_adapter.explore_ingredient;

import interface_adapter.ViewModel;

/**
 * The View Model for the Search Recipe View.
 */
public class ExploreIngredientViewModel extends ViewModel<ExploreIngredientState> {
    public ExploreIngredientViewModel() {
        super("explore ingredient");
        setState(new ExploreIngredientState());
    }
}
