package app;

import app.usecase_factory.SearchRecipeUseCaseFactory;
import database.CocktailDataAccessObject;
import domain.entities.recipe.factory.CocktailFactory;
import domain.entities.recipe.factory.RecipeFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.search_recipe.SearchRecipeState;
import interface_adapter.search_recipe.SearchRecipeViewModel;
import services.image_service.LocalImageService;
import services.image_service.WebImageService;
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
        final WebImageService webImageService = new WebImageService();
        final LocalImageService localImageService = new LocalImageService();

        // SearchRecipeView initialization
        final SearchRecipeViewModel searchRecipeViewModel = new SearchRecipeViewModel();
        final CocktailDataAccessObject cocktailDataAccessObject = new CocktailDataAccessObject(new CocktailFactory());
        final SearchRecipeView searchRecipeView = SearchRecipeUseCaseFactory.create(viewManagerModel,
                searchRecipeViewModel, cocktailDataAccessObject);
        views.add(searchRecipeView, searchRecipeView.getViewName());

        viewManagerModel.setState(searchRecipeView.getViewName());
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setVisible(true);
    }
}
