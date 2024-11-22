package app;

import app.usecase_factory.*;
import data_access.CocktailDataAccessObject;
import data_access.UserDataAccessObject;
import entities.recipe.factory.CocktailFactory;
import entities.user.factory.CommonUserFactory;
import interface_adapter.ViewManagerModel;
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
import view.ExploreIngredientRecipeView;
import view.RecipeDetailView;
import view.SearchRecipeView;
import view.ViewManager;
import view.ViewPlaceholder.HomeView;
import view.ViewPlaceholder.LoginView;
import view.ViewPlaceholder.SignupView;

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

        // api/database initialization
        final CocktailDataAccessObject cocktailDataAccessObject = new CocktailDataAccessObject(new CocktailFactory());
        final UserDataAccessObject userDataAccessObject = new UserDataAccessObject(new CommonUserFactory());

        // View Model initialization
        final SignupViewModel signupViewModel = new SignupViewModel();
        final LoginViewModel loginViewModel = new LoginViewModel();
        final interface_adapter.home_page.HomePageViewModel homePageViewModel = new interface_adapter.home_page.HomePageViewModel();
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
        final HomeView homeView = HomeUseCaseFactory.create(
                viewManagerModel,
                homePageViewModel,
                searchRecipeViewModel,
                recipeDetailViewModel,
                cocktailDataAccessObject,  // Only pass this once
                serviceManager
        );
        views.add(homeView, homePageViewModel.getViewName());

        final SearchRecipeView searchRecipeView = SearchRecipeUseCaseFactory.create(viewManagerModel,
                searchRecipeViewModel, recipeDetailViewModel, homePageViewModel,
                cocktailDataAccessObject, cocktailDataAccessObject, serviceManager);
        views.add(searchRecipeView, searchRecipeView.getViewName());

        final RecipeDetailView recipeDetailView = RecipeDetailUseCaseFactory.create(viewManagerModel,
                recipeDetailViewModel, searchRecipeViewModel,
                cocktailDataAccessObject, serviceManager);
        views.add(recipeDetailView, recipeDetailView.getViewName());

        final ExploreIngredientRecipeView exploreIngredientView = ExploreIngredientUseCaseFactory.create(
                viewManagerModel,
                cocktailDataAccessObject,
                serviceManager
        );
        views.add(exploreIngredientView, "explore ingredient");

        // Handles what view model to be shown first
        viewManagerModel.setState(signupView.getViewName());
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setVisible(true);
    }
}
