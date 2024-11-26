package interface_adapter.custom_recipe;

import interface_adapter.ViewManagerModel;
import interface_adapter.home_page.HomePageState;
import interface_adapter.home_page.HomePageViewModel;
import use_case.create_recipe.CustomRecipeOutputBoundary;
import use_case.create_recipe.CustomRecipeOutputData;

import java.util.ArrayList;

/**
 * Output boundary for creating custom recipes view.
 */
public class CustomRecipePresenter implements CustomRecipeOutputBoundary {
    private final HomePageViewModel homePageViewModel;
    private final CustomRecipeViewModel customRecipeViewModel;
    private final ViewManagerModel viewManagerModel;

    public CustomRecipePresenter(HomePageViewModel homePageViewModel,
                                 CustomRecipeViewModel customRecipeViewModel,
                                 ViewManagerModel viewManagerModel) {
        this.homePageViewModel = homePageViewModel;
        this.customRecipeViewModel = customRecipeViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void switchToRecipeCreationView() {
        final CustomRecipeState state = customRecipeViewModel.getState();
        state.setRecipeName("");
        state.setRecipeInstruction("");
        state.setIngredients(new ArrayList<>());
        state.setIngredients(new ArrayList<>());

        customRecipeViewModel.setState(state);
        customRecipeViewModel.firePropertyChanged();

        viewManagerModel.setState(customRecipeViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToHomePageView() {
        viewManagerModel.setState(homePageViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
