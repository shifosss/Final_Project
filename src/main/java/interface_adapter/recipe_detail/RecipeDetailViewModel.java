package interface_adapter.recipe_detail;

import interface_adapter.ViewModel;

/**
 * The View Model for the Search Recipe View.
 */
public class RecipeDetailViewModel extends ViewModel<RecipeDetailState> {
    public RecipeDetailViewModel() {
        super("recipe detail");
        setState(new RecipeDetailState());
    }
}
