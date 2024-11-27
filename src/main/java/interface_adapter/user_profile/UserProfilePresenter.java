package interface_adapter.user_profile;

import interface_adapter.ViewManagerModel;
import interface_adapter.home_page.HomePageViewModel;
import interface_adapter.preference.PreferenceState;
import interface_adapter.preference.PreferenceViewModel;
import interface_adapter.recipe_detail.RecipeDetailState;
import interface_adapter.recipe_detail.RecipeDetailViewModel;
import use_case.bookmark_recipe.BookmarkRecipeOutputData;
import use_case.change_preference.ChangePreferenceOutputBoundary;
import use_case.change_preference.ChangePreferenceOutputData;
import use_case.user_profile.UserProfileOutputBoundary;
import use_case.user_profile.UserProfileOutputData;
import use_case.view_recipe.ViewRecipeOutputBoundary;
import use_case.view_recipe.ViewRecipeOutputData;

/**
 * Presenter for the userprofile view.
 */
public class UserProfilePresenter implements UserProfileOutputBoundary,
        ViewRecipeOutputBoundary, ChangePreferenceOutputBoundary {
    private final HomePageViewModel homePageViewModel;
    private final UserProfileViewModel userProfileViewModel;
    private final RecipeDetailViewModel recipeDetailViewModel;
    private final PreferenceViewModel preferenceViewModel;
    private final ViewManagerModel viewManagerModel;

    public UserProfilePresenter(HomePageViewModel homePageViewModel,
                                UserProfileViewModel userProfileViewModel,
                                RecipeDetailViewModel recipeDetailViewModel,
                                PreferenceViewModel preferenceViewModel,
                                ViewManagerModel viewManagerModel) {
        this.homePageViewModel = homePageViewModel;
        this.userProfileViewModel = userProfileViewModel;
        this.recipeDetailViewModel = recipeDetailViewModel;
        this.preferenceViewModel = preferenceViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccess(ChangePreferenceOutputData outputData) {
        // From the user profile, this presenter won't be needed.
    }

    @Override
    public void switchToHomePageView() {
        viewManagerModel.setState(homePageViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToPreferenceView(ChangePreferenceOutputData outputData) {
        final PreferenceState state = preferenceViewModel.getState();
        state.setIngredients(outputData.getIngredients());

        preferenceViewModel.setState(state);
        preferenceViewModel.firePropertyChanged();

        viewManagerModel.setState(preferenceViewModel.getViewName());
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
        // Usable only if app allows viewing other user profiles than yourself.
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
        // View recipe can't fail.
    }

    @Override
    public void switchToSearchRecipeView() {
        // Cannot go to search view while on user profile.
    }

    @Override
    public void updateBookmarksView(BookmarkRecipeOutputData outputData) {
        // bookmarking isn't implemented in user profile, no point updating the view.
    }
}
