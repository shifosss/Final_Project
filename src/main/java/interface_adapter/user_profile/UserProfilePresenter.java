package interface_adapter.user_profile;


import interface_adapter.ViewManagerModel;
import interface_adapter.home_page.HomePageState;
import interface_adapter.home_page.HomePageViewModel;
import interface_adapter.recipe_detail.RecipeDetailState;
import interface_adapter.recipe_detail.RecipeDetailViewModel;
import use_case.user_profile.UserProfileOutputBoundary;
import use_case.user_profile.UserProfileOutputData;
import use_case.view_recipe.ViewRecipeOutputBoundary;
import use_case.view_recipe.ViewRecipeOutputData;

/**
 * Presenter for the userprofile view.
 */
public class UserProfilePresenter implements UserProfileOutputBoundary, ViewRecipeOutputBoundary {
    private final HomePageViewModel homePageViewModel;
    private final UserProfileViewModel userProfileViewModel;
    private final RecipeDetailViewModel recipeDetailViewModel;
    private final ViewManagerModel viewManagerModel;

    public UserProfilePresenter(HomePageViewModel homePageViewModel,
                                UserProfileViewModel userProfileViewModel,
                                RecipeDetailViewModel recipeDetailViewModel,
                                ViewManagerModel viewManagerModel) {
        this.homePageViewModel = homePageViewModel;
        this.userProfileViewModel = userProfileViewModel;
        this.recipeDetailViewModel = recipeDetailViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void switchToHomeView() {
        viewManagerModel.setState(homePageViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToProfileView(UserProfileOutputData outputData) {
        final UserProfileState state = userProfileViewModel.getState();
        state.setUsername(outputData.getUsername());
        state.setCreatedRecipes(outputData.getCreatedRecipes());

        userProfileViewModel.setState(state);
        userProfileViewModel.firePropertyChanged();

        viewManagerModel.setState(userProfileViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void presentUserNotFound(String error, String username) {

    }

    @Override
    public void prepareSuccessView(ViewRecipeOutputData outputData) {
        final RecipeDetailState state = recipeDetailViewModel.getState();
        state.setRecipe(outputData.getRecipe());
        state.setIsBookmarked(outputData.isBookmarked());

        recipeDetailViewModel.setState(state);
        recipeDetailViewModel.firePropertyChanged();

        viewManagerModel.setState(recipeDetailViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(ViewRecipeOutputData outputData, String errorMessage) {

    }

    @Override
    public void switchToSearchRecipeView() {

    }
}
