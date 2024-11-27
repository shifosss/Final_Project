package interface_adapter.custom_recipe;

import interface_adapter.ViewModel;

/**
 * View model for the custom recipe view.
 */
public class CustomRecipeViewModel extends ViewModel<CustomRecipeState> {
    public CustomRecipeViewModel() {
        super("create recipe");
        setState(new CustomRecipeState());
    }
}
