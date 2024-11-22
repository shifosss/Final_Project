package interface_adapter.explore_ingredient;

import interface_adapter.ViewModel;
import interface_adapter.home_page.HomePageState;
import interface_adapter.search_recipe.SearchRecipeState;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * View model for the explore ingredients usecase.
 */
public class ExploreIngredientViewModel extends ViewModel<ExploreIngredientState> {
    public ExploreIngredientViewModel() {
        super("explore ingredient");
        setState(new ExploreIngredientState());
    }
}
