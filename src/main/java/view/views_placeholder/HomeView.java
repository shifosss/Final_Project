package view.views_placeholder;

import interface_adapter.home_page.HomePageController;
import interface_adapter.home_page.HomePageState;
import interface_adapter.home_page.HomePageViewModel;
import interface_adapter.services.ServiceManager;
import view.PageView;
import view.concrete_page.HomeConcrete;
import view.ui_components.main_page.BookmarkedDecoratorPanel;
import view.ui_components.main_page.ContentPanel;
import view.ui_components.main_page.HeaderPanel;
import view.ui_components.main_page.RecommendedPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * View for the home page.
 */
public class HomeView extends JPanel implements ActionListener, PropertyChangeListener {
    private final String viewName = "home page";
    private final PageView<HomePageState> pageHandler;

    private final JButton searchButton = new JButton("Search");
    private final JButton exploreByIngredientButton = new JButton("Explore By Ingredient");
    private final JButton userButton = new JButton("User");
    private final JButton customRecipeButton = new JButton("Create custom recipe");

    private final HomePageViewModel homePageViewModel;
    private final ServiceManager serviceManager;
    private final HomePageController homePageController;

    public HomeView(HomePageViewModel homePageViewModel,
                    HomePageController homePageController,
                    ServiceManager serviceManager) {
        this.homePageViewModel = homePageViewModel;
        this.homePageController = homePageController;
        this.serviceManager = serviceManager;

        homePageViewModel.addPropertyChangeListener(this);
        setLayout(new BorderLayout());

        final HomeConcrete homeConcrete = new HomeConcrete();
        final ContentPanel contentPanel = new ContentPanel(
                homePageViewModel, homePageController, serviceManager, homeConcrete);
        final RecommendedPanel recommendedPanel = new RecommendedPanel(
                homePageViewModel, homePageController, serviceManager, contentPanel);
        final BookmarkedDecoratorPanel bookmarkedPanel = new BookmarkedDecoratorPanel(
                homePageViewModel, homePageController, serviceManager, recommendedPanel);
        pageHandler = bookmarkedPanel;

        // Handles Headers
        final HeaderPanel headerPanel = new HeaderPanel(
                searchButton, exploreByIngredientButton, customRecipeButton, userButton);

        searchButton.addActionListener(event -> homePageController.switchToSearchView());
        exploreByIngredientButton.addActionListener(event -> homePageController.switchToExploreIngredients());
        userButton.addActionListener(event -> homePageController.switchToUserButton());
        customRecipeButton.addActionListener(event -> homePageController.switchToCustomRecipeView());

        contentPanel.add(recommendedPanel);
        contentPanel.add(bookmarkedPanel);

        add(headerPanel, BorderLayout.NORTH);
        add(contentPanel, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent event) {
        final HomePageState state = (HomePageState) event.getNewValue();
        pageHandler.update(state);
    }

    public String getViewName() {
        return viewName;
    }
}
