package app;

import app.usecase_factory.SearchRecipeUseCaseFactory;
import app.usecase_factory.ExploreIngredientUseCaseFactory;
import data_access.CocktailDataAccessObject;
import interface_adapter.ViewManagerModel;
import interface_adapter.search_recipe.SearchRecipeViewModel;
import interface_adapter.explore_ingredient.ExploreIngredientViewModel;
import interface_adapter.services.ServiceManager;
import interface_adapter.services.image_service.ImageServiceInterface;
import interface_adapter.services.image_service.LocalImageService;
import interface_adapter.services.image_service.WebImageService;
import view.SearchRecipeView;
import view.ExploreIngredientRecipeView;
import view.ViewManager;

import javax.swing.*;
import java.awt.*;

public class MainApp {
    public static void main(String[] args) {
        final JFrame application = new JFrame("Recipe Lookup");
        application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final CardLayout cardLayout = new CardLayout();
        final JPanel views = new JPanel(cardLayout);
        application.add(views);

        final ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);

        // Initialize Services
        final ImageServiceInterface webImageService = new WebImageService();
        final ImageServiceInterface localImageService = new LocalImageService();
        final ServiceManager serviceManager = new ServiceManager(webImageService, localImageService);

        // Initialize ViewModels
        final SearchRecipeViewModel searchRecipeViewModel = new SearchRecipeViewModel();
        final ExploreIngredientViewModel exploreIngredientViewModel = new ExploreIngredientViewModel();

        // Initialize Data Access Object
        final CocktailDataAccessObject cocktailDataAccessObject = new CocktailDataAccessObject();

        // Create Views
        final SearchRecipeView searchRecipeView = SearchRecipeUseCaseFactory.create(
                viewManagerModel,
                searchRecipeViewModel,
                cocktailDataAccessObject,
                serviceManager
        );

        final ExploreIngredientRecipeView exploreIngredientView = ExploreIngredientUseCaseFactory.create(
                viewManagerModel,
                exploreIngredientViewModel,
                cocktailDataAccessObject,  // Now correctly implements ExploreIngredientDataAccessInterface
                serviceManager
        );

        // Add views to card layout
        views.add(searchRecipeView, searchRecipeView.getViewName());
        views.add(exploreIngredientView, exploreIngredientView.getViewName());

        // Set initial view
        viewManagerModel.setState(searchRecipeView.getViewName());
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setVisible(true);
    }
}