package app;

import app.usecase_factory.*;
import data_access.CocktailDataAccessObject;
import data_access.UserDataAccessObject;
import entities.recipe.factory.CocktailFactory;
import entities.recipe.factory.RecipeFactory;
import entities.user.factory.CommonUserFactory;
import entities.user.factory.UserFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.explore_ingredient.ExploreIngredientViewModel;
import interface_adapter.home_page.HomePageViewModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.preference.PreferenceViewModel;
import interface_adapter.recipe_detail.RecipeDetailViewModel;
import interface_adapter.search_recipe.SearchRecipeViewModel;
import interface_adapter.services.ServiceManager;
import interface_adapter.services.image_service.ImageServiceInterface;
import interface_adapter.services.image_service.LocalImageService;
import interface_adapter.services.image_service.WebImageService;
import interface_adapter.services.video_service.VideoServiceInterface;
import interface_adapter.services.video_service.WebVideoService;
import interface_adapter.signup.SignupViewModel;
import interface_adapter.user_profile.UserProfileViewModel;
import view.ExploreIngredientRecipeView;
import view.RecipeDetailView;
import view.SearchRecipeView;
import view.ViewManager;
import view.views_placeholder.HomeView;
import view.views_placeholder.LoginView;
import view.views_placeholder.SignupView;
import view.views_placeholder.UserProfileView;

import javax.swing.*;
import java.awt.*;

/**
 * Main Application Interface.
 */
public class MainApp {
    /**
     * Main that runs the app.
     * @param args user command input.
     */
    public static void main(String[] args) {
        final JFrame application = new JFrame("Recipe Lookup");
        application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        application.setLayout(new BorderLayout());
        final CardLayout cardLayout = new CardLayout();

        // Only one view at a time.
        final JPanel views = new JPanel(cardLayout);
        application.add(views);

        // manages the panels to be viewed.
        final ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);

        // Initializes Services.
        final ImageServiceInterface webImageService = new WebImageService();
        final ImageServiceInterface localImageService = new LocalImageService();
        final VideoServiceInterface webVideoService = new WebVideoService();
        // Manages the interface_adapter.services.
        final ServiceManager serviceManager = new ServiceManager(
                webImageService, localImageService,
                webVideoService);

        // Entity Factories
        final RecipeFactory recipeFactory = new CocktailFactory();
        final UserFactory userFactory = new CommonUserFactory();

        // api/database initialization
        final CocktailDataAccessObject cocktailDataAccessObject = new CocktailDataAccessObject(recipeFactory);
        final UserDataAccessObject userDataAccessObject = new UserDataAccessObject(userFactory, recipeFactory);

        // View Model initialization
        final SignupViewModel signupViewModel = new SignupViewModel();
        final LoginViewModel loginViewModel = new LoginViewModel();
        final HomePageViewModel homePageViewModel = new HomePageViewModel();
        final ExploreIngredientViewModel exploreIngredientViewModel = new ExploreIngredientViewModel();
        final UserProfileViewModel userProfileViewModel = new UserProfileViewModel();
        final PreferenceViewModel preferenceViewModel = new PreferenceViewModel();
        final SearchRecipeViewModel searchRecipeViewModel = new SearchRecipeViewModel();
        final RecipeDetailViewModel recipeDetailViewModel = new RecipeDetailViewModel();

        // SignupView initialization
        final SignupView signupView = SignupUseCaseFactory.create(viewManagerModel,
                signupViewModel, loginViewModel, userDataAccessObject);
        views.add(signupView, signupView.getViewName());

        // LoginView initialization
        final LoginView loginView = LoginUseCaseFactory.create(viewManagerModel,
                signupViewModel, loginViewModel, preferenceViewModel, homePageViewModel,
                userDataAccessObject, cocktailDataAccessObject);
        views.add(loginView, loginView.getViewName());

        // SearchRecipeView initialization
        final HomeView homeView = HomeUseCaseFactory.create(viewManagerModel,
                homePageViewModel, searchRecipeViewModel, recipeDetailViewModel, exploreIngredientViewModel,
                userProfileViewModel, cocktailDataAccessObject, userDataAccessObject, serviceManager);
        views.add(homeView, homePageViewModel.getViewName());

        final SearchRecipeView searchRecipeView = SearchRecipeUseCaseFactory.create(viewManagerModel,
                searchRecipeViewModel, recipeDetailViewModel, homePageViewModel,
                cocktailDataAccessObject, userDataAccessObject, serviceManager);
        views.add(searchRecipeView, searchRecipeView.getViewName());

        final RecipeDetailView recipeDetailView = RecipeDetailUseCaseFactory.create(viewManagerModel,
                recipeDetailViewModel, searchRecipeViewModel,
                cocktailDataAccessObject, userDataAccessObject, serviceManager);
        views.add(recipeDetailView, recipeDetailView.getViewName());

        final ExploreIngredientRecipeView exploreIngredientRecipeView = ExploreIngredientUseCaseFactory.create(
                viewManagerModel,
                homePageViewModel, searchRecipeViewModel, exploreIngredientViewModel,
                cocktailDataAccessObject, serviceManager);
        views.add(exploreIngredientRecipeView, exploreIngredientRecipeView.getViewName());

        final UserProfileView userProfileView = UserProfileUseCaseFactory.create(
                viewManagerModel, userProfileViewModel, homePageViewModel, userDataAccessObject, serviceManager);
        views.add(userProfileView, userProfileView.getViewName());

        // Handles what view model to be shown first
        viewManagerModel.setState(loginView.getViewName());
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setVisible(true);
    }
}
