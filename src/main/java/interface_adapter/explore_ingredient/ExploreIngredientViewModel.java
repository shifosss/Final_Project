package interface_adapter.explore_ingredient;

import interface_adapter.ViewModel;
import interface_adapter.search_recipe.SearchRecipeState;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ExploreIngredientViewModel extends ViewModel {
    private ExploreIngredientState state = new ExploreIngredientState();

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public ExploreIngredientViewModel() {
        super("explore_ingredient");
        setState(new SearchRecipeState());
    }

    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public ExploreIngredientState getState() {
        return state;
    }

    public void setState(ExploreIngredientState state) {
        this.state = state;
        System.out.println("State updated: " + state);
        firePropertyChanged();
    }
}
