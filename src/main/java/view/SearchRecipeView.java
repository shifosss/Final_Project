package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import interface_adapter.ViewManagerModel;
import interface_adapter.search_recipe.SearchRecipeController;
import interface_adapter.search_recipe.SearchRecipeState;
import interface_adapter.search_recipe.SearchRecipeViewModel;
import interface_adapter.services.ServiceManager;
import view.ui_components.search_recipe.RecipeScrollPanel;
import view.ui_components.search_recipe.SearchPanel;
import view.ui_components.search_recipe.IngredientsPanel;

public class SearchRecipeView extends JPanel implements ActionListener, PropertyChangeListener {
    private final String viewName = "search recipe";
    private final SearchRecipeViewModel searchRecipeViewModel;
    private final SearchRecipeController searchRecipeController;
    private final ServiceManager serviceManager;
    private final ViewManagerModel viewManagerModel;

    // UI Components
    private final JTextField searchTextField;
    private final JButton searchButton;
    private final JButton backButton;
    private final JButton exploreIngredientsButton;
    private final RecipeScrollPanel recipeScrollPanel;
    private final IngredientsPanel ingredientsPanel;
    private final CardLayout contentLayout;
    private final JPanel contentPanel;
    private final SearchPanel searchBar;

    public SearchRecipeView(SearchRecipeViewModel searchRecipeViewModel,
                            SearchRecipeController searchRecipeController,
                            ServiceManager serviceManager,
                            ViewManagerModel viewManagerModel) {
        this.searchRecipeViewModel = searchRecipeViewModel;
        this.searchRecipeController = searchRecipeController;
        this.serviceManager = serviceManager;
        this.viewManagerModel = viewManagerModel;
        this.searchRecipeViewModel.addPropertyChangeListener(this);

        // Initialize components
        searchTextField = new JTextField(15);
        searchButton = new JButton("Search");
        backButton = new JButton("<");
        exploreIngredientsButton = new JButton("Explore by Ingredients");

        // Set up content panel with CardLayout
        contentLayout = new CardLayout();
        contentPanel = new JPanel(contentLayout);

        // Create panels - note the added parameters for IngredientsPanel
        recipeScrollPanel = new RecipeScrollPanel(serviceManager);
        ingredientsPanel = new IngredientsPanel(serviceManager, recipeScrollPanel, contentLayout, contentPanel);

        // Create search bar
        searchBar = new SearchPanel(
                backButton,
                searchTextField,
                searchButton,
                exploreIngredientsButton,
                recipeScrollPanel
        );

        // Set up content panel with CardLayout
        contentPanel.add(recipeScrollPanel, "recipes");
        contentPanel.add(ingredientsPanel, "ingredients");

        // Set up main layout
        setLayout(new BorderLayout());
        add(searchBar, BorderLayout.NORTH);
        add(contentPanel, BorderLayout.CENTER);

        // Add action listeners
        setupActionListeners();
        setupSearchTextField();
    }

    private void setupActionListeners() {
        // Search button action
        searchButton.addActionListener(e -> {
            if (e.getSource().equals(searchButton)) {
                performSearchAction();
            }
        });

        // Back button action
        backButton.addActionListener(e -> {
            // Clear current state
            searchTextField.setText("");
            recipeScrollPanel.clearRecipes();

            // Reset view state
            contentLayout.show(contentPanel, "recipes");
            exploreIngredientsButton.setText("Explore by Ingredients");
            searchBar.setSearchEnabled(true);

            // Navigate back to main menu using setState
            viewManagerModel.setState("main menu");
            viewManagerModel.firePropertyChanged();
        });

        // Explore button action
        exploreIngredientsButton.addActionListener(e -> {
            if (contentPanel.isAncestorOf(recipeScrollPanel)) {
                // Switch to ingredients view
                contentLayout.show(contentPanel, "ingredients");
                exploreIngredientsButton.setText("Back to Search");
                searchBar.setSearchEnabled(false);
                recipeScrollPanel.setExploreMode(true);
            } else {
                // Switch back to search view
                switchToSearchView();
            }
        });
    }

    private void switchToSearchView() {
        contentLayout.show(contentPanel, "recipes");
        exploreIngredientsButton.setText("Explore by Ingredients");
        searchBar.setSearchEnabled(true);
        recipeScrollPanel.setExploreMode(false);
        recipeScrollPanel.clearRecipes();
        searchTextField.setText("");
    }

    private void setupSearchTextField() {
        // Enter key in search field
        searchTextField.addActionListener(e -> {
            if (e.getSource().equals(searchTextField)) {
                performSearchAction();
            }
        });

        // Text change listener
        searchTextField.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            private void updateState() {
                SearchRecipeState currentState = searchRecipeViewModel.getState();
                currentState.setQuery(searchTextField.getText());
                searchRecipeViewModel.setState(currentState);
            }

            @Override
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                updateState();
            }

            @Override
            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                updateState();
            }

            @Override
            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                updateState();
            }
        });
    }

    private void performSearchAction() {
        SearchRecipeState currentState = searchRecipeViewModel.getState();
        searchRecipeController.execute(currentState.getQuery());

        // Show recipes panel when searching
        contentLayout.show(contentPanel, "recipes");
        exploreIngredientsButton.setText("Explore by Ingredients");
        searchBar.setSearchEnabled(true);

        recipeScrollPanel.displayRecipes(currentState.getRecipes());
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        System.out.println("Click " + event.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        SearchRecipeState state = (SearchRecipeState) evt.getNewValue();
        setFields(state);
    }

    private void setFields(SearchRecipeState state) {
        searchTextField.setText(state.getQuery());
    }

    public String getViewName() {
        return viewName;
    }
}