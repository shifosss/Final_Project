package view.views_placeholder;

import interface_adapter.explore_ingredient.ExploreIngredientController;
import interface_adapter.home_page.HomePageController;
import interface_adapter.home_page.HomePageState;
import interface_adapter.home_page.HomePageViewModel;
import interface_adapter.recipe_detail.RecipeDetailController;
import interface_adapter.search_recipe.SearchRecipeController;
import interface_adapter.services.ServiceManager;
import okhttp3.internal.framed.Header;
import view.PageView;
import view.ui_components.main_page.ContentPanel;
import view.ui_components.main_page.HeaderPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * View for the home page.
 */
public class HomeView extends JPanel implements PageView, ActionListener, PropertyChangeListener {
    private JPanel recommendationsPanel;
    private JPanel recommendedRecipesPanel;
    private String currentUser = "";

    private final String viewName = "home page";

    private final JButton searchButton = new JButton("Search");
    private final JButton exploreByIngredientButton = new JButton("Explore By Ingredient");
    private final JButton userButton = new JButton("User");
    private final ContentPanel contentPanel;

    private final HomePageViewModel homePageViewModel;
    private final ServiceManager serviceManager;
    private final HomePageController homePageController;

    public HomeView(HomePageViewModel homePageViewModel,
                    HomePageController homePageController,
                    ServiceManager serviceManager) {
        this.homePageViewModel = homePageViewModel;
        this.homePageController = homePageController;
        this.serviceManager = serviceManager;

        contentPanel = new ContentPanel(homePageViewModel,
                homePageController,
                serviceManager);

        // Handles Headers
        final HeaderPanel headerPanel = new HeaderPanel(searchButton, exploreByIngredientButton, userButton);

        searchButton.addActionListener(event -> homePageController.switchToSearchView());
        exploreByIngredientButton.addActionListener(event -> homePageController.switchToExploreIngredients());
        userButton.addActionListener(event -> {
            homePageController.switchToUserButton(currentUser);
        });

        homePageViewModel.addPropertyChangeListener(this);
        setLayout(new BorderLayout());

        add(headerPanel, BorderLayout.NORTH);
        add(contentPanel.getScrollPane(), BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent event) {
        final HomePageState state = (HomePageState) event.getNewValue();
        setFields(state);
    }

    @Override
    public String getViewName() {
        return viewName;
    }

    private void setFields(HomePageState state) {
        currentUser = state.getUsername();
        contentPanel.updatePanel(state);
    }
}
