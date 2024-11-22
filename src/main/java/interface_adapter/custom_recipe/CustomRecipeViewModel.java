package interface_adapter.custom_recipe;

import interface_adapter.ViewModel;

/**
 * View model for the custom recipe view.
 */
public class CustomRecipeViewModel extends ViewModel<CustomRecipeState> {
    public CustomRecipeViewModel(String viewName) {
        super(viewName);
        setState(new CustomRecipeState());
    }
}
