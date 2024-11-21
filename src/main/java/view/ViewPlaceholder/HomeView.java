package view.ViewPlaceholder;

import interface_adapter.home_page.HomePageController;
import interface_adapter.home_page.HomePageState;
import interface_adapter.home_page.HomePageViewModel;
import interface_adapter.recipe_detail.RecipeDetailController;
import interface_adapter.recipe_detail.RecipeDetailState;
import interface_adapter.search_recipe.SearchRecipeController;
import interface_adapter.search_recipe.SearchRecipeState;
import interface_adapter.services.ServiceManager;
import view.PageView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * View for the home page.
 */
public class HomeView extends JPanel implements PageView, ActionListener, PropertyChangeListener {
    private final String viewName = "home page";

    private final JTextField searchTextField = new JTextField(15);

    private final HomePageViewModel homePageViewModel;
    private final ServiceManager serviceManager;
    private final HomePageController homePageController;
    private final SearchRecipeController searchRecipeController;
    private final RecipeDetailController recipeDetailController;

    public HomeView(HomePageViewModel homePageViewModel,
                    HomePageController homePageController,
                    SearchRecipeController searchRecipeController, RecipeDetailController recipeDetailController,
                    ServiceManager serviceManager) {
        this.homePageViewModel = homePageViewModel;
        this.homePageController = homePageController;
        this.searchRecipeController = searchRecipeController;
        this.recipeDetailController = recipeDetailController;
        this.serviceManager = serviceManager;

        final JPanel searchBar = new JPanel(new BorderLayout());
        searchBar.add(searchTextField);
        searchTextField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                homePageController.switchToSearchRecipeView();
            }
        });
        homePageViewModel.addPropertyChangeListener(this);

        setLayout(new BorderLayout());

        add(searchBar, BorderLayout.NORTH);
        add(new JLabel("WELCOME TO THE HOME PAGE MF!"), BorderLayout.CENTER);
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
        final String query = state.getQuery();
        searchTextField.setText(query);
    }
}
