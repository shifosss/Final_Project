package app;

import app.usecase_factory.RecipeDetailUseCaseFactory;
import app.usecase_factory.SearchRecipeUseCaseFactory;
import data_access.CocktailDataAccessObject;
import entities.recipe.factory.CocktailFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.recipe_detail.RecipeDetailViewModel;
import interface_adapter.search_recipe.SearchRecipeViewModel;
import interface_adapter.services.ServiceManager;
import interface_adapter.services.image_service.ImageServiceInterface;
import interface_adapter.services.image_service.LocalImageService;
import interface_adapter.services.image_service.WebImageService;
import view.RecipeDetailView;
import view.SearchRecipeView;
import view.ViewManager;

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
        // Manages the interface_adapter.services.
        final ServiceManager serviceManager = new ServiceManager(webImageService, localImageService);

        // api/database initialization
        final CocktailDataAccessObject cocktailDataAccessObject = new CocktailDataAccessObject(new CocktailFactory());

        // View Model initialization
        final SearchRecipeViewModel searchRecipeViewModel = new SearchRecipeViewModel();
        final RecipeDetailViewModel recipeDetailViewModel = new RecipeDetailViewModel();

        // HomePageView initialization
        /*
        final HomeViewModel homeViewModel = new HomeView();
        // TODO: Check what API/DAO we can inject into the home view
        final HomeView homeView = HomeUseCaseFactory.create(viewManagerModel,
                homeViewModel, serviceManager);
        views.add(homeView, homeView.getViewName());
        */
        // SearchRecipeView initialization
        final SearchRecipeView searchRecipeView = SearchRecipeUseCaseFactory.create(viewManagerModel,
                searchRecipeViewModel, cocktailDataAccessObject, serviceManager);
        views.add(searchRecipeView, searchRecipeView.getViewName());

        // RecipeDetailView initialization
        final RecipeDetailView recipeDetailView = RecipeDetailUseCaseFactory.create(viewManagerModel,
                recipeDetailViewModel, cocktailDataAccessObject, serviceManager);
        views.add(recipeDetailView, recipeDetailView.getViewName());

        // Handles what view model to be shown first
        viewManagerModel.setState(searchRecipeView.getViewName());
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setVisible(true);
    }
}
