package interface_adapter.search_recipe;

import interface_adapter.ViewModel;

/**
 * The View Model for the Search Recipe View.
 */
public class SearchRecipeViewModel extends ViewModel<SearchRecipeState> {
    public SearchRecipeViewModel() {
        super("search recipe");
        setState(new SearchRecipeState());
    }
}
