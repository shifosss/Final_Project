package app.usecase_factory;

import data_access.CocktailDataAccessObject;
import data_access.UserDataAccessObject;
import interface_adapter.ViewManagerModel;
import interface_adapter.custom_recipe.CustomRecipeViewModel;
import interface_adapter.explore_ingredient.ExploreIngredientViewModel;
import interface_adapter.home_page.HomePageController;
import interface_adapter.home_page.HomePagePresenter;
import interface_adapter.home_page.HomePageViewModel;
import interface_adapter.recipe_detail.RecipeDetailViewModel;
import interface_adapter.search_recipe.SearchRecipeViewModel;
import interface_adapter.services.ServiceManager;
import interface_adapter.user_profile.UserProfileViewModel;
import use_case.create_recipe.CustomRecipeInputBoundary;
import use_case.create_recipe.CustomRecipeInteractor;
import use_case.explore_ingredient.ExploreIngredientInputBoundary;
import use_case.explore_ingredient.ExploreIngredientInteractor;
import use_case.user_profile.UserProfileInputBoundary;
import use_case.user_profile.UserProfileInteractor;
import use_case.view_recipe.ViewRecipeInputBoundary;
import use_case.view_recipe.ViewRecipeInteractor;
import view.views_placeholder.HomeView;

/**
 * Home page factory that creates necessary usecase.
 */
public final class HomeUseCaseFactory {
    private HomeUseCaseFactory() {
    }

    /**
     * The Home View.
     * @param viewManagerModel the ViewManagerModel to be injected into the View
     * @param homePageViewModel the HomePageViewModel to be injected into the View.
     * @param searchRecipeViewModel the SearchRecipeViewModel to be injected into the View
     * @param recipeDetailViewModel the RecipeDetailViewModel to be injected into the View.
     * @param exploreIngredientViewModel the ExploreIngredientViewModel to be injected into the view.
     * @param userProfileViewModel the UserProfileViewModel to be injected into the View.
     * @param cocktailDataAccessObject the CocktailDAO to be injected into the View
     * @param userDataAccessObject the UserDAO to be injected into the View.
     * @param serviceManager the ServiceManager to be injected into the View
     * @return the home view.
     */
    public static HomeView create(ViewManagerModel viewManagerModel,
                                  HomePageViewModel homePageViewModel,
                                  SearchRecipeViewModel searchRecipeViewModel,
                                  RecipeDetailViewModel recipeDetailViewModel,
                                  ExploreIngredientViewModel exploreIngredientViewModel,
                                  UserProfileViewModel userProfileViewModel,
                                  CustomRecipeViewModel customRecipeViewModel,
                                  CocktailDataAccessObject cocktailDataAccessObject,
                                  UserDataAccessObject userDataAccessObject,
                                  ServiceManager serviceManager) {

        final HomePageController homePageController = createHomePageUseCases(
                viewManagerModel, homePageViewModel, searchRecipeViewModel,
                recipeDetailViewModel, exploreIngredientViewModel, userProfileViewModel,
                customRecipeViewModel, cocktailDataAccessObject, userDataAccessObject);

        return new HomeView(homePageViewModel,
                homePageController,
                serviceManager);
    }

    private static HomePageController createHomePageUseCases(
            ViewManagerModel viewManagerModel,
            HomePageViewModel homePageViewModel,
            SearchRecipeViewModel searchRecipeViewModel,
            RecipeDetailViewModel recipeDetailViewModel,
            ExploreIngredientViewModel exploreIngredientViewModel,
            UserProfileViewModel userProfileViewModel,
            CustomRecipeViewModel customRecipeViewModel,
            CocktailDataAccessObject cocktailDataAccessObject,
            UserDataAccessObject userDataAccessObject) {

        final HomePagePresenter homePagePresenter = new HomePagePresenter(
                homePageViewModel, searchRecipeViewModel,
                recipeDetailViewModel, exploreIngredientViewModel, userProfileViewModel,
                customRecipeViewModel, viewManagerModel);
        final ExploreIngredientInputBoundary exploreIngredientInteractor = new ExploreIngredientInteractor(
                cocktailDataAccessObject, homePagePresenter);
        final ViewRecipeInputBoundary viewRecipeInteractor = new ViewRecipeInteractor(
                cocktailDataAccessObject, userDataAccessObject,
                homePagePresenter
        );
        final UserProfileInputBoundary userProfileInteractor = new UserProfileInteractor(
                homePagePresenter, userDataAccessObject
        );
        final CustomRecipeInputBoundary customRecipeInteractor = new CustomRecipeInteractor(
                userDataAccessObject, homePagePresenter
        );

        return new HomePageController(viewRecipeInteractor, exploreIngredientInteractor,
                userProfileInteractor, customRecipeInteractor);
    }
}
