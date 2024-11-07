package app;

import app.usecase_factory.SearchRecipeUseCaseFactory;
import data_access.CocktailDataAccessObject;
import entities.recipe.factory.CocktailFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.search_recipe.SearchRecipeViewModel;
import interface_adapter.services.ServiceManager;
import interface_adapter.services.image_service.ImageServiceInterface;
import interface_adapter.services.image_service.LocalImageService;
import interface_adapter.services.image_service.WebImageService;
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

        // SearchRecipeView initialization
        final SearchRecipeViewModel searchRecipeViewModel = new SearchRecipeViewModel();
        final CocktailDataAccessObject cocktailDataAccessObject = new CocktailDataAccessObject();
        final SearchRecipeView searchRecipeView = SearchRecipeUseCaseFactory.create(viewManagerModel,
                searchRecipeViewModel, cocktailDataAccessObject, serviceManager);
        views.add(searchRecipeView, searchRecipeView.getViewName());

        viewManagerModel.setState(searchRecipeView.getViewName());
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setVisible(true);
    }
}
