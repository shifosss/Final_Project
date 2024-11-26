package interface_adapter.home_page;

import use_case.create_recipe.CustomRecipeInputBoundary;
import use_case.explore_ingredient.ExploreIngredientInputBoundary;
import use_case.user_profile.UserProfileInputBoundary;
import use_case.user_profile.UserProfileInputData;
import use_case.view_recipe.ViewRecipeInputBoundary;
import use_case.view_recipe.ViewRecipeInputData;

/**
 * Controller for the home view.
 */
public class HomePageController {
    private final ViewRecipeInputBoundary recipeDetailInteractor;
    private final ExploreIngredientInputBoundary ingredientInteractor;
    private final UserProfileInputBoundary userInteractor;
    private final CustomRecipeInputBoundary customRecipeInteractor;

    public HomePageController(ViewRecipeInputBoundary recipeDetailInteractor,
                              ExploreIngredientInputBoundary ingredientInteractor,
                              UserProfileInputBoundary userInteractor,
                              CustomRecipeInputBoundary customRecipeInteractor) {
        this.recipeDetailInteractor = recipeDetailInteractor;
        this.ingredientInteractor = ingredientInteractor;
        this.userInteractor = userInteractor;
        this.customRecipeInteractor = customRecipeInteractor;
    }

    /**
     * Switch to Recipe View.
     * @param id the given recipe id. (useful for clicking recipes directly on homepage)
     */
    public void switchToRecipeView(int id) {
        final ViewRecipeInputData inputData = new ViewRecipeInputData(id);
        recipeDetailInteractor.execute(inputData);
    }

    /**
     * Switches to the search view.
     */
    public void switchToSearchView() {
        recipeDetailInteractor.switchToSearchView();
    }

    /**
     * Switches to the explore ingredients view.
     */
    public void switchToExploreIngredients() {
        ingredientInteractor.switchToExploreIngredients();
    }

    /**
     * Switches to the user profile view.
     * @param username the username of the user to be viewed.
     */
    public void switchToUserButton(String username) {
        final UserProfileInputData inputData = new UserProfileInputData(username);
        userInteractor.switchToUserView(inputData);
    }

    /**
     * Goes to the custom recipe view.
     */
    public void switchToCustomRecipeView() {
        customRecipeInteractor.switchToCustomRecipeView();
    }
}
