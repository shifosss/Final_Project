package interface_adapter.home_page;

import interface_adapter.ViewManagerModel;
import interface_adapter.explore_ingredient.ExploreIngredientState;
import interface_adapter.explore_ingredient.ExploreIngredientViewModel;
import interface_adapter.recipe_detail.RecipeDetailViewModel;
import interface_adapter.search_recipe.SearchRecipeViewModel;
import interface_adapter.user_profile.UserProfileState;
import interface_adapter.user_profile.UserProfileViewModel;
import use_case.explore_ingredient.ExploreIngredientOutputBoundary;
import use_case.explore_ingredient.ExploreIngredientOutputData;
import use_case.search_recipes.SearchRecipeOutputData;
import use_case.user_profile.UserProfileOutputBoundary;
import use_case.user_profile.UserProfileOutputData;
import use_case.view_recipe.ViewRecipeOutputBoundary;
import use_case.view_recipe.ViewRecipeOutputData;

/**
 * Presenter for the home page.
 */
public class HomePagePresenter implements ExploreIngredientOutputBoundary, ViewRecipeOutputBoundary,
        UserProfileOutputBoundary {
    private final HomePageViewModel homePageViewModel;
    private final RecipeDetailViewModel recipeDetailViewModel;
    private final SearchRecipeViewModel searchRecipeViewModel;
    private final ExploreIngredientViewModel exploreIngredientViewModel;
    private final UserProfileViewModel userProfileViewModel;
    private final ViewManagerModel viewManagerModel;

    public HomePagePresenter(HomePageViewModel homePageViewModel,
                             SearchRecipeViewModel searchRecipeViewModel,
                             RecipeDetailViewModel recipeDetailViewModel,
                             ExploreIngredientViewModel exploreIngredientViewModel,
                             UserProfileViewModel userProfileViewModel,
                             ViewManagerModel viewManagerModel) {
        this.homePageViewModel = homePageViewModel;
        this.searchRecipeViewModel = searchRecipeViewModel;
        this.recipeDetailViewModel = recipeDetailViewModel;
        this.exploreIngredientViewModel = exploreIngredientViewModel;
        this.userProfileViewModel = userProfileViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareIngredientsListView(ExploreIngredientOutputData outputData) {
        final ExploreIngredientState exploreIngredientState = exploreIngredientViewModel.getState();
        exploreIngredientState.setIngredients(outputData.getIngredientsList());

        this.exploreIngredientViewModel.setState(exploreIngredientState);
        this.exploreIngredientViewModel.firePropertyChanged();

        this.viewManagerModel.setState(exploreIngredientViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {

    }

    @Override
    public void prepareFailView(ViewRecipeOutputData outputData, String errorMessage) {

    }

    @Override
    public void prepareSuccessView(SearchRecipeOutputData outputData) {
    }

    @Override
    public void prepareSuccessView(ViewRecipeOutputData outputData) {

    }

    @Override
    public void switchToSearchRecipeView() {
        viewManagerModel.setState(searchRecipeViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
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
}
